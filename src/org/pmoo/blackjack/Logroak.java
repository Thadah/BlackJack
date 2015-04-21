package org.pmoo.blackjack;

import java.util.ArrayList;

public class Logroak {
	//Atributuak
	private static Logroak helbidea = null;
	private ArrayList<Boolean> lista;
	
	//Eraikitzailea
	private Logroak(){
		this.lista = new ArrayList<Boolean>();
			boolean blackJack = false; 		//Norbaitek BlackJack egitea
			boolean bankarrota = false;		//Dirua 0 izatea
			boolean erretiratu = false;		//Aposturenbat ez ikustea
			boolean lagunikGabe = false; 	//Jokalari bakarra (Croupierarekin)
			boolean lagunAsko = false;		//7 jokalari
			boolean mahaikoErregea = false;	//Jokalari bakarra (Besteak erretiratuak)
			boolean bikoitza = false;		//Jokalari batek bikoitza apostatzea partidaren batean
			boolean helloWorld = false;		//Partida bat jolastea
			boolean ludopata = false;		//Partida bat baino gehiago jolastea
			boolean emotikonoak = false; 	//Emotikono guztiak desblokeatzea
			//Logro gehiago...
	}
	
	public static synchronized Logroak getNireLogroak(){
		if (Logroak.helbidea == null){
			Logroak.helbidea = new Logroak();
		}
		return Logroak.helbidea;
	}
	
	//Beste Metodoak
}