-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.0.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- react-spring 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `react-spring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `react-spring`;

-- 테이블 react-spring.tbl_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '댓글 번호 (PK)',
  `post_id` bigint(20) NOT NULL COMMENT '게시글 번호 (FK)',
  `content` varchar(1000) NOT NULL COMMENT '내용',
  `writer` varchar(20) NOT NULL COMMENT '작성자',
  `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`),
  KEY `fk_post_comment` (`post_id`),
  CONSTRAINT `fk_post_comment` FOREIGN KEY (`post_id`) REFERENCES `tbl_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='댓글';

-- 테이블 데이터 react-spring.tbl_comment:~0 rows (대략적) 내보내기
DELETE FROM `tbl_comment`;
/*!40000 ALTER TABLE `tbl_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_comment` ENABLE KEYS */;

-- 테이블 react-spring.tbl_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '파일 번호 (PK)',
  `post_id` bigint(20) NOT NULL COMMENT '게시글 번호 (FK)',
  `original_name` varchar(255) NOT NULL COMMENT '원본 파일명',
  `save_name` varchar(40) NOT NULL COMMENT '저장 파일명',
  `size` int(11) NOT NULL COMMENT '파일 크기',
  `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `deleted_date` datetime DEFAULT NULL COMMENT '삭제일시',
  PRIMARY KEY (`id`),
  KEY `fk_post_file` (`post_id`),
  CONSTRAINT `fk_post_file` FOREIGN KEY (`post_id`) REFERENCES `tbl_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='파일';

-- 테이블 데이터 react-spring.tbl_file:~0 rows (대략적) 내보내기
DELETE FROM `tbl_file`;
/*!40000 ALTER TABLE `tbl_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_file` ENABLE KEYS */;

