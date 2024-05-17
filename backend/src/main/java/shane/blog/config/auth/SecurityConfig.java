// package shane.blog.config.auth;

// // import com.codeverse.springstudy.web.user.enums.Role;
// import lombok.RequiredArgsConstructor;
// import shane.blog.user.enums.Role;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig {
//     @Autowired
//     private final CustomOAuth2UserService customOAuth2UserService;

//     @Bean
//     public AuthenticationManager authenticationManager(
//             AuthenticationConfiguration authenticationConfiguration) throws Exception {
//         return authenticationConfiguration.getAuthenticationManager();
//     }
	
//     @SuppressWarnings("removal")
//     @Bean
//     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//                 .headers(headers -> headers.frameOptions().disable())
//                 .authorizeHttpRequests(requests -> requests
//                         .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/user/**", "/login/oauth2/code/{registrationId}").permitAll()
//                         .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
//                         .anyRequest().authenticated())
//                 .logout(logout -> logout
//                         .logoutSuccessUrl("/"))
//                 .oauth2Login(login -> login
//                         .userInfoEndpoint()
//                         .userService(customOAuth2UserService));
		
//         return http.build();
//     }
// }