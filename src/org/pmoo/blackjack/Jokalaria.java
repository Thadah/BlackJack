package org.pmoo.blackjack;

public class Jokalaria {
	
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
		System.out.println("Zenbat diru apostatu nahi duzu, " + this.izena + "?");
		//int apostua = Teklatutik irakurri
		while(apostua>this.dirua){
			System.out.println("Apostua handiegia da, apostatu berriro");
			//apostua = Teklatutik irakurri
		}
		this.dirua = this.dirua - apostua;
		this.apostua = apostua;
		return apostua;
		
	}
	
	public int eskuaKalkulatu(){//Metodo honek bakarik deitu beharko lioke listaKartak klaseko kartenBalioaLortu() metodoari
		return this.eskua.kartenBalioaLortu();
	}
	
	public void kartaEskatu(){
		Baraja nireBaraja = Baraja.getBaraja();
		Karta kartaBat = nireBaraja.emanKarta();
		this.eskua.gehituKarta(kartaBat);
	}
	
	public void txanda(){
		//TODO
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
	
	

}