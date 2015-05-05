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
		
		l1 = null;
	}

	@Test
	public void testListaKartak() {
		
		assertEquals(l1.tamaina(), 0);
		
		Karta k1 = new Karta(7, 1); //Zazpiko pika
		l1.gehituKarta(k1);
		
		assertEquals(l1.tamaina(), 1);
		assertEquals(l1.kartenBalioaLortu(), 7);
		
		Karta k2 = new Karta(1, 3); //Bateko erronboa
		l1.gehituKarta(k2);
		assertEquals(l1.kartenBalioaLortu(), 18); //Normalean, bateko bat jasotzean 11 balioko du
		
		
		Karta k3 = new Karta(1, 2); //Bateko hirusta
		l1.gehituKarta(k3);
		assertEquals(l1.kartenBalioaLortu(), 9); 
		//Hala ere, karten balioa 21 baino handiagoa bada, batekoren bat egotekotan 
		//1 balioko dute jokalariaren eskua 21 baino handiagoa ez izateko
	}

}
