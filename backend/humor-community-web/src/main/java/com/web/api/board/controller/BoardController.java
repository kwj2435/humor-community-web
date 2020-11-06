package com.web.api.board.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.api.board.entity.BoardInfo;
import com.web.api.board.entity.FileInfo;
import com.web.api.board.service.BoardService;
import com.web.api.common.service.FileService;

@RestController
@RequestMapping("/v1/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	
	//Service가 다른 Service에 종속적이어도 괜찮은가?
	//우선 Service를 나누고 Controller에서 각 Service를 호출하는 것으로.. 20.10.29
	@PostMapping("/{boardName}")	//게시판 글 작성
	public ResponseEntity<BoardInfo> postBoardContent(
			@PathVariable("boardName")String boardName
			,BoardInfo boardInfo
			,@RequestParam(value = "boardFile", required = false)MultipartFile file) throws Exception{
		
		if(file != null) {
			String resultFileInfo = fileService.uploadFile(file);
		}
		boardInfo.setBoardName(boardName);
		BoardInfo resultInfo = boardService.postBoardContent(boardInfo);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resultInfo.getBoardIdx())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/list")	//게시판 목록
	public ResponseEntity<List<String>> getBoardList() throws Exception{
	
		List<String> boardList = boardService.getBoardNameList();
		
		return ResponseEntity.ok(boardList);
	}
	
	@GetMapping("/{boardName}")	//게시판 글 목록
	public ResponseEntity<List<BoardInfo>> getBoardContentList(
			@PathVariable("boardName") String boardName,
			@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limit") Integer limit) throws Exception{

		List<BoardInfo> boardContentList = boardService.getBoardContentList(boardName,currentPage,limit);
		
		return ResponseEntity.ok(boardContentList);
	}
	
	@GetMapping(value = "/{boardName}/list",produces = "application/json; charset=utf8")  //메인 게시판 글 목록
	public ResponseEntity<List<BoardInfo>> getBoardContentListForMain(
			@PathVariable("boardName") String boardName,
			@RequestParam("limitNum") Integer limit) throws Exception{
		
		List<BoardInfo> requestResult = boardService.getBoardContentListForMain(boardName,limit);
		
		return ResponseEntity.ok(requestResult);
	}
	@GetMapping("/{boardName}/rows")	//게시판 글 갯수
	public ResponseEntity<Map<String,Long>> geteBoardContentCount(@PathVariable("boardName") String boardName) throws Exception{
		Map<String, Long> resultMap = new HashMap<String,Long>();
		resultMap.put("rowCount", boardService.getBoardContentCount(boardName));
		return ResponseEntity.ok(resultMap);
	}
	@GetMapping("/{boardName}/{boardIdx}")	//게시판 글 상세정보
	public ResponseEntity<BoardInfo> getBoardContentDetail(
			@PathVariable("boardName") String boardName,
			@PathVariable("boardIdx") Integer boardIdx) throws Exception{
		
		BoardInfo resultBoardInfo = boardService.getBoardContentDetail(boardName, boardIdx);
		
		return ResponseEntity.ok(resultBoardInfo);
	}
	
	@DeleteMapping("/{boardName}/{boardIdx}")	//게시판 글 삭제
	public ResponseEntity<Map<String,String>> deleteBoardContent(
			@PathVariable("boardName") String boardName,
			@PathVariable("boardIdx") Integer boardIdx) throws Exception{
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("result", "ok");
		boardService.deleteBoardContent(boardName, boardIdx);
		
		return ResponseEntity.ok(resultMap);
	}
}
