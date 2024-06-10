package shane.blog.domain.commoncode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 스프링빈으로 사용하기 위해서 어노테이션 사용
public class CommonServiceImpl implements CommonService {

//	@Autowired // 댓글 DAO 클래스 주입
//	private ReplyDAO replyDAO;

//	@Autowired // DAO클래스 주입받아서 사용변수 생성
//	private BoardDAO boardDAO;

	@Autowired // DAO클래스 주입받아서 사용변수 생성
	private CommonMapper commonMapper;

	@Override
	public MsgResponse getLoginMsg(int i) {
		MsgResponse msg = new MsgResponse();
		switch (i) {
			case 0:
				msg.setMsgCode("0000");
				msg.setMsgMent("로그인에 성공하였습니다.");
				break;
			case 100:
				msg.setMsgCode("0100");
				msg.setMsgMent("입력하신 정보가 다름니다. 확인 후 재입력 바랍니다.");
				break;
			case 200:
				msg.setMsgCode("0200");
				msg.setMsgMent("접속 권한이 없습니다. 관리자에게 문의하세요.");
				break;
			default:
				msg.setMsgCode("9999");
				msg.setMsgMent("확인 후 재입력 바랍니다.");
				break;
		}
		return msg;
	}

	@Override
	public MsgResponse getCommonMent(boolean checkBool) {
		MsgResponse result = new MsgResponse();
		if (checkBool) {
			result.setMsgCode("0000");
			result.setMsgMent("요청하신 작업이 처리되었습니다.");
		} else {
			result.setMsgCode("9999");
			result.setMsgMent("요청하신 작업이 실패되었습니다.");
		}
		return result;
	}

	@Override
	public MsgResponse getCompleteMent(String string) {
		MsgResponse msg = new MsgResponse();
		switch (string) {
			case "part":
				msg.setMsgCode("0500");
				msg.setMsgMent("부분적으로 완료되었습니다.");
				break;
			case "noMatchList":
				msg.setMsgCode("0600");
				msg.setMsgMent("매칭되는 정보가 존재하지 않습니다. 다시 확인 후 진행하여 주세요.");
				break;
			case "NotRegList":
				msg.setMsgCode("0610");
				msg.setMsgMent("정상적으로 등록되지 않았습니다. 다시 진행해 주세요.");
				break;
			case "required":
				msg.setMsgCode("0100");
				msg.setMsgMent("필수입력값이 정상적으로 전달되지 않았습니다.");
				break;
			case "duplication":
				msg.setMsgCode("0200");
				msg.setMsgMent("중복된 정보가 등록되어 있습니다.");
				break;
			default:
				msg.setMsgCode("9999");
				msg.setMsgMent("확인 후 다시 실행 바랍니다.");
				break;
		}
		return msg;
	}

	@Override
	public MsgResponse getduleMent(boolean checkBool) {
		MsgResponse result = new MsgResponse();
		if (checkBool) {
			result.setMsgCode("9000");
			result.setMsgMent("등록된 데이터가 존재합니다.");
		} else {
			result.setMsgCode("9999");
			result.setMsgMent("등록된 데이터가 존재하지 않습니다.");
		}
		return result;
	}

	@Override
	public MsgResponse doUserTranMent(int i) {
		MsgResponse msg = new MsgResponse();
		switch (i) {
			case 800:
				msg.setMsgCode(String.valueOf(i));
				msg.setMsgMent("중복된 사용자가 존재합니다. 사번 및 아이디 값을 확인하여 주세요.");
				break;
			case 810:
				msg.setMsgCode(String.valueOf(i));
				msg.setMsgMent("등록할 사용자 정보를 다시 한번 확인하여 주세요.");
				break;
			case 820:
				msg.setMsgCode(String.valueOf(i));
				msg.setMsgMent("사용자 권한 등록에 실패하였습니다.");
				break;
			case 821:
				msg.setMsgCode(String.valueOf(i));
				msg.setMsgMent("기존 사용자 권한 수정에 실패하였습니다.");
				break;
			default:
				break;
		}
		return msg;
	}

	@Override
	public MsgResponse getMatchMent(boolean checkBool) {
		MsgResponse result = new MsgResponse();
		if (checkBool) {
			result.setMsgCode("0000");
			result.setMsgMent("일치합니다.");
		} else {
			result.setMsgCode("9999");
			result.setMsgMent("일치하지 않습니다.");
		}
		return result;
	}

//	@Override
//	public SiteAdminVO getSiteAdminDetail(SiteAdminVO siteAdminVo) {
//		return commonMapper.getSiteAdminDetail(siteAdminVo);
//	}
//
//	@Override
//	public MsgResponse doInsertSiteAdmin(SiteAdminVO siteAdminVo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public MsgResponse doUpdateSiteAdmin(SiteAdminVO siteAdminVo) {
//		MsgResponse result = new MsgResponse();
//		if (commonMapper.dupleSiteAdmin(siteAdminVo) > 0) {
//			siteAdminVo.setModUser(new CustomUtil().getSessionData(Constants.USERCODE));
//			if (commonMapper.doUpdateSiteAdmin(siteAdminVo) > 0) {
//				result = getCommonMent(true);
//			} else {
//				result = getCommonMent(false);
//			}
//			/*
//			 * String[] fptSeqArr = siteAdminVo.getFptSeq().split(",", -1);
//			 * String[] weekdayArr = siteAdminVo.getWeekday().split(",", -1);
//			 * String[] startTimeArr = siteAdminVo.getStartTime().split(",", -1);
//			 * String[] endTimeArr = siteAdminVo.getEndTime().split(",", -1);
//			 * 
//			 * for(int i=0; i<fptSeqArr.length; i++) {
//			 * siteAdminVo.setFptSeq(fptSeqArr[i]);
//			 * siteAdminVo.setWeekday(weekdayArr[i]);
//			 * siteAdminVo.setStartTime(startTimeArr[i]);
//			 * siteAdminVo.setEndTime(endTimeArr[i]);
//			 * 
//			 * commonMapper.doUpdateFreeProgramTime(siteAdminVo);
//			 * }
//			 */
//		} else {
//			siteAdminVo.setModUser(new CustomUtil().getSessionData(Constants.USERCODE));
//			if (commonMapper.doInsertSiteAdmin(siteAdminVo) > 0) {
//				result = getCommonMent(true);
//			} else {
//				result = getCommonMent(false);
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public MsgResponse doDeleteSiteAdmin(SiteAdminVO siteAdminVo) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<CommonResponse> selectCommonList(CommonResponse commonVO) throws Exception {
		return commonMapper.selectCommonList(commonVO);
	}

}
