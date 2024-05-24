package shane.blog.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.config.auth.dto.OAuthAttributes;
import shane.blog.config.auth.dto.SessionUser;
import shane.blog.entity.Member;
import shane.blog.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository userRepository;
    private final HttpSession httpSession;
	
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.debug("loadUser() 시작. \t {}", userRequest);

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
        log.debug("attributes: {}", attributes);
        
        Member user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
		
        String roles = "";
        switch(user.getRoles()) {
            case ADMIN:
                roles = "ADMIN";
                break;
            case USER:
                roles = "USER";
                break;
            default:
                roles = "GUEST";
                break;
        }
        
        return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(roles)),
                    attributes.getAttributes(),
                    attributes.getNameAttributeKey()
        );
    }
	
    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member user = userRepository.findByEmail(attributes.getEmail())
                                    // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
                                    .map(entity -> {
                                        entity.update(attributes.getPassword(), attributes.getUsername());
                                        return entity;
                                    })
                                    .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}