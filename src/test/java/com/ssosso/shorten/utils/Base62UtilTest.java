package com.ssosso.shorten.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Base62UtilTest {
	@Test
	public void toBase62테스트() {
		assertEquals("biH6xg", Base62Util.toBase62(1_042_432_728L));
	}
	
	@Test
	public void fromBase테스트() {
		assertEquals(1_042_432_728L, Base62Util.fromBase62("biH6xg"));
	}
}

