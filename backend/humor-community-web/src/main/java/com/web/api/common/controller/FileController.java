package com.web.api.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.api.board.entity.FileInfo;
import com.web.api.common.service.FileService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/api/")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@ApiOperation("파일 업로드")
	@SuppressWarnings("unchecked")
	@PostMapping("/upload")
	public JSONObject fileUpload(
			List<MultipartFile> file,
			@RequestBody Integer boardIdx,
			@RequestBody Integer fileGubun) throws IllegalStateException, IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uploaded", true);
		jsonObject.put("url","http://localhost:8081/v1/api");
		fileService.uploadMultiFile(file,boardIdx,fileGubun);
		return jsonObject;
	}
	@ApiOperation("파일 이름 가져오기")
	@GetMapping("/file/boardIdx")
	public ResponseEntity<FileInfo> getFileOriginalName(@PathVariable("boardIdx") Integer boardIdx){
		
		FileInfo fileInfo = fileService.getFileInfo(boardIdx);
		
		return ResponseEntity.ok(fileInfo);
	}
}
