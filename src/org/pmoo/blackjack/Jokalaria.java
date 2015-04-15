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
	
	public int apostuaEgin() throws ApostuException{
		this.apostua = 0;
		System.out.println("Zenbat diru apostatu nahi duzu, " + this.izena + "?");
		this.apostua = sc.nextInt();
		
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
			if(konfirm == "B"){
				System.out.println("All-in egin duzu.");
			}
			else{
				throw(new ApostuException(""));
			}
			
		}
		return this.apostua;
		
	}
	
	public int eskuaKalkulatu(){//Metodo honek bakarik deitu beharko lioke listaKartak klaseko kartenBalioaLortu() metodoari
		return this.eskua.kartenBalioaLortu();
	}
	
	public void kartaEskatu() throws InterruptedException{
		Baraja nireBaraja = Baraja.getBaraja();
		Karta kartaBat = nireBaraja.emanKarta();
		this.eskua.gehituKarta(kartaBat);
		switch(kartaBat.getKartaBalioa()){
			case 1: 
				System.out.println(this.izena + ", Bateko " + kartaBat.idatziPalua() + " bat hartu duzu");
				int totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 11:
				System.out.println(this.izena + ", Txankako " + kartaBat.idatziPalua() + " bat hartu duzu");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 12:
				System.out.println(this.izena + ", Erreginako " + kartaBat.idatziPalua() + " bat hartu duzu.");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 13:
				System.out.println(this.izena + ", Erregeko " + kartaBat.idatziPalua() + " bat hartu duzu.");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			default:
				System.out.println(this.izena + ",  " + kartaBat.getKartaBalioa() + "-ko " + kartaBat.idatziPalua() + " bat hartu duzu");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
		}
	}
	
	public void txanda() throws InterruptedException{
		boolean pasatuda=false;
		boolean plantatuta=false;
		String eman = null;
		if (!erretiratua){
			while(!pasatuda&&!plantatuta){
				System.out.println(this.izena + " karta bat nahi duzu?? (B/E)");
				eman = sc.next();
				if (eman.equals("B")){
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
				else if(!eman.equals("B")){
					System.out.println(this.izena + ", plantatu zara.");
					plantatuta=true;
				}
			}
		}
	}
	
	public void setErretiratua(boolean pBoolean){
		this.erretiratua = pBoolean;
	}
	
	public boolean getErretiratua(){
		return this.erretiratua;
	}
	
	public int getApostua(){
		return this.apostua;
	}
	public String getIzena() {
		return this.izena;
	}
	public int getDirua() {
		return this.dirua;
	}
	public void setDirua(int pDirua) {
		this.dirua = pDirua;
	}
	public void setApostua(int pApostua){
		this.apostua = pApostua;
	}
	
	public void kartakItzuli(){
		this.eskua.erreseteatu();
	}
	

}