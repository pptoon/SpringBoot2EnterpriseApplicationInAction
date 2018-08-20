package org.fkit.springbootmybatistest.controller;
import java.util.List;

import javax.annotation.Resource;

import org.fkit.springbootmybatistest.bean.User;
import org.fkit.springbootmybatistest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController {
	
	// 注入UserService
	@Resource
	private UserService userService;
	
	@RequestMapping("/insertUser")
	public String insertUser(User user){
		return "插入数据["+userService.insertUser(user)+"]条";
	}
	
	@RequestMapping("/insertGetKey")
	public User insertGetKey(User user) {
		userService.insertGetKey(user);
		return user ;
	}
	
	@RequestMapping("/selectByUsername")
	public User selectByUsername(String username){
		return userService.selectByUsername(username);
	}
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("/update")
	public void update(User user) {
		userService.update(user);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer id) {
		userService.delete(id);
	}
}
