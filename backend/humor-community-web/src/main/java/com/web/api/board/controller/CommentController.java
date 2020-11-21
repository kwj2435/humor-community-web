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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.api.board.entity.CommentInfo;
import com.web.api.board.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/api/comment/")
@Api(value="CommentController V1")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "댓글 작성",notes = "입력된 댓글을 저장합니다.")
	@PostMapping("/")	// 댓글 작성
	public ResponseEntity<CommentInfo> writeComment(@RequestBody CommentInfo commentInfo){
		
		CommentInfo resultInfo = commentService.writeComment(commentInfo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resultInfo.getBoardIdx())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "댓글 목록",notes = "게시글에 대한 댓글목록을 가져옵니다.")
	@GetMapping("/{boardIdx}")	// Get 댓글목록
	public ResponseEntity<List<CommentInfo>> getCommentList(@PathVariable("boardIdx")Integer boardIdx){
		return ResponseEntity.ok(commentService.getComment(boardIdx));
	}
	
	@ApiOperation(value = "댓글 삭제")
	@DeleteMapping("/{commentIdx}")	// Delete 댓글
	public ResponseEntity<Map<String,Integer>> deleteComment(@PathVariable("commentIdx") Integer commentIdx) throws Exception{
		
		Map<String,Integer> deletedCommentIdx = new HashMap<String,Integer>();

		deletedCommentIdx.put("result", commentService.deleteComment(commentIdx));
		
		return ResponseEntity.ok(deletedCommentIdx);
	}
	
	@ApiOperation(value = "댓글 수정",notes = "수정된 댓글을 저장합니다.")
	@PutMapping("/{commentIdx}")	// Update 댓글
	public ResponseEntity<CommentInfo> updateComment(
			@ApiParam(value = "댓글 Idx 값")
			@PathVariable("commentIdx") Integer commentIdx,
			@ApiParam(value = "Json형태 댓글내용(CommentContent)")
			@RequestBody Map<String,String> comment){
		
		System.out.println(comment.get("commentContent"));
		CommentInfo resultCommentInfo = commentService.updateComment(commentIdx,comment.get("commentContent"));
		
		return ResponseEntity.ok(resultCommentInfo);
	}
}
