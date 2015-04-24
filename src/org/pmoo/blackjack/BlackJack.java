package org.pmoo.blackjack;

import java.util.*;

public class BlackJack {
	
	//Atributuak
	Scanner sk = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;
	public static boolean croupierrarekin = false;

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
		BlackJack.croupierrarekin = this.croupierrarekin();
		jokalariak.jokalariakInskribatu();
		if(BlackJack.croupierrarekin){
			jokalariak.croupierInskribatu();
		}
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
					this.irabazleaKalkulatu();
					
					partidaAmaitu();
					jokalariak.guztienDiruaInprimatu();
					
					//Galdetu ea norbai partidatik joan nahi den
					jokalariak.galdetuDenakJoan();
					if(jokalariak.tamaina() < 2){
						partidaZuzena = true;
						throw(new JokalariException("Jokalarien kopurua ez da nahikoa partida jarraitzeko D:<"));
					}
					
				}
				catch(JokalariException e){
					System.out.println(e.getMessage());
					if(this.rankingakIkusiNahi()){
						this.rankingakInprimatu();
					}
					JokoaAmaitu();
					jolastuNahi = "E";
				}
			}
			else{
				if(this.rankingakIkusiNahi()){
					this.rankingakInprimatu();
				}
				JokoaAmaitu();
			}
		}while(!partidaZuzena);

	}
	
	private boolean croupierrarekin(){
		System.out.println("Croupier-arekin jolastu nahi al duzu(e)? (B/E)");
		String bai = sc.next();
		if(bai.equals("B") || bai.equals("b")){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	private void irabaziCroupierGabe(){
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		Ranking rankinga = jokalariak.rankingEzCroupier();
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
	}
	
	private void irabaziCroupierrarekin(){
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		Ranking rankinga = jokalariak.rankingCroupier();
		//TODO: Hemen ez ditu irabazleen izenak inprimatzen.
		rankinga.boteaBanatu();
	}
	
	private void irabazleaKalkulatu(){
		if(!BlackJack.croupierrarekin){
			this.irabaziCroupierGabe();
		}
		else{
			this.irabaziCroupierrarekin();
		}
		this.botea = 0;
	}
	
	private void JokoaAmaitu(){
		sk.close();
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
		sk.nextLine();
		this.kontsolaGarbitu();
		Thread.sleep(2000);
	}
	
	private boolean rankingakIkusiNahi(){
		System.out.println("Partida bukatu da, rankingak ikusi nahi dituzue? (B/E)");
		String bai = sc.next();
		if(bai.equals("B") || bai.equals("b")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void rankingakInprimatu(){
		ListaPartidak listaPartidak = ListaPartidak.getNireListaPartidak();
		listaPartidak.partidakIdatzi();
	}
}
