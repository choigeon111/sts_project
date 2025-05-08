package com.example.demo.common.files.controller;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

@RestController
public class FileController {
	public static final String FILE_REPO = 
			"/home/ubuntu/app/final/build/libs/file_repo";
	
	@GetMapping("/files/{articleNo}/{fileName}")
	public ResponseEntity<Resource> downloadFile(
			@PathVariable("articleNo") String articleNo,
			@PathVariable("fileName") String fileName
			) throws Exception{
		Path filePath = Paths.get(FILE_REPO).resolve(articleNo).resolve(fileName);
		
		if(!Files.exists(filePath)) {
			throw new FileNotFoundException("파일을 찾을 수 없습니다.");
		}
		
		Resource resource = new UrlResource(filePath.toUri());
		
		// 한글 파일도 처리 가능
		String encodedFileName = UriUtils.encode(resource.getFilename(),
				StandardCharsets.UTF_8);
		String contentDisposition = "attachment;filename=\""+encodedFileName +
				"\";filename*=UTF-8''" + encodedFileName;
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
				
	}
}
