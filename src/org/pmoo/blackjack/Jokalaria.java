package org.pmoo.blackjack;
import java.util.Scanner;
public class Jokalaria {
	Scanner sc=new Scanner(System.in);
	private ListaKartak eskua;
	private String izena;
	private int dirua;
	private int apostua;
	private boolean erretiratua;
	
	public Jokalaria(String pIzena){
		this.eskua = new ListaKartak();
		this.izena = pIzena;
		this.dirua = 500;
		this.erretiratua=false;
	}
	
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
					String konfirm=null;
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
				this.erretiratua = true;
			}
		}
		else {
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
			switch(kartaBat.getKartaBalioa()){
				case 1: 
					System.out.println(this.izena + ", Bateko " + kartaBat.idatziPalua() + " bat hartu duzu");
					int totala = this.eskuaKalkulatu();
					Thread.sleep(1000);
					System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
					break;
				case 11:
					System.out.println(this.izena + ", Txankako " + kartaBat.idatziPalua() + " bat hartu duzu");
					totala = this.eskuaKalkulatu();
					Thread.sleep(1000);
					System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
					break;
				case 12:
					System.out.println(this.izena + ", Erreginako " + kartaBat.idatziPalua() + " bat hartu duzu.");
					totala = this.eskuaKalkulatu();
					Thread.sleep(1000);
					System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
					break;
				case 13:
					System.out.println(this.izena + ", Erregeko " + kartaBat.idatziPalua() + " bat hartu duzu.");
					totala = this.eskuaKalkulatu();
					Thread.sleep(1000);
					System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
					break;
				default:
					System.out.println(this.izena + ",  " + kartaBat.getKartaBalioa() + "-ko " + kartaBat.idatziPalua() + " bat hartu duzu");
					totala = this.eskuaKalkulatu();
					Thread.sleep(1000);
					System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
					break;
			}
		}
	}
	
	public void boteaHartu(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		this.dirua = this.dirua + mahaia.getBotea();
	}
	
	public void txanda() throws InterruptedException{
		boolean pasatuda=false;
		boolean plantatuta=false;
		String eman = null;
		if (!erretiratua){
			while(!pasatuda&&!plantatuta){
				System.out.println(this.izena + " karta bat nahi duzu?? (B/E)");
				eman = sc.next();
				if (eman.equals("B") || eman.equals("b")){
					this.kartaEskatu();
					if(this.eskuaKalkulatu()>21){
						System.out.println("pasatu zara :,(");
						pasatuda=true;
						this.erretiratua=true;
					}
					else if(this.eskuaKalkulatu()==21){
						System.out.println(this.izena + ", 21era iritsi zara :P");
						plantatuta=true;
					}
				}
				else if(!eman.equals("B") && !eman.equals("b")){
					System.out.println(this.izena + ", plantatu zara.");
					plantatuta=true;
				}
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
	
}