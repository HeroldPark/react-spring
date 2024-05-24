package shane.blog.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.config.auth.MemberProfile;
import shane.blog.config.auth.dto.OAuthAttributes;
import shane.blog.entity.Member;
import shane.blog.repository.MemberRepository;
import shane.blog.user.entries.User;
import shane.blog.user.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class OAuth2UserService extends DefaultOAuth2UserService {

//     private final MemberRepository memberRepository;

//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//         OAuth2UserService delegate = (OAuth2UserService) new DefaultOAuth2UserService();
//         OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(kakao, google, naver)에서 가져온 유저 정보를 담고있음

//         String registrationId = userRequest.getClientRegistration()
//                                            .getRegistrationId(); // OAuth 서비스 이름(ex. kakao, naver, google)
//         String userNameAttributeName = userRequest.getClientRegistration()
//                                                   .getProviderDetails()
//                                                   .getUserInfoEndpoint()
//                                                   .getUserNameAttributeName(); // OAuth 로그인 시 키(pk)가 되는 값
//         Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth 서비스의 유저 정보들

//         // MemberProfile memberProfile = OAuthAttributes.extract(enumValue, attributes); // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌
//         MemberProfile memberProfile = new MemberProfile();
//         memberProfile.setProvider(registrationId);
//         Member member = saveOrUpdate(memberProfile);

//         Map<String, Object> customAttribute = customAttribute(attributes, userNameAttributeName, memberProfile, registrationId);

//         return new DefaultOAuth2User(
//                 Collections.singleton(new SimpleGrantedAuthority("USER")),
//                 customAttribute,
//                 userNameAttributeName);
//     }

//     private Map customAttribute(Map attributes, String userNameAttributeName, MemberProfile memberProfile, String registrationId) {
//         Map<String, Object> customAttribute = new LinkedHashMap<>();
//         customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
//         customAttribute.put("provider", registrationId);
//         customAttribute.put("name", memberProfile.getUsername());
//         customAttribute.put("email", memberProfile.getEmail());
//         return customAttribute;
//     }

//     private Member saveOrUpdate(MemberProfile memberProfile) {
//         Optional<Member> optionalMember = memberRepository.findByEmailAndProvider(memberProfile.getEmail(), memberProfile.getProvider());
//         if (optionalMember.isPresent()) {
//             Member member = optionalMember.get();
//             member.update(memberProfile.getPassword(), memberProfile.getUsername());
//             return memberRepository.save(member);
//         } else {
//             Member member = memberProfile.toMember();
//             return memberRepository.save(member);
//         }
//     }
// }


@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.debug("OAuth2UserService.loadUser() 시작. \t {}", userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            log.debug("OAuth2UserService.loadUser() oAuth2User: {}", oAuth2User.getAttributes());
        } catch (Exception e) {
            log.error("OAuth2UserService.loadUser() error: {}", e);
        }

        // OAuth2Client 정보
        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // Google
        // String OAuthClientName = userRequest.getClientRegistration().getClientName();    // Google
        log.debug("OAuth2UserService.loadUser() registrationId: {}", registrationId);

        // Optional<Member> member = Optional.ofNullable(new Member());
        // String email = null;
        // if(registrationId.equals("google")) {
        //     email = oAuth2User.getAttribute("email");
        //     member = memberRepository.findByEmail(email);
        //     if(member == null) {
        //         Member newUser = new Member();
        //         newUser.setUsername(oAuth2User.getAttribute("name"));
        //         newUser.setEmail(email);
        //         newUser.setPicture(oAuth2User.getAttribute("picture"));
        //         memberRepository.save(newUser);
        //     }
        // }
        // memberRepository.save(member);

        
        // Role generate
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");
 
        // nameAttributeKey
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        
        // // DB 저장로직이 필요하면 추가
 
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
    }
}

// 기존 코드
// @Slf4j
// @Service
// public class OAuth2UserService extends DefaultOAuth2UserService {

//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//         log.debug("OAuth2UserService.loadUser() 시작. \t {}", userRequest);

//         OAuth2User oAuth2User = super.loadUser(userRequest);
 
//         // Role generate
//         List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");
 
//         // nameAttributeKey
//         String userNameAttributeName = userRequest.getClientRegistration()
//                 .getProviderDetails()
//                 .getUserInfoEndpoint()
//                 .getUserNameAttributeName();
        
//         // DB 저장로직이 필요하면 추가
 
//         return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
//     }
// }