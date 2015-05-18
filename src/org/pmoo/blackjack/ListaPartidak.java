package org.pmoo.blackjack;

import java.util.*;

public class ListaPartidak {
	
	//Atributuak
	private static ListaPartidak helbidea;
	private ArrayList<Ranking> listaPartidak;
	private ArrayList<Jokalaria> hallOfFame;      //lehenen lista;
	//Eraikitzailea
	private ListaPartidak(){
		this.listaPartidak = new ArrayList<Ranking>();
		this.hallOfFame = new ArrayList<Jokalaria>();
		}
	
	public static final synchronized ListaPartidak getNireListaPartidak(){
		if(ListaPartidak.helbidea == null){
			ListaPartidak.helbidea = new ListaPartidak();
		}
		return ListaPartidak.helbidea;
	}
	
	//Beste metodoak
	private Iterator<Ranking> getIteradorea(){
		return this.listaPartidak.iterator();
	}
	
	private Iterator<Jokalaria> getIteradorea2(){
		return this.hallOfFame.iterator();
	}
	
	public void partidaGorde(Ranking pRanking){
		this.listaPartidak.add(pRanking);
	}
	
	public void partidakIdatzi(){
		Iterator<Ranking> itr = this.getIteradorea();
		Ranking rankingBat = null;
		int i = 1;
		while(itr.hasNext()){
			rankingBat = itr.next();
			System.out.println(i + ". Partida:");
			rankingBat.partidakIdatzi();
			i++;
		}		
	}
	private boolean badagoHallOfFamean(Jokalaria pJok){
		Iterator<Jokalaria> itr2=this.getIteradorea2();
		boolean bai = false;
		Jokalaria jok=null;
		while(itr2.hasNext()&&!bai){
			jok=itr2.next();
			if(pJok==jok){
				bai=true;
			}
		}
		return bai;
	}
	
	public boolean hallOfFameBete(){ 		//boolearra pertsona errepikatzen bada true itzuliko du;
		Iterator<Ranking> itr=this.getIteradorea();
		boolean bai=false;
		Ranking rank = null;
		Jokalaria jok = null;
		while(itr.hasNext()){
			rank=itr.next();
			jok=rank.norDaLehenengoa();
			if(this.badagoHallOfFamean(jok)){
				bai=true;
			}
			else{
				this.hallOfFame.add(jok);
			}

		}
		return bai;
	}
	

}
