package com.web.api.board.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.api.board.entity.CommentInfo;
import com.web.api.board.service.CommentService;

@RestController
@RequestMapping("/v1/api/comment/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/")	// 댓글 작성
	public ResponseEntity<CommentInfo> writeComment(@RequestBody CommentInfo commentInfo){
		
		CommentInfo resultInfo = commentService.writeComment(commentInfo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resultInfo.getBoardIdx())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{boardIdx}")	// Get 댓글목록
	public ResponseEntity<List<CommentInfo>> getCommentList(@PathVariable("boardIdx")Integer boardIdx){
		return ResponseEntity.ok(commentService.getComment(boardIdx));
	}
	
	@DeleteMapping("/{commentIdx}")	// Delete 댓글
	public ResponseEntity<Map<String,Integer>> deleteComment(@PathVariable("commentIdx") Integer commentIdx) throws Exception{
		
		Map<String,Integer> deletedCommentIdx = new HashMap<String,Integer>();

		deletedCommentIdx.put("result", commentService.deleteComment(commentIdx));
		
		return ResponseEntity.ok(deletedCommentIdx);
	}
	
	@PatchMapping("/{commentIdx}")	// Update 댓글
	public ResponseEntity<CommentInfo> updateComment(@RequestBody Integer commentIdx){
		
	}
}
