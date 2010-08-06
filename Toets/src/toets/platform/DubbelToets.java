package toets.platform;

import static org.junit.Assert.*;

import org.junit.Test;

import platform.Dubbel;

public class DubbelToets {

	@Test
	public void testKryEersteEnTweede() {
		Dubbel dubbel = new Dubbel("eerste", "tweede");
		assertEquals("eerste", dubbel.kryEerste());
		assertEquals("tweede", dubbel.kryTweede());
	}

}
