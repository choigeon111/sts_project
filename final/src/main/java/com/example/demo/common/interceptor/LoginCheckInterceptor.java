package com.example.demo.common.interceptor;

import java.net.URLEncoder;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("loginId") == null) {
			response.sendRedirect("/member/loginForm?message="+
					URLEncoder.encode("게시글은 로그인 후 사용할 수 있습니다.", "utf-8"));
			return false;
		}
		return true;
	}
}
