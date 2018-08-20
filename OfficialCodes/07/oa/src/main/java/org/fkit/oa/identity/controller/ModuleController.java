package org.fkit.oa.identity.controller;

import java.util.List;

import javax.annotation.Resource;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.identity.domain.Module;
import org.fkit.oa.identity.service.IdentityService;
import org.fkit.oa.identity.vo.TreeData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月16日下午2:38:06
 * @From http://www.fkjava.org 
 * 模块菜单
 */
@Controller
@RequestMapping("/identity/module")
public class ModuleController {
	
	@Resource // by type
	private IdentityService identityService;
	
	@RequestMapping(value="/mgrModule")
	public String mgrModule(){
		return "identity/module/mgrModule";
	}
	
	@ResponseBody
	@RequestMapping(value="/loadAllModuleTrees",produces="application/json; charset=UTF-8")
	public List<TreeData> loadAllModuleTrees(){
		try {
			return identityService.loadAllModuleTrees();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping(value="/getModulesByParent")
	public String getModulesByParent(String parentCode,PageModel pageModel,Model model){
		try {
			List<Module> sonModules = identityService.getModulesByParent(parentCode,pageModel);
			model.addAttribute("modules", sonModules);
			model.addAttribute("parentCode", parentCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/module/module";
	}
	@RequestMapping(value="/deleteModules")
	public String deleteModules(String ids,Model model){
		try {
			identityService.deleteModules(ids);
			model.addAttribute("tip", "删除成功");
		} catch (Exception e) {
			model.addAttribute("tip", "删除失败");
			e.printStackTrace();
		}
		return "forward:/identity/module/getModulesByParent";
	}
	@RequestMapping(value="/addModule")
	public String addModule(String parentCode,Module module ,Model model){
		try {
			identityService.addModule(parentCode,module);
			model.addAttribute("tip", "添加成功");
			model.addAttribute("parentCode", parentCode);
		} catch (Exception e) {
			model.addAttribute("tip", "添加失败");
			e.printStackTrace();
		}
		return "identity/module/addModule";
	}
	@RequestMapping(value="/updateModule")
	public String updateModule(Module module ,Model model){
		try {
			identityService.updateModule(module);
			model.addAttribute("tip", "修改成功");
		} catch (Exception e) {
			model.addAttribute("tip", "修改失败");
			e.printStackTrace();
		}
		return "identity/module/updateModule";
	}
	@RequestMapping(value="/showAddModule")
	public String showAddModule(String parentCode,Model model){
		model.addAttribute("parentCode", parentCode);
		return "identity/module/addModule";
	}
	
	@RequestMapping(value="/showUpdateModule")
	public String showUpdateModule(Module module ,Model model){
		try {
			module = identityService.getModuleByCode(module.getCode());
			model.addAttribute("module", module);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/module/updateModule";
	}
}





