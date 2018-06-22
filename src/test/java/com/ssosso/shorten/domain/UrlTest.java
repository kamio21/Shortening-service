package com.ssosso.shorten.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlTest {
	@Test
	public void shorten테스트() {
		Url url = new Url(1_042_432_728L, "https://ssossohow.tistory.com");
		
		assertEquals("sso.so/biH6xg", url.shorten());
	}
}
