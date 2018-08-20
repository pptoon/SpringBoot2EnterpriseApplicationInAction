package org.fkit.oa.identity.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.identity.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/identity/user")
public class UserController {
	/** 1.定义业务层对象 */
	@Resource // by type
	private IdentityService identityService;
	
	@RequestMapping(value="/updateSelf")
	public String updateSelf(User user,Model model,HttpSession session){
		try {
			identityService.updateSelf(user,session);
			model.addAttribute("tip", "修改成功！");
		} catch (Exception e) {
			model.addAttribute("tip", "修改失败！");
			e.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping(value="/selectUser")
	public String selectUser(User user ,HttpServletRequest request, PageModel pageModel,Model model){
		try {
			/** 1.自己处理 ：只需要处理get请求的参数 
			 *   post请求的参数不会乱码 
			 *  */
			if(request.getMethod().toLowerCase().indexOf("get")!=-1){
			    if(user!=null && !StringUtils.isEmpty(user.getName())){
			    	String name = user.getName();
					/**
					 * 浏览器到后台乱码 
					 * */
				    name = new String(name.getBytes("ISO-8859-1") , "UTF-8");
				    user.setName(name);
			    }
			}
			
			List<User> users = identityService.getUsersByPager(user,pageModel);
			model.addAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/user/user";
	}
	
	
	@RequestMapping(value="/deleteUser")
	public String deleteUser(String ids ,Model model){
		try {
			/** 批量删除  */
			identityService.deleteUserByUserIds(ids);
			model.addAttribute("tip", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "删除失败");
		}
		return "forward:/identity/user/selectUser";
	}
	
	@RequestMapping(value="/showAddUser")
	public String showAddUser(){
		return "identity/user/addUser";
	}
	
	@ResponseBody
	@RequestMapping(value="/isUserValidAjax")
	public String isUserValid(String userId){
		try {
			return identityService.isUserValidAjax(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(User user,Model model){
		try {
			/** 批量删除  */
			identityService.addUser(user);
			model.addAttribute("tip", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "添加失败");
		}
		return "identity/user/addUser";
	}
	
	@RequestMapping(value="/updateUser")
	public String updateUser(User user,Model model){
		try {
			/** 批量删除  */
			identityService.updateUser(user);
			model.addAttribute("tip", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "修改失败");
		}
		return "identity/user/updateUser";
	}
	
	@RequestMapping(value="/activeUser")
	public String activeUser(User user,Model model){
		try {
			/** 激活用户  */
			identityService.activeUser(user);
			model.addAttribute("tip", user.getStatus()==1?"激活成功":"冻结成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", user.getStatus()==1?"激活失败":"冻结失败");
		}
		return "forward:/identity/user/selectUser";
	}
	
	
	@RequestMapping(value="/showPreUser")
	public String showPreUser(String userId,Model model){
		try {
			/** 批量删除  */
			User user = identityService.getUserById(userId);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/user/preUser";
	}
	
	@RequestMapping(value="/showUpdateUser")
	public String showUpdateUser(String userId,Model model){
		try {
			/** 批量删除  */
			User user = identityService.getUserById(userId);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/user/updateUser";
	}
	
	
	
	
}





