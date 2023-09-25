package com.example.Quiz.Application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String validateLogin() {
		return "login";
	}
	
	@GetMapping("/getcategories")
	public String getCategories() {
		return "quizcategory";
	}
	
	@GetMapping("/getquiz")
	public String getQuiz(@RequestParam String categoryId, RedirectAttributes redirectAttributes) {
		return "quizdata";
	}
	
}
