package com.ssosso.shorten.service;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service("urlService")
public class UrlService {
	
	@Resource(name = "urlRepository")
	private UrlRepository urlRepository;
	
	@Transactional
	public String generateShortenUrl(String originUrl) {
		Optional<Url> saveUrl = findUrlByOriginUrl(originUrl);
		if(saveUrl.isPresent()) {
			return saveUrl.get().shorten();
		}
		
		return urlRepository.save(new Url(originUrl)).shorten();
	}
	
	public Optional<Url> findUrlByOriginUrl(String originUrl) {
		return Optional.of(urlRepository.findUrlByOriginUrl(originUrl));
	}
}
