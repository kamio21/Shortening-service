package com.ssosso.shorten.service;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.repository.UrlRepository;
import com.ssosso.shorten.utils.Base62Util;
import com.ssosso.shorten.valid.UrlTypeValidation;
import com.ssosso.shorten.valid.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {
	
	private UrlRepository urlRepository;

	/**
	 * URL Validation 검사, 기존에 저장된 URL인지 탐색 후 축약된 URL을 생성하여 반환
	 * @param originUrl
	 * @return
	 */
	@Transactional
	public Url generateShortUrl(String originUrl) {
		boolean isNotValid = Arrays.stream(new Validation[]{new UrlTypeValidation()})
				.noneMatch(validation -> validation.valid(originUrl));
		if(isNotValid) throw new IllegalArgumentException("잘못된 URL 타입입니다.");

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
