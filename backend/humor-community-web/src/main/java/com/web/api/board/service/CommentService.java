package com.web.api.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.api.board.entity.CommentInfo;
import com.web.api.board.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public CommentInfo writeComment(CommentInfo commentInfo) {
		return commentRepository.save(commentInfo);
	}
	public List<CommentInfo> getComment(int boardIdx){
		return commentRepository.findAllByBoardIdx(boardIdx);
	}
	public int deleteComment(int commentIdx) {
		
		int deletedCommentInfo = commentRepository.deleteByCommentIdx(commentIdx);
		
		return deletedCommentInfo;
	}
	public CommentInfo updateComment(int commentIdx, String commentContent) {
		
		CommentInfo savedCommentInfo = commentRepository.findByCommentIdx(commentIdx);
		
		savedCommentInfo.setCommentContent(commentContent);
		
		return commentRepository.save(savedCommentInfo);
	}
}
