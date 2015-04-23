package org.pmoo.blackjack;

import java.util.ArrayList;
import java.util.Iterator;

public class Ranking {

	//Atributuak
	private ArrayList<Jokalaria> ranking;
	
	//Eraikitzailea
	public Ranking(){
		this.ranking = new ArrayList<Jokalaria>();
	}
	
	//Beste Metodoak
	private Iterator<Jokalaria> getIteradorea(){
		return this.ranking.iterator();
	}
	
	public void rankingeanSartu (Jokalaria pJokalaria){
		this.ranking.add(pJokalaria);
	}
	
	public int irabazleKop(){
		return this.ranking.size();
	}
	
	public void boteaBanatu(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		int bakoitzari = mahaia.getBotea()/this.irabazleKop();
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.boteaHartuCroupier(bakoitzari);
		}
	}
	
	public void rankingaOrdenatu(){
		//TODO: Rankinga eskuaren arabera ordenatzea
	}
	
	public Jokalaria posizioan(int pInt){
		return this.ranking.get(pInt);
	}
	
	public void partidakIdatzi(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		int i = 4;
		while(itr.hasNext() && i < 4){
			jokalariBat = itr.next();
			System.out.println("  - " + i + ". postuan: " + jokalariBat.getIzena() + ", " + jokalariBat.eskuaKalkulatu() + " eskuarekin.");
			i++;
		}	
	}
		
}

