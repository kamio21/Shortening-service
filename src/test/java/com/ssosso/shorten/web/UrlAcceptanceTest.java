package com.ssosso.shorten.web;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import com.ssosso.support.test.AcceptanceTest;
import com.ssosso.support.utils.HtmlFormDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlAcceptanceTest extends AcceptanceTest {
	@Autowired
	private UrlRepository urlRepository;
	
	static final private String DEFAULT_ORIGIN_URL = "https://ssossohow.tistory.com";
	private Url defaultUrl;
	
	@Before
	public void setUp() throws Exception {
		IntStream.range(0, 100)
				.forEach(i -> urlRepository.save(new Url(DEFAULT_ORIGIN_URL + i)));
		defaultUrl = urlRepository.save(new Url(DEFAULT_ORIGIN_URL));
	}
	
	@Test
	public void 이전에_등록한_url을_입력하면_동일한_shortUrl이_나오는가() {
		ResponseEntity<String> response = template().postForEntity("/",
				HtmlFormDataBuilder.urlEncodedForm()
					.addParameter("originUrl", DEFAULT_ORIGIN_URL)
					.build(),
				String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains(defaultUrl.shorten()));
	}
}
