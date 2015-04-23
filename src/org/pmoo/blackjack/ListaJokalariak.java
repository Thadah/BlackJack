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
	
	//Beste Medotoak
	private Iterator<Jokalaria> getIteradorea(){
		return this.lista.iterator();
	}
	
	public Jokalaria eskuHandienaKalkulatu(){
		Jokalaria jok, eskuHandienaDuenJokalaria = null;
		Iterator<Jokalaria> itr = this.getIteradorea();
		int eskua = 0;
		while(itr.hasNext()){
			jok = itr.next();
			if(eskua < jok.eskuaKalkulatu() && jok.eskuaKalkulatu() <= 21){
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
					throw(new JokalariException("Jokalari kopurua" + this.jokalariMin() + " eta 7 zenbakien artean egon behar da"));
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
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.kartaEskatu();
			jokalariBat.kartaEskatu();
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
					System.out.println(jokalariBat.getIzena() + ", Apostatu Berriro");
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
			jokalariBat.kartakItzuli();
		}
	}
		
	public void guztienDiruaInprimatu(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			System.out.println(jokalariBat.getIzena() + "-(r)en dirua: " + jokalariBat.getDirua() + "�");
		}
	}
	
	public boolean batBainoGehiagoIrabazi(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		boolean bai = false;
		Jokalaria irabazlea = this.eskuHandienaKalkulatu();
		if (irabazlea != null){
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
	
	public Ranking croupierIrabazi(){
		Ranking rankinga = new Ranking();
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if (jokalariBat.eskuaKalkulatu() > this.lista.get(this.tamaina()-1).eskuaKalkulatu()){
				rankinga.rankingeanSartu(jokalariBat);
			}
		}
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
	
	public void galdetuJoan(){
		int kont = 0;
		while(kont <= this.tamaina()-1){
			Jokalaria jokalariBat = this.lista.get(kont);
			if(jokalariBat.getDirua() == 0){
				System.out.println("Sentitzen dugu " + jokalariBat.getIzena() + ", baina Kasinotik joan behar zara ez duzulako dirurik. >:(");
				this.lista.remove(jokalariBat);
				System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
			}
			else{		
				System.out.println(jokalariBat.getIzena() + ", partida utzi nahi duzu? (B/E)");
				String bai = sc.next();
				if(bai.equals("B") || bai.equals("b")){
					System.out.println(jokalariBat.getIzena() + " mahaia utzi du ;_;");
					this.lista.remove(jokalariBat);
				}
				else{kont++;}
			}
		}
	}
	
	private int jokalariMin(){
		int min = 2;
		if (BlackJack.croupierrarekin){
			min = 1;
		}
		return min;
	}

}
