package shane.blog.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT(JSON Web Token) 토큰의 유효성을 검사하고, 인증
 * 현재는 인증이 유효하지 않더라도 오류 없이 통과되는 것 같다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}") private String HEADER_STRING;
    @Value("${jwt.prefix}") private String TOKEN_PREFIX;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());

        // get token
        // client에서 header에 담아 보낸 토큰을 가져온다.
        // 토큰은 "Bearer "로 시작한다.(Bearer 뒤에 공백이 있음에 주의!)
        // 예) BbsList.js에서 /board/list로 보낸다.
        String header = request.getHeader(HEADER_STRING);
        String username = null; // username 또는 email
        String authToken = null;

        if(header == null) {
            log.info("header is null");
            // throw new MemberException("header is null", HttpStatus.UNAUTHORIZED);
        }

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX," ");
            try {
                username = this.jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException ex) {
                log.info("fail get user id");
                ex.printStackTrace();
            } catch (ExpiredJwtException ex) {
                log.info("Token expired");
                ex.printStackTrace();
            } catch (MalformedJwtException ex) {
                log.info("Invalid JWT !!");
                System.out.println();
                ex.printStackTrace();
            } catch (Exception e) {
                log.info("Unable to get JWT Token !!");
                e.getStackTrace();
            }
        } else {
            log.info("JWT does not begin with Bearer !!");
        }

        if ((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenUtil.validateToken(authToken, userDetails)) {

                // All things going well
                // userDetails 정보로 UsernamePasswordAuthenticationToken 발급
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authenticated user " + username + ", setting security context");

                // 권한 부여
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                // SecurityConfig.filterChain에 설정한 hasRole("USER")가 정상적으로 작동하는지 확인
                // member.getRoles()=="ROLE_USER"과 hasRole("USER")이 일치해야 controller의 써레드가 실행된다.
                // 가령, "/employees/"가 hasRole("ADMIN")이면 hasRole("ADMIN")으로 설정해야 조회 가능하다.
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(
                        auth -> log.info("auth: " + auth.getAuthority())
                );
            } else {
                log.info("Invalid JWT Token !!");
            }
        } else {
            log.info("Username is null or context is not null !!");
        }

        filterChain.doFilter(request, response);
    }
}