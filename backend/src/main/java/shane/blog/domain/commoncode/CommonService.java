package shane.blog.domain.commoncode;

import java.util.List;

public interface CommonService {

	/**
	 * 로그인 메시지
	 * 
	 * @param i
	 * @return
	 */
	public MsgResponse getLoginMsg(int i);

	/**
	 * 성공/실패 멘트
	 * 
	 * @param b
	 * @return
	 */
	public MsgResponse getCommonMent(boolean b);

	/**
	 * 작업완료에 대한 멘트
	 * 
	 * @param string
	 * @return
	 */
	public MsgResponse getCompleteMent(String string);

	/**
	 * 중복 멘트 처리(성공/실패)
	 * 
	 * @param b
	 * @return
	 */
	public MsgResponse getduleMent(boolean b);

	/**
	 * 공용 멘트 처리(일치/불일치)
	 * 
	 * @param b
	 * @return
	 */
	public MsgResponse getMatchMent(boolean b);

	/**
	 * userTran에 대한 Ment
	 * 
	 * @param i
	 * @return
	 */
	public MsgResponse doUserTranMent(int i);

	/**
	 * 사이트 관리 상세보기
	 * 
	 * @param siteAdminVo
	 * @return
	 */
	// public SiteAdminVO getSiteAdminDetail(SiteAdminVO siteAdminVo);

	/**
	 * 사이트 설정 관리 등록(사용하지 않음)
	 * 
	 * @param siteAdminVo
	 * @return
	 */
	// public MsgResponse doInsertSiteAdmin(SiteAdminVO siteAdminVo);

	/**
	 * 사이트 설정 관리 수정
	 * 
	 * @param siteAdminVo
	 * @return
	 */
	// public MsgResponse doUpdateSiteAdmin(SiteAdminVO siteAdminVo);

	/**
	 * 사이트 설정관리 삭제(사용하지 않음)
	 * 
	 * @param siteAdminVo
	 * @return
	 */
	// public MsgResponse doDeleteSiteAdmin(SiteAdminVO siteAdminVo);

	public List<CommonResponse> selectCommonList(CommonResponse commonVO) throws Exception;
}
