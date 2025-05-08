package com.example.demo.common.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttribute {
	
	@ModelAttribute("loginId")
	public String loginid(HttpSession session) {
		return (String) session.getAttribute("loginId");
	}
	
}
