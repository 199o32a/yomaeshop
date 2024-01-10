package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Member;
import com.example.demo.service.MemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login")
public class SecurityLoginController {
	
	private final MemService memService;
	
	@GetMapping(value= {"","/"})
	public String home(Model model, Authentication auth) {
		model.addAttribute("loginType", "security-login");
		model.addAttribute("pageName","Security 로그인");
		
		if(auth != null) {
			Member loginMem = memService.getLoginUserByLoginId(auth.getName());
			if (loginMem != null) {
				model.addAttribute("m_name", loginMem.getM_name());
			}
		}
		
		return "index";
	}
}
