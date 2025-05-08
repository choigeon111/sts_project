package com.example.demo.member.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Member")
@Table(name="member")
@Component @Setter @Getter @ToString
public class MemberDTO {
	@Id
	private String id;
	private String pwd;
	private String name;
	private String email;
	@Column(insertable=false, updatable=false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date joinDate;
}
