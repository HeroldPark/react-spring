package shane.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.common.Role;
import shane.blog.config.auth.dto.OAuthAttributes;
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
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        @SuppressWarnings("rawtypes")
        OAuth2UserService delegate = (OAuth2UserService) new DefaultOAuth2UserService();
        
        String registrationId = userRequest.getClientRegistration()
                                           .getRegistrationId(); // OAuth 서비스 이름(ex. kakao, naver, google)
        String userNameAttributeName = userRequest.getClientRegistration()
                                            .getProviderDetails()
                                            .getUserInfoEndpoint()
                                            .getUserNameAttributeName(); // OAuth 로그인 시 키(pk)가 되는 값
        @SuppressWarnings("unchecked")
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);

        return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority(member.getRoles().toString())),
                    attributes.getAttributes(),
                    attributes.getNameAttributeKey()
        );
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                    // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
                    .map(entity -> {
                        entity.update(attributes.getPassword(), attributes.getUsername());
                        return entity;
                    })
                    .orElse(attributes.toEntity());

        if(attributes.getPassword() == null || attributes.getUsername() == null) {
            member.setPassword("p@ssw0rd");
            member.setRoles(Role.USER);
            member.setUsername(attributes.getAttributes().get("name").toString());
        }

        return memberRepository.save(member);
    }
}

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

//     private final MemberRepository memberRepository;
//     // private final HttpSession httpSession;
	
//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//         log.debug("loadUser() 시작. \t {}", userRequest);

//         OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//         OAuth2User oAuth2User = delegate.loadUser(userRequest);
//         String registrationId = userRequest.getClientRegistration().getRegistrationId();
//         String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//         OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
//         log.debug("attributes: {}", attributes);
        
//         Member member = saveOrUpdate(attributes);
//         // httpSession.setAttribute("user", new SessionUser(member));
        
//         return new DefaultOAuth2User(
//                     Collections.singleton(new SimpleGrantedAuthority(member.getRoles().toString())),
//                     attributes.getAttributes(),
//                     attributes.getNameAttributeKey()
//         );
//     }
	
//     private Member saveOrUpdate(OAuthAttributes attributes) {
//         Member member = memberRepository.findByEmail(attributes.getEmail())
//                                     // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
//                                     .map(entity -> {
//                                         entity.update(attributes.getPassword(), attributes.getUsername());
//                                         return entity;
//                                     })
//                                     .orElse(attributes.toEntity());

//         if(attributes.getEmail() == null) {
//             member.setEmail("p@ssword");
//             member.setUsername("oath2");
//         }

//         return memberRepository.save(member);
//     }
// }