-- 테이블 react-spring.tbl_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '회원 번호 (PK)',
  `login_id` varchar(20) NOT NULL COMMENT '로그인 ID',
  `password` varchar(60) NOT NULL COMMENT '비밀번호',
  `name` varchar(20) NOT NULL COMMENT '이름',
  `gender` tinyint(1) DEFAULT 1 COMMENT '성별(0: M, 1:W)',
  `birthday` varchar(30) DEFAULT NULL COMMENT '생년월일',
  `delete_yn` tinyint(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uix_member_login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='회원';

-- 테이블 데이터 react-spring.tbl_member:~1 rows (대략적) 내보내기
DELETE FROM `tbl_member`;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` (`id`, `login_id`, `password`, `name`, `gender`, `birthday`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(1, 'admin', '1', '관리자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52');
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;

-- 테이블 react-spring.tbl_post 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `title` varchar(100) NOT NULL COMMENT '제목',
  `content` varchar(3000) NOT NULL COMMENT '내용',
  `writer` varchar(20) NOT NULL COMMENT '작성자',
  `view_cnt` int(11) NOT NULL COMMENT '조회 수',
  `notice_yn` tinyint(1) NOT NULL COMMENT '공지글 여부',
  `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6018 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글';

-- 테이블 데이터 react-spring.tbl_post:~0 rows (대략적) 내보내기
DELETE FROM `tbl_post`;
/*!40000 ALTER TABLE `tbl_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_post` ENABLE KEYS */;

-- 테이블 react-spring.tb_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(50) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FK_board_member` (`member_id`),
  CONSTRAINT `FK_board_member` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_board:~2 rows (대략적) 내보내기
DELETE FROM `tb_board`;
/*!40000 ALTER TABLE `tb_board` DISABLE KEYS */;
INSERT INTO `tb_board` (`board_id`, `title`, `content`, `view_count`, `created_date`, `modified_date`, `member_id`, `create_date`) VALUES
	(1, 'Hello react-spring world~~', 'start react-spring study...', 21, NULL, '2024/05/06 16:09:34', 1, '2024/04/02 11:21:05'),
	(2, 'test', 'contents', 7, NULL, '2024/04/26 09:09:28', 1, '2024/04/25 16:31:29');
/*!40000 ALTER TABLE `tb_board` ENABLE KEYS */;

-- 테이블 react-spring.tb_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(50) NOT NULL DEFAULT '',
  `created_date` varchar(50) NOT NULL DEFAULT '',
  `modified_date` varchar(50) NOT NULL DEFAULT '',
  `board_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_comment_board` (`board_id`),
  KEY `FK_comment_member` (`user_id`),
  KEY `FKmrrrpi513ssu63i2783jyiv9m` (`member_id`),
  CONSTRAINT `FK_comment_board` FOREIGN KEY (`board_id`) REFERENCES `tb_board` (`board_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_comment_member` FOREIGN KEY (`user_id`) REFERENCES `tb_member` (`member_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_comment:~3 rows (대략적) 내보내기
DELETE FROM `tb_comment`;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
INSERT INTO `tb_comment` (`comment_id`, `content`, `created_date`, `modified_date`, `board_id`, `user_id`, `create_date`, `member_id`) VALUES
	(2, '', '', '2024/04/02 11:25:13', 1, NULL, '2024/04/02 11:25:13', 1),
	(3, '댓글 테스트', '', '2024/04/02 11:26:06', 1, NULL, '2024/04/02 11:26:06', 1),
	(4, 'Writing success', '', '2024/04/25 16:39:39', 2, NULL, '2024/04/25 16:39:39', 1);
/*!40000 ALTER TABLE `tb_comment` ENABLE KEYS */;

-- 테이블 react-spring.tb_employee 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='for JPA';

-- 테이블 데이터 react-spring.tb_employee:~11 rows (대략적) 내보내기
DELETE FROM `tb_employee`;
/*!40000 ALTER TABLE `tb_employee` DISABLE KEYS */;
INSERT INTO `tb_employee` (`employee_id`, `name`, `role`, `created_date`, `modified_date`) VALUES
	(30, 'Bilbo Baggins', 'burglar', '2024/04/23 11:10:59', '2024/04/23 11:10:59'),
	(31, 'Frodo Baggins', 'thief', '2024/04/23 11:10:59', '2024/04/23 11:10:59'),
	(32, 'Bilbo 첫 번째', 'burglar', '2024/04/23 14:40:09', '2024/04/23 14:40:09'),
	(33, 'Frodo 두 번째', 'thief', '2024/04/23 14:40:09', '2024/04/23 14:40:09'),
	(34, 'Bilbo 첫 번째', 'burglar', '2024/04/23 15:01:46', '2024/04/23 15:01:46'),
	(35, 'Frodo 두 번째', 'thief', '2024/04/23 15:01:46', '2024/04/23 15:01:46'),
	(36, 'Bilbo 첫 번째', 'burglar', '2024/04/23 15:05:51', '2024/04/23 15:05:51'),
	(37, 'Frodo 두 번째', 'thief', '2024/04/23 15:05:51', '2024/04/23 15:05:51'),
	(38, 'admin', 'administator', '2024/04/23 16:06:20', '2024/04/23 16:06:20'),
	(39, 'admin', 'administator', '2024/04/23 16:09:38', '2024/04/23 16:09:38'),
	(40, 'Bilbo 첫 번째', 'burglar', '2024/04/24 08:30:58', '2024/04/24 08:30:58');
/*!40000 ALTER TABLE `tb_employee` ENABLE KEYS */;

-- 테이블 react-spring.tb_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_file` (
  `file_id` bigint(20) NOT NULL,
  `origin_file_name` varchar(50) NOT NULL,
  `file_path` varchar(50) NOT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `board_id` int(11) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `FK_file_board` (`board_id`),
  CONSTRAINT `FK_file_board` FOREIGN KEY (`board_id`) REFERENCES `tb_board` (`board_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_file:~0 rows (대략적) 내보내기
DELETE FROM `tb_file`;
/*!40000 ALTER TABLE `tb_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_file` ENABLE KEYS */;

-- 테이블 react-spring.tb_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `roles` enum('ADMIN','USER') DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_member:~0 rows (대략적) 내보내기
DELETE FROM `tb_member`;
/*!40000 ALTER TABLE `tb_member` DISABLE KEYS */;
INSERT INTO `tb_member` (`member_id`, `email`, `username`, `password`, `role`, `created_date`, `modified_date`, `create_date`, `roles`) VALUES
	(1, 'admin@deltax.ai', 'admin', '$2a$10$HTWwKtPoEo2dfnw7rXMJh.m9Iwn2COOcHquv7TMwKC9BufUMBcCW2', NULL, NULL, '2024/04/02 11:19:39', '2024/04/02 11:19:39', 'USER');
/*!40000 ALTER TABLE `tb_member` ENABLE KEYS */;

-- 테이블 react-spring.tb_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 react-spring.tb_user:~2 rows (대략적) 내보내기
DELETE FROM `tb_user`;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`USER_ID`, `ID`, `NAME`, `ROLE`) VALUES
	(1, 'admin', 'Administrator', 'ADMIN'),
	(2, 'user', 'User Name', 'USER');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
