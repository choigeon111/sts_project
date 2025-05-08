package com.example.demo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.dao.BoardDAO;
import com.example.demo.board.dto.BoardDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO dao;
	
	@Override
	public List<BoardDTO> boardList() {
		return dao.boardList();
	}

	@Override
	public void insertBoard(BoardDTO dto) {
		dao.insertBoard(dto);
	}

	@Override
	public BoardDTO getBoard(int articleNo) {
		BoardDTO dto = dao.getBoard(articleNo);
		dto.setCnt(dto.getCnt() + 1);
		dao.update(dto);
		return dto;
	}

	@Override
	public void updateBoard(BoardDTO dto) {
		dao.update(dto);
	}

	@Override
	public int deleteBoard(int articleNo) {
		// BoardDTO dto = dao.getBoard(articleNo);
		// dao.delete(dto);
		return dao.deleteBoard(articleNo);
	}
}



