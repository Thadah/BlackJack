package org.pmoo.blackjack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JokalariaTest {
	
	private Jokalaria j1;

	@Before
	public void setUp() throws Exception {
		j1 = new Jokalaria("Ludopata");
	}

	@After
	public void tearDown() throws Exception {
		j1 = null;
	}

	@Test
	public void testKartaEskatu() throws InterruptedException{
		assertEquals(j1.kartaKop(), 0);
		j1.kartaEskatu();
		System.out.println();
		assertEquals(j1.kartaKop(), 1);
	}
	
	@Test
	public void testApostuaEgin() throws ApostuException, InterruptedException{
		/*
		Apostatzean 3 kasu gerta daitezke:
			1: Ondo apostatzea (Diru nahikoa izatea eta lehendik apostatu duten kopuruaren berdina edo handiagoa izatea)
				1.1: Baliteke jokalariak All-In egitea nahi izatea, kasu horretan aukera hori baiztatzea eskatuko zaio
			2: Apostatzean 0 idaztea, partida hau ez duzula jolastu nahi adieraziz
			3: 0 ez apostatzea eta txarto apostatzea. Kasu honetan beste zifra bat idazteko eskatuko da
		*/
		
		assertEquals(j1.getDirua(), 500);
		j1.apostuaEgin(); 
		//Jokalaria apostatzen lehenengoa da, beraz 
		//apostatu nahi badu ez du kopuru minimo bat apostatu behar
		System.out.println("Aposatu eta gero " + j1.getIzena() + "-(r)en dirua " + j1.getDirua() + "\u20AC-koa da");
		
		//Orain partida berria hasiko dugu, baina lehendik beste jokalari batek apostatuko du.
		//Honela bigarrenak apostu minimo bat egin beharko du partida jolastu nahi badu.
		BlackJack.getNireBlackJack().setBotea(0);
		BlackJack.getNireBlackJack().setApostuMax(0);
		
		System.out.println("\nPartida berria hasiko da\n");
		Thread.sleep(2000);
		
		j1 = new Jokalaria("Ludopata");
		Jokalaria j2 = new Jokalaria("Beste ludopata bat");
		j1.apostuaEgin();
		System.out.println("Aposatu eta gero " + j1.getIzena() + "-(r)en dirua " + j1.getDirua() + "\u20AC-koa da\n");
		j2.apostuaEgin(); 
		System.out.println("Aposatu eta gero " + j2.getIzena() + "-(r)en dirua " + j2.getDirua() + "\u20AC-koa da\n");
		System.out.println("Momentuz mahaiko botea " + BlackJack.getNireBlackJack().getBotea() + "\u20AC-koa da\n\n");
		
	}
	
	
	@Test
	public void testTxanda() throws InterruptedException, ApostuException{
		System.out.println("Hasierako apostua:");
		j1.apostuaEgin();
		/*
		Hasieran erretiratu ez diren jokalarek 2 karta jasotzen dituzte gehiagoren bat 
		nahi duten galdetu baino lehen
		*/
		j1.kartaEskatu();
		j1.kartaEskatu();
		
		/*
		 Txandaren hasieran, 2 kasu egon daitezke:
		 	1: Eskuaren balioa 21 izatekotan, ez dio galdetuko jokalariari ea karta bat nahi duen
		 	2: 21 baino txikiagoa izatekotan txanda normala geratuko da:
		 		2.1: Doblatzea aukeratu bada (hau da, diru nahikoa izatea apostua bikoizteko), irabaztekotan 
		 			 normalean lortuko zuen diruaren bikoitza izango da
		 			 2.1.1: Azkenik kartak eskatzeko ordua da, eta jokalariak pasatu arte (eskua > 21) edo plantatu arte eskatu ahal izango du
		*/
		j1.txanda();
	}
	
	@Test
	public void testApostuaIkusi() throws InterruptedException, ApostuException{
		
		j1.apostuaEgin();
		System.out.println("Aposatu eta gero " + j1.getIzena() + "-(r)en dirua " + j1.getDirua() + "\u20AC-koa da\n");
		
		Jokalaria j2 = new Jokalaria("Beste ludopata bat");
		j2.apostuaEgin();
		System.out.println("Aposatu eta gero " + j2.getIzena() + "-(r)en dirua " + j2.getDirua() + "\u20AC-koa da\n");

		/*
		Bigarren jokalariak lehenengoaren apostua igo badu, lehenengoari galdetuko zaio ea partidan jarraitu nahi duen.
		Jarraitzekotan, lehenengo jokalariaren apostua bigarrenaren berdina izango da (diru-aldea gehituz)
		*/
		
		j1.apostuaIkusi();
		System.out.println("Amaieran " + j1.getIzena() + "-(r)en dirua " + j1.getDirua() + "\u20AC-koa da\n");
	}


	
	
	
	
	
	
	
}