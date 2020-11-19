package com.web.api.board.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.api.board.dto.BoardContentUpdateVO;
import com.web.api.board.entity.BoardInfo;
import com.web.api.board.entity.BoardListInfo;
import com.web.api.board.service.BoardService;
import com.web.api.common.entity.FileInfo;
import com.web.api.common.service.FileService;

import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	
	@ApiOperation(value = "게시판 글 등록")
	@PostMapping("/")
	public ResponseEntity<EntityModel<BoardInfo>> postBoardContent(
			BoardInfo boardInfo,
			@RequestParam(value = "boardFile", required = false)MultipartFile[] file,
			@RequestParam(value = "fileGubun") Integer fileGubun) throws Exception{
		
		BoardInfo resultInfo = boardService.postBoardContent(boardInfo);
		
		System.out.println(file);
		if(file != null) {
			fileService.uploadMultiFile(file,resultInfo.getBoardIdx(),fileGubun);
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resultInfo.getBoardIdx())
				.toUri();
		
		EntityModel<BoardInfo> resource = EntityModel.of(resultInfo)
				.add(linkTo(methodOn(BoardController.class).postBoardContent(boardInfo, file, fileGubun)).withSelfRel())
				.add(linkTo(methodOn(BoardController.class)
						.getBoardContentDetail(boardInfo.getBoardName(), resultInfo.getBoardIdx())).withRel("contentDetail"));
		
		return ResponseEntity.created(uri).body(resource);
	}

	@ApiOperation(value = "게시판 목록 리스트",notes = "사용중인 게시판 목록을 가져옵니다.")
	@GetMapping("/list")	//게시판 목록
	public ResponseEntity<CollectionModel<BoardListInfo>> getBoardList() throws Exception{
	
		List<BoardListInfo> boardList = boardService.getBoardNameList();
		
		CollectionModel<BoardListInfo> resource = CollectionModel
											.of(boardList)
											.add(linkTo(methodOn(BoardController.class).getBoardList()).withSelfRel());
		
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation("게시판 글 목록")
	@GetMapping("/{boardName}")	//게시판 글 목록
	public ResponseEntity<List<BoardInfo>> getBoardContentList(
			@PathVariable("boardName") String boardName,
			@RequestParam("currentPage") Integer currentPage,
			@RequestParam("limit") Integer limit) throws Exception{

		List<BoardInfo> boardContentList = boardService.getBoardContentList(boardName,currentPage,limit);
		
		return ResponseEntity.ok(boardContentList);
	}
	
	@ApiOperation("메인 화면 게시판 글 목록")
	@GetMapping(value = "/{boardName}/list",produces = "application/json; charset=utf8")  //메인 게시판 글 목록
	public ResponseEntity<List<BoardInfo>> getBoardContentListForMain(
			@PathVariable("boardName") String boardName,
			@RequestParam("limitNum") Integer limit) throws Exception{
		
		List<BoardInfo> requestResult = boardService.getBoardContentListForMain(boardName,limit);
		
		//HATEOAS
		return ResponseEntity.ok(requestResult);
	}
	
	@ApiOperation("게시판 글 갯수")
	@GetMapping("/{boardName}/rows")	//게시판 글 갯수
	public ResponseEntity<Map<String,Long>> geteBoardContentCount(@PathVariable("boardName") String boardName) throws Exception{
		
		Map<String, Long> resultMap = new HashMap<String,Long>();
		
		resultMap.put("rowCount", boardService.getBoardContentCount(boardName));
		
		return ResponseEntity.ok(resultMap);
	}
	
	@ApiOperation("게시판 글 상세정보")
	@GetMapping("/{boardName}/{boardIdx}")	//게시판 글 상세정보
	public ResponseEntity<EntityModel<BoardInfo>> getBoardContentDetail(
			@PathVariable("boardName") String boardName,
			@PathVariable("boardIdx") Integer boardIdx) throws Exception{
		
		BoardInfo resultBoardInfo = boardService.getBoardContentDetail(boardName, boardIdx);
		
		EntityModel<BoardInfo> resource = EntityModel.of(resultBoardInfo)
													 	.add(linkTo(methodOn(BoardController.class)
													 			.getBoardContentDetail(boardName, boardIdx))
													 			.withSelfRel());
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation("게시판 글 삭제")
	@DeleteMapping("/{boardName}/{boardIdx}")	//게시판 글 삭제
	public ResponseEntity<Map<String,String>> deleteBoardContent(
			@PathVariable("boardName") String boardName,
			@PathVariable("boardIdx") Integer boardIdx) throws Exception{
		
		Map<String,String> resultMap = new HashMap<String,String>();
		
		resultMap.put("result", "ok");
		boardService.deleteBoardContent(boardName, boardIdx);
		
		return ResponseEntity.ok(resultMap);
	}
	
	@ApiOperation("게시판 글 수정")
	@PutMapping("/{boardName}/{boardIdx}")
	public ResponseEntity<EntityModel<BoardInfo>> updateBoardContent(
			@PathVariable("boardName")String boardName,
			@PathVariable("boardIdx")Integer boardIdx,
			@RequestBody BoardContentUpdateVO boardContent){
		
		BoardInfo resultBoardInfo = boardService.updateBoardContent(boardName, boardIdx, boardContent);
		
		EntityModel<BoardInfo> resource = EntityModel.of(resultBoardInfo)
													 	.add(linkTo(methodOn(BoardController.class)
															.updateBoardContent(boardName, boardIdx, boardContent))
													 		.withSelfRel());
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation("게시판 첨부파일 리스트")
	@GetMapping("/{boardIdx}/files")
	public ResponseEntity<CollectionModel<FileInfo>> getBoardFileList(@PathVariable Integer boardIdx){
		
		List<FileInfo> resultFileList = fileService.getFileInfoList(boardIdx);
		
		CollectionModel<FileInfo> resource = CollectionModel.of(resultFileList)
																.add(linkTo(methodOn(BoardController.class)
																	.getBoardFileList(boardIdx))
																    .withSelfRel());
		return ResponseEntity.ok(resource);
	}
	
	@ApiOperation("게시글 조회수 증가")
	@PutMapping("/{boardName}/{boardIdx}/counts")
	public ResponseEntity<Map<String,String>> increaseViewConut(
			@PathVariable String boardName,
			@PathVariable Integer boardIdx){
		
		Map<String,String> resultMap = new HashMap<String,String>();
		
		boardService.updateBoardViewCount(boardName,boardIdx);
		resultMap.put("result", "ok");
		
		return ResponseEntity.ok(resultMap);
	}
}
