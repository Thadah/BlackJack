package org.pmoo.blackjack;


import java.util.*;


public class Baraja {
	//Atributuak
	private static Baraja helbidea;
	private ArrayList<Karta> listaKartak;
	
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
		this.barajatu();
	}
	
	public static synchronized Baraja getBaraja(){
		if (Baraja.helbidea == null){
			Baraja.helbidea = new Baraja();
		}
		return Baraja.helbidea;
	}
	
	//Beste Metodoak
	public void erreseteatu(){
		this.listaKartak.clear();
		Baraja.helbidea = null;
		Baraja.getBaraja();
	}
	
	private void barajatu(){
		Collections.shuffle(listaKartak);
	}
}
