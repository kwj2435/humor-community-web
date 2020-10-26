CREATE DATABASE community;
USE community;

DROP TABLE if EXISTS tb_user;
CREATE TABLE tb_user(
	user_idx INT AUTO_INCREMENT PRIMARY KEY,
	user_email VARCHAR(30) NOT NULL,
	user_password VARCHAR(200) NOT NULL,
	user_nickname VARCHAR(30) NOT NULL,
	user_roll VARCHAR(10) NOT null
);

DROP TABLE IF EXISTS tb_board;
CREATE TABLE tb_board(
	board_idx INT AUTO_INCREMENT PRIMARY KEY,
	board_name VARCHAR(30) NOT NULL,             # 게시판 이름
	board_content_title VARCHAR(200) NOT NULL,	# 게시글 제목
	board_content TEXT NOT NULL,					   # 게시글 내용
	board_content_writer VARCHAR(30) NOT NULL,   # 게시글 작성자
	board_content_begin DATE NOT NULL,            # 작성일
	board_view_count INT NOT NULL                # 조회수
);
	