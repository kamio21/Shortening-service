package com.ssosso.shorten.service;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import com.ssosso.shorten.utils.Base62Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UrlService {
	
	private UrlRepository urlRepository;
	
	@Transactional
	public Url generateShortUrl(String originUrl) {
		Optional<Url> saveUrl = findUrlByOriginUrl(originUrl);
		if(saveUrl.isPresent()) {
			return saveUrl.get();
		}
		
		return urlRepository.save(new Url(originUrl));
	}
	
	public Optional<Url> findUrlByOriginUrl(String originUrl) {
		return urlRepository.findByOriginUrl(originUrl);
	}
	
	public Optional<Url> findUrlByShortenValue(String shortenValue) {
		return urlRepository.findById(Base62Util.fromBase62(shortenValue));
	}
}
