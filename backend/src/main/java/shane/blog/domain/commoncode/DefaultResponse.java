package shane.blog.domain.commoncode;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse implements Serializable {

	// private static final long serialVersionUID = 297412998341549023L;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private String startDateS;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String endDateS;
	private String csrf;

	private int totalCount; // 총 갯수
	private int pageCount; // 페이지당 조회 개수
	private int pageNumber; // 페이지번호(1부터 시작)
	private int limitStartNumber; // limit 시작 for maria db
	private String searchType = null; // 검색타입
	private String keyword = null; // 검색 키워드
	private int startCnt, endCnt;
	private String orderType = null; // 정렬필드
	private String sortOrder = "DESC"; // 정렬 기준(DESC, ASC)
	private String pagingYn = "Y"; // 페이지 유무
	private String doTran = "I"; // I:insert, U:update, D:delete

	private String pValue = null; // 임시 공용데이터

	private String seqArr = null;

	private String siteCate = null; /* 사이트 구분 */

	/* for DataTable */
	private int start;
	private int length;
	private int darw;

	/**
	 * 사용유무
	 */
	private String useYN;
	/**
	 * 삭제유무
	 */
	private String delYN = "N";
	/**
	 * 등록자
	 */
	private String regUser = null;
	/**
	 * 등록일
	 */
	private java.util.Date regDate = null;
	/**
	 * 수정자
	 */
	private String modUser = null;
	/**
	 * 수정일
	 */
	private java.util.Date modDate = null;
	/**
	 * 삭제자
	 */
	private String delUser = null;

	private String ergDateStr = null;

	private String clientIp = null;

	private String incomeCode = null;
	/**
	 * 삭제일
	 */
	private java.util.Date delDate = null;

	private String bigo = null;

	public DefaultResponse() {
		pageCount = 10;
		pageNumber = 1;
		endCnt = pageCount;
		pagingYn = "Y";
		length = 1;
	}

	public int getStartCnt() {
		startCnt = (((pageNumber - 1) * pageCount));
		return startCnt;
	}

}
