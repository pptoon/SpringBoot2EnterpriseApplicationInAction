package org.fkit.oa.identity.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.fkit.oa.identity.domain.Dept;
import org.fkit.oa.identity.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午9:34:55
 * @From http://www.fkjava.org 
 *  部门的控制层 
 */
@Controller
@RequestMapping("/identity/dept")
public class DeptController {
	
	/** 定义业务层对象 */

	@Resource // by type
	private IdentityService identityService;
	
	@RequestMapping(value="/selectAll")
	public String selectAll(Model model){
		try {
			List<Dept> depts = identityService.getAllDepts();
			System.out.println("depts:"+depts);
			model.addAttribute("depts", depts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "aaa";
	}
	// 异步方法  写数据回去 
	@ResponseBody
	@RequestMapping(value="/getAllDeptsAndJobsAjax",produces="application/json; charset=UTF-8")
	public Map<String, Object> getAllDeptsAndJobsAjax(){
		try {
			Thread.sleep(2000);
			Map<String, Object> rs = identityService.getAllDeptsAndJobsAjax();
			System.out.println("rs:"+rs);
			return rs ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}




