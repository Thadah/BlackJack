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


		String jolastuNahi="B";
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		System.out.println("Ongi etorri Atutxa kasinora, ondo pasa dezazuen espero dugu :)");
		Thread.sleep(1000);
		//Jokalariak inskribatu
		jokalariak.jokalariakInskribatu();
		System.out.println("(Jokalari bakoitzak predeterminatuki 500� ditu)\n");
		boolean partidaZuzena = false;
		do{
		
			if(jolastuNahi.equals("B")){
				try{
					Baraja.getBaraja().erreseteatu();
					//Apostatu
					jokalariak.apostuak();
					//Apostuak ikusi
					jokalariak.apostuGuztiakIkusi();
					//Kartak eskatu
					jokalariak.hasierakoBiKartak();
					//Kartak eskatu
					jokalariak.kartakBanatu();
					
					Thread.sleep(2500);
					
					//Irabazlea kalkulatu
					if(!jokalariak.batBainoGehiagoIrabazi()){
						System.out.println(irabazleaKalkulatu().getIzena() + " ZORIONAK irabazi duzu !!! :3  ");
						System.out.println(irabazleaKalkulatu().getIzena() + " " + this.botea + "�-ko botea irabazi duzu :D");
						irabazleaKalkulatu().boteaHartu();
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
					if(jokalariak.tamaina()<2){
						throw(new JokalariException("Jokalarien kopurua ez da nahikoa partida jarraitzeko D:<"));
					}
					partidaZuzena = true;
				}
				catch(JokalariException e){
					System.out.println(e.getMessage());
					System.out.println("Partida Bukatuta");
					jolastuNahi = "E";
				}
			}
			else{
				JokoaAmaitu();
			}
		}while(!partidaZuzena);
		
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
	
	public void kontsolaGarbitu(){
		int i;
		for(i=1; i < 20; i++){
			System.out.println("\n");
		}
	}
	
}

//Kontsola ezabatu
//Pertsona bat joaten deneko kasua konpondu
//Kartak eskatzeko momentuan azkenean jokalari bat bakarrik geratzen bada partida jarraian amaitzea
//Klase- eta sekuentzia-diagrama