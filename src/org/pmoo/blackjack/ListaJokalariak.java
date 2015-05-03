package org.pmoo.blackjack;

import java.util.*;

public class ListaJokalariak {
	
	//Atributuak
	Scanner sc = new Scanner(System.in);
	private static ListaJokalariak helbidea = null;
	private ArrayList<Jokalaria> lista;
	
	//Eraikitzailea
	private ListaJokalariak(){
		this.lista=new ArrayList<Jokalaria>();
	}
	
	public static synchronized ListaJokalariak getNireListaJokalariak(){
		if (ListaJokalariak.helbidea == null){
			ListaJokalariak.helbidea = new ListaJokalariak();
		}
		return ListaJokalariak.helbidea;
	}
	
	//Beste metodoak
	public Iterator<Jokalaria> getIteradorea(){
		return this.lista.iterator();
	}
	
	public Jokalaria eskuHandienaKalkulatu(){
		Jokalaria jok, eskuHandienaDuenJokalaria = null;
		Iterator<Jokalaria> itr = this.getIteradorea();
		boolean blackJack = false;
		int eskua = 0;
		while(itr.hasNext() && !blackJack){
			jok = itr.next();
			if(jok.eskuaKalkulatu() >= eskua && jok.eskuaKalkulatu() <= 21){
				if(jok.blackJackDu()){
					blackJack = true;
				}
				eskua = jok.eskuaKalkulatu();
				eskuHandienaDuenJokalaria = jok;
			}
		}
		return eskuHandienaDuenJokalaria;
	}
	
	public void jokalariakBueltatu(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.bueltatu();
		}
	}
	
	public int tamaina(){
		return this.lista.size();
	}

	public void erreseteatu(){
		this.lista.clear();
		ListaJokalariak.helbidea = null;
	}
	
	public void guztiakErreseteatu(){
		Baraja.getBaraja().erreseteatu();
		ListaJokalariak.getNireListaJokalariak().erreseteatu();
	}
	
	public void boteaBanatu(int pBotea, Ranking pRanking){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if (pRanking.rankingeanDago(jokalariBat)){
				jokalariBat.boteaHartuCroupier(pBotea);
			}
		}
	}
	
	public void jokalariakInskribatu(){
		int jokalariKop = 0;
		boolean intOngi = false;
		boolean denaOngi = false;
		Jokalaria jokalariBat = null;
		System.out.println("Zenbat jokalarik jolastuko dute?");
		try{
			jokalariKop = Integer.parseInt(sc.next());
			intOngi = true;
		}
		catch(NumberFormatException e){
			System.out.println("Ez duzu zenbaki bat sartu, saiatu berriro:");
			this.jokalariakInskribatu();
		}
		if(intOngi){
			try{
				if(jokalariKop > 7 || jokalariKop < this.jokalariMin()){
					throw(new JokalariException("Jokalari kopurua " + this.jokalariMin() + " eta 7 zenbakien artean egon behar da"));
				}
				denaOngi = true;
			}
			catch(JokalariException e){
				System.out.println(e.getMessage());
				this.jokalariakInskribatu();
			}
			if(denaOngi){
				int i = 1;
				String izen;
				for(i=1; i<=jokalariKop; i++){
					System.out.println("Zein da "+ i +". jokalariaren izena?");
					izen = sc.next();
					jokalariBat = new Jokalaria(izen);
					this.lista.add(jokalariBat);
				}
			}
		}
	}
	
	public void croupierInskribatu(){
		Croupier cr = new Croupier("Croupier");
		this.lista.add(cr);
	}
	
	public void hasierakoBiKartak() throws InterruptedException{
		Jokalaria jokalariBat = null;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			System.out.println(jokalariBat.getIzena() + "-ren txanda da.\n");
			jokalariBat.kartaEskatu();
			jokalariBat.kartaEskatu();
			if(jokalariBat.blackJackDu()){
				System.out.println("ZORIONAK BlackJack duzu!!! :3\n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":3");
			}
			jokalariBat.eskuaIdatzi();
		}
	}

	
	public void apostuak(){
		Jokalaria jokalariBat = null;
		boolean apostuaEginda = false;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			do{
				try{
					jokalariBat.apostuaEgin();
					apostuaEginda = true;
				}
				catch(ApostuException e){
					System.out.println(e.getMessage());
					System.out.println(jokalariBat.getIzena() + ", apostatu berriro");
					apostuaEginda = false;
				}
			}while(!apostuaEginda);
		}
	}
	
	public void apostuGuztiakIkusi(){
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.apostuaIkusi();
		}
	}
	
	public void kartakBanatu() throws InterruptedException{
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat=itr.next();
			jokalariBat.txanda();
			jokalariBat.eskuaIdatzi();
		}
	}
	
	public void kenduKartak(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.eskuBerria();
		}
	}
		
	public void guztienDiruaInprimatu(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.diruaInprimatu();
		}
	}
	
	public boolean batBainoGehiagoIrabazi(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		boolean bai = false;
		Jokalaria irabazlea = this.eskuHandienaKalkulatu();
		if (irabazlea != null && !irabazlea.blackJackDu()){
			int eskuIrabazlea = irabazlea.eskuaKalkulatu();
			while(itr.hasNext() && !bai){
				jokalariBat = itr.next();
				if(jokalariBat != irabazlea && eskuIrabazlea == jokalariBat.eskuaKalkulatu()){
					bai = true;
				}
			}
		}
		return bai;
	}
	
	public Ranking rankingCroupier(){
		ListaPartidak listaPartidak = ListaPartidak.getNireListaPartidak();
		Ranking rankinga = new Ranking();
		int croupierEskua = this.lista.get(this.tamaina()-1).eskuaKalkulatu();
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if ((jokalariBat.eskuaKalkulatu() <= 21) && (jokalariBat.eskuaKalkulatu() > croupierEskua)){
				rankinga.rankingeanSartu(jokalariBat);
			}
		}
		if (rankinga.irabazleKop() == 0){
			rankinga.rankingeanSartu(jokalariBat);
		}
		rankinga.rankingaOrdenatu();
		listaPartidak.partidaGorde(rankinga);
		return rankinga;
	}
	
	public Ranking rankingEzCroupier(){
		ListaPartidak listaPartidak = ListaPartidak.getNireListaPartidak();
		Ranking rankinga = new Ranking();
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat = null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			rankinga.rankingeanSartu(jokalariBat);
		}
		rankinga.rankingaOrdenatu();
		listaPartidak.partidaGorde(rankinga);
		return rankinga;
	}
	
	public void apostuakBueltatu(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.apostuaBueltatu();
		}
		
	}
	
	public void galdetuDenakJoan(){
		boolean joanDa;
		Jokalaria jokalariBat;
		int kont = 0;
		while(kont <= this.tamaina()-1){
			jokalariBat = this.lista.get(kont);
			joanDa = jokalariBat.galdetuJoan();
			if(!joanDa){
				kont++;
			}
		}
	}
	
	public void erretiratu(Jokalaria pJok){
		this.lista.remove(pJok);
	}
	
	private int jokalariMin(){
		int min = 2;
		if (BlackJack.croupierrarekin){
			min = 1;
		}
		return min;
	}

}
