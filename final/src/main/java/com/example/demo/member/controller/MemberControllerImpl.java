package com.example.demo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController{
	@Autowired
	MemberService service;
	
	@Override
	@GetMapping("/memberList")
	public String memberList(Model model) {
		List<MemberDTO> memberList = service.findAll();
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}

	@Override
	@GetMapping("/{formName:.*Form}")
	public String joinMemberForm(String formName) {
		return "member/" + formName; 
	}

	@Override
	@PostMapping("/joinMember")
	public String joinMember(MemberDTO dto, Model model) {
		service.save(dto);
		model.addAttribute("message", "회원가입에 성공했습니다.");
		model.addAttribute("redirectUrl", "/member/loginForm");
		return "common/alert";
	}

	@Override
	@GetMapping("/detailMember")
	public String detailMember(String id, Model model) {
		MemberDTO dto = service.findById(id);
		model.addAttribute("member", dto);
		return "member/detailMember";
	}

	@Override
	@PostMapping("/updateMember")
	public String updateMember(MemberDTO dto, Model model) {
		// TODO Auto-generated method stub
		int result = service.merge(dto);
		
		if(result >= 1) {
			model.addAttribute("message", "회원 수정에 성공했습니다.");
			model.addAttribute("redirectUrl", "/member/memberList");
		} else {
			model.addAttribute("message", "회원 수정에 실패했습니다. 다시 시도하세요.");
			model.addAttribute("redirectUrl", "/member/detailMember?id="+dto.getId());
		}
		return "common/alert";
	}

	@Override
	@GetMapping("/deleteMember")
	public String deleteMember(String id, Model model) {
		int result = service.remove(id);
		if (result >= 1) {
			model.addAttribute("message", "회원 삭제에 성공했습니다.");
			model.addAttribute("redirectUrl", "/member/memberList");
		} else {
			model.addAttribute("message", "회원 수정에 실패했습니다. 다시 시도하세요.");
			model.addAttribute("redirectUrl", "/member/memberList");
		}
		return "common/alert";
	}

	@Override
	@PostMapping("/login")
	public String login(String id, String pwd, Model model, HttpSession session) {
		boolean result = service.login(id, pwd);
		
		if(result) {
			session.setAttribute("loginId", id);
			model.addAttribute("message", id+"님 환영합니다. 로그인에 성공했습니다.");
		} else {
			model.addAttribute("message", "아이디나 암호가 잘못 되었습니다. 다시 로그인 하세요.");
			model.addAttribute("redirectUrl", "/member/loginForm");
		}
		return "common/alert";
	}

	@Override
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		String loginId = (String) session.getAttribute("loginId");
		session.invalidate();
		model.addAttribute("message", loginId + "님이 로그아웃 하셨습니다.");
		model.addAttribute("redirectUrl", "/member/loginForm");
		return "common/alert";
	}

}