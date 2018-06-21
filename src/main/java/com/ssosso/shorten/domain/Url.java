package com.ssosso.shorten.domain;

import javax.persistence.*;

@Entity
public class Url {
	static final private long SHORTEN_BASE = 62;
	static final private char[] BASE62_TABLE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String originUrl;
	
	
	public Url() {
	
	}
	
	public Url(long id, String originUrl) {
		this.id = id;
		this.originUrl = originUrl;
	}
	
	public String shorten() {
		long value = this.id;
		StringBuilder builder = new StringBuilder();
		
		do {
			int remainder = Math.toIntExact(value % SHORTEN_BASE);
			builder.insert(0, BASE62_TABLE[remainder]);
			value = value/SHORTEN_BASE;
		} while (value > SHORTEN_BASE);
		
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return "Url{" +
				"id=" + id +
				", originUrl='" + originUrl + '\'' +
				'}';
	}
}
