package org.fkit.hellotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：<br/>
 * 说明：<br/> @RestController相当于SpringMVC中的 @Controller + @ResponseBody
 * @author Finersoft
 * @date 2018年8月22日
 */
@RestController
public class HelloController {
	
		/** 映射"/hello"请求
		 * @return 
		 * */
		@RequestMapping("/hello")
		public String hello(){
			return "Hello Spring Boot,9999!";
		}
}
