package shane.blog.domain.commoncode;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shane.blog.utils.Constants;
import shane.blog.utils.CustomUtil;
import shane.blog.utils.EgovStringUtil;

@Service
public class CodeServiceImpl implements CodeService {

	// @Resource(name = "applProperties")
	// private Properties applProperties;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	CommonService commonService;

	private static final Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);

	@Override
	public List<CommonResponse> getParentsCodes(String commonResponse, String groupCode, String useYN, String delYN) {
		CommonResponse commonResponseVo = new CommonResponse();
		commonResponseVo.setGroupCode(groupCode);
		commonResponseVo.setCommonCode(commonResponse);
		commonResponseVo.setUseYN(useYN);
		commonResponseVo.setDelYN(delYN);
		return commonMapper.getParentsCodeList(commonResponseVo);
	}

	@Override
	public CommonResponse commonCodeDetail(CommonResponse commonResponse) {
		MsgResponse result = new MsgResponse();
		if (!EgovStringUtil.isEmpty(commonResponse.getCommonCode())) {
			commonResponse = commonMapper.getCommonCodeDetail(commonResponse);
		} else {
			result = commonService.getCommonMent(false);
			logger.debug("code : " + result.getMsgCode() + " // ment : " + result.getMsgMent());
		}
		return commonResponse;
	}

	@Override
	public MsgResponse doDeleteCode(CommonResponse commonResponse) {
		MsgResponse result = new MsgResponse();
		if (!EgovStringUtil.isEmpty(commonResponse.getCommonCode())) {
			if (commonMapper.dupleCode(commonResponse) > 0) {
				if (commonMapper.getChildNodes(commonResponse) > 0) {
					result = commonService.getduleMent(true);
				} else {
					// commonResponse.setDelUser(new CustomUtil().getSessionData(Constances.USERCODE));
					if (commonMapper.doDeleteCode(commonResponse) > 0) {
						result = commonService.getCommonMent(true);
					} else {
						result = commonService.getCommonMent(false);
					}
				}
			} else {
				result = commonService.getduleMent(false);
			}
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}

	@Override
	public MsgResponse doUpdateCode(CommonResponse commonResponse) {
		MsgResponse result = new MsgResponse();
		if (!EgovStringUtil.isEmpty(commonResponse.getCommonCode())
				|| !EgovStringUtil.isEmpty(commonResponse.getCommonName())) {
			if (commonMapper.dupleCode(commonResponse) > 0) {
				commonResponse.setModUser(new CustomUtil().getSessionData(Constants.USERCODE));
				if (commonMapper.doUpdateCode(commonResponse) > 0) {
					result = commonService.getCommonMent(true);
				} else {
					result = commonService.getCommonMent(false);
				}
			} else {
				result = commonService.getduleMent(false);
			}
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}

	@Override
	public MsgResponse doInsertCode(CommonResponse commonResponse) {
		MsgResponse result = new MsgResponse();
		if (!EgovStringUtil.isEmpty(commonResponse.getCommonCode())
				|| !EgovStringUtil.isEmpty(commonResponse.getCommonName())) {
			if (commonMapper.dupleCode(commonResponse) > 0) {
				result = commonService.getduleMent(true);
			} else {
				commonResponse.setRegUser(new CustomUtil().getSessionData(Constants.USERCODE));
				if (commonMapper.doInsertCode(commonResponse) > 0) {
					result = commonService.getCommonMent(true);
				} else {
					result = commonService.getCommonMent(false);
				}
			}
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}

	@Override
	public List<CommonResponse> getSubCodeList(CommonResponse commonResponse) {
		return commonMapper.getSubCodeList(commonResponse);
	}

	@Override
	public List<CommonResponse> getSubCodeList(String commonResponse, String groupCode, String useYN, String delYN) {
		CommonResponse commonResponseVo = new CommonResponse();
		commonResponseVo.setGroupCode(groupCode);
		commonResponseVo.setCommonCode(commonResponse);
		commonResponseVo.setUseYN(useYN);
		commonResponseVo.setDelYN(delYN);
		return commonMapper.getSubCodeList(commonResponseVo);
	}

	@Override
	public List<CommonResponse> getFullCodes(CommonResponse commonResponse) {
		return commonMapper.getFullCodeList(commonResponse);
	}

	@Override
	public int getCodesOrderMax(CommonResponse commonResponse) {
		return commonMapper.getCodesOrderMax(commonResponse);
	}

	@Override
	public MsgResponse dupleCodeCheck(CommonResponse commonResponse) {
		MsgResponse result = new MsgResponse();
		if (!EgovStringUtil.isEmpty(commonResponse.getCommonCode())
				|| !EgovStringUtil.isEmpty(commonResponse.getCommonName())) {
			if (commonMapper.dupleCode(commonResponse) > 0) {
				result = commonService.getduleMent(true);
			} else {
				result = commonService.getduleMent(false);
			}
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}

}
