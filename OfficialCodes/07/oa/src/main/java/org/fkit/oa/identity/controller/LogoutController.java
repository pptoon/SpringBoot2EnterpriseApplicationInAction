package org.fkit.oa.identity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		try {
			/** 注销当前会话 */
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/oa/login";
		
	}

}
