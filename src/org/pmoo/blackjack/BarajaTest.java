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

	@Test
	public void testEmanKarta(){
			assertSame(b1.tamaina(),52);
			System.out.println(b1.tamaina());
			Karta kartaBat = b1.emanKarta();
			assertSame(b1.tamaina(),51);
			System.out.println(b1.tamaina());
		}
	
	@Test
	public void testBarajatu(){
		System.out.println(b1.);
	}
}
