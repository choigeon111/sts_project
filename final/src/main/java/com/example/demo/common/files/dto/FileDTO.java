package com.example.demo.common.files.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="imageFile")
@Table(name="imageFile")
@Component @Getter @Setter @ToString
public class FileDTO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int imageNo;
	private int articleNo;
	private String fileName;
}
