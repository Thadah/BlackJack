package org.pmoo.blackjack;

import java.util.*;

public class ListaJokalariak {
	
	private static ListaJokalariak helbidea=null;
	private ArrayList<Jokalaria> lista,listaErretiratuak;
	
	private ListaJokalariak(){
		this.lista=new ArrayList<Jokalaria>();
		this.listaErretiratuak=new ArrayList<Jokalaria>();
	}
	
	public static synchronized ListaJokalariak getNireListaJokalariak(){
		if (ListaJokalariak.helbidea==null){
			ListaJokalariak.helbidea=new ListaJokalariak();
		}
		return ListaJokalariak.helbidea;
		
	}
	
	public Iterator<Jokalaria> getIteradorea(){
		return this.lista.iterator();
	}
	
	public Jokalaria eskuHandienaKalkulatu(){
		Jokalaria jok,eskuHandienaDuenJOkalaria=null;
		Iterator<Jokalaria> itr=this.getIteradorea();
		int eskua=0;
		while(itr.hasNext()){
			jok=itr.next();
			if(eskua<jok.eskuaKalkulatu()&&jok.eskuaKalkulatu()<=21){
				eskua=jok.eskuaKalkulatu();
				eskuHandienaDuenJOkalaria=jok;
			}
		}
		return eskuHandienaDuenJOkalaria;
	}
	
	public int tamaina(){
		return this.lista.size();
	}
	
	private void ezabatuJok(Jokalaria pJok){
		this.lista.remove(pJok);
	}
	
	private void gehituJok(Jokalaria pJok){
		this.lista.add(pJok);
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
		Scanner sc=new Scanner(System.in);
		Jokalaria jokalariBat = null;
		System.out.println("Zenbat jokalarik jolastuko dute?");
		int jokalariKop = sc.nextInt();
		while(jokalariKop>7||jokalariKop<2){
			System.out.println("Jokalari koporua 2 eta 7 zenbakien artean egon behar da");
			System.out.println("Zenbaki berria idatzi :");
			jokalariKop = sc.nextInt();
		}
		int i = 1;
		String izen;
		for(i=1;i<=jokalariKop;i++){
			System.out.println("Zein da "+ i +". jokalariaren izena?");
			izen = sc.nextLine();
			jokalariBat = new Jokalaria(izen);
			this.gehituJok(jokalariBat);
		}
	}
	
	public void hasierakoBiKartak(){
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			jokalariBat.kartaEskatu();
			jokalariBat.kartaEskatu();
		}
	}
	
	public void apostuak(){
		Scanner sc=new Scanner(System.in);
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat = null;
		int apostua = 0;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getDirua()>=mahaia.getApostuMax()){
				apostua = jokalariBat.apostuaEgin();
				if(apostua==0){
					this.listaErretiratuak.add(jokalariBat);
					this.ezabatuJok(jokalariBat);
					System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
				}
				else if(apostua>=mahaia.getApostuMax()){
					BlackJack.getNireBlackJack().setApostuMax(apostua);
					mahaia.setBotea( mahaia.getBotea() + apostua);
				}
				else{
					while(apostua<mahaia.getApostuMax()){
						System.out.println("Diru gutxiegi apostatu duzu, apostatu ezazu berriro");
						jokalariBat.setDirua(jokalariBat.getDirua()+apostua);
						jokalariBat.apostuaEgin();
					}
				}
			}
			else if(jokalariBat.getDirua()==0){
				System.out.println("Sentitzen dugu, baina Kasinotik joan behar zara ez duzulako dirurik.");
				ezabatuJok(jokalariBat);
				System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
			}
			else{
				System.out.println("All-in egin nahi duzu?   (B/E)");
				String allIn = sc.nextLine();
				if(allIn=="B"){
					mahaia.setBotea( mahaia.getBotea() + jokalariBat.getDirua() );
					jokalariBat.setDirua(0);
				}
				else {
					this.listaErretiratuak.add(jokalariBat);
					this.ezabatuJok(jokalariBat);
					System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
				}
				
			}
		}
	}
	
	public boolean guztiekApostatuDute(){
		Jokalaria jokalariBat;
		boolean bai = true;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext() && bai){
			jokalariBat = itr.next();
			if(jokalariBat.getApostua()<BlackJack.getNireBlackJack().getApostuMax()){
				bai = false;
			}
		}
		return bai;
	}
	
	public void apostuaIkusi(){
		Scanner sc=new Scanner(System.in);
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getApostua()<mahaia.getApostuMax()){
				if( (jokalariBat.getDirua() + jokalariBat.getApostua()) >= mahaia.getApostuMax()){
					System.out.println(jokalariBat.getIzena() + ", apostua ikusi nahi duzu?   (B/E)");
					String bai = sc.nextLine();
					if(bai=="B"){
						jokalariBat.setDirua(jokalariBat.getDirua()+jokalariBat.getApostua());
						int apostua = jokalariBat.apostuaEgin();
						while(apostua<mahaia.getApostuMax()){
							System.out.println("Diru gutxiegi apostatu duzu, apostatu ezazu berriro");
							apostua = jokalariBat.apostuaEgin();
						}
					}
					else{
						this.listaErretiratuak.add(jokalariBat);
						this.ezabatuJok(jokalariBat);
					}
				}		
			}
		}
	}
	
	public void kartakBanatu(){
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
	private Iterator<Jokalaria> getIteradorea2(){
		return this.listaErretiratuak.iterator();
	}
	
	public void jokalariakBueltatu(){
		Iterator<Jokalaria> itr=this.getIteradorea2();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat=itr.next();
			this.lista.add(jokalariBat);
			this.listaErretiratuak.remove(jokalariBat);
		}
	}
}
