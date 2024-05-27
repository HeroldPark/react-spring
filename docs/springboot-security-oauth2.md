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
            # kakao:
            #     client-id: TODO
            #     client-secret: TODO
            #     redirect-uri: http://localhost:8989/login/oauth2/code/{registrationId}
            #     scope: profile_nickname, profile_image, account_email
            #     authorization-grant-type: authorization_code
        provider:
            google:
                authorization-uri: https://accounts.google.com/o/oauth2/auth
                token-uri: https://oauth2.googleapis.com/token
                user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
                user-name-attribute: sub

2. SecurityConfig.java
   public class SecurityConfig {
        private final CustomOAuth2UserService customOAuth2UserService; // 최종
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            ........................................................................
            // OAuth2 로그인을 사용
            .oauth2Login(oauth2 -> oauth2 // OAuth2 로그인을 이용한다.
                    // .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2")) // Login.js에서 "http://localhost:8989/oauth2/authorization/google"와 동일한 결과
                    .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/google")) // OAuth2 로그인 성공 후 리다이렉션을 처리한다.
                    .userInfoEndpoint(endpoint -> endpoint.userService(customOAuth2UserService)) // OAuth2 로그인 성공 후 사용자 정보를 가져온다.
                    .successHandler(oAuth2SuccessHandler)   //인증에 성공하면 실행할 handler (redirect 시킬 목적)
                )
            ........................................................................
        }
   }

3. Login.js
   function Login() {

        return (
            <div className="my-1 d-flex justify-content-center">
				<button className="btn btn-outline-secondary" onClick={login}><i className="fas fa-sign-in-alt"></i> 로그인</button>
				{/* <button className="btn btn-outline-secondary" onClick={googleLogin}><i className="fas fa-sign-in-alt"></i> 구글 로그인</button> */}
				<a href="http://localhost:8989/oauth2/authorization/google" className="btn btn-sm btn-success active" role="button">Google Login</a>
			</div>
        );
   }

4. CustomOAuth2UserService.java
   public class CustomOAuth2UserService extends DefaultOAuth2UserService {
        @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            ........................................................................
        }
    }

5. OAuth2SuccessHandler.java
   public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
        
        private static final String REDIRECT_URI = "http://localhost:3000/googlecallback";

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            ........................................................................
        }
   }

6. CallBackGoogle.js
    import React, { useEffect } from 'react';
    import { useCookies } from 'react-cookie';
    import { useLocation, useNavigate, useParams } from 'react-router-dom';
    import { useState, useContext } from "react";
    import { AuthContext } from "../context/AuthProvider";
    import { HttpHeadersContext } from "../context/HttpHeadersProvider";

    export default function CallBackGoogle() {
        console.log("[CallBackGoogle.js] CallBackGoogle() start");

        const { auth, setAuth } = useContext(AuthContext);
        const { headers, setHeaders } = useContext(HttpHeadersContext);

        // const {token, expirationTime } = useParams();
        const location = useLocation();
        const searchParams = new URLSearchParams(location.search);
        const token = searchParams.get("token");
        const name = searchParams.get("name");
        const email = searchParams.get("email");
        const expirationTime = searchParams.get("expirationTime");

        const [cookies, setCookie] = useCookies();
        const navigate = useNavigate();

        console.log("token: ", token);
        console.log("name: ", name);
        console.log("email: ", email);
        console.log("expirationTime: ", expirationTime);

        useEffect(() => {
            if(!token || !expirationTime) { return; }

            const now = (new Date().getTime()) * 1000;
            const expires = new Date(now + Number(expirationTime));

            setCookie('accessToken', token, { path: '/', expires });

            // JWT 토큰 저장
            localStorage.setItem("bbs_access_token", token);
            localStorage.setItem("id", email);

            setAuth(email); // 사용자 인증 정보(아이디 저장)
            setHeaders({"Authorization": `Bearer ${token}`}); // 헤더 Authorization 필드 저장

            navigate('/bbslist');

        }, [token, expirationTime]);

        return (
            <div></div>
        );
    }