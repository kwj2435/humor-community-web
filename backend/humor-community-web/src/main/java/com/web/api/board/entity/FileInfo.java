package com.web.api.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="tb_file")
public class FileInfo {
	@Id
	@Column(name = "file_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileNo;
	@Column(name = "file_original_name")
	private String fileOriginalName;
	@Column(name = "file_stored_name")
	private String fileStoredName;
	@Column(name = "file_path")
	private String filePath;
	@Column(name = "file_begin_date")
	private Date fileBeginDate;
	
	@PrePersist
	private void onCreate() {
		this.fileBeginDate = new Date();
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileStoredName() {
		return fileStoredName;
	}
	public void setFileStoredName(String fileStoredName) {
		this.fileStoredName = fileStoredName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getFileBeginDate() {
		return fileBeginDate;
	}
	public void setFileBeginDate(Date fileBeginDate) {
		this.fileBeginDate = fileBeginDate;
	}
}
