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

-- 테이블 react-spring.city 구조 내보내기
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 react-spring.city:~0 rows (대략적) 내보내기
DELETE FROM `city`;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id`, `name`, `state`, `country`) VALUES
	(1, 'San Francisco', 'CA', 'US');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- 테이블 react-spring.tbl_feedback 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '댓글 번호 (PK)',
  `post_id` bigint(20) NOT NULL COMMENT '게시글 번호 (FK)',
  `content` varchar(1000) NOT NULL COMMENT '내용',
  `title` varchar(100) NOT NULL COMMENT '제목',
  `writer` varchar(20) NOT NULL COMMENT '작성자',
  `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`),
  KEY `fk_post_comment` (`post_id`),
  CONSTRAINT `fk_post_comment` FOREIGN KEY (`post_id`) REFERENCES `tbl_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='댓글';

-- 테이블 데이터 react-spring.tbl_feedback:~23 rows (대략적) 내보내기
DELETE FROM `tbl_feedback`;
/*!40000 ALTER TABLE `tbl_feedback` DISABLE KEYS */;
INSERT INTO `tbl_feedback` (`id`, `post_id`, `content`, `title`, `writer`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(103, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:06:22', NULL),
	(104, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:18:03', NULL),
	(105, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:22:22', NULL),
	(106, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:27:37', NULL),
	(108, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:39:37', NULL),
	(109, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 15:42:28', NULL),
	(110, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:12:02', NULL),
	(111, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:13:37', NULL),
	(112, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:15:08', NULL),
	(113, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:28:38', NULL),
	(114, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:29:40', NULL),
	(115, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:42:42', NULL),
	(116, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:45:16', NULL),
	(117, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:47:14', NULL),
	(118, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:49:29', NULL),
	(119, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:50:53', NULL),
	(120, 6018, 'sss', '', 'admin@deltax.ai', 0, '2024-05-29 16:58:56', NULL),
	(121, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 16:59:49', NULL),
	(122, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 17:02:11', NULL),
	(123, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 17:04:28', NULL),
	(124, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 17:05:24', NULL),
	(125, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 17:07:56', NULL),
	(126, 6018, 'ddd', '', 'admin@deltax.ai', 0, '2024-05-29 17:11:20', NULL);
/*!40000 ALTER TABLE `tbl_feedback` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='회원';

-- 테이블 데이터 react-spring.tbl_member:~13 rows (대략적) 내보내기
DELETE FROM `tbl_member`;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` (`id`, `login_id`, `password`, `name`, `gender`, `birthday`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(1, 'admin', '1', '관리자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(2, 'user 2', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(3, 'user 3', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(4, 'user 4', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(5, 'user 5', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(6, 'user 6', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(7, 'user 7', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(8, 'user 8', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(9, 'user 9', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(10, 'user 10', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(11, 'user 11', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(12, 'user 12', '1', '사용자', 1, '2024-05-07', 0, '2024-05-07 14:28:52', '2024-05-07 14:28:52'),
	(13, 'test', '$2a$10$I/iXcBFjDsdMgy6X6EQ.1.MlnLihuBrORWFdUcSuOTzxy/0zMGroS', '테스트', 0, '2024-05-08', 0, '2024-05-10 08:14:49', NULL);
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;

-- 테이블 react-spring.tbl_picture 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `writer` varchar(50) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `view_cnt` int(6) DEFAULT NULL,
  `delete_yn` tinyint(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 react-spring.tbl_picture:~2 rows (대략적) 내보내기
DELETE FROM `tbl_picture`;
/*!40000 ALTER TABLE `tbl_picture` DISABLE KEYS */;
INSERT INTO `tbl_picture` (`id`, `title`, `content`, `writer`, `file_path`, `view_cnt`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(1, 'Test Title', 'Test Content', 'tester', '/picture/john-lee.jpg', 2, 0, '2024-05-27 13:08:10', '2024-05-27 13:08:10'),
	(2, 'Test Title 1', 'Test Content 1', 'tester', '/video/sam.mp4', 2, 0, '2024-05-27 13:13:39', '2024-05-27 13:13:39');
/*!40000 ALTER TABLE `tbl_picture` ENABLE KEYS */;

-- 테이블 react-spring.tbl_post 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `title` varchar(100) NOT NULL COMMENT '제목',
  `content` text NOT NULL COMMENT '내용',
  `writer` varchar(20) NOT NULL COMMENT '작성자',
  `view_cnt` int(11) NOT NULL COMMENT '조회 수',
  `notice_yn` tinyint(1) DEFAULT NULL COMMENT '공지글 여부',
  `delete_yn` tinyint(1) NOT NULL COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6019 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글';

-- 테이블 데이터 react-spring.tbl_post:~3 rows (대략적) 내보내기
DELETE FROM `tbl_post`;
/*!40000 ALTER TABLE `tbl_post` DISABLE KEYS */;
INSERT INTO `tbl_post` (`id`, `title`, `content`, `writer`, `view_cnt`, `notice_yn`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(1, 'Test Title 1', 'Test Content 1', 'tester', 12, 1, 0, '2024-05-09 10:32:23', '2024-05-31 07:41:01'),
	(2, 'Test Title 2', 'Test Content 2', 'tester', 19, 1, 0, '2024-05-10 09:18:00', '2024-05-31 07:41:06'),
	(6018, 'aaa', 'bbb', 'admin@deltax.ai', 148, NULL, 0, '2024-05-28 11:57:42', '2024-05-31 08:47:37');
/*!40000 ALTER TABLE `tbl_post` ENABLE KEYS */;

-- 테이블 react-spring.tb_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(2048) NOT NULL,
  `content` text DEFAULT NULL COMMENT '65535 chars까지 가능',
  `view_count` int(11) DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FK_board_member` (`member_id`),
  CONSTRAINT `FK_board_member` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_board:~2 rows (대략적) 내보내기
DELETE FROM `tb_board`;
/*!40000 ALTER TABLE `tb_board` DISABLE KEYS */;
INSERT INTO `tb_board` (`board_id`, `title`, `content`, `view_count`, `member_id`, `created_date`, `modified_date`) VALUES
	(1, 'Hello react-spring world~~', 'start react-spring study...\n\n1. Spring boot + React\n2. Spring boot 3.x.x 은 backend에서 처리함.(여기서 Thymeleaf는 사용하지 않는다)\n3. React는 frontend 아래 배치함.\n4. Optional JPA(Java Persistence API), MyBatis\n5. Left or Header Menu\n6. JWT(Josn Web Token) Security - Authority를 계정에 따라 GUEST, USER, ADMIN으로 설정하여 각각의 메뉴를 권한에 따라 사용 가능하게 함.\n7. 일반 로그인, Google OAuth2 로그인', 42, 1, NULL, '2024/05/29 16:23:52'),
	(4, 'Development react-spring on the Spring boot framework', '게시판 관리    => JPA ORM 사용\n . 게시판        => Authentication, Authorization 불필요\n . 직원리스트  => USER 권한만 조회 가능\n . 갤러리형     => 준비 중\n . 카렌다형     => 준비 중\n\n웹서버 관리       => MyBatis 사용\n . 게시판           => "ADMIN", "USER" 권한 필요\n . 사용자리스트   => ADMIN 권한만 조회 가능', 54, 1, '2024/05/08 11:22:10', '2024/05/31 08:19:09');
/*!40000 ALTER TABLE `tb_board` ENABLE KEYS */;

-- 테이블 react-spring.tb_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(2048) NOT NULL DEFAULT '',
  `board_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `created_date` varchar(50) NOT NULL DEFAULT '',
  `modified_date` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`comment_id`),
  KEY `FK_comment_board` (`board_id`),
  KEY `FK_comment_member` (`user_id`),
  KEY `FKmrrrpi513ssu63i2783jyiv9m` (`member_id`),
  CONSTRAINT `FK_comment_board` FOREIGN KEY (`board_id`) REFERENCES `tb_board` (`board_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_comment_member` FOREIGN KEY (`user_id`) REFERENCES `tb_member` (`member_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_comment:~4 rows (대략적) 내보내기
DELETE FROM `tb_comment`;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
INSERT INTO `tb_comment` (`comment_id`, `content`, `board_id`, `user_id`, `member_id`, `created_date`, `modified_date`) VALUES
	(6, 'react-spring 게시판 기본은 github에서 Download 함(https://github.com/jhcode33).\nSpring boot : 3.1.11\nMyBatis : 3.0.3\nJava : 17', 4, NULL, 1, '2024/05/14 11:20:29', '2024/05/14 11:30:57'),
	(7, '왼쪽 메뉴는 아인커뮤니케이션의 static resources customizing 함.\n. Backend(Spring boot)\n. Frontend(React)', 4, NULL, 1, '2024/05/14 11:21:57', '2024/05/14 13:02:48'),
	(8, '상황에 따라 JPA(Java Persistence API)와 MyBatis를 적적하게 사용할 수 있도록 함.', 4, NULL, 1, '2024/05/14 11:24:15', '2024/05/14 11:24:15'),
	(9, 'JWT(Json Web Token)을 적용하여 Authentication(인증), Authorization(인가, 권한)을 사용할 수 있도록 함.\n로그인 하지 않으면 게시판(JPA) 보기만 가능함', 4, NULL, 1, '2024/05/14 11:24:32', '2024/05/16 08:43:33');
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
  `board_id` int(11) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
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
  `roles` enum('ADMIN','USER','GUEST') DEFAULT NULL,
  `picture` varchar(512) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `provider` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tb_member:~3 rows (대략적) 내보내기
DELETE FROM `tb_member`;
/*!40000 ALTER TABLE `tb_member` DISABLE KEYS */;
INSERT INTO `tb_member` (`member_id`, `email`, `username`, `password`, `roles`, `picture`, `created_date`, `modified_date`, `provider`) VALUES
	(1, 'admin@deltax.ai', 'admin', '$2a$10$HTWwKtPoEo2dfnw7rXMJh.m9Iwn2COOcHquv7TMwKC9BufUMBcCW2', 'ADMIN', NULL, '2024/04/02 11:19:39', '2024/04/02 11:19:39', NULL),
	(3, 'user@deltax.ai', 'user', '$2a$10$f2APTXwqSTjmYn8oAcWw.ugYzdVHlvNyoywXrZZvr2ydajtbax3SK', 'USER', NULL, '2024/05/14 08:57:20', '2024/05/14 08:57:20', NULL),
	(7, 'heroldpark@gmail.com', '박용렬', 'p@ssw0rd', 'USER', 'https://lh3.googleusercontent.com/a/ACg8ocKOei1-nXJsQQwULK7zncX2e9UIWrcOVjiUWqSHWMmpLmcR12IWGA=s96-c', '2024/05/27 09:53:08', '2024/05/27 09:53:08', NULL);
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

-- 테이블 react-spring.users 구조 내보내기
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `picture` varchar(1024) DEFAULT NULL,
  `role` enum('ADMIN','USER','GUEST') DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 react-spring.users:~2 rows (대략적) 내보내기
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `email`, `picture`, `role`, `created_date`, `modified_date`) VALUES
	(1, 'admin', 'admin@deltax.ai', 'admin picture', 'ADMIN', NULL, NULL),
	(2, '박용렬', 'heroldpark@gmail.com', 'https://lh3.googleusercontent.com/a/ACg8ocKOei1-nXJsQQwULK7zncX2e9UIWrcOVjiUWqSHWMmpLmcR12IWGA=s96-c', 'ADMIN', NULL, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
