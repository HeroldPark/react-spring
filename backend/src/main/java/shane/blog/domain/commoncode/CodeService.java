package shane.blog.domain.commoncode;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CodeService {

	/**
	 * 1Dept에 존재하는 코드들을 불러 온다.
	 * 
	 * @param string
	 * @return
	 */
	public List<CommonResponse> getParentsCodes(String commonCode, String groupCode, String useYN, String delYN);

	/**
	 * 코드정보 상세보기
	 * 
	 * @param commonCode
	 * @return
	 */
	public CommonResponse commonCodeDetail(CommonResponse commonCode);

	/**
	 * 코드 삭제
	 * 
	 * @param commonCode
	 * @return
	 */
	public MsgResponse doDeleteCode(CommonResponse commonCode);

	/**
	 * 코드 업데이트
	 * 
	 * @param commonCode
	 * @return
	 */
	public MsgResponse doUpdateCode(CommonResponse commonCode);

	/**
	 * 코드 등록
	 * 
	 * @param commonCode
	 * @return
	 */
	public MsgResponse doInsertCode(CommonResponse commonCode);

	/**
	 * 하위 코드 호출
	 * 
	 * @param commonCode
	 * @return
	 */
	public List<CommonResponse> getSubCodeList(CommonResponse commonCode);

	/**
	 * 하위 코드 호출(String 전달)
	 * 
	 * @param commonCode
	 * @param groupCode
	 * @param useYN
	 * @param delYN
	 * @return
	 */
	public List<CommonResponse> getSubCodeList(String commonCode, String groupCode, String useYN, String delYN);

	/**
	 * 전체코드 정보 호출
	 * 
	 * @param commonCode
	 * @return
	 */
	public List<CommonResponse> getFullCodes(CommonResponse commonCode);

	/**
	 * 전체코드 정보 호출
	 * 
	 * @param commonCode
	 * @return
	 */
	public int getCodesOrderMax(CommonResponse commonCode);

	/**
	 * 코드 중복 체크 확인
	 * 
	 * @param commonCode
	 * @return
	 */
	public MsgResponse dupleCodeCheck(CommonResponse commonCode);

}
