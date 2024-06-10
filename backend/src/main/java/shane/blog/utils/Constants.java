package shane.blog.utils;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class Constants {
	// encrytion key
	public static final String ENC_KEY = "12345678091";

	public static final String IDENTITY_TYPE_RFID = "RFID";
	public static final String IDENTITY_TYPE_BARCODE = "BARCODE";
	/**
	 * UserID 상수명
	 */
	public static final String USERID = "USERID";
	/**
	 * userCode 상수명
	 */
	public static final String USERCODE = "USERCODE";
	/**
	 * User 이름
	 */
	public static final String USERNAME = "USERNAME";
	/**
	 * 사용자 상태
	 */
	public static final String USERSTATUS = "USERSTATUS";

	/**
	 * 사용자 폰 상수명
	 */
	public static final String USERPHONE = "USERPHONE";
	/**
	 * 사용자 모바일폰 상수명
	 */
	public static final String USERMIBILEPHONE = "USERMIBILEPHONE";
	/**
	 * 사용자 이메일 상수명
	 */
	public static final String USEREMAIL = "USEREMAIL";
	/**
	 * 사용자 팩스 상수명
	 */
	public static final String USERFAX = "USERFAX";
	/**
	 * 사용자 성별 상수명
	 */
	public static final String USERSEX = "USERSEX";

	/**
	 * 사용자 권한코드 상수명
	 */
	public static final String AUTHCODE = "AUTHCODE";

	/**
	 * 사용자 권한명 상수명
	 */
	public static final String AUTHNAME = "AUTHNAME";

	/**
	 * 사용자 권한명 상수명
	 */
	public static final String AUTHSCOPE = "AUTHSCOPE";

	/**
	 * organCode 상수명
	 */
	public static final String ORGANID = "ORGANID";
	/**
	 * organName 상수명
	 */
	public static final String ORGANNAME = "ORGANNAME";
	/**
	 * organName 상수명
	 */
	public static final String ORGANPHONE = "ORGANPHONE";
	/**
	 * 사용자 소속 사업소 고유번호 상수명
	 */
	public static final String OFFICESEQ = "OFFICESEQ";

	/**
	 * 사용자 소속 사업소명
	 */
	public static final String OFFICNAME = "OFFICNAME";

	/**
	 * 직책 코드
	 */
	public static final String POSCODE = "POSCODE";
	/**
	 * 직책 코드
	 */
	public static final String POSNAME = "POSNAME";

	public static final String TOPMENU = "TOPMENU";
	public static final String LEFTMENU = "LEFTMENU";
	public static final String MEMBERMENU = "MEMBERMENU";
	public static final String MEMBERMENU_F_URL = "MEMBERMENUFURL";
	public static final String SITEMENU = "SITEMENU";
	public static final String SITEMENU_F_URL = "SITEMENUFURL";

	/**
	 *
	 */
	public static final String LOGINKEY = "LOGINKEY";

	/**
	 * 사이트 카테고리
	 * voyager : 회원관리
	 * site : 사이트 관리
	 */
	public static final String SITECATE = "SITECATE";

	public static final String SITEOFFICE = "SITEOFFICE";

	/**
	 * 사용자 등급 - 작은숫자가 높은 권한
	 */
	// superADMIN
	public static final String AUTH_SA = "SA";
	// Operater Catain
	public static final String AUTH_OC = "OC";
	// Team Catain
	public static final String AUTH_CA = "CA";
	// Team User
	public static final String AUTH_TE = "TE";

	/**
	 * 마지막 접속 시간
	 */
	public static final String LASTLOGINTIME = "LASTLOGINTIME";

	// 사업소코드
	public static final String OFFICE_INFO_CENTER = "1";
	public static final String OFFICE_INFO_TRAIN = "2";
	public static final String OFFICE_INFO_CULTURE = "3";
	public static final String OFFICE_INFO_COUNSEL = "4";
	public static final String OFFICE_INFO_HS_CULTURE = "5";

	// 참여기구 타입
	public static final String CIRCLES_TYPE_JACHI = "CIRCLES_T01";
	public static final String CIRCLES_TYPE_JACHE = "CIRCLES_T02";
	public static final String CIRCLES_TYPE_SISUL = "CIRCLES_T03";

	// program 구분(PGM_TYPE)
	public static final String PGM_TYPE_REG = "PGM_REG";
	public static final String PGM_TYPE_YNG = "PGM_YNG";
	public static final String PGM_TYPE_FREE = "PGM_FREE";
	public static final String PGM_TYPE_ALW = "PGM_ALW";

	// program category 1(수영,체육)
	public static final String PGM_CATE_SWIM = "PGM_CATE009";
	public static final String PGM_CATE_PSCAL = "PGM_CATE011";
	// program target (개인,단체,개인/단체)
	public static final String PGM_TARGET_U = "PGM_TARGET01";
	public static final String PGM_TARGET_G = "PGM_TARGET02";
	public static final String PGM_TARGET_A = "PGM_TARGET03";
	// 사용료 타입
	public static final String CHG_TYPE_FREE = "CHGTYPE_000";
	public static final String CHG_TYPE_CHARGE = "CHGTYPE_001";

	// 결제품목
	public static final String GOODS_DIV_PGM = "SALE_ITEM_000";
	public static final String GOODS_DIV_USER_KEY = "SALE_ITEM_001";
	public static final String GOODS_DIV_USER_CARD = "SALE_ITEM_002";
	public static final String GOODS_DIV_FACILITY = "SALE_ITEM_003";
	public static final String GOODS_DIV_EQUIP = "SALE_ITEM_004";

	// pg van
	public static final String PG_DELNG_TY_P = "00101000";
	public static final String PG_DELNG_TY_U = "00201000";
	public static final String VAN_DELNG_TY_P = "A";
	public static final String VAN_DELNG_TY_U = "Q";
	// 지불기기
	public static final String PYMNT_MHRLS_PG = "11";
	public static final String PYMNT_MHRLS_CARD_TRMNL = "21";
	public static final String PYMNT_MHRLS_KOSK = "31";
	// 결제수단
	public static final String PYMNT_TY_CREDIT = "SALE_ACC_000";
	public static final String PYMNT_TY_CASH = "SALE_ACC_001";
	public static final String PYMNT_TY_REAL_ACOUNT = "SALE_ACC_002";
	public static final String PYMNT_TY_ACOUNT = "SALE_ACC_003";
	public static final String PYMNT_TY_FREE = "SALE_ACC_005";
	// 카드구분
	public static final String CARD_KND_NM_CREDIT = "신용카드";
	public static final String CARD_KND_NM_CHECK = "체크카드";
	// 지불구문
	public static final String PYMNT_DIV_PAY = "P";
	public static final String PYMNT_DIV_CANCL = "C";
	public static final String PYMNT_DIV_REFND = "R";
	public static final String PYMNT_DIV_PYMNT_WAIT = "W";
	public static final String PYMNT_DIV_CANCL_WAIT = "D";
	public static final String PYMNT_DIV_FEE = "F";
	public static final String PYMNT_DIV_MANUAL = "M";
	public static final String PYMNT_DIV_APPLY = "Q";
	// 취소일 구분
	public static final String PYMNT_CANCL_TODAY = "T";
	public static final String PYMNT_CANCL_NEXT_TODAY = "N";

	// 마감항목
	public static final String AMOUNT_ADMIN = "목표액설정";
	public static final String INCOME = "수입금";
	public static final String DEAD_BEFORE = "마감전";

	public static final String CSRF_TOKEN = "CSRF_TOKEN";

	// SEQ관리 테이블
	public static final String TB_ORDER_SETLE = "ORDER_SETLE";

	// QR
	public static final int QR_WIDTH = 150;
	public static final int QR_HIGHT = 150;
}
