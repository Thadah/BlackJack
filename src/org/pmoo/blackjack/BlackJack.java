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
		main.playAudio();
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
		System.out.println("(Jokalari bakoitzak predeterminatuki 500 Jauregi Points ditu)\n");
		boolean partidaZuzena = false;
		do{		
			if(jolastuNahi.equals("B")){
				try{
					
					Baraja.getBaraja().erreseteatu();
					System.out.println("Barajatzen...");
					Audio shuffle = new Audio("Shuffling.mp3");
					shuffle.playAudio();
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
					partidaZuzena = true;
				}
			}
			else{
				if(this.rankingakIkusiNahi()){
					this.rankingakInprimatu();
					logroak.pistarenErregeaLogroa();
					logroak.logroenErregeaLogroa();
					partidaZuzena = true;
				}
				JokoaAmaitu();
			}
		}while(!partidaZuzena);
		System.out.println("\nSakatu enter programa ixteko");
		this.enterItxaron();
		System.exit(0); 
	}
	
	private boolean croupierrarekin(){
		System.out.println("Croupier-arekin jolastu nahi al duzu(e)? (B/E)");
		return this.baiEdoEz();
	}
	
	private void irabaziCroupierGabe() throws InterruptedException{
		ListaJokalariak jokalariak = ListaJokalariak.getNireListaJokalariak();
		jokalariak.rankingEzCroupier();
		if(!jokalariak.batBainoGehiagoIrabazi()){
			Jokalaria irabazlea = jokalariak.eskuHandienaKalkulatu();
			if (irabazlea != null){
				System.out.println("ZORIONAK " + irabazlea.getIzena() + ", "  + this.botea + " Jauregi Points-ko botea irabazi duzu!!! :3\n");
				ListaEmotikonoak.getNireListaEmotikonoak().gehituEmotikonoa(":3");
				Audio woohoo = new Audio("WooHoo.mp3");
				woohoo.playAudio();
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
		try{
			int bakoitzari = (int)(this.getBotea())/(rankinga.irabazleKop());
			try{
				rankinga.irabazleakInprimatu();
				jokalariak.boteaBanatu(bakoitzari, rankinga);
			}
			catch(RankingException e){
				System.out.println(e.getMessage());
				jokalariak.apostuakBueltatu();
			}
		}
		catch(ArithmeticException e){
			System.out.println("Ez dago irabazlerik, beraz apostuak bueltatuko dira");
			jokalariak.apostuakBueltatu();
		}
	}

	
	private void irabazleaKalkulatu() throws InterruptedException{
		if(!BlackJack.croupierrarekin){
			this.irabaziCroupierGabe();
		}
		else{
			this.irabaziCroupierrarekin();
		}
		this.botea = 0;
	}
	
	private void JokoaAmaitu(){
		//sk.close();
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
		return this.baiEdoEz();
	}
	
	public void rankingakInprimatu(){
		ListaPartidak listaPartidak = ListaPartidak.getNireListaPartidak();
		listaPartidak.partidakIdatzi();
	}
	
	public boolean baiEdoEz(){
		boolean emaitza = false;
		boolean ondo = false;
		do{
			try{
				String baiEdoEz = sc.next();
				if (baiEdoEz.equals("B") || baiEdoEz.equals("b")){
					emaitza = true;
					ondo = true;
				}
				else if(baiEdoEz.equals("E") || baiEdoEz.equals("e")){
					emaitza = false;
					ondo = true;
				}		
				else{
					throw(new baiEdoEzException("Mesedez, sartu B (bai) edo E (ez).\n"));
				}		
			}
			catch(baiEdoEzException e){
				System.out.println(e.getMessage());
			}
		}while(!ondo);
		return emaitza;
	}
	
}
