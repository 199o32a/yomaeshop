package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Member;
import com.example.demo.service.MemService;
import com.example.demo.vo.LoginVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login")
public class SecurityLoginController {
	
	private final MemService memService;
	
	@GetMapping(value= {"","/"})
	public String index(Model model, Authentication auth) {
		model.addAttribute("loginType", "security-login");
		model.addAttribute("pageName","Security 로그인");
		
		if(auth != null) {
			Member loginMem = memService.getLoginUserByLoginId(auth.getName());
			if (loginMem != null) {
				model.addAttribute("loginid", loginMem.getM_name());
			}
		}
		
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginType", "security-login");
		model.addAttribute("pageName", "Security 로그인");
		
		model.addAttribute("loginVo",new LoginVO());
		return "login";
	}
	
	@GetMapping("/info")
	public String memInfo(Model model, Authentication auth) {
		model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");
        
        Member loginMem = memService.getLoginUserByLoginId(auth.getName());
        
        if(loginMem == null) {
        	return "redirect:/security-login/login";
        }
        
        model.addAttribute("Member",loginMem);
        return "info";
	}
	
    @GetMapping("/admin")
    public String adminPage( Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        return "admin";
    }
	
	
}
