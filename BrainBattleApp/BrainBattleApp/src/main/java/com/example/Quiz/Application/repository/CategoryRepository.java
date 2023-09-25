package com.example.Quiz.Application.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Quiz.Application.entity.CategoryData;
import com.example.Quiz.Application.entity.ResultData;

@Repository
public class CategoryRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<CategoryData> getAllCategories() {
		try {			
			List<CategoryData> categoryDataList = jdbcTemplate.query("SELECT * FROM brain_battle.category", BeanPropertyRowMapper.newInstance(CategoryData.class));
			return categoryDataList;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	

}
