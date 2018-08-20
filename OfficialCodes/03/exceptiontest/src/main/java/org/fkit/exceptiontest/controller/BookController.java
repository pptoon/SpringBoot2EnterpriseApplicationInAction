package org.fkit.exceptiontest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController extends BaseController{
	
	@RequestMapping("/find")
	public String find() throws Exception{
		System.out.println("find()......");
		int i = 5/0;
		return "success";
	}

}
