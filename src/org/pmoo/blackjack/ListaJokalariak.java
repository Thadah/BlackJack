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
	
	public Iterator<Jokalaria> getIteradorea(){
		return this.lista.iterator();
	}
	
	public Jokalaria eskuHandienaKalkulatu(){
		Jokalaria jok,eskuHandienaDuenJOkalaria=null;
		Iterator<Jokalaria> itr=this.getIteradorea();
		int eskua=0;
		while(itr.hasNext()){
			jok=itr.next();
			if(eskua<jok.eskuaKalkulatu()){
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
	
	public void jokalariakInskribatu(){//TODO
		Jokalaria jokalariBat = null;
		System.out.println("Zenbat jokalarik jolastuko dute?");
		//int jokalariKop = Hemen teklatutik irakurri beharko genuke;
		int i = 1;
		String izen;
		for(i=1;i<=jokalariKop;i++){
			System.out.println("Zein da "+ i +". jokalariaren izena?");
			//izen = Hemen teklatutik irakurri beharko genuke;
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
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat = null;
		int apostua = 0;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getDirua()>=mahaia.getApostuMax()){
				apostua = jokalariBat.apostuaEgin();
				if(apostua==0){
					this.ezabatuJok(jokalariBat);
					System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
				}
				else if(apostua>=mahaia.getApostuMax()){
					BlackJack.getNireBlackJack().setApostuMax(apostua);
					mahaia.setBotea( mahaia.getBotea() + apostua);
				}
				else{
					while(apostua<mahaia.getApostuMax())
					System.out.println("Diru gutxiegi apostatu duzu, apostatu ezazu berriro");
					jokalariBat.setDirua(jokalariBat.getDirua()+apostua);
					jokalariBat.apostuaEgin();
				}
			}
			else if(jokalariBat.getDirua()==0){
				System.out.println("Sentitzen dugu, baina Kasinotik joan behar zara ez duzulako dirurik.");
				ezabatuJok(jokalariBat);
				System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
			}
			else{
				System.out.println("All-in egin nahi duzu?");
				//boolean allIn = Teklatutik irakurri
				if(allIn){
					mahaia.setBotea( mahaia.getBotea() + jokalariBat.getDirua() );
					jokalariBat.setDirua(0);
				}
				else{
					this.ezabatuJok(jokalariBat);
					System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
				}
				
			}
		}
	}
	
}
