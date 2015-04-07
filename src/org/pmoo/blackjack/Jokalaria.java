package org.pmoo.blackjack;

public class Jokalaria {
	
	private ListaKartak eskua;
	private String izena;
	private int dirua;
	private int apostua;
	
	public Jokalaria(String pIzena, int pDirua, int pApostua){
		this.eskua = new ListaKartak();
		this.izena = pIzena;
		this.dirua = pDirua;
		this.apostua = pApostua;
	}
	
	private void apostuaEgin(){
		//TODO
	}
	
	public int eskuaKalkulatu(){//Metodo honek bakarik deitu beharko lioke listaKartak klaseko kartenBalioaLortu() metodoari
		return this.eskua.kartenBalioaLortu();
	}
	
	public void kartaEskatu(){
		//TODO
	}
	
	public void txanda(){
		//TODO
	}
	
	public int getApostua(){
		return this.apostua;
	}
	

}