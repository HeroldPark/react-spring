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

-- 테이블 react-spring.employee 구조 내보내기
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='for JPA';

-- 테이블 데이터 react-spring.employee:~8 rows (대략적) 내보내기
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employee_id`, `name`, `role`, `create_date`, `modified_date`) VALUES
	(2, 'Bilbo Baggins', 'burglar', '2024/04/22 10:14:16', '2024/04/22 10:14:16'),
	(3, 'Frodo Baggins', 'thief', '2024/04/22 10:14:16', '2024/04/22 10:14:16'),
	(4, 'Bilbo Baggins', 'burglar', '2024/04/22 10:50:03', '2024/04/22 10:50:03'),
	(5, 'Frodo Baggins', 'thief', '2024/04/22 10:50:03', '2024/04/22 10:50:03'),
	(6, 'Bilbo Baggins', 'burglar', '2024/04/22 13:08:21', '2024/04/22 13:08:21'),
	(7, 'Frodo Baggins', 'thief', '2024/04/22 13:08:21', '2024/04/22 13:08:21'),
	(8, 'Bilbo Baggins', 'burglar', '2024/04/23 07:57:43', '2024/04/23 07:57:43'),
	(9, 'Frodo Baggins', 'thief', '2024/04/23 07:57:43', '2024/04/23 07:57:43'),
	(10, 'Bilbo Baggins', 'burglar', '2024/04/23 09:32:02', '2024/04/23 09:32:02'),
	(11, 'Frodo Baggins', 'thief', '2024/04/23 09:32:02', '2024/04/23 09:32:02'),
	(12, 'Bilbo Baggins', 'burglar', '2024/04/23 09:33:23', '2024/04/23 09:33:23'),
	(13, 'Frodo Baggins', 'thief', '2024/04/23 09:33:23', '2024/04/23 09:33:23'),
	(14, 'Bilbo Baggins', 'burglar', '2024/04/23 09:34:05', '2024/04/23 09:34:05'),
	(15, 'Frodo Baggins', 'thief', '2024/04/23 09:34:06', '2024/04/23 09:34:06'),
	(16, 'Bilbo Baggins', 'burglar', '2024/04/23 09:34:38', '2024/04/23 09:34:38'),
	(17, 'Frodo Baggins', 'thief', '2024/04/23 09:34:38', '2024/04/23 09:34:38'),
	(18, 'Bilbo Baggins', 'burglar', '2024/04/23 09:40:19', '2024/04/23 09:40:19'),
	(19, 'Frodo Baggins', 'thief', '2024/04/23 09:40:19', '2024/04/23 09:40:19'),
	(20, 'Bilbo Baggins', 'burglar', '2024/04/23 10:05:27', '2024/04/23 10:05:27'),
	(21, 'Frodo Baggins', 'thief', '2024/04/23 10:05:27', '2024/04/23 10:05:27'),
	(22, 'Bilbo Baggins', 'burglar', '2024/04/23 10:10:27', '2024/04/23 10:10:27'),
	(23, 'Frodo Baggins', 'thief', '2024/04/23 10:10:27', '2024/04/23 10:10:27'),
	(24, 'Bilbo Baggins', 'burglar', '2024/04/23 10:26:09', '2024/04/23 10:26:09'),
	(25, 'Frodo Baggins', 'thief', '2024/04/23 10:26:10', '2024/04/23 10:26:10'),
	(26, 'Bilbo Baggins', 'burglar', '2024/04/23 10:55:02', '2024/04/23 10:55:02'),
	(27, 'Frodo Baggins', 'thief', '2024/04/23 10:55:03', '2024/04/23 10:55:03'),
	(28, 'Bilbo Baggins', 'burglar', '2024/04/23 10:56:19', '2024/04/23 10:56:19'),
	(29, 'Frodo Baggins', 'thief', '2024/04/23 10:56:19', '2024/04/23 10:56:19');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
