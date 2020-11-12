package com.web.api.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.board.entity.FileInfo;

public interface FileRepository extends JpaRepository<FileInfo, Integer>{
	public FileInfo findByFileNo(int fileNo);
	public List<FileInfo> findAllByBoardIdx(int boardIdx);
}
