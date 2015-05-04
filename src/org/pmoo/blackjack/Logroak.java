package org.pmoo.blackjack;

public class Logroak {
	
	//Atributuak
	private static Logroak helbidea = null;
	private boolean blackJack ; 		//Norbaitek BlackJack egitea
	private boolean bankarrota ;		//Dirua 0 izatea
	private boolean erretiratu ;		//Aposturenbat ez ikustea
	private boolean lagunikGabe; 		//Jokalari bakarra (Croupierarekin)
	private boolean lagunAsko ;			//7 jokalari
	private boolean mahaikoErregea ;	//Jokalari bakarra
	private boolean bikoitza ;			//Jokalari batek bikoitza apostatzea partidaren batean
	private boolean helloWorld ;		//Partida bat jolastea
	private boolean ludopata ;			//Partida bat baino gehiago jolastea
	private boolean emotikonoak ; 		//Emotikono guztiak desblokeatzea
	private boolean pistarenErregea ;	//Ranking-ean jokalari bera behin baino gehiagotan lehenengo postuan agertzea
	private boolean logroenErregea ; 	//Logro guztiak desblokeatzea
	
	//Eraikitzailea
	private Logroak(){
		this.blackJack = false; 		
		this.bankarrota = false;		
		this.erretiratu = false;		
		this.lagunikGabe = false; 	
		this.lagunAsko = false;		
		this.mahaikoErregea = false;	
		this.bikoitza = false;		
		this.helloWorld = false;	
		this.ludopata = false;		
		this.emotikonoak = false; 	
		this.pistarenErregea = false;
		this.logroenErregea = false; 
		//Logro gehiago...
	}
	
	public static synchronized Logroak getNireLogroak(){
		if (Logroak.helbidea == null){
			Logroak.helbidea = new Logroak();
		}
		return Logroak.helbidea;
	}
	
	//Beste Metodoak
	public void blackJackLogroa(){
		if(!this.blackJack){
			System.out.println("\n-=BlackJack Logroa Desblokeatuta=-\n");
			
		
			this.blackJack = true;
		}
	}
	
	public void erretiratuLogroa(){
		if(!this.erretiratu){
			System.out.println("\n-=Erretiratua Logroa Desblokeatuta=-\n");
			
		
			this.erretiratu = true;
		}
	}
		
	public void bikoitzaLogroa(){
		if(!this.bikoitza){
			System.out.println("\n-=Apostu Bikoitzaren Logroa Desblokeatuta=-\n");
		
			this.bikoitza = true;
		}
	}
	
	public void logroenErregeaLogroa(){
		if (this.blackJack && this.bankarrota && this.bikoitza && this.emotikonoak && this.erretiratu && this.helloWorld && (this.lagunAsko || this.lagunikGabe) && this.logroenErregea && this.ludopata && this.mahaikoErregea && this.pistarenErregea){
			System.out.println("\n-=LogroenErregea Logroa Desblokeatuta=-\n");
			System.out.println("\n-=Logro Guztiak Desblokeatuta=-\n");
		
			this.logroenErregea = true;
		}
	}

	public void helloWorldLogroa(){
		if(!this.helloWorld){
			System.out.println("\n-=HelloWorld Logroa Desblokeatuta=-\n");
		
			this.helloWorld = true;
		}
	}
	
	public void ludopataLogroa(){
		if(!this.ludopata && this.helloWorld){
			System.out.println("\n-=Ludopata Logroa Desblokeatuta=-\n");
		
			this.ludopata = true;
		}
	}
	
	
	public void bankarrotaLogroa(){
		if(!this.bankarrota){
			System.out.println("\n-=Bankarrota Logroa Desblokeatuta=-\n");
		
			this.bankarrota=true;
		}
	}
	
	public void mahaikoErregeaLogroa(){
		if(!this.mahaikoErregea && ListaJokalariak.getNireListaJokalariak().tamaina() == 1){
			System.out.println("\n-=Mahaiko Erregearen Logroa Desblokeatuta=-\n");
			this.mahaikoErregea = true;
		}
	}
	
	public void lagunikGabeLogroa(){
		if(!this.lagunikGabe && ListaJokalariak.getNireListaJokalariak().tamaina()==2){
			System.out.println("\n-=Lagunik Gabe Logroa Desblokeatuta=-\n");
			this.lagunikGabe = true;
			
		}
	}
	
	public void lagunAskoLogroa(){
		if(!this.lagunAsko && ListaJokalariak.getNireListaJokalariak().tamaina()>=7){
			System.out.println("\n-=Lagun Asko Logroa Desblokeatuta=-\n");
			this.lagunAsko = true;
		}
	}
	
	public void emotikonoakLogroa(){
		if(!this.emotikonoak && ListaEmotikonoak.getNireListaEmotikonoak().listarenTamaina()==10){
			System.out.println("\n-=Emotikonoen Logroa Desblokeatuta=-\n");
			this.emotikonoak = true;
			
		}
	}
	public void pistarenErregeaLogroa(){
		if(!this.pistarenErregea && ListaPartidak.getNireListaPartidak().hallOfFameBete()){	
			System.out.println("\n-=Pistaren Erregea Logroa Desblokeatuta=-\n");
			this.pistarenErregea = true;
		}
	}
}