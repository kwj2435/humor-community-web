package com.web.api.board.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.api.board.entity.BoardInfo;
import com.web.api.board.service.BoardService;

@RestController
@RequestMapping("/v1/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/")
	public ResponseEntity<BoardInfo> postBoardContent(@RequestBody BoardInfo boardVO) throws Exception{
		
		BoardInfo resultInfo = boardService.postBoardContent(boardVO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resultInfo.getBoardIdx())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<String>> getBoardList() throws Exception{
		List<String> boardList = boardService.getBoardNameList();
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BoardInfo>> getBoardContentList(@RequestBody String boardName) throws Exception{
		
		List<BoardInfo> boardContentList = boardService.getBoardContentList(boardName);
		
		return ResponseEntity.ok(boardContentList);
	}
}
