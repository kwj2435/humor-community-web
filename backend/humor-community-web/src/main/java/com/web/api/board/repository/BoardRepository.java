package com.web.api.board.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.api.board.entity.BoardInfo;

public interface BoardRepository extends JpaRepository<BoardInfo, Integer> {

    public List<BoardInfo> findAllByBoardName(String boardName, Pageable pageable);

    @Query("SELECT a.boardName FROM BoardInfo a")
    public List<String> findBoardNameList();

    public BoardInfo findByBoardNameAndBoardIdx(String boardName, int boardIdx);

    @Transactional
    public void deleteByBoardNameAndBoardIdx(String boardName, int boardIdx);

    public Long countByBoardName(String boardName);
}
