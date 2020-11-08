package com.web.api.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_comment")
public class CommentInfo {
	@Id
	@Column(name = "comment_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentIdx;
	@Column(name = "board_idx")
	private int boardIdx;
	@Column(name = "comment_no")
	private int commentNo;
	@Column(name = "comment_depth")
	private int commentDepth;
	@Column(name = "comment_content")
	private String commentContent;
	@Column(name = "comment_user_email")
	private String commentUserEmail;
	@Column(name = "comment_user_nickname")
	private String commentUserNickname;
	@Column(name = "comment_begin_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date commentBeginDate;
	
	@PrePersist
	private void onCreate() {
		this.commentBeginDate = new Date();
	}
	public int getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(int commentIdx) {
		this.commentIdx = commentIdx;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public int getCommentDepth() {
		return commentDepth;
	}
	public void setCommentDepth(int commentDepth) {
		this.commentDepth = commentDepth;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentUserEmail() {
		return commentUserEmail;
	}
	public void setCommentUserEmail(String commentUserEmail) {
		this.commentUserEmail = commentUserEmail;
	}
	public String getCommentUserNickname() {
		return commentUserNickname;
	}
	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}
	public Date getCommentBeginDate() {
		return commentBeginDate;
	}
	public void setCommentBeginDate(Date commentBeginDate) {
		this.commentBeginDate = commentBeginDate;
	}
}
