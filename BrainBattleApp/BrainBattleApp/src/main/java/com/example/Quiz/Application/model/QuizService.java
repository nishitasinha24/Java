package com.example.Quiz.Application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Quiz.Application.entity.QuizData;
import com.example.Quiz.Application.repository.QuizRepository;

@Component
public class QuizService {
	@Autowired
	private QuizRepository quizRepository;
	
	public List<QuizData> getQuizzesByCategory(String categoryId) {
		return quizRepository.getQuizzesByCategoryId(categoryId);
	}

}
