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
		this.kartak.clear();
	}
	
	
	private Iterator<Karta> getIteradorea(){
		return this.kartak.iterator();
	}
	
	public int kartenBalioaLortu(){
		Karta kartaBat;
		int balioa = 0;
		Iterator<Karta> itr=this.getIteradorea();
		while(itr.hasNext()){
			kartaBat = itr.next();
			balioa = balioa + kartaBat.getKartaBalioa();
		}
		if( balioa>21  && this.batekorikDago() ){
			balioa = balioa - (this.zenbatBatekoDago()*10);
		}
		return balioa;
	}
	
	private boolean batekorikDago(){
		Karta kartaBat;
		boolean badago = false;
		Iterator<Karta> itr=this.getIteradorea();
		while(itr.hasNext() && !badago){
			kartaBat = itr.next();
			badago = kartaBat.batekoa();
		}
		return badago;
	}
	
	private int zenbatBatekoDago(){
		Karta kartaBat;
		int batekoKop = 0;
		Iterator<Karta> itr=this.getIteradorea();
		while(itr.hasNext()){
			kartaBat = itr.next();
			if(kartaBat.batekoa()){
				batekoKop++;
			}
		}
		return batekoKop;
	}

}
