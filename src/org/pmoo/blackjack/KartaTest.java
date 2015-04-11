package org.pmoo.blackjack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KartaTest {
	Karta k1, k2;

	@Before
	public void setUp() throws Exception {
		k1 = new Karta(10,1);
		k2 = new Karta(1,1);
	}

	@After
	public void tearDown() throws Exception {
		k1 = null;
		k2 = null;
	}
	
	@Test
	public void testBatekoa() {
		assertFalse(k1.batekoa());
		assertTrue(k2.batekoa());
	}
	
	@Test
	public void testGetKartarenBalioa() {
		assertSame(k1.getKartaBalioa(),10);
	}
}
