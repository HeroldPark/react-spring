package shane.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class OAuthController {
    
    @GetMapping("/oauth2/google")
    public String oauthLoginInfo(Authentication authentication) {
        log.debug("OAuthController.oauthLoginInfo() 시작.");

        // 인증되지 않은 사용자에게 호출되었을 때의 처리
        if (authentication == null) {
            log.debug("OAuthController.oauthLoginInfo() : authentication is null.");
            return "You are not authenticated.";
        }

        //oAuth2User.toString() 예시 : Name: [2346930276], Granted Authorities: [[USER]], User Attributes: [{id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}]
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        //attributes.toString() 예시 : {id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}
        Map<String, Object> attributes = oAuth2User.getAttributes();
        
        return attributes.toString();
    }
}