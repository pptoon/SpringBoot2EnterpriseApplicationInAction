package org.fkit.thymeleaftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(){
		System.out.println("IndexController index方法被调用......");
		return "index";
	}
	
}
