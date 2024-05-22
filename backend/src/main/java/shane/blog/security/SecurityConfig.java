package shane.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
// import shane.blog.service.PrincipalOAuth2DetailsService;
import shane.blog.service.CustomOAuth2UserService;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    // private final PrincipalOAuth2DetailsService principalOAuth2DetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private final String[] allowedUrls = {
          "/user/checkId"
        , "/user/register"
        , "/user/login"
        , "/login/oauth2/google"
        // , "/login/oauth2/code/**"
        , "/board/list"
        , "/board/{boardId}"
        , "/board/search"
        , "/board/{boardId}/comment/list/**"
        , "/board/{boardId}/file/download/**"
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
                                        // .requestMatchers("/login/getGoogleAuthUrl").permitAll() // 해당 경로에 대해 POST 요청을 허용

                                        .requestMatchers("/employees").hasAnyRole("USER") // 추가(JPA)
                                        .requestMatchers("/post/**").hasAnyRole("ADMIN", "USER") // 추가(Mybatis)
                                        .requestMatchers("/member/**").hasRole("ADMIN") // 추가(Mybatis)

                                        .requestMatchers("/board/**").hasAnyRole("ADMIN", "USER", "GUEST")
                                        .anyRequest().authenticated()      // 나머지는 모두 인증, 인가 받아야 한다.
                // .anyRequest().permitAll()
                )

                //basic 인증방식은 username:password를 base64 인코딩으로 Authroization 헤더로 보내는 방식
                .httpBasic(basic -> basic.disable())
                .formLogin(login -> login.disable())

                // OAuth2 로그인을 사용
                .oauth2Login(login -> login // OAuth2 로그인을 이용한다.
                        .defaultSuccessUrl("/login/oauth2/google", true) // OAuth2 성공시 redirect
                        .userInfoEndpoint() // 로그인된 유저의 정보를 가져온다.
                        .userService(customOAuth2UserService))
                // .userService(principalOAuth2DetailsService)) // 가져온 유저의 정보를
                // principalOAuth2DetailsService 객체가 처리한다.

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