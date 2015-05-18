package org.pmoo.blackjack;

public class Karta {
	
	//Atributuak
	private int zenbakia;
	private int palua;
	
	//Eraikitzailea	
	public Karta(int pZenbakia, int pPalua){		
		this.palua = pPalua; 
		this.zenbakia = pZenbakia;	
	}
	
	//Beste metodoak
	public String idatziPalua(){
		String paluIzena = null;
		switch(this.palua){
			case 1:
				paluIzena = "Pika";
				break;
			case 2:
				paluIzena = "Hirusta";
				break;
			case 3:
				paluIzena = "Erronbo";
				break;
			case 4:
				paluIzena = "Bihotz";
				break;
		}
		return paluIzena;	
	}
	
	public int getKartaBalioa(){
		return this.zenbakia;
	}
	
	private int getKartaPalua(){
		return this.palua;
	}
	
	public boolean batekoa(){
		if (this.zenbakia == 1){
			return true;
		}else{return false;}
	}
	
	public String kartaIdatzi(){
		String karta = null;
		switch(this.zenbakia){
		case 1: 
			karta = "Bateko " + this.idatziPalua();
			break;
		case 11:
			karta = "Txankako " + this.idatziPalua();
			break;
		case 12:
			karta = "Erreginako " + this.idatziPalua();
			break;
		case 13:
			karta = "Erregeko " + this.idatziPalua();
			break;
		default:
			karta = this.zenbakia + "-ko " + this.idatziPalua();
			break;
		}
		return karta;
	}
	
	public boolean figuraDa(){
		boolean bada = false;
		if (this.zenbakia>=11){
			bada = true;
		}
		return bada;
	}
}
