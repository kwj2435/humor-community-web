package com.web.api.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.api.board.entity.BoardInfo;

public interface BoardRepository extends JpaRepository<BoardInfo, Integer>{
	public List<BoardInfo> findAllByBoardName(String BoardName);
	@Query("SELECT a.boardName FROM BoardInfo a")
	public List<String> findBoardNameList();
}
