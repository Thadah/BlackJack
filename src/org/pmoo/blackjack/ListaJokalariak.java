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
		int jokalariKop;

		Jokalaria jokalariBat = null;
		System.out.println("Zenbat jokalarik jolastuko dute?");
		jokalariKop = sc.nextInt();
		while(jokalariKop>7||jokalariKop<2){
			System.out.println("Jokalari kopurua 2 eta 7 zenbakien artean egon behar da");
			System.out.println("Zenbaki berria idatzi :");
			jokalariKop = sc.nextInt();
		}
		int i = 1;
		String izen;
		for(i=1;i<=jokalariKop;i++){
			System.out.println("Zein da "+ i +". jokalariaren izena?");

			izen = sc.next();
			jokalariBat = new Jokalaria(izen);
			this.gehituJok(jokalariBat);
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
				//Thread.sleep(2000);
			}
		}
	}
	
	public void apostuak(){

		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat = null;
		int apostua = 0;
		Jokalaria jokAux = null;
		boolean ezabatuBehar = false;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(ezabatuBehar){
				this.ezabatuJok(jokAux);
				ezabatuBehar = false;
			}
			if(jokalariBat.getDirua()>=mahaia.getApostuMax()){
				apostua = jokalariBat.apostuaEgin();
				if(apostua==0){
					jokalariBat.setErretiratua(true);
					System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
					ezabatuBehar = true;

				}
				else if(apostua>=mahaia.getApostuMax()){
					BlackJack.getNireBlackJack().setApostuMax(apostua);
					mahaia.setBotea( mahaia.getBotea() + apostua);
				}
				else{
					while(apostua<mahaia.getApostuMax()){
						System.out.println("Diru gutxiegi apostatu duzu, apostatu ezazu berriro");
						jokalariBat.setDirua(jokalariBat.getDirua()+apostua);
						
					
						apostua = jokalariBat.apostuaEgin();
					}
					mahaia.setBotea(mahaia.getBotea()+apostua);
					mahaia.setApostuMax(apostua);
				}
			}
			else if(jokalariBat.getDirua()==0){
				System.out.println("Sentitzen dugu, baina Kasinotik joan behar zara ez duzulako dirurik.");
				ezabatuJok(jokalariBat);
				System.out.println(jokalariBat.getIzena() + " jokalaria jokotik atera da.");
			}
			else{
				System.out.println("All-in egin nahi duzu?   (B/E)");
				String allIn = sc.next();
				if(allIn=="B"){
					mahaia.setBotea( mahaia.getBotea() + jokalariBat.getDirua() );
					jokalariBat.setDirua(0);
				}
				else {
					jokalariBat.setErretiratua(true);
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
		
		BlackJack mahaia = BlackJack.getNireBlackJack();
		Jokalaria jokalariBat;
		Iterator<Jokalaria> itr = this.getIteradorea();
		while(itr.hasNext()){
			jokalariBat = itr.next();
			if(jokalariBat.getApostua()<mahaia.getApostuMax()){
				if( (jokalariBat.getDirua() + jokalariBat.getApostua()) >= mahaia.getApostuMax()){
					System.out.println(jokalariBat.getIzena() + ", apostua ikusi nahi duzu?   (B/E)");
					String bai = sc.next();
					if(bai.equals("B")){
						mahaia.setBotea(mahaia.getBotea() + (mahaia.getApostuMax() - jokalariBat.getApostua()));
						jokalariBat.setDirua(jokalariBat.getDirua() - (mahaia.getApostuMax() - jokalariBat.getApostua()));
						jokalariBat.setApostua(mahaia.getApostuMax());
					}
					else if(!bai.equals("B")){
						jokalariBat.setErretiratua(true);
					}
				}
				else {
					jokalariBat.setErretiratua(true);
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
			System.out.println(jokalariBat.getIzena() + "-(r)en dirua: " + jokalariBat.getDirua() + "�");
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
			if(jokalariBat!=irabazlea && eskuIrabazlea==jokalariBat.eskuaKalkulatu()){
				bai = true;
			}
		}
		return bai;
	}
	
	public void galdetuJoan(){
		Iterator<Jokalaria> itr=this.getIteradorea();
		Jokalaria jokalariBat;
		while(itr.hasNext()){
			jokalariBat=itr.next();
			System.out.println(jokalariBat.getIzena() + ", partida utzi nahi duzu? (B/E)");
			String bai = sc.next();
			if(bai.equals("B")){
				System.out.println(jokalariBat.getIzena() + " mahaia utzi du ;_;");
				this.ezabatuJok(jokalariBat);
			}
		}
	}
}
