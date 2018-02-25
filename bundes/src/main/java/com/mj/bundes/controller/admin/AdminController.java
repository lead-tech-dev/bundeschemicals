package com.mj.bundes.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	
	@RequestMapping("/admin")
	public String index(){
		return "redirect:/admin/home";
	}
	
	@RequestMapping("/admin/home")
	public String home(){
		return "admin/home";
	}
	
	@RequestMapping("/admin/login")
	public String login(){
		return "admin/login";
	}
}
