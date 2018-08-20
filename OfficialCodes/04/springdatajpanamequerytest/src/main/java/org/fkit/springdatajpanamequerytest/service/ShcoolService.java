package org.fkit.springdatajpanamequerytest.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.fkit.springdatajpanamequerytest.bean.Clazz;
import org.fkit.springdatajpanamequerytest.bean.Student;
import org.fkit.springdatajpanamequerytest.repository.ClazzRepository;
import org.fkit.springdatajpanamequerytest.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ShcoolService {
	// 注入数据访问层接口对象 
	@Resource
	private StudentRepository studentRepository;
	@Resource
	private ClazzRepository clazzRepository;
	
	@Transactional
	public void saveClazzAll(List<Clazz> clazzs) {
		clazzRepository.saveAll(clazzs);
	}
	@Transactional
	public void saveStudentAll(List<Student> students) {
		studentRepository.saveAll(students);
	}
	
	public List<Map<String, Object>> getStusByClazzName(String clazzName) {
		// 查询班级下的所有学生
		List<Student> students = studentRepository.findStudentsByClazzName(clazzName);
		List<Map<String, Object>>  results = new ArrayList<>(); 
		// 遍历查询出的学生对象，提取姓名，年龄，性别信息
		for(Student student:students){
			Map<String , Object> stu = new HashMap<>(); 
			stu.put("name", student.getName());
			stu.put("age", student.getAge());
			stu.put("sex", student.getSex());
			results.add(stu);
		}
		return results;
	}
}
