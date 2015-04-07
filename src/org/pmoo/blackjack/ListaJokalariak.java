package org.pmoo.blackjack;
import java.util.*;
public class ListaJokalariak {
	private static ListaJokalariak helbidea=null;
	private ArrayList<Jokalaria> lista;
	
	private ListaJokalariak(){
		this.lista=new ArrayList<Jokalaria>();
	}
	
	public static synchronized ListaJokalariak getNireListaJokalariak(){
		if (ListaJokalariak.helbidea==null){
			ListaJokalariak.helbidea=new ListaJokalariak();
		}
		return ListaJokalariak.helbidea;
		
	}
	
	
}
