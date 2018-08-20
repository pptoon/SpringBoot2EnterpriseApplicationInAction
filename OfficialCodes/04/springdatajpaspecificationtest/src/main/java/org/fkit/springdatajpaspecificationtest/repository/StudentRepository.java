package org.fkit.springdatajpaspecificationtest.repository;
import org.fkit.springdatajpaspecificationtest.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 * @From http://www.fkjava.org 疯狂软件
 * @Version 1.0
 * 
 */
public interface StudentRepository extends JpaRepository<Student,Integer>,JpaSpecificationExecutor<Student>{
	
}
