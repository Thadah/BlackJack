package org.pmoo.blackjack;

import java.util.*;

import org.pmoo.audio.Audio;

public class Ranking {

	//Atributuak
	private ArrayList<Jokalaria> ranking;
	static final Comparator<Jokalaria> ESKUA = new Comparator<Jokalaria>() {
	public int compare(Jokalaria pJok1, Jokalaria pJok2) {
	return new Integer(pJok2.eskuaKalkulatu()).compareTo(new Integer(pJok1.eskuaKalkulatu()));
		 }
	 };
	
	//Eraikitzailea
	public Ranking(){
		this.ranking = new ArrayList<Jokalaria>();
	}
	
	//Beste metodoak
	private Iterator<Jokalaria> getIteradorea(){
		return this.ranking.iterator();
	}
	
	public void rankingeanSartu (Jokalaria pJokalaria){
		Jokalaria jokalariBat = new Jokalaria(pJokalaria.izena);
		jokalariBat.eskua = pJokalaria.eskua;
		this.ranking.add(jokalariBat);
	}
	
	public int irabazleKop(){
		return this.ranking.size();
	}
	
	
	public void rankingaOrdenatu(){
		 Collections.sort(this.ranking, ESKUA);
	}
	
	public Jokalaria posizioan(int pInt){
		return this.ranking.get(pInt);
	}
	
	public void partidakIdatzi(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		int i = 1;
		while(i < 4){
			if(itr.hasNext()){
				jokalariBat = itr.next();
				if(jokalariBat.eskuaKalkulatu() <= 21){
					System.out.println("  - " + i + ". postuan: " + jokalariBat.getIzena() + ", " + jokalariBat.eskuaKalkulatu() + " eskuarekin.");
					i++;
				}
			}
			else{
				System.out.println("  - " + i + ". postuan: ---");
				i++;
			}
		}
	}
	
	public void irabazleakInprimatu() throws RankingException{
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;	
			if(this.irabazleKop() == 0){
				{ throw(new RankingException("Ez duenez inork irabazi apostuak itzuliko dira")); }
			}
			while(itr.hasNext()){
				jokalariBat = itr.next();
				System.out.print(jokalariBat.getIzena() + " ");
			}
			System.out.print((int)(mahaia.getBotea())/(this.irabazleKop()) + "\u20AC-ko botea irabazi duzu(e) ;)\n\n");
			Audio woohoo = new Audio("WooHoo.mp3");
			woohoo.playAudio();
	}
	
	public Jokalaria norDaLehenengoa(){
		Jokalaria lehena=null;
		Iterator<Jokalaria> itr = this.getIteradorea();
		lehena=itr.next();
		return lehena;
	}
	
	public boolean rankingeanDago(Jokalaria pJok){
		boolean aurkitua = false;
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext() && !aurkitua){
			jokalariBat = itr.next();
			if(pJok.getIzena() == jokalariBat.getIzena() && pJok.eskuaKalkulatu() == jokalariBat.eskuaKalkulatu()){
				aurkitua = true;
			}
		}
		return aurkitua;
	}
		
}

