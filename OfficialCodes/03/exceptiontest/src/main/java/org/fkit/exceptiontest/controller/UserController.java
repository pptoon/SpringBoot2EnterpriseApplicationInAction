package org.fkit.exceptiontest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController extends BaseController{
	
	@RequestMapping("/login")
	public String login(String username) throws Exception{
		System.out.println("login()......");
		if(username == null ){
			throw new NullPointerException("用户名不存在!");
		}
		return "success";
	}
	

	
}
