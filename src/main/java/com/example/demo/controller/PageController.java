package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController
{
	@RequestMapping("/greeting")
	public String greeting (@RequestParam(value = "aku", required = false, defaultValue="sayang") String dimas, Model model) {
		model.addAttribute("aku", dimas);
		return "greeting";
	}
	
//	@RequestMapping("/greeting/{dimas}")
//	public String greetingPath (@PathVariable String dimas, Model model) {
//		model.addAttribute("aku", dimas);
//		return "greeting";
//	}
	
	@RequestMapping(value = {"/greeting", "greeting/{dimas}"})
	public String greetingPath (@PathVariable Optional<String> dimas, Model model) {
		if(dimas.isPresent()) {
			model.addAttribute("aku", dimas.get());
		}else {
			model.addAttribute("aku", "dengklek");
		}
		return "greeting";
	}
	
	@RequestMapping("/perkalian")
	public String perkalian (@RequestParam(value="a", required=false) Integer a, 
							@RequestParam(value="b", required=false) Integer b, Model model) 
	{
		if(a == null && b == null) {
			a = 0;
			b = 0;
			model.addAttribute("a", a);
			model.addAttribute("b", b);
		} else if (a == null) {
			a = 0;
			model.addAttribute("a", a);
			model.addAttribute("b", b);
		} else if (b == null) {
			b = 0;
			model.addAttribute("a", a);
			model.addAttribute("b", b);
		} else {
			model.addAttribute("a", a);
			model.addAttribute("b", b);
		}
		int hasil = a * b;
		model.addAttribute("hasil", hasil);
		
		return "perkalian";
		
	}
}
