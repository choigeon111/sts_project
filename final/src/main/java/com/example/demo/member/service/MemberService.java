package com.example.demo.member.service;

import java.util.List;

import com.example.demo.member.dto.MemberDTO;

public interface MemberService {
	List<MemberDTO> findAll();
	void save(MemberDTO dto);
	MemberDTO findById(String id);
	int merge(MemberDTO dto);
	int remove(String id);
	boolean login(String id, String pwd);
}
