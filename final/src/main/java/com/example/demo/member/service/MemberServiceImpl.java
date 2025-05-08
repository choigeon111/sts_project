package com.example.demo.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.common.security.SecurityConfig;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final SecurityConfig securityConfig;
	@Autowired
	MemberRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

    MemberServiceImpl(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }
	
	@Override
	public List<MemberDTO> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(MemberDTO dto) {
		String encodedPwd = passwordEncoder.encode(dto.getPwd());
		dto.setPwd(encodedPwd);
		repository.save(dto);
	}

	@Override
	public MemberDTO findById(String id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원정보가 없습니다."));
	}

	@Override
	public int merge(MemberDTO dto) {
		int result = repository.updateMember(dto.getId(), dto.getPwd(), dto.getName(), dto.getEmail());
		return result;
	}

	@Override
	public int remove(String id) {
		int result = repository.deleteMember(id);
		return result;
	}

	@Override
	public boolean login(String id, String pwd) {
		Optional<MemberDTO> memberOpt = repository.findById(id);
		
		if(memberOpt.isPresent()) {
			MemberDTO member = memberOpt.get();
			String savePwd = member.getPwd();
			if(passwordEncoder.matches(pwd, savePwd)) {
				// 로그인 성공
				return true;
			}
		}
		// 로그인 실패
		return false;
	}

}




