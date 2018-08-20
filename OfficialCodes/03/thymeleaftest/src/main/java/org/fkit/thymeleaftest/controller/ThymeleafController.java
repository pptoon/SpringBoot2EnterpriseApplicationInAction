package org.fkit.thymeleaftest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.fkit.thymeleaftest.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ThymeleafController {
	
	/*
	 * 保存数据到作用范围域，用于测试Thymeleaf表达式访问数据
	 * */
	@RequestMapping("/regexptest")
	public String regexptest(
			HttpServletRequest request,
			HttpSession session){
		System.out.println(request.getParameter("loginName"));
		System.out.println(request.getParameter("password"));
		// 保存数据到request作用范围域当中
		request.setAttribute("book", "疯狂Spring Boot讲义");
		// 保存数据到session作用范围域当中
		session.setAttribute("school", "疯狂软件");
		// 保存数据到ServletContext（application）作用范围域当中
		request.getServletContext().setAttribute("name", "Thymeleaf模板引擎");
		return "success1";
	}

	/*
	 * 保存数据到作用范围域，用于测试Thymeleaf的条件判断
	 * */
	@RequestMapping("/iftest")
	public String iftest(WebRequest webRequest){
		// 保存数据到request作用范围域，Spring MVC更推荐使用WebRequest
		webRequest.setAttribute("username", "fkit", RequestAttributes.SCOPE_REQUEST);
		webRequest.setAttribute("age", 21, RequestAttributes.SCOPE_REQUEST);
		webRequest.setAttribute("role", "admin", RequestAttributes.SCOPE_REQUEST);
		return "success2";
	}
	
	/*
	 * 保存数据到作用范围域，用于测试Thymeleaf的循环获取数据
	 * */
	@RequestMapping("/eachtest")
	public String eachtest(WebRequest webRequest){
		// 模拟数据库数据保存到List集合
		List<Book> books = new ArrayList<>();
		books.add(new Book(1, "疯狂Java讲义", "java.jpg", "李刚 编著", 109.00));
		books.add(new Book(2, "轻量级Java EE企业应用实战", "ee.jpg", "李刚 编著", 108.00));
		books.add(new Book(3, "Spring+MyBatis应用实战", "SpringMyBatis.jpg", "疯狂软件 编著", 58.00));
		books.add(new Book(4, "疯狂Android讲义", "android.jpg", "李刚 编著", 108.00));
		books.add(new Book(5, "疯狂Ajax开发", "ajax.jpg", "李刚 编著", 79.00));
		// 保存数据到request作用范围域
		webRequest.setAttribute("books", books, RequestAttributes.SCOPE_REQUEST);
		return "success3";
	}
	
}




