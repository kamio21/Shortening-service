package com.ssosso.shorten.web;

import com.ssosso.shorten.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class UrlController {
	private UrlService urlService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	public String shorten(@RequestParam String originUrl, Model model) {
		model.addAttribute("originUrl", originUrl);
		model.addAttribute("shortUrl", urlService.generateShortUrl(originUrl).shorten());
		return "index";
	}
}
