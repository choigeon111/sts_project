package com.example.demo.board.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.dto.BoardDTO;

public interface BoardController {
	public String boardList(Model model);
	public String insertBoard();
	public String insertBoard(BoardDTO dto, Model model, @RequestParam("files") List<MultipartFile> files) throws Exception;
	public String getBoard(@RequestParam("articleNo") int articleNo, Model model);
	public String getBoard(BoardDTO dto, Model model);
	public String deleteBoard(@RequestParam("articleNo") int articleNo, Model model);
}
