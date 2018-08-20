package org.fkit.springdatajpaquerytest.repository;
import java.util.List;
import java.util.Map;

import org.fkit.springdatajpaquerytest.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author dlei(徐磊)
 * @Email dlei0009@163.com
 * @From http://www.fkjava.org 疯狂软件
 * @Version 1.0
 * 
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	/**
	 * 根据班级名称查询这个班级下所有的学生信息
	 * 相当于JPQL语句： select s from Student s where s.clazz.name = ?1
	 * @param clazzName
	 * @return
	 */
	List<Student> findByClazz_name(String clazzName);
	
	/**
	 * @Query写法
	 * 根据班级名称查询这个班级下所有的学生信息
	 * ?1此处使用的是参数的位置，代表的是第一个参数
	 * 此写法与 findByClazz_name方法实现的功能完全一致
	 * */
	@Query("select s from Student s where s.clazz.name = ?1")
	List<Student> findStudentsByClazzName(String clazzName);
	/**
	 * 使用@Query注解的形式，查询某个班级下所有学生的姓名和性别 
	 * @param clazzName
	 * @return
	 */
	@Query("select new Map(s.name as name , s.sex as sex) "
			+ "from Student s where s.clazz.name = ?1")
	List<Map<String, Object>> findNameAndSexByClazzName(String clazzName);
	
	/**
	 *  使用@Query注解的形式，查询某个班级下某种性别的所有学生的姓名
	 *  上面方法是用的是参数的位置来查询的，Spring Data JPA中还支持用
	 *  名称来匹配查询使用格式 “:参数名称” 引用
	 * @param clazzName
	 * @return
	 */
	@Query("select s.name from Student s "
			+ "where s.clazz.name = :clazzName and s.sex = :sex ")
	List<String> findNameByClazzNameAndSex(@Param("clazzName")String clazzName , @Param("sex")char sex);
	/**
	 *  使用@Query注解的形式，查询某个学生属于哪个班级
	 * @param stuName
	 * @return
	 */
	@Query("select c.name from Clazz c inner join c.students s "
			+ "where s.name = ?1 ")
	String findClazzNameByStuName(String stuName);
	/**
	 * 执行更新查询，使用@Query与@Modifying可以执行更新操作
	 * 例如删除牛魔王这个学生
	 * */
	@Modifying
	@Query("delete from Student s where s.name = ?1")
	int deleteStuByStuName(String stuName);
}
