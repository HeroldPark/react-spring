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


-- visiondb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `react-spring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `react-spring`;

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

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
