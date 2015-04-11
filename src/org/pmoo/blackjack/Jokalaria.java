package org.pmoo.blackjack;
import java.util.Scanner;
public class Jokalaria {
	Scanner sc=new Scanner(System.in);
	private ListaKartak eskua;
	private String izena;
	private int dirua;
	private int apostua;
	
	public Jokalaria(String pIzena){
		this.eskua = new ListaKartak();
		this.izena = pIzena;
		this.dirua = 500;
	}
	public int apostuaEgin(){
		int apostua1=0;
		System.out.println("Zenbat diru apostatu nahi duzu, " + this.izena + "?");
		apostua1= sc.nextInt();
		while(apostua1>this.dirua){
			System.out.println("Apostua handiegia da, apostatu berriro");
			apostua1 = sc.nextInt();
		}
		this.dirua = this.dirua - apostua1;
		this.apostua = apostua1;
		return apostua1;
		
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
				System.out.println(this.izena + ", bateko " + kartaBat.idatziPalua() + " bat hartu duzu");
				int totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 11:
				System.out.println(this.izena + ", txankako " + kartaBat.idatziPalua() + " bat hartu duzu");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 12:
				System.out.println(this.izena + ", erreginako " + kartaBat.idatziPalua() + " bat hartu duzu.");
				totala = this.eskuaKalkulatu();
				//Thread.sleep(1000);
				System.out.println(this.izena + ", zure karten totala " + totala + " da.\n");
				break;
			case 13:
				System.out.println(this.izena + ", erregeko " + kartaBat.idatziPalua() + " bat hartu duzu.");
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
		while(!pasatuda&&!plantatuta){
			System.out.println(this.izena + " karta bat nahi duzu?? (B/E)");
			eman = sc.next();
			if (eman.equals("B")){
				this.kartaEskatu();
				if(this.eskuaKalkulatu()>21){
					System.out.println("pasatu zara :,(");
					pasatuda=true;
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