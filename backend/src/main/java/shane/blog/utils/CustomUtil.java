/**
 * 
 */
package shane.blog.utils;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import shane.blog.domain.member.MemberResponse;

public class CustomUtil {

	private static final Logger logger = LoggerFactory.getLogger(CustomUtil.class);

	public String objectToJson(Object object) {
		ObjectMapper map = new ObjectMapper();
		String json = null;
		try {
			json = map.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.debug(e.toString());
		}

		return json;
	}

	public String getRandomAuthKey(int length) {
		String result = "";
		Random rd = new Random();
		for (int i = 0; i < length; i++) {
			result += String.valueOf(rd.nextInt(10));
		}
		logger.debug("random Key : {}", result);
		return result;
	}

	/**
	 * 세션에서 값 확인하여 세션 타입에 맞추어 전달
	 * 
	 * @param type
	 * @return
	 */
	public String getSessionData(String type) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		return String.valueOf(session.getAttribute(type));
	}

	/**
	 * <pre>
	 * 클라이언트 IP 가져오기
	 * </pre>
	 * 
	 * @Class Name : CommonUtil
	 * @Method Name : getUserIp
	 * @return
	 * @throws UnknownHostException
	 */
	public String getUserIp() throws UnknownHostException {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0)
			ip = req.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0)
			ip = req.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0)
			ip = req.getRemoteAddr();
		return ip;
	}

	/**
	 * 사용자 정보의 개인정보 삭제 함수
	 * 
	 * @param memberVo
	 * @return
	 */
	public MemberResponse cleanPrivacyUser(MemberResponse memberResponse) {
		memberResponse.setLoginId(null);
		memberResponse.setName(null);
		memberResponse.setGender(null);
		return memberResponse;
	}

	public boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("win") >= 0);
	}

	public String getOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = "";

        if (osName.contains("win")) {
            osArch = "windows";
            System.out.println("현재 운영체제는 Windows입니다.");
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            osArch = "linux";
            System.out.println("현재 운영체제는 Unix/Linux 계열입니다.");
        } else if (osName.contains("mac")) {
            osArch = "mac";
            System.out.println("현재 운영체제는 macOS입니다.");
        } else {
            osArch = "none";
            System.out.println("현재 운영체제를 확인할 수 없습니다.");
        }
        return osArch;
    }

	public Date getDateAndTime() {
		// 서울 시간대
    	ZoneId seoulTimeZone = ZoneId.of("Asia/Seoul");
		// 현재 시간을 가져오고 서울 시간대로 변환
		LocalDateTime localDateTime = LocalDateTime.now().atZone(seoulTimeZone).toLocalDateTime();
		// LocalDateTime을 Date로 변환
		Date date = java.sql.Timestamp.valueOf(localDateTime);
		
		return date;
	}

	// 밀리초를 HH:mm:ss 형식으로 변환하는 메서드
    public static String convertToHHMMSS(long millis) {
        long seconds = millis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
