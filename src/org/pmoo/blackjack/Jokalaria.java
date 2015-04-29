package org.pmoo.blackjack;

import java.util.*;

import org.pmoo.audio.*;

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
	
	//Beste metodoak
	public void apostuaEgin() throws ApostuException{
		BlackJack mahaia = BlackJack.getNireBlackJack();
		boolean denaOndo = false;
		this.apostua = 0;
		if(this.dirua >= mahaia.getApostuMax()){
			System.out.println("Zenbat diru apostatu nahi duzu, " + this.izena + "?");
			try{
				this.apostua = Integer.parseInt(sc.next());
				Audio chips = new Audio("Chips" + (int)(Math.random() * ((3 - 1) + 1) + 1) + ".mp3");
				chips.PlayAudio();
				chips.StopAudio(chips);				
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
					Logroak.getNireLogroak().erretiratuLogroa();
					Logroak.getNireLogroak().logroenErregeaLogroa();
					System.out.println(this.izena + " jokalaria erretiratu da.");
				}
				else if(this.apostua == this.dirua){
					String konfirm = null;
					System.out.println("Ziur zaude All-in egin nahi duzula? (B/E)");
					konfirm = sc.next();
					if(konfirm.equals("B") || konfirm.equals("b")){
						Audio chips = new Audio("Chips" + (int)(Math.random() * ((3 - 1) + 1) + 1) + ".mp3");
						chips.PlayAudio();
						chips.StopAudio(chips);	
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
		Logroak logroak = Logroak.getNireLogroak();
		int totala = this.apostua + this.dirua;
		BlackJack mahaia = BlackJack.getNireBlackJack();
		if(!this.erretiratua){
			if (this.apostua < mahaia.getApostuMax() && totala >= mahaia.getApostuMax()){
		
				System.out.println(this.izena + ", apostua ikusi nahi duzu?   (B/E)");
				String bai = sc.next();
				if(bai.equals("B") || bai.equals("b")){
					Audio chips = new Audio("Chips" + (int)(Math.random() * ((3 - 1) + 1) + 1) + ".mp3");
					chips.PlayAudio();
					chips.StopAudio(chips);	
					mahaia.setBotea(mahaia.getBotea() + (mahaia.getApostuMax() - this.apostua));
					this.dirua = this.dirua - (mahaia.getApostuMax() - this.apostua);				
					this.apostua = mahaia.getApostuMax();
				}
				else if(!bai.equals("B") && !bai.equals("b")){
					System.out.println(this.izena + ", ez duzu apostua ikusi, beraz, erretiratua izan zara.");
					this.erretiratua = true;
					logroak.erretiratuLogroa();
				}
			}
			else if(this.apostua < mahaia.getApostuMax()){
				System.out.println(this.izena + " , ez dituzu apostua ikusteko baldintzak betetzen, erretiratua izan zara. T_T");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa("T_T");
				this.erretiratua = true;
			}	
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
			Audio slide = new Audio("Slide" + (int)(Math.random() * ((3 - 1) + 1) + 1) + ".mp3");
			slide.PlayAudio();
			Thread.sleep(300);
			slide.StopAudio(slide);
			Karta kartaBat = nireBaraja.emanKarta();
			this.eskua.gehituKarta(kartaBat);
			System.out.println(kartaBat.kartaIdatzi() + " bat hartu duzu.");
		}
	}
	
	public void boteaHartu(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		this.dirua = this.dirua + mahaia.getBotea();
	}
	
	public void boteaHartuCroupier(int pBotea){
		this.dirua = this.dirua + pBotea;
	}
	
	public void txanda() throws InterruptedException{
		Logroak logroak = Logroak.getNireLogroak();
		BlackJack mahaia = BlackJack.getNireBlackJack();
		boolean plantatuta = false;
		String eman = null;
		
		if (!erretiratua && this.eskuaKalkulatu() < 21){

			System.out.println(this.izena + "-ren txanda da.\n");
			if (this.dirua >= this.apostua){
				System.out.println("Doblatu nahi al duzu? (B/E)");
				eman = sc.next();
				if(eman.equals("B") || eman.equals("b")){
					this.kartaEskatu();
					Audio chips = new Audio("Chips" + (int)(Math.random() * ((3 - 1) + 1) + 1) + ".mp3");
					chips.PlayAudio();
					chips.StopAudio(chips);	
					this.dirua = this.dirua - this.apostua;
					mahaia.setBotea(mahaia.getBotea() + this.apostua);
					this.apostua = this.apostua*2;
					plantatuta = true;
					System.out.println("Plantatu zara eta zure apostua doblatu duzu. \n");
					logroak.bikoitzaLogroa();
					logroak.logroenErregeaLogroa();
				}
			}
			while(!plantatuta && this.eskuaKalkulatu() < 21){
				System.out.println("Karta bat nahi duzu?? (B/E)");
				eman = sc.next();
				if (eman.equals("B") || eman.equals("b")){
					this.kartaEskatu();
					System.out.println("Zure karten totala " + this.eskuaKalkulatu() + " da.");
				}
				else if(!eman.equals("B") && !eman.equals("b")){
					System.out.println("Plantatu zara.");
					plantatuta=true;
				}
			}
			if (this.eskuaKalkulatu() > 21){
				System.out.println("Pasatu egin zara :( \n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":(");
			}
			if(this.eskuaKalkulatu() == 21){
				Thread.sleep(1000);
				System.out.println("21-era iritsi zara :P \n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":P");
			}
		}
	}

	public String getIzena() {
		return this.izena;
	}
	
	public int getDirua() {
		return this.dirua;
	}
	
	public void eskuBerria(){
		this.eskua = new ListaKartak();
	}
	
	public void eskuaIdatzi() throws InterruptedException{
		if (!this.erretiratua){
			if (this.eskuaKalkulatu() <= 21){
				System.out.println("Zure karten totala " + this.eskuaKalkulatu() + " da.\n");
			}
			System.out.println("Sakatu enter txanda bukatzeko.");
			BlackJack.getNireBlackJack().enterItxaron();
		}
	}
	public boolean blackJackDu(){
		Logroak logroak = Logroak.getNireLogroak();
		boolean badu = false;
		boolean figura = false;
		boolean batekoa = false;
		int kartaKop = this.eskua.tamaina();
		Karta kartaBat = null;
		Iterator<Karta> itr = this.eskua.getIteradorea();
		while(itr.hasNext()){
			kartaBat = itr.next();
			if(kartaBat.batekoa()){
				batekoa = true;
			}
			else if(kartaBat.figuraDa()){
				figura = true;
			}
		}
		if(figura && batekoa && kartaKop == 2){
			badu = true;
			logroak.blackJackLogroa();
			logroak.logroenErregeaLogroa();
		}
		return badu;
	}
	
	public boolean galdetuJoan(){
		boolean joanDa = false;
		ListaJokalariak lista = ListaJokalariak.getNireListaJokalariak();
		if(this.getDirua() == 0){
			System.out.println("Sentitzen dugu " + this.getIzena() + ", baina Kasinotik joan behar zara ez duzulako dirurik. >:(");
			ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(">:(");
			Logroak.getNireLogroak().bankarrotaLogroa();
			lista.erretiratu(this);
			joanDa = true;
		}
		else{		
			System.out.println(this.getIzena() + ", partida utzi nahi duzu? (B/E)");
			String bai = sc.next();
			if(bai.equals("B") || bai.equals("b")){
				System.out.println(this.getIzena() + " mahaia utzi du ;_;");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(";_;");
				lista.erretiratu(this);
				joanDa = true;
			}
		}
		return joanDa;
	}
	
	public void diruaInprimatu(){
		System.out.println(this.izena + "-(r)en dirua: " + this.dirua + "�");
	}
	
}
