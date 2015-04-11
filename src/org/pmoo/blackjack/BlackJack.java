package org.pmoo.blackjack;

import java.util.*;

public class BlackJack {
	
	Scanner sc=new Scanner(System.in);
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;

	private BlackJack(){
		this.botea=0;
		this.apostuMax=0;
	}
	
	public static synchronized BlackJack getNireBlackJack(){
		if(helbidea == null){
			helbidea = new BlackJack();
		}
		return helbidea;
	}
	
	public void partidaJolastu() throws InterruptedException{


		String jolastunahi="B";
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		System.out.println("Ongi etorri Atutxa kasinora, ondo pasa dezazuen espero dugu :)");
		Thread.sleep(1000);
		//Jokalariak inskribatu
		jokalariak.jokalariakInskribatu();
		System.out.println("(Jokalari bakoitzak predeterminatuki 500€ ditu)\n");
		
		while(jolastunahi.equals("B")){
			
			
			Baraja.getBaraja().erreseteatu();
			//Kartak eskatu
			jokalariak.hasierakoBiKartak();
			//Apostatu
			apostuakEgin();
			//Apostuak ikusi
			if(!jokalariak.guztiekApostatuDute()){
				jokalariak.apostuaIkusi();
			}
			//Kartak eskatu
			jokalariak.kartakBanatu();
			
			Thread.sleep(2500);
			
			//Irabazlea kalkulatu
			if(!jokalariak.batBainoGehiagoIrabazi()){
				System.out.println(irabazleaKalkulatu().getIzena() + " ZORIONAK irabazi duzu !!!");
				System.out.println(irabazleaKalkulatu().getIzena() + " " + this.botea + "€-ko botea irabazi duzu :D");
				irabazleaKalkulatu().setDirua(irabazleaKalkulatu().getDirua()+ this.botea);
				this.botea = 0;
			}
			else{
				System.out.println("2 jokalarik edo gehiagok berdindu dutenez, botea ez da emango :/");
			}
			
			partidaAmaitu();
			jokalariak.guztienDiruaInprimatu();
			System.out.println();
			
			//Galdetu ea norbai partidatik joan nahi den
			jokalariak.galdetuJoan();
			
		}
		JokoaAmaitu();
	}
	
	private Jokalaria irabazleaKalkulatu(){
		Jokalaria irabazle=null;
		irabazle=ListaJokalariak.getNireListaJokalariak().eskuHandienaKalkulatu();
		return irabazle;
	}
	
	private void JokoaAmaitu(){
		Baraja.getBaraja().erreseteatu();
		ListaJokalariak.getNireListaJokalariak().erreseteatu();
		BlackJack.helbidea = null;
	}
	
	private void apostuakEgin(){
		ListaJokalariak.getNireListaJokalariak().apostuak();
	}

	public int getApostuMax() {
		return this.apostuMax;
	}

	public void setApostuMax(int pApostuMax) {
		this.apostuMax = pApostuMax;
	}

	public int getBotea() {
		return botea;
	}

	public void setBotea(int pBotea) {
		this.botea = pBotea;
	}
	
	private void partidaAmaitu(){
		
		this.apostuMax=0;
		ListaJokalariak.getNireListaJokalariak().jokalariakBueltatu();
		ListaJokalariak.getNireListaJokalariak().kenduKartak();
	}
	
}

//Kontsola ezabatu
//Pertsona bat joaten deneko kasua konpondu
//Kartak eskatzeko momentuan azkenean jokalari bat bakarrik geratzen bada partida jarraian amaitzea
//Klase- eta sekuentzia-diagrama