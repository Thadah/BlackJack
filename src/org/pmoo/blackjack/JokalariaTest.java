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
		j1.kartakItzuli();
		j1 = null;
	}

	@Test
	public void testApostuaEgin() {
		int hasierakoDirua = j1.getDirua();
		j1.apostuaEgin();
		assertEquals(hasierakoDirua, j1.getApostua()+j1.getDirua());
	}
}
