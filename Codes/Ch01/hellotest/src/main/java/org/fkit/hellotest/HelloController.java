/**
 * 
 */
package org.fkit.hellotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fatcat
 * @date 2018年8月17日上午4:13:58
 */
@RestController
public class HelloController {
	@RequestMapping("/hello")
	public String hello()
	{
		return "Hello Spring Boot ";
	}
}
