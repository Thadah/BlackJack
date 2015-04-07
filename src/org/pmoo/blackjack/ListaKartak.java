package org.pmoo.blackjack;

import java.util.*;

public class ListaKartak {
	
	private ArrayList<Karta> kartak;
	
	public ListaKartak(){
		this.kartak = new ArrayList<Karta>();
	}
	
	public void gehituKarta(Karta pKarta){
		this.kartak.add(pKarta);
	}
	
	public void erreseteatu(){
		this.kartak = null;
	}
	
	
	private Iterator<Karta> getIteradorea(){
		return this.kartak.iterator();
	}
	
	public int kartenBalioaLortu(){//Gero agian metodo hau aldatu beharko da batekoa dela-eta
		Karta kartaBat;
		int balioa = 0;
		Iterator<Karta> itr=this.getIteradorea();
		while(itr.hasNext()){
			kartaBat = 
			balioa = balioa + kartaBat.getKartaBalioa;
		}
	}

}
