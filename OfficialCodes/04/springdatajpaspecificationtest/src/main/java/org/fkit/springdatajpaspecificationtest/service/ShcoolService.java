package org.fkit.springdatajpaspecificationtest.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fkit.springdatajpaspecificationtest.bean.Clazz;
import org.fkit.springdatajpaspecificationtest.bean.Student;
import org.fkit.springdatajpaspecificationtest.repository.ClazzRepository;
import org.fkit.springdatajpaspecificationtest.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
	
	/**
	 * 根据性别查询学生信息
	 * @param clazzName
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Map<String, Object>> getStusBySex(char sex) {
		List<Student> students = studentRepository.findAll(new Specification<Student>() {

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
			   // root.get("sex")表示获取sex这个字段名称,equal表示执行equal查询
			   // 相当于 select s from Student s where s.sex = ?1
               Predicate p1 = cb.equal(root.get("sex"), sex);
               return p1;
			}
		});
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
	
	
	/**
	 * 动态查询学生信息 ：可以根据学生对象的姓名(模糊匹配)， 地址查询(模糊匹配),性别,班级查询学生信息 
	 *               如果没有传输参数,默认查询所有的学生信息
	 * @param clazzName
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Map<String, Object>> getStusByDynamic(Student student) {
		List<Student> students = studentRepository.findAll(new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				// 本集合用于封装查询条件
				List<Predicate> predicates = new ArrayList<Predicate>();  
				if(student!=null){
					/** 是否传入了姓名来查询  */
					if(!StringUtils.isEmpty(student.getName())){
						predicates.add(cb.like(root.<String> get("name"),"%" + student.getName() + "%"));
					}
					/** 是否传入了地址来查询  */
					if(!StringUtils.isEmpty(student.getAddress())){
						predicates.add(cb.like(root.<String> get("address"),"%" + student.getAddress() + "%"));
					}
					/** 是否传入了性别来查询 */
					if(student.getSex() != '\0'){
						predicates.add(cb.equal(root.<String> get("sex"),student.getSex()));
					}
					/** 判断是否传入了班级信息来查询 */
					if(student.getClazz()!=null && !StringUtils.isEmpty(student.getClazz().getName())){
						root.join("clazz", JoinType.INNER);
						Path<String> clazzName = root.get("clazz").get("name");
						predicates.add(cb.equal(clazzName, student.getClazz().getName()));
					}
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		});
		List<Map<String, Object>>  results = new ArrayList<>(); 
		// 遍历查询出的学生对象，提取姓名，年龄，性别信息
		for(Student stu :students){
			Map<String , Object> stuMap = new HashMap<>(); 
			stuMap.put("name", stu.getName());
			stuMap.put("age", stu.getAge());
			stuMap.put("sex", stu.getSex());
			stuMap.put("address", stu.getAddress());
			stuMap.put("clazzName", stu.getClazz().getName());
			results.add(stuMap);
		}
		return results;
	}
	/**
	 * 分页查询某个班级的学生信息
	 * @param clazzName 班级名称
	 * @param pageIndex 代表当前查询第几页 
	 * @param pageSize 代表每页查询的最大数据量
	 * @return
	 */
	@SuppressWarnings("serial")
	public Page<Student> getStusByPage(String clazzName , int pageIndex , int pageSize ) {
		// 指定排序参数对象：根据id，进行降序查询
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		// 分页查询学生信息，返回分页实体对象数据
		// pages对象中包含了查询出来的数据信息，以及与分页相关的信息
		Page<Student> pages = studentRepository.findAll(new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join("clazz", JoinType.INNER);
				Path<String> cn = root.get("clazz").get("name");
	            Predicate p1 = cb.equal(cn, clazzName);
	            return p1 ;
			}
		},PageRequest.of(pageIndex-1, pageSize, sort));
		return pages;
	}
	
}
