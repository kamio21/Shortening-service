package com.ssosso.shorten.domain;

import javax.persistence.*;

@Entity
public class Url {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String originUrl;
	
	@Override
	public String toString() {
		return "Url{" +
				"id=" + id +
				", originUrl='" + originUrl + '\'' +
				'}';
	}
}
