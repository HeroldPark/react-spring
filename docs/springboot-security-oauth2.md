Spring Boot 3.x + Security + OAuth2 설정

1. application.yml

# oauth2:
spring:
  security:
    oauth2:
      client:
        registration:
            google:
                client-id: 33063057275-5rtjgjlas7ia19fdrkfvrh4sm4gc92oi.apps.googleusercontent.com
                client-secret: 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
                scope: profile, email
                redirect-uri: http://localhost:8989/login/oauth2/code/google
        provider:
            google:
                authorization-uri: https://accounts.google.com/o/oauth2/auth
                token-uri: https://oauth2.googleapis.com/token
                user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
                user-name-attribute: sub

2. SecurityConfig.java
   public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            ........................................................................
            .oauth2Login(login -> login                                 // OAuth2 로그인을 이용한다.
                .loginPage("/login/oauth2/info")                        // OAuth2 로그인 페이지(여기로 넘어가지 않는다.?)
                .successHandler(new MyAuthenticationSuccessHandler())   // 인증에 성공하면 실행할 handler (redirect 시킬 목적)
                .userInfoEndpoint()                                     // 로그인된 유저의 정보를 가져온다.
                .userService(oAuth2UserService)                         // 가져온 유저의 정보를 oAuth2UserService 객체가 처리한다.
                )
            ........................................................................
        }
   }

3. Login.js
   function Login() {

        return (
            <div className="my-1 d-flex justify-content-center">
				<button className="btn btn-outline-secondary" onClick={login}><i className="fas fa-sign-in-alt"></i> 로그인</button>
				<a href="http://localhost:8989/oauth2/authorization/google" className="btn btn-sm btn-success active" role="button">Google Login</a>
			</div>
        );
   }

4. OAuth2UserService.java
   public class OAuth2UserService extends DefaultOAuth2UserService {
        @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            ........................................................................
        }
    }

5. MyAuthenticationSuccessHandler.java
   public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        
        private static final String REDIRECT_URI = "http://localhost:3000/callbacklogin";

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            ........................................................................
        }
   }

6. CallBackLogin.js
    const CallBack = () => {
        return (
            <div>
                <h1>로그인 중...</h1>
            </div>
        );
    }