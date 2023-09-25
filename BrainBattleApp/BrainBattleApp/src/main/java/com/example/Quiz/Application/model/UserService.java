package com.example.Quiz.Application.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Quiz.Application.entity.ResultData;
import com.example.Quiz.Application.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean validateUser(String emailID, String password) {
		return userRepository.validateUser(emailID, password);
	}
	
	public boolean addUser(String firstName, String lastName, String emailID, String password) {
		return userRepository.addUser(firstName, lastName, emailID, password);
	}
	
	public boolean addResults( String categoryId, String score) {
		return userRepository.addResults(categoryId,score);
	}
	
	public List<ResultData>  getResults() {
		return userRepository.getResults();
	}
}
