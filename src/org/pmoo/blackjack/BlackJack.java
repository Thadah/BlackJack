package org.pmoo.blackjack;

import org.pmoo.audio.*;

import java.util.Scanner;

public class BlackJack {
	
	//Atributuak
	Scanner sk = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;
	public static boolean croupierrarekin = false;
	
	//Main Metodoa
	public static void main(String[] args) throws InterruptedException{
		BlackJack.getNireBlackJack().partidaJolastu();
	}

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
	
	//Beste metodoak
	public void partidaJolastu() throws InterruptedException{
		Audio main = new Audio("Theme.mp3");
		main.PlayAudio();
		String jolastuNahi = "B";
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		Logroak logroak = Logroak.getNireLogroak();
		System.out.println("Ongi etorri Atutxa kasinora, ondo pasa dezazuen espero dugu :)");
		ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":)");
		Thread.sleep(1000);
		//Jokalariak inskribatu
		BlackJack.croupierrarekin = this.croupierrarekin();
		jokalariak.jokalariakInskribatu();
		if(BlackJack.croupierrarekin){
			jokalariak.croupierInskribatu();
			logroak.lagunikGabeLogroa();
			logroak.logroenErregeaLogroa();
		}
		logroak.lagunAskoLogroa();
		logroak.logroenErregeaLogroa();
		System.out.println("(Jokalari bakoitzak predeterminatuki 500� ditu)\n");
		boolean partidaZuzena = false;
		do{		
			if(jolastuNahi.equals("B")){
				try{
					
					Baraja.getBaraja().erreseteatu();
					System.out.println("Barajatzen...");
					Audio shuffle = new Audio("Shuffling.mp3");
					shuffle.PlayAudio();
					Thread.sleep(3000);
					//Apostatu
					jokalariak.apostuak();
					//Apostuak ikusi
					jokalariak.apostuGuztiakIkusi();
					if (jokalariak.tamaina() >= 2){
						logroak.ludopataLogroa();
						logroak.helloWorldLogroa();
						logroak.logroenErregeaLogroa();
						//Itxaron
						System.out.println("\nSakatu enter jokoa hasteko.");
						this.enterItxaron();
						//Kartak eskatu
						jokalariak.hasierakoBiKartak();
						//Kartak eskatu
						jokalariak.kartakBanatu();
						logroak.mahaikoErregeaLogroa();
						logroak.logroenErregeaLogroa();
						
					}
					
					//Irabazlea kalkulatu
					this.irabazleaKalkulatu();
					
					partidaAmaitu();
					logroak.emotikonoakLogroa();
					logroak.logroenErregeaLogroa();
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
					ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa("D:<");
					if(this.rankingakIkusiNahi()){
						this.rankingakInprimatu();
						logroak.pistarenErregeaLogroa();
						logroak.logroenErregeaLogroa();
					}
					JokoaAmaitu();
					jolastuNahi = "E";
				}
			}
			else{
				if(this.rankingakIkusiNahi()){
					this.rankingakInprimatu();
					logroak.pistarenErregeaLogroa();
					logroak.logroenErregeaLogroa();
				}
				JokoaAmaitu();
			}
		}while(!partidaZuzena);

	}
	
	private boolean croupierrarekin(){
		System.out.println("Croupier-arekin jolastu nahi al duzu(e)? (B/E)");
		String bai = sc.next();
		boolean emaitza = false;
		try{
			emaitza = this.baiEdoEz(bai);
		}
		catch(baiEdoEzException e){
			System.out.println(e.getMessage());
			this.croupierrarekin();
		}
		return emaitza;
	}
	
	private void irabaziCroupierGabe(){
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		Ranking rankinga = jokalariak.rankingEzCroupier();
		if(!jokalariak.batBainoGehiagoIrabazi()){
			Jokalaria irabazlea = jokalariak.eskuHandienaKalkulatu();
			if (irabazlea != null){
				System.out.println("ZORIONAK " + irabazlea.getIzena() + ", "  + this.botea + " �-ko botea irabazi duzu!!! :3\n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":3");
				irabazlea.boteaHartu();
			} 
			else {
				System.out.println("Jokalari guztiak pasatu dira, beraz, ez dago irabazlerik :|\n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":|");
				jokalariak.apostuakBueltatu();
			}
		}
		else{
			System.out.println("2 jokalarik edo gehiagok berdindu dutenez, apostuak itzuliko dira :/\n");
			ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":/");
			jokalariak.apostuakBueltatu();
		}
	}
	
	private void irabaziCroupierrarekin(){
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		Ranking rankinga = jokalariak.rankingCroupier();
		int bakoitzari = (int)(this.getBotea())/(rankinga.irabazleKop());
		int azkenEskua = rankinga.azkenarenEskua();
		try{
			rankinga.irabazleakInprimatu();
			jokalariak.boteaBanatu(bakoitzari, azkenEskua);
		}
		catch(RankingException e){
			System.out.println(e.getMessage());
			jokalariak.apostuakBueltatu();
		}
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
		return this.botea;
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
		boolean emaitza = false;
		String bai = sc.next();
		try{
			emaitza = this.baiEdoEz(bai);
		}
		catch(baiEdoEzException e){
			System.out.println(e.getMessage());
			this.rankingakIkusiNahi();
		}
		return emaitza;
	}
	
	public void rankingakInprimatu(){
		ListaPartidak listaPartidak = ListaPartidak.getNireListaPartidak();
		listaPartidak.partidakIdatzi();
	}
	
	public boolean baiEdoEz(String pTeklatua) throws baiEdoEzException{
		if (pTeklatua.equals("B") || pTeklatua.equals("b")){
			return true;
		}
		else if(pTeklatua.equals("E") || pTeklatua.equals("e")){
			return false;
		}
		else{
			throw(new baiEdoEzException("Mesedez, sartu B (bai) edo E (ez).\n"));
		}
	}
}
