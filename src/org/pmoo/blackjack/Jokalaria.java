package org.pmoo.blackjack;

import java.util.Scanner;

public class Jokalaria {
	
	//Atributuak
	Scanner sc = new Scanner(System.in);
	protected ListaKartak eskua;
	protected String izena;
	protected int dirua;
	protected int apostua;
	protected boolean erretiratua;
	
	//Eraikitzailea
	public Jokalaria(String pIzena){
		this.eskua = new ListaKartak();
		this.izena = pIzena;
		this.dirua = 500;
		this.erretiratua = false;
	}
	
	//Beste Metodoak
	public void apostuaEgin() throws ApostuException{
		BlackJack mahaia = BlackJack.getNireBlackJack();
		boolean denaOndo = false;
		this.apostua = 0;
		if(this.dirua >= mahaia.getApostuMax()){
			System.out.println("Zenbat diru apostatu nahi duzu, " + this.izena + "?");
			try{
				this.apostua = Integer.parseInt(sc.next());
				denaOndo = true;
			}
			catch(NumberFormatException e){
				System.out.println("Ez duzu zenbaki bat sartu, saiatu berriro:");
				this.apostuaEgin();			
			}
			if(denaOndo){
				if(this.apostua > this.dirua){
					throw (new ApostuException("Apostatu duzun dirua daukazuna baino handiagoa da"));
				}
				else if(this.apostua < 0){
					throw(new ApostuException("Ezin dituzu zenbaki negatiboak sartu"));
				}
				else if(this.apostua < BlackJack.getNireBlackJack().getApostuMax() && this.apostua != 0){
					throw(new ApostuException("Apostua txikiegia da, ikusi edo handitu zure apostua"));
				}
				else if(this.apostua == 0){
					this.erretiratua = true;
					System.out.println(this.izena + " jokalaria erretiratu da.");
				}
				else if(this.apostua == this.dirua){
					String konfirm = null;
					System.out.println("Ziur zaude All-in egin nahi duzula? (B/E)");
					konfirm = sc.next();
					if(konfirm.equals("B") || konfirm.equals("b")){
						System.out.println("All-in egin duzu.");
					}
					else{
						throw(new ApostuException(""));
					}
					
				}
				if(this.apostua > mahaia.getApostuMax()){
					mahaia.setApostuMax(this.apostua);
				}
				mahaia.setBotea(mahaia.getBotea() + this.apostua);
				this.dirua = this.dirua - this.apostua;
			}
		}
		else{
			System.out.println(this.izena + "Ez duzu dirurik apostua ikusteko, beraz, erretiratua izan zara");
			this.erretiratua = true;
		}
	}
	
	public void bueltatu(){
		if(this.erretiratua){
			this.erretiratua = false;
		}
	}
	
	public void apostuaBueltatu(){
		this.dirua = this.dirua + this.apostua;
		this.apostua = 0;
	}
	
	public void apostuaIkusi(){
		int totala = this.apostua + this.dirua;
		BlackJack mahaia = BlackJack.getNireBlackJack();
		if(!this.erretiratua && (this.apostua < mahaia.getApostuMax()) && (totala >= mahaia.getApostuMax())){
			System.out.println(this.izena + ", apostua ikusi nahi duzu?   (B/E)");
			String bai = sc.next();
			if(bai.equals("B") || bai.equals("b")){
				mahaia.setBotea(mahaia.getBotea() + (mahaia.getApostuMax() - this.apostua));
				this.dirua = this.dirua - (mahaia.getApostuMax() - this.apostua);				
				this.apostua = mahaia.getApostuMax();
			}
			else if(!bai.equals("B") && !bai.equals("b")){
				System.out.println(this.izena + ", ez duzu apostua ikusi, beraz, erretiratua izan zara.");
				this.erretiratua = true;
			}
		}
		else if(this.apostua < mahaia.getApostuMax()){
			System.out.println(this.izena + " , ez dituzu apostua ikusteko baldintzak betetzen, erretiratua izan zara. T_T");
			this.erretiratua = true;
		}	
	}
	
	public int eskuaKalkulatu(){
		int eskua = 0;
		if(!this.erretiratua){
			eskua = this.eskua.kartenBalioaLortu();
		}
		return eskua;
	}
	
	public void kartaEskatu() throws InterruptedException{
		Baraja nireBaraja = Baraja.getBaraja();
		if(!this.erretiratua){
			Karta kartaBat = nireBaraja.emanKarta();
			this.eskua.gehituKarta(kartaBat);
			System.out.println(this.izena + ", " + kartaBat.kartaIdatzi() + " bat hartu duzu");
		}
	}
	
	public void boteaHartu(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		this.dirua = this.dirua + mahaia.getBotea();
	}
	
	public void txanda() throws InterruptedException{
		BlackJack mahaia = BlackJack.getNireBlackJack();
		boolean plantatuta = false;
		String eman = null;
		if (!erretiratua){
			System.out.println(this.izena + ", Doblatu nahi al duzu? (B/E)");
			eman = sc.next();
			if(eman.equals("B") || eman.equals("b")){
				this.kartaEskatu();
				this.dirua = this.dirua - this.apostua;
				mahaia.setBotea(mahaia.getBotea() + this.apostua);
				this.apostua = this.apostua*2;
				plantatuta = true;
				System.out.println(this.izena + ", plantatu zara eta zure apostua doblatu duzu. \n");
			}
			while(this.eskuaKalkulatu() <= 21 && !plantatuta){
				System.out.println(this.izena + ", karta bat nahi duzu?? (B/E)");
				eman = sc.next();
				if (eman.equals("B") || eman.equals("b")){
					this.kartaEskatu();
					System.out.println(this.izena + ", zure karten totala " + this.eskuaKalkulatu() + " da." );
				}
				else if(!eman.equals("B") && !eman.equals("b")){
					System.out.println(this.izena + ", plantatu zara.");
					plantatuta=true;
				}
			}
			if (this.eskuaKalkulatu() > 21){
				System.out.println(this.izena + ", pasatu egin zara :( \n");
			}
			if(this.eskuaKalkulatu() == 21){
				Thread.sleep(1000);
				System.out.println(this.izena + ", 21-era iritsi zara :P \n");
			}
		}
	}

	public String getIzena() {
		return this.izena;
	}
	
	public int getDirua() {
		return this.dirua;
	}
	
	public void kartakItzuli(){
		this.eskua.erreseteatu();
	}
	
	public void eskuaIdatzi() throws InterruptedException{
		if (!this.erretiratua){
			System.out.println("Zure karten totala " + this.eskuaKalkulatu() + " da.\n");
			System.out.println("Sakatu enter txanda bukatzeko.");
			BlackJack.getNireBlackJack().enterItxaron();
		}
	}
	
}
