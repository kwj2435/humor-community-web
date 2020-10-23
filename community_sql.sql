CREATE DATABASE community;
USE community;

DROP TABLE if EXISTS tb_user;
CREATE TABLE tb_user(
	user_idx INT AUTO_INCREMENT PRIMARY KEY,
	user_email VARCHAR(30) NOT NULL,
	user_password VARCHAR(200) NOT NULL,
	user_nickname VARCHAR(30) NOT NULL,
	user_roll VARCHAR(10) NOT null
)