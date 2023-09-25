package com.example.Quiz.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quiz.Application.entity.CategoryData;
import com.example.Quiz.Application.entity.QuizData;
import com.example.Quiz.Application.entity.ResultData;
import com.example.Quiz.Application.model.CategoryService;
import com.example.Quiz.Application.model.QuizService;
import com.example.Quiz.Application.model.UserService;


@RestController
public class QuizControllerRest {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getQuizData", method = RequestMethod.GET)
	public List<QuizData> getQuizData(@RequestParam String categoryId) {
		return quizService.getQuizzesByCategory(categoryId);
	}
	
	@RequestMapping(value = "/getCategoryData", method = RequestMethod.GET)
	public List<CategoryData> getCategoryData() {
		return categoryService.getAllCategories();
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public boolean validate(@RequestParam String emailID, @RequestParam String password) {
		return userService.validateUser(emailID, password);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public boolean addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String emailID, @RequestParam String password) {
		return userService.addUser(firstName, lastName, emailID, password);
	}
	
	@RequestMapping(value = "/addResult", method = RequestMethod.GET)
	public boolean addResult(@RequestParam String categoryId, @RequestParam String score) {
		return userService.addResults(categoryId, score);
	}
	
	@RequestMapping(value = "/getResult", method = RequestMethod.GET)
	public List<ResultData>  getResults() {
		return userService.getResults();
	}

}
