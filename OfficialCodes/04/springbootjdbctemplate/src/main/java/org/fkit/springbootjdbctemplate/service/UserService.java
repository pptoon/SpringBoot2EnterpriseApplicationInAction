package org.fkit.springbootjdbctemplate.service;

import java.util.List;

import javax.annotation.Resource;

import org.fkit.springbootjdbctemplate.bean.User;
import org.fkit.springbootjdbctemplate.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	// 注入UserRepository
	@Resource
	private UserRepository userRepository;
	
	public int insertUser(){
		return userRepository.insertUser();
	}
	
	public User selectByUsername(String username){
		return userRepository.selectByUsername(username);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User insertGetKey(User user) {
		return userRepository.insertGetKey(user);
	}
	
	public void update(User user) {
		userRepository.update(user);
	}
	
	public void delete(Integer id) {
		userRepository.delete(id);
	}
}
