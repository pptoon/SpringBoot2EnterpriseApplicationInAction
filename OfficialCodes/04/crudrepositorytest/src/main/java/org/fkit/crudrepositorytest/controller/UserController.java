package org.fkit.crudrepositorytest.controller;

import javax.annotation.Resource;

import org.fkit.crudrepositorytest.bean.User;
import org.fkit.crudrepositorytest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	// 注入UserService
	@Resource
	private UserService userService;

	@RequestMapping("/save")
	public String save() {
		User user = new User();
		user.setLoginName("dlei");
		user.setUsername("徐磊");
		user.setSex('男');
		user.setAge(3);
		user = userService.save(user);
		System.out.println("保存数据成功，返回的结果：" + user);
		return "保存数据成功！";
	}

	@RequestMapping("/update")
	public String update() {
		// 修改的对象必须是持久化对象，所以先从数据库查询出id为1的对象进行修改
		User user = userService.getById(1);
		userService.update(user);
		return "修改数据成功!";
	}

	@RequestMapping("/delete")
	public String delete() {
		userService.delete(1);
		return "删除数据成功！";
	}

	@RequestMapping("/getAll")
	public Iterable<User> getAll() {
		return userService.getAll();
	}
}
