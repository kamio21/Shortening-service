package com.ssosso.shorten.service;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import com.ssosso.shorten.utils.Base62Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {
	
	private UrlRepository urlRepository;

	/**
	 * 저장되어있는 URL인지 검색하고 없으면 축약된 URL을 생성하고 반환한다.
	 * @param originUrl
	 * @return
	 */
	@Transactional
	public Url generateShortUrl(String originUrl) {
		Optional<Url> saveUrl = findUrlByOriginUrl(originUrl);
		return saveUrl.orElseGet(() -> urlRepository.save(new Url(originUrl)));
	}

	/**
	 * 원래의 URL을 사용하여 축약한 URL 검색
	 * @param originUrl
	 * @return
	 */
	private Optional<Url> findUrlByOriginUrl(String originUrl) {
		return urlRepository.findByOriginUrl(originUrl);
	}

	/**
	 * 축약한 URL을 사용하여 원래의 URL 검색
	 * @param shortenValue
	 * @return
	 */
	public Optional<Url> findUrlByShortenValue(String shortenValue) {
		return urlRepository.findById(Base62Util.fromBase62(shortenValue));
	}
}
