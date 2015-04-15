package org.pmoo.blackjack;

import java.util.*;

public class ListaJokalariak {
	Scanner sc=new Scanner(System.in);
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
	
	private Iterator<Jokalaria> getIteradorea(){
		return this.lista.iterator();
	}
	
	public Jokalaria eskuHandienaKalkulatu(){
		Jokalaria jok,eskuHandienaDuenJokalaria=null;
		Iterator<Jokalaria> itr=this.getIteradorea();
		int eskua=0;
		while(itr.hasNext()){
			jok=itr.next();
			if(eskua<jok.eskuaKalkulatu()&&jok.eskuaKalkulatu()<=21 && !jok.getErretiratua()){
				eskua=jok.eskuaKalkulatu();
				eskuHandienaDuenJokalaria=jok;
			}
		}
		return eskuHandienaDuenJokalaria;
	}
	
	public void jokalariakBueltatu(){
		Iterator<Jokalaria> itr = this.getIteradorea();
		Jokalaria jokalariBat=null;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getErretiratua() == true){
				jokalariBat.setErretiratua(false);
			}
		}
	}
	
	public int tamaina(){
		return this.lista.size();
	}

	public void erreseteatu(){
		this.lista.clear();
		ListaJokalariak.helbidea=null;
	}
	
	public void guztiakErreseteatu(){
		Baraja.getBaraja().erreseteatu();
		ListaJokalariak.getNireListaJokalariak().erreseteatu();
	}
	
	public void jokalariakInskribatu(){
		int jokalariKop;
		boolean denaOngi = false;
		Jokalaria jokalariBat = null;
		System.out.println("Zenbat jokalarik jolastuko dute?");
		jokalariKop = sc.nextInt();
		try{
			if(jokalariKop>7||jokalariKop<2){
				throw(new JokalariException("Jokalari kopurua 2 eta 7 zenbakien artean egon behar da"));
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
			for(i=1;i<=jokalariKop;i++){
				System.out.println("Zein da "+ i +". jokalariaren izena?");
		
				izen = sc.next();
				jokalariBat = new Jokalaria(izen);
				this.lista.add(jokalariBat);
			}
		}
	}
	
	public void hasierakoBiKartak() throws InterruptedException{
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if (!jokalariBat.getErretiratua()){
				jokalariBat.kartaEskatu();
				jokalariBat.kartaEskatu();
				Thread.sleep(2000);//TODO
			}
		}
	}

	
	public void apostuak(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat = null;
		int apostua = 0;
		boolean apostuaEginda = false;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getDirua()>=mahaia.getApostuMax()){
				do{
					try{
						apostua = jokalariBat.apostuaEgin();
						apostuaEginda = true;
					}
					catch(ApostuException e){
						System.out.println(e.getMessage());
						System.out.println(jokalariBat.getIzena() + ", Apostatu Berriro");
						apostuaEginda = false;
					}
				}while(!apostuaEginda);
			}
			if (apostua > mahaia.getApostuMax()){
				mahaia.setApostuMax(apostua);
			}
			mahaia.setBotea( mahaia.getBotea() + apostua);
			jokalariBat.setDirua(jokalariBat.getDirua() - jokalariBat.getApostua());
		}
	}
	
	public void apostuaIkusi(){
		
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if (!jokalariBat.getErretiratua()){
				if(jokalariBat.getApostua()<mahaia.getApostuMax()){
					if( (jokalariBat.getDirua() + jokalariBat.getApostua()) >= mahaia.getApostuMax()){
						System.out.println(jokalariBat.getIzena() + ", apostua ikusi nahi duzu?   (B/E)");
						String bai = sc.next();
						if(bai.equals("B") || bai.equals("b")){
							mahaia.setBotea(mahaia.getBotea() + (mahaia.getApostuMax() - jokalariBat.getApostua()));
							jokalariBat.setDirua(jokalariBat.getDirua() - (mahaia.getApostuMax() - jokalariBat.getApostua()));
							jokalariBat.setApostua(mahaia.getApostuMax());
						}
						else if(!bai.equals("B") && !bai.equals("b")){
							jokalariBat.setErretiratua(true);
						}
					}
					else {
						System.out.println(jokalariBat.getIzena() + " , ez du diru nahikorik apostua ikusteko, erretiratua izan da. T_T");
						jokalariBat.setErretiratua(true);
					}
				}
			}
		}
	}
	
	public void kartakBanatu() throws InterruptedException{
		Iterator<Jokalaria> itr=this.getIteradorea();
		Jokalaria jokalariBat=null;
		while(itr.hasNext()){
			jokalariBat=itr.next();
			jokalariBat.txanda();	
		}
	}
	
	public void kenduKartak(){
		Iterator<Jokalaria> itr=this.getIteradorea();
		Jokalaria jokalariBat=null;
		while(itr.hasNext()){
			jokalariBat=itr.next();
			jokalariBat.kartakItzuli();
		}
	}
		
	public void guztienDiruaInprimatu(){
		Iterator<Jokalaria> itr=this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat = itr.next();
			System.out.println(jokalariBat.getIzena() + "-(r)en dirua: " + jokalariBat.getDirua() + "€");
		}
	}
	
	public boolean batBainoGehiagoIrabazi(){
		Iterator<Jokalaria> itr=this.getIteradorea();
		Jokalaria jokalariBat;
		boolean bai = false;
		Jokalaria irabazlea = this.eskuHandienaKalkulatu();
		int eskuIrabazlea = irabazlea.eskuaKalkulatu();
		while(itr.hasNext() && !bai){
			jokalariBat = itr.next();
			if(jokalariBat!=irabazlea && eskuIrabazlea==jokalariBat.eskuaKalkulatu()&& !jokalariBat.getErretiratua()){
				bai = true;
			}
		}
		return bai;
	}
	
	public void galdetuJoan(){
		int kont=0;
		while(kont <= this.tamaina()-1){
			Jokalaria jokalariBat = this.lista.get(kont);
			if(jokalariBat.getDirua()==0){
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
}
