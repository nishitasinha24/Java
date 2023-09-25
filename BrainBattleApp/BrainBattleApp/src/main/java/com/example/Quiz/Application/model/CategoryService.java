package com.example.Quiz.Application.model;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Quiz.Application.entity.CategoryData;
import com.example.Quiz.Application.entity.ResultData;
import com.example.Quiz.Application.repository.CategoryRepository;

@Component
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryData> getAllCategories() {
		return categoryRepository.getAllCategories();
	}

}
