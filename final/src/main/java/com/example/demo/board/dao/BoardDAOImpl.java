package com.example.demo.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.board.dto.BoardDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<BoardDTO> boardList(){
		return em.createQuery("select b From Board b order by b.articleNo desc", BoardDTO.class)
				.getResultList();
	}

	@Override
	public void insertBoard(BoardDTO dto) {
		// hibernate의 JPA 방식
		em.persist(dto);
	}

	@Override
	public BoardDTO getBoard(int articleNo) {
//		BoardDTO dto = (BoardDTO) em.createNativeQuery
//			 ("select * From board where article_No = :articleNo", BoardDTO.class)
//			 .setParameter("articleNo", articleNo)
//			 .getSingleResult();
		BoardDTO dto = em.createQuery
				 ("select b From Board b where b.articleNo = :articleNo", BoardDTO.class)
				 .setParameter("articleNo", articleNo)
				 .getSingleResult();
		// hibernate의 JPA 방식 - select
		// BoardDTO dto = em.find(BoardDTO.class, articleNo);
		return dto;
	}

	@Override
	public void update(BoardDTO dto) {
		// hibernate의 JPA 방식 - update
		em.merge(dto);
	}

	@Override
	public void delete(BoardDTO dto) {
		// return 값 없음
		em.remove(dto);
	}

	@Override
	public int deleteBoard(int articleNo) {
		return em.createQuery("delete from Board b where b.articleNo = :articleNo")
				.setParameter("articleNo", articleNo)
				.executeUpdate();
	}
}
