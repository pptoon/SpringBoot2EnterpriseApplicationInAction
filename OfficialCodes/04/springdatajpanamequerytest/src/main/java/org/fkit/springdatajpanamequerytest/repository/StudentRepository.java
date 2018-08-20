package org.fkit.springdatajpanamequerytest.repository;
import java.util.List;

import org.fkit.springdatajpanamequerytest.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
	/**
	 * 查询班级下的所有学生
	 * @param clazzName
	 * @return
	 */
	List<Student> findStudentsByClazzName(String clazzName);
}
