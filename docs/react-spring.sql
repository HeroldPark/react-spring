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
CREATE DATABASE IF NOT EXISTS `react-spring` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `react-spring`;

-- 테이블 react-spring.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
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
  CONSTRAINT `FK_board_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.board:~0 rows (대략적) 내보내기
DELETE FROM `board`;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`board_id`, `title`, `content`, `view_count`, `created_date`, `modified_date`, `member_id`, `create_date`) VALUES
	(1, 'Hello react-spring world~~', 'start react-spring study...', 7, NULL, '2024/04/02 11:26:31', 1, '2024/04/02 11:21:05');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 react-spring.board_seq 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.board_seq:~1 rows (대략적) 내보내기
DELETE FROM `board_seq`;
/*!40000 ALTER TABLE `board_seq` DISABLE KEYS */;
INSERT INTO `board_seq` (`next_val`) VALUES
	(51);
/*!40000 ALTER TABLE `board_seq` ENABLE KEYS */;

-- 테이블 react-spring.comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment` (
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
  CONSTRAINT `FK_comment_board` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_comment_member` FOREIGN KEY (`user_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.comment:~0 rows (대략적) 내보내기
DELETE FROM `comment`;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`comment_id`, `content`, `created_date`, `modified_date`, `board_id`, `user_id`, `create_date`, `member_id`) VALUES
	(2, '', '', '2024/04/02 11:25:13', 1, NULL, '2024/04/02 11:25:13', 1),
	(3, '댓글 테스트', '', '2024/04/02 11:26:06', 1, NULL, '2024/04/02 11:26:06', 1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 react-spring.comment_seq 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.comment_seq:~1 rows (대략적) 내보내기
DELETE FROM `comment_seq`;
/*!40000 ALTER TABLE `comment_seq` DISABLE KEYS */;
INSERT INTO `comment_seq` (`next_val`) VALUES
	(101);
/*!40000 ALTER TABLE `comment_seq` ENABLE KEYS */;

-- 테이블 react-spring.file 구조 내보내기
CREATE TABLE IF NOT EXISTS `file` (
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
  CONSTRAINT `FK_file_board` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.file:~0 rows (대략적) 내보내기
DELETE FROM `file`;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;

-- 테이블 react-spring.file_seq 구조 내보내기
CREATE TABLE IF NOT EXISTS `file_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.file_seq:~1 rows (대략적) 내보내기
DELETE FROM `file_seq`;
/*!40000 ALTER TABLE `file_seq` DISABLE KEYS */;
INSERT INTO `file_seq` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `file_seq` ENABLE KEYS */;

-- 테이블 react-spring.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
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

-- 테이블 데이터 react-spring.member:~1 rows (대략적) 내보내기
DELETE FROM `member`;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`, `email`, `username`, `password`, `role`, `created_date`, `modified_date`, `create_date`, `roles`) VALUES
	(1, 'admin@deltax.ai', 'admin', '$2a$10$HTWwKtPoEo2dfnw7rXMJh.m9Iwn2COOcHquv7TMwKC9BufUMBcCW2', NULL, NULL, '2024/04/02 11:19:39', '2024/04/02 11:19:39', 'USER');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 react-spring.member_seq 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.member_seq:~1 rows (대략적) 내보내기
DELETE FROM `member_seq`;
/*!40000 ALTER TABLE `member_seq` DISABLE KEYS */;
INSERT INTO `member_seq` (`next_val`) VALUES
	(51);
/*!40000 ALTER TABLE `member_seq` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
