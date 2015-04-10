package org.pmoo.blackjack;

public class BlackJack {
	
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;
	private int txanda;

	private BlackJack(){}
	
	public static synchronized BlackJack getNireBlackJack(){
		if(helbidea == null){
			helbidea = new BlackJack();
		}
		return helbidea;
	}
	
	public void partidaJolastu(){
		boolean jolastunahi=true;
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		System.out.println("Ongi etorri Atutxa kasinora, ondo pasa dezazuen espero dugu :)");
		
		//Jokalariak inskribatu
		jokalariak.jokalariakInskribatu();
		
		while(jolastunahi){
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
			kartakJolastu();
			//Irabazlea kalkulatu
			System.out.println(irabazleaKalkulatu().getIzena() + " ZORIONAK irabazi duzu !!!");
			irabazleaKalkulatu().setDirua(irabazleaKalkulatu().getDirua()+ this.botea);

			
			System.out.println("Jolastu nahi duzue berriro ??");
			jolastunahi=//teklatutik irakurri;
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
		this.helbidea = null;
	}
	
	private void apostuakEgin(){
		ListaJokalariak.getNireListaJokalariak().apostuak();
	}
	
	private void kartakJolastu(){
		ListaJokalariak.getNireListaJokalariak().kartakBanatu();
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
		this.botea=0;
		this.apostuMax=0;
		ListaJokalariak.getNireListaJokalariak().kenduKartak();
	}
	
}

