package shane.blog.domain.commoncode;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse extends DefaultResponse {
	private String eventId;
	private String sessionId;
	private String sessionEventId;
	private String windowEventId;
	private String imageData; // hive image

	private String startTime; // input
	private String endTime; // input

	private String srcIp;
	private String dstIp;

	private String period; // endTime - startTime

	private String sentPkt;
	private String sentByte;

	private String rcvdPkt;
	private String rcvdByte;

	private String attackClass;
	private String attackScore;
	private Date createTime;
	private String minScore; // 최저 점수(input)
	private String maxScore; // 최고 점수(input)

	private String threat; // input : dos, ddos, botnet, BruteForce, Infiltration, SQLinjection, Normal
	private String protocol; // input : TCP, UDP

	private String keyword; // search keyword
	private String query; // search query
	private String searchType; // member, group
	private int totalCount;

	/**
	 * 코드고유번호
	 */
	private int commSeq = 0;
	/**
	 * 공용코드
	 */
	private String commonCode = null;
	private String commonCodeVal = null;
	/**
	 * 공용코드 이름
	 */
	private String commonName = null;
	/**
	 * 그룹코드
	 */
	private String groupCode = null;
	/**
	 * 순서
	 */
	private int codeOrder;
	/**
	 * 상위 공용코드
	 */
	private String parentsCode = "ROOT";
	/**
	 * 상위 공용코드 이름
	 */
	private String parentsName = "최상위";
	/**
	 * 비고
	 */
	private String comment = null;
	/**
	 * 순서 최대값
	 */
	private int orderMax;
	private int rootOrderMax;

	private String isCategory; // 프로그램 카테고리 전용 메뉴

	private String referId1; // 프로그램 카테고리 사용 추가 필드..
	private int referId2; // 할인규정 사용 추가 필드..

	private int edSeq; // 엑셀다운로드 키
	private String edName; // 엑셀이름
	private String edType; // 엑셀타입
	private String organId; // 조직명
	private String adminUserCode;// 관리자코드
	private int rowCnt; // 로우데이터 수
	private String privateYn; // 개인정보 유무

}
