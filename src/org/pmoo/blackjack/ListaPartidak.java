package org.pmoo.blackjack;

import java.util.*;

public class ListaPartidak {
	
	//Atributuak
	private static ListaPartidak helbidea;
	private ArrayList<Ranking> listaPartidak;
	
	//Eraikitzailea
	private ListaPartidak(){
		this.listaPartidak = new ArrayList<Ranking>();
	}
	
	public static final synchronized ListaPartidak getNireListaPartidak(){
		if(ListaPartidak.helbidea == null){
			ListaPartidak.helbidea = new ListaPartidak();
		}
		return ListaPartidak.helbidea;
	}
	
	//Beste Metodoak
	private Iterator<Ranking> getIteradorea(){
		return this.listaPartidak.iterator();
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
}
