package com.web.api.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_board")
public class BoardInfo {
	@Id
	@Column(name = "board_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardIdx;
	@Column(name = "board_name")
	private String boardName;
	@Column(name = "board_content_title")
	private String boardContentTitle;
	@Lob
	@Column(name = "board_content",length = 100000)
	private String boardContent;
	@Column(name = "board_content_writer")
	private String boardContentWriter;
	@Column(name = "board_view_count")
	private int boardViewCount;
	@Column(name = "board_content_begin")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date boardContentBegin;
	
	@PrePersist
	private void onCreate() {
		this.boardContentBegin = new Date();
	}
	public Date getBoardContentBegin() {
		return boardContentBegin;
	}
	public void setBoardContentBegin(Date boardContentBegin) {
		this.boardContentBegin = boardContentBegin;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardContentTitle() {
		return boardContentTitle;
	}
	public void setBoardContentTitle(String boardContentTitle) {
		this.boardContentTitle = boardContentTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardContentWriter() {
		return boardContentWriter;
	}
	public void setBoardContentWriter(String boardContentWriter) {
		this.boardContentWriter = boardContentWriter;
	}
	public int getBoardViewCount() {
		return boardViewCount;
	}
	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}
	
}
