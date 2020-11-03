package com.web.api.common.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.api.board.entity.FileInfo;
import com.web.api.common.repository.FileRepository;

/*
 * 추후 마이페이지와 같이 업로드 기능이 필요한 모듈을 위해 별도 분리
 */
@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	
	private final String FILE_PATH = "C:\\upload\\";
	
	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
		FileInfo fileInfo = new FileInfo();
		UUID uuid = UUID.randomUUID();

		String fileOriginalName = file.getOriginalFilename();
		String fileStoredName = uuid.toString() + "_" + fileOriginalName;
		String filePath = FILE_PATH + fileStoredName;
		
		fileInfo.setFileOriginalName(fileOriginalName);
		fileInfo.setFileStoredName(fileStoredName);
		fileInfo.setFilePath(filePath);
		
		fileRepository.save(fileInfo);
		file.transferTo(new File(FILE_PATH + fileStoredName));
		
		return FILE_PATH + fileStoredName;
	}
}
