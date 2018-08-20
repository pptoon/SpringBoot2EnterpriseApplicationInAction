package org.fkit.springdatajpaquerytest.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.fkit.springdatajpaquerytest.bean.Clazz;
import org.fkit.springdatajpaquerytest.bean.Student;
import org.fkit.springdatajpaquerytest.repository.ClazzRepository;
import org.fkit.springdatajpaquerytest.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SchoolService {
	
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
		// 使用"_" 和 @Query查询方式结果一致
		List<Student> students = studentRepository.findByClazz_name(clazzName);
//		List<Student> students = studentRepository.findStudentsByClazzName(clazzName);
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
	public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName) {
		return studentRepository.findNameAndSexByClazzName(clazzName);
	}
	public List<String> findNameByClazzNameAndSex(
			String clazzName, char sex) {
		return studentRepository.findNameByClazzNameAndSex(clazzName, sex);
	}
	public String findClazzNameByStuName(String stuName) {
		return studentRepository.findClazzNameByStuName(stuName);
	}
	@Transactional
	public int deleteStuByStuName(String stuName) {
		return studentRepository.deleteStuByStuName(stuName);
	}
}
