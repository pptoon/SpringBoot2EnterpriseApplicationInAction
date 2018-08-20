package org.fkit.devtoolstest.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("测试spring-boot-devtools热部署");
		return "hello,devtools.....!";
	}
	
}