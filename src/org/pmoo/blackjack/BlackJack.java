package org.pmoo.blackjack;

import java.util.*;

public class BlackJack {
	
	//Atributuak
	Scanner sc = new Scanner(System.in);
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;

	//Eraikitzailea
	private BlackJack(){
		this.botea = 0;
		this.apostuMax = 0;
	}
	
	public static synchronized BlackJack getNireBlackJack(){
		if(helbidea == null){
			helbidea = new BlackJack();
		}
		return helbidea;
	}
	
	//Beste Metodoak
	public void partidaJolastu() throws InterruptedException{
		String jolastuNahi = "B";
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
					if (jokalariak.tamaina() >= 2){
						//Itxaron
						System.out.println("\nSakatu enter jokoa hasteko.");
						this.enterItxaron();
						//Kartak eskatu
						jokalariak.hasierakoBiKartak();
						//Kartak eskatu
						jokalariak.kartakBanatu();
					}
					
					//Irabazlea kalkulatu
					if(!jokalariak.batBainoGehiagoIrabazi()){
						Jokalaria irabazlea = jokalariak.eskuHandienaKalkulatu();
						if (irabazlea != null){
							System.out.println(irabazlea.getIzena() + " ZORIONAK irabazi duzu !!! :3  ");
							System.out.println(irabazlea.getIzena() + " " + this.botea + " �-ko botea irabazi duzu :D");
							irabazlea.boteaHartu();
						} 
						else {
							System.out.println("Jokalari guztiak pasatu dira, beraz, ez dago irabazlerik :|");
							jokalariak.apostuakBueltatu();
						}
					}
					else{
						System.out.println("2 jokalarik edo gehiagok berdindu dutenez, apostuak itzuliko dira :/");
						jokalariak.apostuakBueltatu();
					}
					this.botea = 0;
					
					partidaAmaitu();
					jokalariak.guztienDiruaInprimatu();
					System.out.println();
					
					//Galdetu ea norbai partidatik joan nahi den
					jokalariak.galdetuJoan();
					if(jokalariak.tamaina() < 2){
						throw(new JokalariException("Jokalarien kopurua ez da nahikoa partida jarraitzeko D:<"));
					}
					partidaZuzena = true;
				}
				catch(JokalariException e){
					System.out.println(e.getMessage());
					System.out.println("Partida Bukatuta");
					JokoaAmaitu();
					jolastuNahi = "E";
				}
			}
			else{
				JokoaAmaitu();
			}
		}while(!partidaZuzena);
		
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
		this.apostuMax = 0;
		ListaJokalariak.getNireListaJokalariak().jokalariakBueltatu();
		ListaJokalariak.getNireListaJokalariak().kenduKartak();
	}
	
	public void kontsolaGarbitu(){
		int i;
		for(i=1; i < 20; i++){
			System.out.println("\n");
		}
	}
	
	public void enterItxaron() throws InterruptedException{
		sc.nextLine();
		this.kontsolaGarbitu();
		Thread.sleep(2000);
	}
	
}
