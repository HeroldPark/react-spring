package shane.blog.domain.commoncode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonMapper {

	/**
	 * 공용코드 상세 보기
	 * 
	 * @param commonResponse
	 * @return
	 */
	public CommonResponse getCommonCodeDetail(CommonResponse commonResponse);

	/**
	 * 1Dept에 존재하는 Parnets코드 호출
	 * 
	 * @return
	 */
	public List<CommonResponse> getParentsCodeList(CommonResponse commonResponseVo);

	/**
	 * 공용 코드 삭제
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int doDeleteCode(CommonResponse commonResponse);

	/**
	 * 중복 체크
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int dupleCode(CommonResponse commonResponse);

	/**
	 * 공용코드 정보 업데이트
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int doUpdateCode(CommonResponse commonResponse);

	/**
	 * 공용코드 등록
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int doInsertCode(CommonResponse commonResponse);

	/**
	 * 하위노드가 존재하는 지 확인
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int getChildNodes(CommonResponse commonResponse);

	/**
	 * 하위 코드 호출
	 * 
	 * @param commonResponse
	 * @return
	 */
	public List<CommonResponse> getSubCodeList(CommonResponse commonResponse);

	/**
	 * 전체코드 정보리스트 호출
	 * 
	 * @param commonResponse
	 * @return
	 */
	public List<CommonResponse> getFullCodeList(CommonResponse commonResponse);

	/**
	 * 전체코드 순서 최대값 호출
	 * 
	 * @param commonResponse
	 * @return
	 */
	public int getCodesOrderMax(CommonResponse commonResponse);

	public List<CommonResponse> selectCommonList(CommonResponse commonVO);

}
