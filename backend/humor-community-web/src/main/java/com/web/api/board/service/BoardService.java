package com.web.api.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.web.api.board.dto.BoardContentUpdateVO;
import com.web.api.board.entity.BoardInfo;
import com.web.api.board.entity.BoardListInfo;
import com.web.api.board.repository.BoardListRepository;
import com.web.api.board.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private BoardListRepository boardListRepository;
	
	public BoardInfo postBoardContent(BoardInfo boardInfo) throws Exception{	// 세부 예외처리 필요
		
		boardInfo.setBoardViewCount(0);	//초기 조회수 세팅
		
		return boardRepository.save(boardInfo);
	}
	public List<BoardInfo> getBoardContentListForMain(String boardName,int limitNum){
		
		Pageable pageAble = PageRequest.of(0 ,limitNum,Sort.by(Direction.DESC,"boardContentBegin"));
		
		return boardRepository.findAllByBoardName(boardName,pageAble);
	}
	public List<BoardInfo> getBoardContentList(String boardName,int currentPage,int limit) throws Exception{
		
		Pageable pageAble = PageRequest.of(currentPage - 1,limit,Sort.by(Direction.DESC,"boardContentBegin"));
		
		return boardRepository.findAllByBoardName(boardName,pageAble);
	}
	public List<BoardListInfo> getBoardNameList() throws Exception{
		return boardListRepository.findAll();
	}
	public BoardInfo getBoardContentDetail(String boardName,Integer boardIdx) {
		return boardRepository.findByBoardNameAndBoardIdx(boardName,boardIdx);
	}
	public void deleteBoardContent(String boardName,int boardIdx) {
		boardRepository.deleteByBoardNameAndBoardIdx(boardName, boardIdx);
	}
	public Long getBoardContentCount(String boardName) {
		return boardRepository.countByBoardName(boardName);
	}
	public BoardInfo updateBoardContent(String boardName,int boardIdx,BoardContentUpdateVO boardContent) {
		BoardInfo savedBoardInfo = boardRepository.findByBoardNameAndBoardIdx(boardName, boardIdx);
		
		savedBoardInfo.setBoardContentTitle(boardContent.getBoardTitle());
		savedBoardInfo.setBoardContent(boardContent.getBoardContent());
		
		return boardRepository.save(savedBoardInfo);
	}
	public void updateBoardViewCount(String boardName,int boardIdx) {
		BoardInfo savedBoardInfo = boardRepository.findByBoardNameAndBoardIdx(boardName, boardIdx);
		
		int savedBoardViewCounts = savedBoardInfo.getBoardViewCount();
		savedBoardInfo.setBoardViewCount(savedBoardViewCounts + 1);
	}
}
