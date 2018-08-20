package org.fkit.devtoolstest.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController2 {
	
	@RequestMapping("/hellonew")
	public String hello(){
		System.out.println("测试spring-boot-devtools new热部署");
		return "hellonew,devtools new.....!";
	}
	
}