package com.ssosso.shorten.web;

import com.ssosso.shorten.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class UrlController {
	@Resource(name = "urlService")
	private UrlService urlService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	public String shorten(String originUrl, Model model) {
		model.addAttribute("originUrl", originUrl);
		model.addAttribute("shortUrl", urlService.generateShortUrl(originUrl));
		return "index";
	}
}
