package com.web.api.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.api.common.service.FileService;

@RestController
@RequestMapping("/v1/api/")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/upload")
	public JSONObject fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uploaded", true);
		jsonObject.put("url","http://localhost:8081/v1/api");
		fileService.uploadFile(file);
		return jsonObject;
	}
}
