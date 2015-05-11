package org.pmoo.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pmoo.blackjack.Jokalaria;
import org.pmoo.blackjack.Ranking;

public class RankingTest {
	private Ranking r1;
	private Jokalaria jok,jok2,jok3;
	@Before
	public void setUp() throws Exception {
		r1 = new Ranking();
		jok= new Jokalaria("Juan");
		jok2= new Jokalaria("Eka");
		jok3= new Jokalaria("Marcos");
	}

	@After
	public void tearDown() throws Exception {
		r1 = null;
		jok = null;
		jok2 = null;
		jok3 = null;
	}

	@Test
	public void testRankingeanSartu() {
		assertEquals(r1.irabazleKop(),0);
		r1.rankingeanSartu(jok);
		assertEquals(r1.irabazleKop(),1);
	}

	@Test
	public void testIrabazleKop() {
		assertEquals(r1.irabazleKop(),0);
		r1.rankingeanSartu(jok);
		assertEquals(r1.irabazleKop(),1);
		
	}


	@Test
	public void testPosizioan() {
		r1.rankingeanSartu(jok);
		r1.rankingeanSartu(jok2);
		r1.rankingeanSartu(jok3);
		assertEquals(r1.posizioan(2).getIzena(),jok3.getIzena());
	}

	@Test
	public void testNorDaLehenengoa() {
		r1.rankingeanSartu(jok);
		r1.rankingeanSartu(jok2);
		r1.rankingeanSartu(jok3);
		assertEquals(r1.norDaLehenengoa().getIzena(),jok.getIzena());
	}

	@Test
	public void testRankingeanDago() {
		r1.rankingeanSartu(jok);
		r1.rankingeanSartu(jok2);
		r1.rankingeanSartu(jok3);
		assertEquals(r1.rankingeanDago(jok2),true);
	}

}
