package com.web.api.common.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.api.common.entity.FileInfo;
import com.web.api.common.exception.CustomFileDownloadException;
import com.web.api.common.exception.CustomFileUploadException;
import com.web.api.common.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	private Path fileLocation;
	private final String FILE_PATH = "C:\\upload\\";
	
	public FileService() {		
		fileLocation = Paths.get(FILE_PATH).normalize();
		
		try{
			Files.createDirectories(this.fileLocation);
		}catch(IOException e) {
			throw new CustomFileUploadException("파일 업로드 디렉토리 생성 실패.", e);
		}
	}
	
	public String uploadMultiFile(MultipartFile[] file,int boardIdx,int fileGubun) throws IllegalStateException, IOException {
		UUID uuid = UUID.randomUUID();
		String fileOriginalName = null;
		String fileStoredName = null;
		String filePath = null;
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		
		for(int i = 0 ; i < file.length ; i++) {
			FileInfo fileInfo = new FileInfo();
			System.out.println(file[i].getName());
			fileOriginalName = file[i].getOriginalFilename();
			fileStoredName = uuid.toString() + "_" + fileOriginalName;
			filePath = FILE_PATH + fileStoredName;
			
			if(fileGubun == 0) { fileInfo.setFileStatus(0); }	//첨부파일  1 or 게시글사진및 동영상 0
			else { fileInfo.setFileStatus(1); }
			fileInfo.setBoardIdx(boardIdx);
			fileInfo.setFileOriginalName(fileOriginalName);
			fileInfo.setFileStoredName(fileStoredName);
			fileInfo.setFilePath(filePath);
			
			fileList.add(fileInfo);
			file[i].transferTo(new File(FILE_PATH + fileStoredName));
		}
		System.out.println(fileList.size());
		fileRepository.saveAll(fileList);
		return FILE_PATH + fileStoredName;
	}
	public void uploadSingleFile() {
		
	}
	public Resource downloadFile(int fileNo) {
		FileInfo fileInfo = fileRepository.findByFileNo(fileNo);
		try {
			Path filePath = this.fileLocation.resolve(fileInfo.getFileStoredName()).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if(resource.exists()) {
				return resource;
			}else {
				throw new CustomFileDownloadException("파일을 찾을 수 없습니다.");
			}
		}catch(Exception e) {
			throw new CustomFileDownloadException("파일을 찾을 수 없습니다.",e);
		}
	}
	public FileInfo getFileInfo(int fileNo) {
		
		FileInfo savedFileInfo = fileRepository.findByFileNo(fileNo);
		
		return savedFileInfo;
	}
	public List<FileInfo> getFileInfoList(int boardIdx){
		return fileRepository.findAllByBoardIdx(boardIdx);
	}
}
