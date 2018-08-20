package org.fkit.simplespringdatajpatest.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.fkit.simplespringdatajpatest.bean.Student;
import org.fkit.simplespringdatajpatest.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	// 注入StudentService
	@Resource
	private StudentService studentService;
	
	@RequestMapping("/save")
	public String save() {
		Student swk = new Student();
		swk.setAddress("广州");
		swk.setName("孙悟空");
		swk.setAge(700);
		swk.setSex('男');
		
		Student zzj = new Student();
		zzj.setAddress("广州");
		zzj.setName("蜘蛛精");
		zzj.setAge(700);
		zzj.setSex('女');
		
		Student nmw = new Student();
		nmw.setAddress("广州");
		nmw.setName("牛魔王");
		nmw.setAge(500);
		nmw.setSex('男');
		
		List<Student> students = new ArrayList<>();
		students.add(swk);
		students.add(zzj);
		students.add(nmw);
		
		studentService.saveAll(students);
		return "保存学生对象成功";
	}
	
	@RequestMapping("/name")
	public Student getByName(String name) {
		return studentService.getStuByName(name);
	}
	
	@RequestMapping("/nameAndAddress")
	public List<Student> getByNameAndAddress(String name,String address) {
		return studentService.getStusByNameAndAddress(name, address);
	}
	
	@RequestMapping("/nameLike")
	public List<Student> getByNameLile(String name) {
		return studentService.getStusByNameLike(name);
	}
	
}
