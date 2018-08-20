package org.fkit.hellotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dlei(徐磊)
 * @Tel  18665616520
 * @Email dlei0009@163.com
 * @QQ 251425887
 * @Date 2017年7月23日下午8:04:21
 * @From http://www.fkjava.org 疯狂软件
 * @Version 1.0
 *   @RestController相当于SpringMVC中的 @Controller + @ResponseBody
 */
@RestController
public class HelloController {
	// 映射"/hello"请求
	@RequestMapping("/hello")
	public String hello(){
		return "Hello Spring Boot!";
	}
}
