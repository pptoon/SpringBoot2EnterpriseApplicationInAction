package org.fkit.springdatajpaspecificationtest.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.fkit.springdatajpaspecificationtest.bean.Clazz;
import org.fkit.springdatajpaspecificationtest.bean.Student;
import org.fkit.springdatajpaspecificationtest.service.ShcoolService;
import org.fkit.springdatajpaspecificationtest.vo.PageData;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	// 注入ShcoolService
	@Resource
	private ShcoolService shcoolService;
	@RequestMapping("/save")
	public String save() {
		
		Clazz clazz1 = new Clazz("疯狂java开发1班");
		Clazz clazz2 = new Clazz("疯狂java开发2班");
		// 保存班级对象数据
		List<Clazz> clazzs = new ArrayList<>();
		clazzs.add(clazz1);
		clazzs.add(clazz2);
		shcoolService.saveClazzAll(clazzs);
		
		Student swk = new Student("孙悟空","花果山",700,'男',clazz1);
		Student zx = new Student("紫霞仙子","盘丝洞",500,'女',clazz1);
		Student zzb = new Student("至尊宝","广州",500,'男',clazz1);
		Student tsgz = new Student("铁扇公主","火焰山",500,'女',clazz2);
		Student nmw = new Student("牛魔王","广州",500,'男',clazz2);
		Student zzj = new Student("蜘蛛精","广州",700,'女',clazz2);
		
		List<Student> students = new ArrayList<>();
		students.add(swk);
		students.add(zx);
		students.add(zzb);
		students.add(tsgz);
		students.add(nmw);
		students.add(zzj);
		shcoolService.saveStudentAll(students);
		return "保存学生对象成功";
	}

	@RequestMapping("/getStusBySex")
	public List<Map<String, Object>> getStusBySex(char sex){
		return shcoolService.getStusBySex(sex);
	} 
	// 动态的查询学生信息
	// 可以根据学生对象的姓名(模糊匹配)， 地址查询(模糊匹配),性别,班级查询学生信息 
	@RequestMapping("/getStusByDynamic")
	public List<Map<String, Object>> getStusByDynamic(Student student) {
		return shcoolService.getStusByDynamic(student);
	} 
	// 分页查询某个班级下的学生信息
	@RequestMapping("/getStusByPage")
	public PageData getStusByPage(String clazzName , int pageIndex , int pageSize ) {
		// 分页查询某个班级的学生信息
		Page<Student> page = shcoolService.getStusByPage(clazzName , pageIndex , pageSize);
		// 对查询出来的结果数据进行分析
		List<Student> students = page.getContent();
		List<Map<String,Object>> stuDatas = new ArrayList<>();
		for(Student stu :students){
			Map<String , Object> stuMap = new HashMap<>(); 
			stuMap.put("id", stu.getId());
			stuMap.put("name", stu.getName());
			stuMap.put("age", stu.getAge());
			stuMap.put("sex", stu.getSex());
			stuMap.put("address", stu.getAddress());
			stuMap.put("clazzName", clazzName);
			stuDatas.add(stuMap);
		}
		// 将分页查询出的结果数据进行分析，然后把数据存入到PageData对象中去保存起来响应给浏览器展示 
		PageData data = new PageData();
		data.setStuDatas(stuDatas);
		data.setPageIndex(page.getNumber()+1);
		data.setPageSize(page.getTotalPages());
		data.setTotalCount(page.getTotalElements());
		data.setPageNum(page.getSize());
		return data ;
	} 
}