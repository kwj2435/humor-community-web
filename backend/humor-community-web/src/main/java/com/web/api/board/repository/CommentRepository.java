package com.web.api.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.web.api.board.entity.CommentInfo;

public interface CommentRepository extends JpaRepository<CommentInfo, Integer>{
	public List<CommentInfo> findAllByBoardIdx(int boardIdx);
	
	@Transactional
	public int deleteByCommentIdx(int commentIdx);
}
