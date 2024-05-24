package shane.blog.security.jwt;

import org.codehaus.commons.compiler.java8.java.util.Optional;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.entity.Member;
import shane.blog.user.entries.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final String REDIRECT_URI = "http://localhost:3000/googlecallback"; // http://localhost:3000/auth/oauth-response
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("OAuth2SuccessHandler.onAuthenticationSuccess() 시작.");

        Object principal = authentication.getPrincipal();
        OAuth2User oAuth2User = null;
        if (principal instanceof OAuth2User) {
            oAuth2User = (OAuth2User) principal;
            System.out.println("OAuth2SuccessHandler.SuccessHandler oAuth2User: " + oAuth2User);
            System.out.println("OAuth2SuccessHandler.SuccessHandler oAuth2User.getAttributes(): " + oAuth2User.getAttributes());
        }
        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserDetails userDetails = new Member();
        ((Member) userDetails).setUsername((String) attributes.get("name"));

        String token = jwtTokenUtil.generateToken(userDetails);
        String name = (String) attributes.get("name");

        String redirectUrl = UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .queryParam("token", token)
                .queryParam("name", name)
                .queryParam("expirationTime", 3600)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        // "http://localhost:3000/auth/oauth-response/" + token + "/3600"
        response.sendRedirect(redirectUrl);
    }
}

// 기존 코드
// @Slf4j
// @Component
// public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//     private static final String REDIRECT_URI = "http://localhost:3000/googlecallback";

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//         log.debug("MyAuthenticationSuccessHandler.onAuthenticationSuccess() 시작.");

//         Object principal = authentication.getPrincipal();
//         if (principal instanceof OAuth2User) {
//             OAuth2User oAuth2User = (OAuth2User) principal;
//             System.out.println("SuccessHandler oAuth2User: " + oAuth2User);
//         }

//         String redirectUrl = UriComponentsBuilder.fromUriString(REDIRECT_URI)
//                 .queryParam("accessToken", "accessToken")
//                 .queryParam("refreshToken", "refreshToken")
//                 .build()
//                 .encode(StandardCharsets.UTF_8)
//                 .toUriString();

//         response.sendRedirect(redirectUrl);
//     }
// }
