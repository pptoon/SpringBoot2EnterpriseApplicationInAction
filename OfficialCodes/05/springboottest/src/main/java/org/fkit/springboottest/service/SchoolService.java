package org.fkit.springboottest.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.fkit.springboottest.bean.Student;
import org.fkit.springboottest.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolService {
	
	// 定义数据访问层接口对象 
	@Resource
	private StudentRepository studentRepository;
	
	@Transactional
	public void save(Student stu) {
		studentRepository.save(stu);
	}

	public Student selectByKey(Integer id) {
		Optional<Student> op = studentRepository.findById(id);
		return op.get();
	}

}
