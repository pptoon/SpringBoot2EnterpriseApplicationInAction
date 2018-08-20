package org.fkit.springboottest.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.fkit.springboottest.bean.Student;
import org.fkit.springboottest.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Resource
	private SchoolService shcoolService;
	
	@PostMapping(value="/save")
	public Map<String,Object> save(@RequestBody Student stu) {
		shcoolService.save(stu);
		Map<String,Object> params = new HashMap<>();
		params.put("code", "success");
		return params;
	}
	
	/**
     * 获取学生信息
     * @param id
     */
    @GetMapping(value="/get/{id}")
    @ResponseBody
    public Student qryStu(@PathVariable(value = "id") Integer id){
    	Student stu = shcoolService.selectByKey(id);
        return stu;
    }
	
}
