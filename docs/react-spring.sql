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

-- 테이블 react-spring.tbl_common_code 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_common_code` (
  `COMM_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '공용코드 SEQ',
  `COMMON_CODE` varchar(50) NOT NULL COMMENT '공용코드',
  `PARENTS_CODE` varchar(50) NOT NULL COMMENT '상위공용코드',
  `GROUP_CODE` varchar(50) NOT NULL COMMENT '그룹코드',
  `COMMON_NAME` varchar(100) NOT NULL COMMENT '코드이름',
  `COMMENT` varchar(255) DEFAULT NULL COMMENT '코멘트',
  `CODE_ORDER` int(11) NOT NULL COMMENT '순서',
  `USE_YN` char(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
  `DEL_YN` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
  `REG_USER` varchar(50) NOT NULL COMMENT '등록자',
  `REG_DATE` datetime NOT NULL COMMENT '등록일시',
  `MOD_USER` varchar(50) DEFAULT NULL COMMENT '수정자',
  `MOD_DATE` datetime DEFAULT NULL COMMENT '수정일시',
  `REFER_ID1` varchar(255) DEFAULT NULL COMMENT '참조1',
  `REFER_ID2` int(11) DEFAULT NULL COMMENT '참조2',
  PRIMARY KEY (`COMM_SEQ`,`COMMON_CODE`,`PARENTS_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=643 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 react-spring.tbl_common_code:~79 rows (대략적) 내보내기
DELETE FROM `tbl_common_code`;
/*!40000 ALTER TABLE `tbl_common_code` DISABLE KEYS */;
INSERT INTO `tbl_common_code` (`COMM_SEQ`, `COMMON_CODE`, `PARENTS_CODE`, `GROUP_CODE`, `COMMON_NAME`, `COMMENT`, `CODE_ORDER`, `USE_YN`, `DEL_YN`, `REG_USER`, `REG_DATE`, `MOD_USER`, `MOD_DATE`, `REFER_ID1`, `REFER_ID2`) VALUES
	(1, 'MODBUS_SERVER', 'ROOT', 'PUB', 'On', 'Modbus Server Configration - Web이 Server 임.\r\n코드명 : On(서버 실행), Off(서버 미실행)\r\n참조명 : ip(사용)\r\n참조값 : port(사용)', 20, 'Y', 'N', 'sysop', '2018-10-17 11:43:17', 'null', '2024-05-30 08:25:15', '127.0.0.1', 9090),
	(2, 'SERVER_CRACKHOLE_000', 'MODBUS_SERVER', 'PUB', 'CrackHole', '코드명 : 미사용\r\n참조명 : register\r\n참조값 : port(미사용)', 10, 'Y', 'N', 'sysop', '2018-10-17 14:18:02', 'null', '2024-04-17 10:13:58', '0', 502),
	(3, 'SERVER_BLANKEDGE_001', 'MODBUS_SERVER', 'PUB', 'BlankEdge', '코드명 : 미사용\r\n참조명 : register\r\n참조값 : port(미사용)', 20, 'Y', 'N', 'sysop', '2018-10-17 15:29:10', 'null', '2024-04-17 10:14:09', '1', 503),
	(4, 'SPT_TYPE', 'ROOT', 'PUB', '종목 타입', '종목에 대한 설정', 195, 'Y', 'Y', 'sysop', '2018-10-17 16:43:46', 'sysop', '2018-12-04 10:30:23', NULL, NULL),
	(5, 'SPT_004', 'SPT_TYPE', 'PUB', '탁구', '탁구에 대한 종목코드', 40, 'Y', 'Y', 'sysop', '2018-10-17 17:00:03', NULL, NULL, NULL, NULL),
	(6, 'SPT_002', 'SPT_TYPE', 'PUB', '수영', '수영에 대한 종목 코드', 20, 'Y', 'Y', 'sysop', '2018-10-18 16:09:09', NULL, NULL, NULL, NULL),
	(7, 'SPT_003', 'SPT_TYPE', 'PUB', '족구', '족구에 대한 종목 코드', 10, 'Y', 'Y', 'sysop', '2018-10-18 17:10:42', 'sysop', '2018-10-18 17:11:37', NULL, NULL),
	(19, 'CHGV_000', 'CHGV_TYPE', 'PUB', '오전(09:00~12:00)', '사용료 항목에 대한 오전 코드', 10, 'Y', 'Y', 'sysop', '2018-10-22 11:40:35', 'sysop', '2019-01-07 08:17:19', NULL, NULL),
	(20, 'CHGV_001', 'CHGV_TYPE', 'PUB', '오후(13:00~17:00)', '사용료 항목에 대한 오후 코드', 20, 'Y', 'Y', 'sysop', '2018-10-22 11:41:04', 'sysop', '2019-01-07 08:17:34', NULL, NULL),
	(21, 'CHGV_002', 'CHGV_TYPE', 'PUB', '1일(09:00~17:00)', '사용료 항목에 대한 1일 항목 코드', 30, 'Y', 'Y', 'sysop', '2018-10-22 11:43:02', 'sysop', '2019-01-07 08:17:47', NULL, NULL),
	(22, 'CHGV_003', 'CHGV_TYPE', 'PUB', '야간(18:00~21:00)', '사용료 항목에 대한 야간 코드', 40, 'Y', 'Y', 'sysop', '2018-10-22 11:44:59', 'sysop', '2019-01-07 08:17:58', NULL, NULL),
	(23, 'CHGV_004', 'CHGV_TYPE', 'PUB', '1일+야간(08:00~21:30)', '사용료 항목에 대한 1일+야간에 대한 코드', 50, 'Y', 'Y', 'sysop', '2018-10-22 11:46:27', 'sysop', '2019-01-07 08:18:09', NULL, NULL),
	(24, 'EQUIP_STS_TYPE', 'ROOT', 'PUB', '장비상태', '장비에 대한 상태 타입 코드\r\n\r\n 사이트관리 &gt; 공용관리 &gt; 장비 &gt; 장비상태', 120, 'Y', 'Y', 'sysop', '2018-10-23 11:11:15', 'sysop', '2018-12-28 11:47:11', NULL, NULL),
	(25, 'EQUIP_STS_000', 'EQUIP_STS_TYPE', 'PUB', '분실', '장비상태에 대한 분실 코드', 10, 'Y', 'Y', 'sysop', '2018-10-23 11:11:52', 'sysop', '2018-11-26 17:07:01', NULL, NULL),
	(26, 'EQUIP_STS_001', 'EQUIP_STS_TYPE', 'PUB', '파손', '장비상태에 대한 분실 상태코드', 20, 'Y', 'Y', 'sysop', '2018-10-23 11:12:23', NULL, NULL, NULL, NULL),
	(45, 'PGM_TYPE', 'ROOT', 'PUB', '프로그램 구분', '프로그램 구분', 210, 'Y', 'Y', 'sysop', '2018-11-01 11:57:03', 'sysop', '2018-11-30 10:07:14', NULL, NULL),
	(46, 'PGM_REG', 'PGM_TYPE', 'PUB', '정기강습', '정기강습', 5, 'Y', 'Y', 'sysop', '2018-11-01 11:57:37', 'sysop', '2020-04-22 13:36:26', NULL, NULL),
	(47, 'PGM_YNG', 'PGM_TYPE', 'PUB', '일반프로그램', '청소년 및 성인대상 수시프로그램', 1, 'N', 'Y', 'sysop', '2018-11-01 11:58:28', 'sysop', '2020-04-22 13:56:03', NULL, NULL),
	(48, 'PGM_FREE', 'PGM_TYPE', 'PUB', '자유 프로그램', '자유 프로그램 \r\n-수영 1일 발권, 1개월권, 3개월권 \r\n-헬스 1개월권, 3개월권  \r\n을 대상으로 하는 프로그램이다.', 7, 'Y', 'Y', 'sysop', '2018-11-01 12:01:44', 'sysop', '2019-01-04 20:55:01', NULL, NULL),
	(49, 'PGM_CATEGORY', 'ROOT', 'PUB', '프로그램 카테고리', '프로그램 카테고리', 250, 'Y', 'N', 'sysop', '2018-11-01 12:05:32', 'sysop', '2018-11-27 10:09:56', NULL, NULL),
	(50, 'PGM_TARGET', 'ROOT', 'PUB', '프로그램 신청대상', '프로그램 신청 대상', 240, 'N', 'Y', 'sysop', '2018-11-01 15:11:18', 'sysop', '2023-01-23 06:11:52', NULL, NULL),
	(51, 'PGM_TARGET01', 'PGM_TARGET', 'PUB', '개인', '일반(개인)', 10, 'Y', 'Y', 'sysop', '2018-11-01 15:12:01', 'sysop', '2018-12-03 18:00:15', NULL, NULL),
	(52, 'PGM_TARGET02', 'PGM_TARGET', 'PUB', '단체', '단체', 20, 'N', 'Y', 'sysop', '2018-11-01 15:12:19', 'sysop', '2020-05-19 21:34:31', NULL, NULL),
	(53, 'PGM_CLASS', 'ROOT', 'PUB', '프로그램 강습과정', '프로그램 강습과정', 200, 'Y', 'Y', 'sysop', '2018-11-01 15:16:02', 'sysop', '2018-12-29 23:27:04', NULL, NULL),
	(54, 'PGM_CLASS01', 'PGM_CLASS', 'PUB', '신규', '신규', 11, 'Y', 'Y', 'sysop', '2018-11-01 15:16:25', NULL, NULL, NULL, NULL),
	(55, 'PGM_CLASS02', 'PGM_CLASS', 'PUB', '상급', '상급', 16, 'Y', 'Y', 'sysop', '2018-11-01 15:17:07', 'sysop', '2018-12-21 17:25:06', NULL, NULL),
	(56, 'PGM_CLASS03', 'PGM_CLASS', 'PUB', '소수정예', '소수정예', 31, 'Y', 'Y', 'sysop', '2018-11-01 15:17:22', 'sysop', '2020-11-16 10:20:26', NULL, NULL),
	(57, 'MODBUS_PLC', 'ROOT', 'PUB', 'address로 사용 여부', 'Modbus TCP configuration - Web이 client 임.\r\n코드명: \r\n               0 : 참조명을 address로 사용, 1: 카메라 ID를 address로 사용\r\n 참조명: address, \r\n참조값: port', 10, 'Y', 'N', 'sysop', '2018-11-02 18:20:50', 'null', '2024-05-30 08:24:55', 'address', NULL),
	(58, 'PLC_HUMAN_000', 'MODBUS_PLC', 'PUB', '0', 'Human Detection : intrusionSts', 10, 'Y', 'N', 'sysop', '2018-11-02 18:21:28', 'null', '2024-04-03 04:35:23', '0', 502),
	(59, 'PLC_CRACKHOLE_001', 'MODBUS_PLC', 'PUB', '0', 'CrackHole : crackDefect or holeDefect이 true이면 1을 PLC로 보낸다.\r\n둘 다 false 이면 아무것도 마무것도 보내지 않는다.', 20, 'Y', 'N', 'sysop', '2018-11-02 18:22:14', 'null', '2024-04-03 04:36:00', '0', 503),
	(60, 'PLC_BLANKEDGE_002', 'MODBUS_PLC', 'PUB', '0', 'BlankEdge : defectSts', 30, 'Y', 'N', 'sysop', '2018-11-02 18:24:03', 'null', '2024-04-03 04:34:57', '3', 504),
	(61, 'PGM_TARGET03', 'PGM_TARGET', 'PUB', '개인/단체', '개인/단체', 30, 'N', 'Y', 'sysop', '2018-11-06 10:14:09', 'sysop', '2020-05-19 21:34:43', NULL, NULL),
	(62, 'DAY_OF_WEEK', 'ROOT', 'PUB', '요일', '', 180, 'Y', 'Y', 'sysop', '2018-11-06 17:32:24', NULL, NULL, NULL, NULL),
	(86, 'SALE_ITEM_TYPE', 'ROOT', 'PUB', '결제품목', '1.결제품목 타입코드\n2.사용부분\n', 165, 'Y', 'Y', 'sysop', '2018-11-20 17:36:49', 'null', '2023-05-05 18:01:57', NULL, NULL),
	(87, 'SALE_ITEM_000', 'SALE_ITEM_TYPE', 'PUB', '프로그램', '결제품목 프로그램에 대한 코드', 10, 'Y', 'Y', 'sysop', '2018-11-20 17:37:16', 'sysop', '2018-12-28 21:09:58', NULL, NULL),
	(88, 'SALE_ITEM_001', 'SALE_ITEM_TYPE', 'PUB', '사물함', '결제품목에 대한 사물함 코드', 20, 'Y', 'Y', 'sysop', '2018-11-20 17:37:43', 'sysop', '2018-12-28 21:10:27', NULL, NULL),
	(89, 'SALE_ITEM_002', 'SALE_ITEM_TYPE', 'PUB', '회원카드', '결재품목에 대한 회원카드 코드', 30, 'Y', 'Y', 'sysop', '2018-11-20 17:38:17', NULL, NULL, NULL, NULL),
	(91, 'SALE_USER_TYPE', 'ROOT', 'PUB', '매출 사용자구분', '매출 사용자 구분에 대한 타입코드', 350, 'Y', 'Y', 'sysop', '2018-11-20 17:51:48', NULL, NULL, NULL, NULL),
	(92, 'SALE_USER_000', 'SALE_USER_TYPE', 'PUB', '개인', '첨부 설명 추가', 10, 'Y', 'Y', 'sysop', '2018-11-20 17:53:51', 'null', '2023-05-05 17:33:48', NULL, NULL),
	(93, 'SALE_USER_001', 'SALE_USER_TYPE', 'PUB', '단체', '매출사용자구분에 대한 단체코드', 20, 'Y', 'Y', 'sysop', '2018-11-20 17:54:24', NULL, NULL, NULL, NULL),
	(95, 'SALE_UNIT_TYPE', 'ROOT', 'PUB', '결재단위', '결재단위에 대한 타입코드\r\n \r\n 통합회원관리 &gt; 결제관리 &gt; 결제단위', 155, 'Y', 'Y', 'sysop', '2018-11-20 18:01:38', 'sysop', '2018-12-28 11:49:09', NULL, NULL),
	(96, 'SALE_UNIT_000', 'SALE_UNIT_TYPE', 'PUB', '개월', '결재단위에 대한 개월코드', 10, 'Y', 'Y', 'sysop', '2018-11-20 18:01:57', NULL, NULL, NULL, NULL),
	(97, 'SALE_UNIT_001', 'SALE_UNIT_TYPE', 'PUB', '일', '결재단위에 대한 일 코드', 20, 'Y', 'Y', 'sysop', '2018-11-20 18:02:17', NULL, NULL, NULL, NULL),
	(98, 'SALE_UNIT_002', 'SALE_UNIT_TYPE', 'PUB', 'EA', '결재 단위에 대한 EA 코드', 30, 'Y', 'Y', 'sysop', '2018-11-20 18:02:44', NULL, NULL, NULL, NULL),
	(199, 'SALE_ACC_000', 'SALE_ACC_TYPE', 'PUB', '신용카드', '결재수단에 대한 신용카드 코드', 10, 'Y', 'Y', 'sysop', '2018-12-16 10:06:02', 'sysop', '2018-12-28 21:09:24', NULL, NULL),
	(200, 'SALE_ACC_001', 'SALE_ACC_TYPE', 'PUB', '현금', '결재수단에 대한 현금 코드', 20, 'Y', 'Y', 'sysop', '2018-12-16 10:06:02', NULL, NULL, NULL, NULL),
	(201, 'SALE_ACC_002', 'SALE_ACC_TYPE', 'PUB', '실시간 계좌이체', '결재수단에 대한 계좌이체 코드', 30, 'N', 'Y', 'sysop', '2018-12-16 10:06:02', 'null', '2023-05-25 11:47:05', NULL, NULL),
	(578, 'SALE_CARD_TYPE', 'ROOT', 'PUB', '결제수단', '결제수단', 2, 'Y', 'Y', 'sysop', '2023-01-24 09:30:04', NULL, NULL, NULL, NULL),
	(579, 'SALE_ACC_TYPE', 'ROOT', 'PUB', '결제수단', '신용, 현금', 30, 'Y', 'Y', 'sysop', '2023-01-24 09:34:08', 'null', '2023-05-11 10:54:57', NULL, NULL),
	(580, 'CHGV_TYPE', 'ROOT', 'PUB', '사용료 항목', '사용료 항목', 4, 'Y', 'Y', 'sysop', '2023-01-24 09:36:43', NULL, NULL, NULL, NULL),
	(581, 'SECURITY_INFO', 'ROOT', 'PUB', 'Security CORS', 'CORS(Cross Origin Resources Sharing) Security\r\nAPI를 사용하기 위해서 사용할 PC 또는 서버 IP를 여기에 등록해야 한다.\r\n필수 : IP, Port', 1, 'Y', 'N', 'null', '2023-05-05 11:28:43', 'null', '2024-01-23 17:49:05', NULL, NULL),
	(582, 'SECURITY_INFO_000', 'SECURITY_INFO', 'PUB', 'localhost', 'Localhost', 1, 'Y', 'N', 'null', '2023-05-05 17:36:21', 'null', '2024-03-26 01:14:12', '8080', 1),
	(583, 'SECURITY_INFO_001', 'SECURITY_INFO', 'PUB', '192.168.200.127', 'local IP', 2, 'Y', 'N', 'null', '2023-05-05 17:37:07', 'null', '2024-03-26 01:13:51', '8080', 2),
	(584, 'SECURITY_INFO_002', 'SECURITY_INFO', 'PUB', 'localhost', 'AI', 3, 'Y', 'N', 'null', '2023-05-05 17:38:12', 'null', '2024-04-05 11:06:29', '8081', 3),
	(585, 'SECURITY_INFO_003', 'SECURITY_INFO', 'PUB', '192.168.200.127', 'FE 1', 4, 'Y', 'N', 'null', '2023-05-05 17:38:57', 'null', '2024-03-26 01:10:04', '65000', 4),
	(586, 'SECURITY_INFO_004', 'SECURITY_INFO', 'PUB', '192.168.200.141', 'FE 2', 5, 'Y', 'N', 'null', '2023-05-05 17:39:58', 'null', '2024-03-26 01:10:33', '65000', 5),
	(587, 'SECURITY_INFO_005', 'SECURITY_INFO', 'PUB', '192.168.200.135', 'Shane', 6, 'Y', 'N', 'null', '2023-05-05 17:39:58', 'null', '2024-04-01 05:05:29', '8080', 6),
	(588, 'SECURITY_INFO_006', 'SECURITY_INFO', 'PUB', '10.160.131.221', 'Sajid', 7, 'Y', 'N', 'null', '2023-05-05 17:39:58', 'null', '2024-02-19 02:52:11', '8080', 7),
	(589, 'SERVER_INFO', 'ROOT', 'PUB', 'Server Info', 'BE server와 통신하는 Server Info', 2, 'Y', 'N', 'null', '2023-05-05 17:47:49', 'null', '2023-12-28 09:26:51', NULL, NULL),
	(591, 'SERVER_WEB', 'SERVER_INFO', 'PUB', 'localhost', 'KIA PASS Web Server', 1, 'Y', 'N', 'null', '2023-05-05 17:49:25', 'null', '2024-04-05 11:03:00', '8080', 1),
	(592, 'SERVER_AI', 'SERVER_INFO', 'PUB', 'localhost', 'AI', 2, 'Y', 'N', 'null', '2023-05-05 17:49:25', 'null', '2024-04-05 11:05:53', '8081', 2),
	(593, 'SERVER_FE', 'SERVER_INFO', 'PUB', 'localhost', 'FE', 3, 'Y', 'N', 'null', '2023-05-05 17:49:25', 'null', '2024-04-05 11:06:03', '65000', 3),
	(594, 'SERVER_PLC', 'SERVER_INFO', 'PUB', '140.12.2.1', 'PLC', 4, 'Y', 'N', 'null', '2023-05-05 17:49:25', 'null', '2024-03-25 00:07:15', '502', 1),
	(595, 'SERVER_LOCAL', 'SERVER_INFO', 'PUB', '192.168.200.133', '미사용', 5, 'Y', 'N', 'null', '2023-05-05 17:49:25', 'null', '2024-03-31 23:52:00', '8080', 5),
	(607, 'FILE_CONFIG', 'ROOT', 'PUB', 'Files Postion', 'AI에서 저장하는 이미지, 동영상 파일의 저장 위치 설정한다.', 5, 'Y', 'N', 'null', '2023-06-12 09:26:39', 'null', '2024-03-31 23:50:44', NULL, NULL),
	(608, 'FILE_UPLOAD_PATH', 'FILE_CONFIG', 'PUB', 'File Upload Path', 'linux : /hdd/smart_perception_group/kia_pass/resource\r\n', 1, 'Y', 'N', 'null', '2023-06-12 09:28:35', 'null', '2024-01-30 09:07:05', '/hdd/smart_perception_group/kia_pass/resource', 1),
	(609, 'FILE_UPLOAD_CRACKHOLE', 'FILE_CONFIG', 'PUB', 'CkackHole Data', 'linux : /home/shanepark/upload/files\r\nwindows : C:\\upload\\files', 1, 'Y', 'Y', 'null', '2023-06-12 09:28:35', 'null', '2024-01-29 14:21:00', 'C:\\\\upload\\\\files\\\\', 1),
	(610, 'FILE_UPLOAD_BLANKEDGE', 'FILE_CONFIG', 'PUB', 'BlankEdge Data', 'linux : /home/shanepark/upload/files\r\nwindows : C:\\upload\\files', 1, 'Y', 'Y', 'null', '2023-06-12 09:28:35', 'null', '2024-01-29 14:21:15', 'C:\\\\upload\\\\files\\\\', 1),
	(611, 'FILE_UPLOAD_DEFAULT', 'FILE_CONFIG', 'PUB', 'Default Data', 'windows : C:\\upload\\files', 1, 'Y', 'N', 'null', '2023-06-12 09:28:35', 'null', '2024-01-29 23:19:54', 'C:\\upload\\files', 1),
	(620, 'CRONJOB_INFO', 'ROOT', 'PUB', 'Cron Job', 'Cronjob scheduler info\r\nStart Day(SCHEDULE_START) ~ End Day(SCHEDULE_END) 기간의 Human Detection, CrackHole, BlankEdge, Web Access History에 대한 데이터를 삭제한다.', 6, 'Y', 'N', 'null', '2023-06-12 09:28:35', 'null', '2024-03-31 23:48:11', 'On', 1),
	(621, 'SCHEDULE_START', 'CRONJOB_INFO', 'PUB', '시작 일', 'before 참조명', 2, 'Y', 'N', 'null', '2023-06-12 09:28:35', 'null', '2024-01-29 14:19:04', '30', 2),
	(622, 'SCHEDULE_END', 'CRONJOB_INFO', 'PUB', '종료 일', 'before 참조명', 3, 'Y', 'N', 'null', '2023-06-12 09:28:35', 'null', '2024-01-29 14:19:18', '15', 3),
	(630, 'NOTIFY_INFO', 'ROOT', 'PUB', 'Notification', '상위공용코드가 NOTIFY_INFO 중에서 Selected No. (참조값: ReferId2)에 해당하는 첨부설명을 Client로 보낸다.\r\n현재 사용되지 않는다.(미래를 위해서 구현함)', 7, 'Y', 'N', 'null', '2024-01-15 09:16:57', 'null', '2024-03-31 23:49:27', '1', 1),
	(631, 'NOTIFY_FIRST', 'NOTIFY_INFO', 'PUB', 'First', 'First Announcement', 2, 'Y', 'N', 'null', '2024-01-15 09:16:57', 'null', '2024-01-22 09:38:13', '1', 1),
	(632, 'NOTIFY_SECOND', 'NOTIFY_INFO', 'PUB', 'Second', 'Second Announcement', 3, 'Y', 'N', 'null', '2024-01-15 09:16:57', 'null', '2024-01-22 09:38:21', '2', 2),
	(633, 'NOTIFY_THIRD', 'NOTIFY_INFO', 'PUB', 'Third', 'Third Announcement', 4, 'Y', 'N', 'null', '2024-01-15 09:16:57', 'null', '2024-01-22 09:38:28', '3', 3),
	(640, 'SERVER_API', 'SERVER_INFO', 'PUB', '10.160.130.148', '미사용', 6, 'Y', 'N', 'null', '2024-02-06 13:00:19', 'null', '2024-03-31 23:52:13', '8080', 6),
	(641, 'SECURITY_INFO_007', 'SECURITY_INFO', 'PUB', '10.160.131.1', 'Sajid', 8, 'Y', 'N', 'null', '2024-02-19 04:04:26', 'null', '2024-02-29 06:22:56', '8080', 8),
	(642, 'SECURITY_INFO_008', 'SECURITY_INFO', 'PUB', '10.160.30.135', 'Zahid', 9, 'Y', 'N', 'null', '2024-02-20 02:12:03', 'null', '2024-02-29 06:23:05', '8080', 9);
/*!40000 ALTER TABLE `tbl_common_code` ENABLE KEYS */;

-- 테이블 react-spring.tbl_feedback 구조 내보내기
CREATE TABLE IF NOT EXISTS `tbl_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '댓글 번호 (PK)',
  `post_id` bigint(20) NOT NULL COMMENT '게시글 번호 (FK)',
  `title` varchar(100) DEFAULT NULL COMMENT '제목',
  `content` varchar(1000) DEFAULT NULL COMMENT '내용',
  `writer` varchar(20) DEFAULT NULL COMMENT '작성자',
  `delete_yn` tinyint(1) NOT NULL DEFAULT 0 COMMENT '삭제 여부',
  `created_date` datetime NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `modified_date` datetime DEFAULT NULL COMMENT '최종 수정일시',
  PRIMARY KEY (`id`),
  KEY `fk_post_comment` (`post_id`),
  CONSTRAINT `fk_post_comment` FOREIGN KEY (`post_id`) REFERENCES `tbl_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='댓글';

-- 테이블 데이터 react-spring.tbl_feedback:~0 rows (대략적) 내보내기
DELETE FROM `tbl_feedback`;
/*!40000 ALTER TABLE `tbl_feedback` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='파일';

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

-- 테이블 데이터 react-spring.tbl_post:~2 rows (대략적) 내보내기
DELETE FROM `tbl_post`;
/*!40000 ALTER TABLE `tbl_post` DISABLE KEYS */;
INSERT INTO `tbl_post` (`id`, `title`, `content`, `writer`, `view_cnt`, `notice_yn`, `delete_yn`, `created_date`, `modified_date`) VALUES
	(1, 'Test Title 1', 'Test Content 1', 'tester', 12, 1, 0, '2024-05-09 10:32:23', '2024-05-31 07:41:01'),
	(2, 'Test Title 2', 'Test Content 2', 'tester', 21, 1, 0, '2024-05-10 09:18:00', '2024-06-05 10:04:38');
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
	(1, 'Hello react-spring world~~', 'start react-spring study...\n\n1. Spring boot + React\n2. Spring boot 3.x.x 은 backend에서 처리함.(여기서 Thymeleaf는 사용하지 않는다)\n3. React는 frontend 아래 배치함.\n4. Optional JPA(Java Persistence API), MyBatis\n5. Left or Header Menu\n6. JWT(Josn Web Token) Security - Authority를 계정에 따라 GUEST, USER, ADMIN으로 설정하여 각각의 메뉴를 권한에 따라 사용 가능하게 함.\n7. 일반 로그인, Google OAuth2 로그인', 43, 1, NULL, '2024/05/31 09:07:05'),
	(4, 'Development react-spring on the Spring boot framework', '게시판 관리    => JPA ORM 사용\n . 게시판        => Authentication, Authorization 불필요\n . 직원리스트  => USER 권한만 조회 가능\n . 갤러리형     => 준비 중\n . 카렌다형     => 준비 중\n\n웹서버 관리       => MyBatis 사용\n . 게시판           => "ADMIN", "USER" 권한 필요\n . 사용자리스트   => ADMIN 권한만 조회 가능', 71, 1, '2024/05/08 11:22:10', '2024/05/31 15:41:41');
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
	(9, 'JWT(Json Web Token)을 적용하여 Authentication(인증), Authorization(인가, 권한)을 사용할 수 있도록 함.\n로그인 하지 않으면 게시판(JPA) 보기만 가능함...~~~', 4, NULL, 1, '2024/05/14 11:24:32', '2024/05/31 12:54:43');
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
