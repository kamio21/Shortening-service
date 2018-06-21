package com.ssosso.shorten.repository;

import com.ssosso.shorten.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
	Url findUrlByOriginUrl(String originUrl);
}
