package shane.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.security.jwt.JwtAuthenticationEntryPoint;
import shane.blog.security.jwt.JwtAuthenticationFilter;
import shane.blog.service.CustomOAuth2UserService;
import shane.blog.security.jwt.OAuth2SuccessHandler;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;
    

    // private final OAuth2UserService oAuth2UserService;   // 참고
    // private final DefaultOAuth2UserService customOAuth2UserService; // 참고
    private final CustomOAuth2UserService customOAuth2UserService; // 최종

    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private final String[] allowedUrls = {
          "/user/checkId"
        , "/user/register"
        , "/user/login"
        , "/login/update"
        , "/oauth2/authorization/google"    // 최초 OAuth2 인증 요청(1)
        // , "/api/v1/auth/oauth2/google"   // 최초 OAuth2 인증 요청(2)
        , "/login/oauth2/code/google"       // OAuth2 리다이렉션(google OAuth2에서 CORS 처리를 해 주지 않아 오류 발생한다.)
        , "/googlecallback"                 // OAuth2 성공시 redirect
        , "/board/list"
        , "/board/{boardId}"
        , "/board/search"
        , "/board/{boardId}/comment/list/**"
        , "/board/{boardId}/file/download/**"

        , "/PictureList"
        , "/detailPicture"
        , "/commoncode/**"
        , "/openapi/**"

        // , "/post/list.do"
        // , "/post/detail.do"
        // , "/post/search"
        // , "/post/{id}/comment/list/**"
        // , "/post/{id}/file/download/**"

        // , "/file/{postId}/files/{postId}/download"
    };

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("SecurityConfig.filterChain() start");
        return http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .authorizeHttpRequests(authorize
                    -> authorize
                            .requestMatchers(allowedUrls).permitAll()

                            .requestMatchers("/board/**").hasAnyRole("ADMIN", "USER", "GUEST")
                            .requestMatchers("/comment/**").hasAnyRole("ADMIN", "USER") // 추가(JPA)

                            .requestMatchers("/employees").hasAnyRole("USER") // 추가(JPA)
                            .requestMatchers("/online/**").hasRole("ADMIN") // 추가(JPA)

                            .requestMatchers("/post/**").hasAnyRole("ADMIN", "USER") // 추가(Mybatis)
                            .requestMatchers("/feedback/**").hasAnyRole("ADMIN", "USER") // 추가(Mybatis)
                            .requestMatchers("/file/**").hasAnyRole("ADMIN", "USER") // 추가(Mybatis)

                            .requestMatchers("/member/**").hasRole("ADMIN") // 추가(Mybatis)

                            .anyRequest().authenticated()      // 나머지는 모두 인증, 인가 받아야 한다.
                // .anyRequest().permitAll()
                )

                //basic 인증방식은 username:password를 base64 인코딩으로 Authroization 헤더로 보내는 방식
                // .httpBasic(basic -> basic.disable())
                // .formLogin(login -> login.disable())

                // OAuth2 로그인을 사용
                .oauth2Login(oauth2 -> oauth2 // OAuth2 로그인을 이용한다.
                        // .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2")) // Login.js에서 "http://localhost:8989/oauth2/authorization/google"와 동일한 결과
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/login/oauth2/code/google")) // OAuth2 로그인 성공 후 리다이렉션을 처리한다.
                        .userInfoEndpoint(endpoint -> endpoint.userService(customOAuth2UserService)) // OAuth2 로그인 성공 후 사용자 정보를 가져온다.
                        .successHandler(oAuth2SuccessHandler)   //인증에 성공하면 실행할 handler (redirect 시킬 목적)
                    )
                // .userService(principalOAuth2DetailsService)) // 가져온 유저의 정보를 principalOAuth2DetailsService 객체가 처리한다.

                // 쿠키와 세션을 사용하지 않는다. 클라이언트의 상태를 유지할 필요가 있는 경우나 인증된 사용자의 상태를 관리해야 하는 경우에는 이 정책을 사용하지 않는다.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 인증되지 않은 사용자가 액세스하려고 시도하여 예외가 발생할 때마다 호출
                .exceptionHandling(excep -> excep.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // UsernamePasswordAuthenticationFilter 앞에 jwtAuthenticationFilter를 추가하면 양식 기반 인증 전에 JWT 인증이 처리됩니다. 
                // 이는 일반적으로 JWT와 양식 기반 인증을 모두 지원하고 JWT 인증의 우선순위를 지정하려는 애플리케이션에서 수행됩니다.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter .class)

                .build();
    }

}