package com.example.Quiz.Application.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Quiz.Application.entity.QuizData;

@Repository
public class QuizRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<QuizData> getQuizzesByCategoryId(String categoryID) {
		try {
			List<QuizData> quizDataList = jdbcTemplate.query(
					"Select * from brain_battle.questions where categoryId = ? order by RAND() limit 5", 
					BeanPropertyRowMapper.newInstance(QuizData.class), categoryID);
			
			return quizDataList;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
