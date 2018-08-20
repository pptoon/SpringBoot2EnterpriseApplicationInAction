package org.fkit.oa.identity.controller;

import java.util.List;

import javax.annotation.Resource;

import org.fkit.oa.identity.domain.Module;
import org.fkit.oa.identity.domain.Role;
import org.fkit.oa.identity.service.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/identity/popedom")
public class PopedomController {
	/** 1.定义业务层对象 */
	@Resource // by type
	private IdentityService identityService;
	
	@RequestMapping(value="/mgrPopedom")
	public String mgrPopedom(Role role,Model model){
		try {
			role = identityService.getRoleById(role.getId());
			model.addAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/role/bindPopedom/mgrPopedom";
	}
	
	@RequestMapping(value="/getOperasByParent")
	public String getOperasByParent(
			@RequestParam("parentCode")String parentCode,
			@RequestParam("moduleName")String moduleName,
			Role role,Model model){
		try {
			List<Module> sonModules = identityService.getModulesByParent(parentCode);
			/** 还应该查询出当前role在当前模块下parentCode拥有哪些操作权限 
			 *  去控制界面的权限显示 
			 *  查询出当前角色role在parentCode模块下拥有的操作编号即可。
			 * */
			List<String> roleModuleOperasCodes = identityService.getRoleModuleOperasCodes(role,parentCode);
			model.addAttribute("modules", sonModules);
			model.addAttribute("moduleName", moduleName);
			model.addAttribute("parentCode", parentCode);
			model.addAttribute("roleModuleOperasCodes", roleModuleOperasCodes+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "identity/role/bindPopedom/operas";
	}
	
	@RequestMapping(value="/bindPopedom")
	public String bindPopedom(@RequestParam("codes")String codes,
			@RequestParam("parentCode")String parentCode,Role role,Model model){
		try {
			identityService.bindPopedom(codes,role,parentCode);
			model.addAttribute("tip", "绑定成功");
		} catch (Exception e) {
			model.addAttribute("tip", "绑定失败");
			e.printStackTrace();
		}
		return "forward:/identity/popedom/getOperasByParent";
	}
	
}





