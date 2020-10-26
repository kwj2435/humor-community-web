package com.web.api.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.api.board.entity.BoardInfo;
import com.web.api.board.repository.BoardRepository;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardInfo postBoardContent(BoardInfo boardInfo) throws Exception{	// 세부 예외처리 필요
		
		boardInfo.setBoardViewCount(0);	//초기 조회수 세팅
		
		return boardRepository.save(boardInfo);
	}
	public List<BoardInfo> getBoardContentList(String boardName) throws Exception{
		return boardRepository.findAllByBoardName(boardName);
	}
	public List<String> getBoardNameList() throws Exception{
		return boardRepository.findBoardNameList();
	}
}
