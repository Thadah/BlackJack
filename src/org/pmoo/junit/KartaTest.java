package org.pmoo.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pmoo.blackjack.Karta;

public class KartaTest {
	
	private Karta pika;
	private Karta hirusta;
	private Karta erronbo;
	private Karta bihotz;

	@Before
	public void setUp() throws Exception {
		pika = new Karta(1, 1);
		hirusta = new Karta(5, 2);
		erronbo = new Karta(11, 3);
		bihotz = new Karta(13, 4);

	}

	@After
	public void tearDown() throws Exception {
		pika = null;
		hirusta = null;
		erronbo = null;
		bihotz = null;
	}

	@Test
	public void testIdatziPalua(){
		
		assertEquals(pika.idatziPalua(), "Pika");
		assertEquals(hirusta.idatziPalua(), "Hirusta");
		assertEquals(erronbo.idatziPalua(), "Erronbo");
		assertEquals(bihotz.idatziPalua(), "Bihotz");
		
	}
	
	@Test
	public void testBatekoa(){
		
		assertTrue(pika.batekoa());
		assertFalse(hirusta.batekoa());
		
	}
	
	@Test
	public void testKartaIdatzi(){
		
		pika.kartaIdatzi();
		hirusta.kartaIdatzi();
		erronbo.kartaIdatzi();
		bihotz.kartaIdatzi();
	}
	
	@Test
	public void testFiguraDa(){
		
		assertFalse(pika.figuraDa());
		assertFalse(hirusta.figuraDa());
		assertTrue(erronbo.figuraDa());
		assertTrue(bihotz.figuraDa());
	}
}