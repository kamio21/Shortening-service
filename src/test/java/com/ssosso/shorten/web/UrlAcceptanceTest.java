package com.ssosso.shorten.web;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import com.ssosso.shorten.utils.Base62Util;
import com.ssosso.support.test.AcceptanceTest;
import com.ssosso.support.utils.HtmlFormDataBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.transaction.AfterTransaction;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlAcceptanceTest extends AcceptanceTest {
	@Autowired
	private UrlRepository urlRepository;
	
	static final private String DEFAULT_ORIGIN_URL = "https://ssossohow.tistory.com";
	static private Url defaultUrl;
	
	@Before
	public void setUp() throws Exception {
		IntStream.range(0, 100)
				.forEach(i -> urlRepository.save(new Url(DEFAULT_ORIGIN_URL + i)));
		defaultUrl = urlRepository.save(new Url(DEFAULT_ORIGIN_URL));
	}
	
	@Test
	public void 이전에_등록한_originUrl을_입력하면_동일한_shortUrl이_나오는가() {
		ResponseEntity<String> response = template().postForEntity("/",
				HtmlFormDataBuilder.urlEncodedForm()
					.addParameter("originUrl", DEFAULT_ORIGIN_URL)
					.build(),
				String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains(defaultUrl.shorten()));
	}
	
	@Test
	public void 등록한_shortUrl접속하면_정상리다이렉트되는가() {
		ResponseEntity<String> response = template().getForEntity("/" + defaultUrl.getShortenValue(), String.class);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(DEFAULT_ORIGIN_URL, response.getHeaders().getLocation().toString());
	}
	
	@Test
	public void 등록하지않은_shortUrl접속하면_에러페이지이동하는가() {
		long maxId = urlRepository.findAll().stream()
				.mapToLong(url -> url.getId())
				.max()
				.orElse(0);
		
		ResponseEntity<String> response = template().getForEntity("/" + Base62Util.toBase62(maxId + 100), String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertTrue(response.getBody().contains("등록되지 않은 URL입니다"));
	}
}
