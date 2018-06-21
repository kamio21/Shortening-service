package com.ssosso.support.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class AcceptanceTest {
	@Autowired
	private TestRestTemplate template;
	
	public TestRestTemplate template() {
		return template;
	}
}
