package org.pmoo.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pmoo.blackjack.Baraja;
import org.pmoo.blackjack.Karta;

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

	@Test
	public void testBaraja(){
		//Barajaren hasierako tamaina (52).
		assertSame(b1.tamaina(),52);
		
		//Karta bat kentzen diogu (52 - 1 = 51).
		Karta kartaBat = b1.emanKarta();
		System.out.println(kartaBat.kartaIdatzi()); //Honekin konprobatzen dugu barajatu ondo dagoela, kartak desberdinak dira run bakoitzean.
		assertSame(b1.tamaina(),51);
		
		//Baraja erreseteatzerakoan berriro 52 karta dira.
		b1.erreseteatu();
		b1 = Baraja.getBaraja();
		assertSame(b1.tamaina(), 52);
	}

}
