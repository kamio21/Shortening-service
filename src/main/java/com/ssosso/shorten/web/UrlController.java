package com.ssosso.shorten.web;

import com.ssosso.shorten.domain.Url;
import com.ssosso.shorten.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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
	
	@GetMapping("/{id}")
	public String redirect(HttpServletResponse response, @PathVariable String id) {
		Optional<Url> url = urlService.findUrlByShortenValue(id);
		if(url.isPresent()) {
			return "redirect:" + url.get().getOriginUrl();
		}
		
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return "error/wrong_shorten";
	}
}
