package com.web.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.board.entity.FileInfo;

public interface FileRepository extends JpaRepository<FileInfo, Integer>{
	public FileInfo findByFileNo(int fileNo);
}
