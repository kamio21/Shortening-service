package com.ssosso.shorten.repository;

import com.ssosso.shorten.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
	Optional<Url> findUrlByOriginUrl(String originUrl);
}
