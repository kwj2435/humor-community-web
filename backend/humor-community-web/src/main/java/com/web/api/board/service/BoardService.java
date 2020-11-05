package com.web.api.board.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public List<BoardInfo> getBoardContentListForMain(String boardName,int limitNum){
		Pageable pageAble = PageRequest.of(0, limitNum);
		return boardRepository.findAllByBoardName(boardName,pageAble);
	}
	public List<BoardInfo> getBoardContentList(String boardName,int currentPage,int limit) throws Exception{
		PageRequest pageRequest = PageRequest.of(currentPage,limit,Sort.by(Direction.DESC,"boardContentBegin"));
		return boardRepository.findAllByBoardNameOrderByBoardContentBeginDesc(boardName,pageRequest);
	}
	public List<String> getBoardNameList() throws Exception{
		return boardRepository.findBoardNameList();
	}
	public BoardInfo getBoardContentDetail(String boardName,Integer boardIdx) {
		return boardRepository.findByBoardNameAndBoardIdx(boardName,boardIdx);
	}
	public void deleteBoardContent(String boardName,int boardIdx) {
		boardRepository.deleteByBoardNameAndBoardIdx(boardName, boardIdx);
	}
}
