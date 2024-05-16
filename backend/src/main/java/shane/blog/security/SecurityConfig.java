package shane.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.config.auth.CustomOAuth2UserService;
import shane.blog.security.jwt.JwtAuthenticationEntryPoint;
import shane.blog.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

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
        , "/user/code/{registrationId}"
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

                // // OAuth2 로그인을 사용
                .oauth2Login(login -> login
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService))

                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers(allowedUrls).permitAll()

                        .requestMatchers("/employees").hasAnyRole("USER")  // 추가(JPA)
                        .requestMatchers("/post/**").hasAnyRole("ADMIN", "USER")    // 추가(Mybatis)
                        .requestMatchers("/member/**").hasRole("ADMIN")  // 추가(Mybatis)

                        .requestMatchers("/board/**").hasAnyRole("ADMIN", "USER", "GUEST")
                        .anyRequest().authenticated()      // 나머지는 모두 인증, 인가 받아야 한다.
                    )

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
