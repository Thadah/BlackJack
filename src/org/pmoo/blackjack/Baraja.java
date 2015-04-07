package org.pmoo.blackjack;



public class Baraja {
	//Atributuak
	private static Baraja helbidea;
	private ListaKartak listaKartak;
	
	//Eraikitzailea
	private Baraja(){
		int i = 1;
		int j = 1;
		int k = 1;
		while (i < 53){
			this.listaKartak.add(new Karta(j,k));
			j++;
			if (j > 12){
				j = 1;
				k++;
			}
			i++;
		}
	}
	
	public static synchronized Baraja getBaraja(){
		if (Baraja.helbidea == null){
			Baraja.helbidea = new Baraja();
		}
		return Baraja.helbidea;
	}
	
	//Beste Metodoak
}
