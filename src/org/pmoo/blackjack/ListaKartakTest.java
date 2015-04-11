package org.pmoo.blackjack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListaKartakTest {
	
	private ListaKartak l1;

	@Before
	public void setUp() throws Exception {
		l1 = new ListaKartak();
	}

	@After
	public void tearDown() throws Exception {
		l1.erreseteatu();
		l1 = null;
	}

	@Test
	public void testKartenBalioaLortu() {
		Karta k1 = new Karta(5,2);
		Karta k2 = new Karta(6,3);
		l1.gehituKarta(k1);
		l1.gehituKarta(k2);
		assertEquals(l1.kartenBalioaLortu(),11);
		l1.erreseteatu();
		
		k1 = new Karta(1,1);
		k2 = new Karta(1,2);
		l1.gehituKarta(k1);
		l1.gehituKarta(k2);
		assertEquals(l1.kartenBalioaLortu(),2);
		l1.erreseteatu();
		
		k1 = new Karta(10,3);
		k2 = new Karta(12,1);
		l1.gehituKarta(k1);
		l1.gehituKarta(k2);
		assertEquals(l1.kartenBalioaLortu(),20);
		
		Karta k3 = new Karta(5,4);
		l1.gehituKarta(k3);
		assertEquals(l1.kartenBalioaLortu(),25);
	}

}
