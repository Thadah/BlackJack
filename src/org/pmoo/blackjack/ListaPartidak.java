package org.pmoo.blackjack;

import java.util.ArrayList;

public class ListaPartidak {
	private static ListaPartidak helbidea;
	private ArrayList<Ranking> listaPartidak;
	
	private ListaPartidak(){
		this.listaPartidak = new ArrayList<Ranking>();
	}
	
	public static final synchronized ListaPartidak getNireListaPartidak(){
		if(ListaPartidak.helbidea == null){
			ListaPartidak.helbidea = new ListaPartidak();
		}
		return ListaPartidak.helbidea;
	}
}
