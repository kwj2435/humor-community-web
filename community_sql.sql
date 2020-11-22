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
DROP TABLE if EXISTS tb_roll;
CREATE TABLE tb_roll(
	roll_idx INT AUTO_INCREMENT PRIMARY KEY,
	roll_name VARCHAR(10) NOT NULL,
	roll_description VARCHAR(200)
);
DROP TABLE IF EXISTS tb_board_list;
CREATE TABLE tb_board_list(
	board_name VARCHAR(30) NOT NULL PRIMARY KEY,
	board_description varchar(100)
);
INSERT INTO tb_board_list (board_name,board_description) VALUES ("dandan","단단한유머");
INSERT INTO tb_board_list (board_name,board_description) VALUES ("reading","읽을거리판");
INSERT INTO tb_board_list (board_name,board_description) VALUES ("exercise","운동판");
INSERT INTO tb_board_list (board_name,board_description) VALUES ("it","IT/프로그래밍판");
INSERT INTO tb_board_list (board_name,board_description) VALUES ("consulting","고민상담판");

DROP TABLE IF EXISTS tb_board;
CREATE TABLE tb_board(
	board_idx INT AUTO_INCREMENT PRIMARY KEY,
	board_name VARCHAR(30) NOT NULL,             # 게시판 이름
	board_content_title VARCHAR(200) NOT NULL,	# 게시글 제목
	board_content blob NOT NULL,					   # 게시글 내용
	board_content_writer VARCHAR(30) NOT NULL,   # 게시글 작성자
	board_content_begin DATE NOT NULL,           # 작성일
	board_view_count INT DEFAULT 0 NOT NULL,     # 조회수
	board_recommand_count INT DEFAULT 0 NULL     # 추천수
);


DROP TABLE IF EXISTS tb_file;
CREATE TABLE tb_file(
	file_no int AUTO_INCREMENT PRIMARY KEY,
	board_idx INT NOT NULL,
	file_status INT NOT NULL,
	file_original_name varchar(100) not null,
	file_stored_name varchar(100) NOT NULL,
	file_path VARCHAR(200) NOT NULL,
	file_begin_date date not null
);
DROP TABLE IF EXISTS tb_comment;
CREATE TABLE tb_comment(
	comment_idx INT AUTO_INCREMENT PRIMARY KEY,
	board_idx INT NOT NULL,
	comment_no INT NOT NULL,
	comment_depth INT NOT NULL,
	comment_content VARCHAR(100) NOT NULL,
	comment_user_email VARCHAR(30) NOT NULL,
	comment_user_nickname VARCHAR(30) NOT NULL,
	comment_begin_date DATE NOT null
)

/* 테스트용 임시  데이터 */

INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count,board_recommand_count)
VALUES("dandan","test","test","더미",99991120,0,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("it","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("exercise","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("consulting","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);
INSERT INTO tb_board (board_name,board_content_title,board_content,board_content_writer,board_content_begin,board_view_count)
VALUES("reading","더미데이터","더미데이터","더미",99991120,0);


