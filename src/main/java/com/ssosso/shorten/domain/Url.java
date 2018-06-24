package com.ssosso.shorten.domain;

import com.ssosso.shorten.utils.Base62Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Url {
	static final private String SITE_HOST = "sso.so";
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String originUrl;
	
	public Url(String originUrl) {
		this.originUrl = originUrl;
	}
	
	public String shorten() {
		return SITE_HOST + "/" + getShortenValue();
	}
	
	public String getShortenValue() {
		return Base62Util.toBase62(id);
	}
	
	@Override
	public String toString() {
		return "Url{" +
				"id=" + id +
				", originUrl='" + originUrl + '\'' +
				'}';
	}
}
