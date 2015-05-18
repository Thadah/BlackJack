package org.pmoo.blackjack;

import java.util.*;

public class Baraja {
	
	//Atributuak
	private static Baraja helbidea;
	private ArrayList<Karta> listaKartak;
	
	//Eraikitzailea
	private Baraja(){
		this.listaKartak = new ArrayList<Karta>();
		int zenbakia = 1;
		int palua = 1;
		while (palua < 5){
			this.listaKartak.add(new Karta(zenbakia, palua));
			zenbakia++;
			if (zenbakia > 13){
				zenbakia = 1;
				palua++;
			}
		}
		this.barajatu();
	}
	
	public static synchronized Baraja getBaraja(){
		if (Baraja.helbidea == null){
			Baraja.helbidea = new Baraja();
		}
		return Baraja.helbidea;
	}
	
	//Beste metodoak
	private Iterator<Karta> getIteradorea(){
		return this.listaKartak.iterator();
	}
	public void erreseteatu(){
		this.listaKartak.clear();
		Baraja.helbidea = null;
		Baraja.getBaraja();
	}
	
	private void barajatu(){
		Collections.shuffle(listaKartak);
	}
	
	public Karta emanKarta(){
		Karta kartaBat = this.listaKartak.get(0);
		this.listaKartak.remove(kartaBat);
		return kartaBat;
	}
	
	//JUnitentzako	
	private int tamaina(){ 
		return this.listaKartak.size();
	}
	
	private void barajaInprimatu(){
		Iterator<Karta> itr = this.getIteradorea();
		while(itr.hasNext()){
			Karta kartaBat = itr.next();
			System.out.println(kartaBat.getKartaBalioa() + " " + kartaBat.idatziPalua());
		}
	}
}
