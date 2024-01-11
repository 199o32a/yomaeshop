package com.example.demo.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Member;
import com.example.demo.service.MemService;
import com.example.demo.vo.MemVO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemController {
	
	private final MemService memService;
	private final BCryptPasswordEncoder PasswordEncoder;
	
	
	@GetMapping("/register")
	public String memForm(@ModelAttribute("memVo") MemVO memVo, Model model) {
		model.addAttribute("loginType","security-login");
		model.addAttribute("pageName","Security 로그인");
		
		model.addAttribute("memVo", new MemVO());
		return "register";
	}
	
	@PostMapping("/register")
	public String memFormSave(@ModelAttribute("memVo") @Valid MemVO memVo, BindingResult bindingResult, Model model) {
		model.addAttribute("loginType","security-login");
		model.addAttribute("pageName","Security 로그인");
		
		if(bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError("m_birth");
	        if (fieldError != null) {
	            String errorCode = fieldError.getCode(); // 사용자가 추가한 에러 코드
	            String errorMessage = fieldError.getDefaultMessage(); // 사용자 정의 에러 메시지
	            model.addAttribute("errorMessage", errorMessage);}
			
			return "/register";
		}
		try {
			Member newMem = Member.CreateMem(memVo, PasswordEncoder);
			memService.saveMem(newMem);
		}catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register";
		}
		
		return "redirect:/";
	}
	
}
