package com.example.Quiz.Application.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Quiz.Application.entity.ResultData;
import com.example.Quiz.Application.entity.User;

@Repository
public class UserRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private String email = "";
	
	public boolean validateUser(String emailID, String password) {
		try {
			
			List<User> userData = jdbcTemplate.query(
					"Select * from brain_battle.login where emailID = ? and password = ?", 
					BeanPropertyRowMapper.newInstance(User.class), emailID, password);
	
			if (userData.size() > 0) {
				email = emailID;
				return true;
			} else {
				return false;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean addUser(String firstName, String lastName, String emailID, String password) {
		try {
			int max_id = jdbcTemplate.queryForObject("SELECT MAX(ID) AS max_id FROM login", Integer.class);
			jdbcTemplate.update("Insert into brain_battle.login(ID, firstName, lastName, emailID, password) values(?,?,?,?,?)", max_id+1, firstName, lastName, emailID, password);
			return true;
		} catch(Exception ex) {
			return false;
		}	
	}
	
	public boolean addResults(String categoryId, String score) {
		try {
			jdbcTemplate.update("Insert into brain_battle.results(userID, categoryId, score) values(?,?,?)",email, categoryId, score);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public List<ResultData> getResults() {
		try {
			List<ResultData> resultData = jdbcTemplate.query("SELECT c.categoryName, r.score FROM brain_battle.results r JOIN brain_battle.category c ON r.categoryId = c.categoryId WHERE r.userID = ?;"
					,BeanPropertyRowMapper.newInstance(ResultData.class), email);
			return resultData;
		}
		catch(Exception e) {
			return null;
		}
	}

}
