package org.pmoo.blackjack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BarajaTest {
	Baraja b1;

	@Before
	public void setUp() throws Exception {
		b1 = Baraja.getBaraja();
	}

	@After
	public void tearDown() throws Exception {
		b1 = null;
	}
/*
	@Test
	public void testEmanKarta(){
		assertSame(b1.tamaina(),52);
		Karta kartaBat = b1.emanKarta();
		kartaBat.idatziPalua(); //Honekin konprobatzen dugu barajatu ondo dagoela, kartak desberdinak dira run bakoitzean.
		assertSame(b1.tamaina(),51);
	}
	
	public void erreseteatu(){
		assertSame(b1.tamaina(),52);
		b1.emanKarta();
		assertSame(b1.tamaina(),51);
		b1.erreseteatu();
		assertSame(b1.tamaina(),52);
	}
	*/
	@Test
	public void testBarajaInprimatu(){
		b1.barajaInprimatu();
	}
}
