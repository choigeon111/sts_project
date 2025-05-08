package com.example.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.member.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String>{
	@Modifying
	@Query("update Member m set m.pwd=:pwd, m.name=:name, m.email=:email where m.id=:id")
	int updateMember(@Param("id") String id, @Param("pwd") String pwd, @Param("name") String name, @Param("email") String email);

	@Modifying
	@Query("delete from Member m where m.id=:id")
	int deleteMember(@Param("id") String id);
}
