package com.web.api.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.board.entity.BoardListInfo;

public interface BoardListRepository extends JpaRepository<BoardListInfo, String>{
}
