package shane.blog.domain.commoncode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgResponse {
	/**
	 * MsgCode
	 */
	private String msgCode = null;
	/**
	 * Messge Ment
	 */
	private String msgMent = null;
	/**
	 * Message Type
	 * 0 : alert
	 * 1 : View
	 */
	private String msgType = null;
	/**
	 * Msg Lange Type
	 * 
	 * @return
	 */
	private String location = "ko_kr";
	/**
	 * 임시 데이터들
	 */
	private Object pValue = null;

	/**
	 * Msg Lange Type
	 * 
	 * @return
	 */
	private String msgFlag = "N";
}
