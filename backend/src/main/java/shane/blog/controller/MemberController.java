package shane.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.common.exception.MemberException;
import shane.blog.config.auth.CustomOAuth2UserService;
import shane.blog.dto.request.member.MemberLoginDto;
import shane.blog.dto.request.member.MemberRegisterDto;
import shane.blog.dto.request.member.MemberUpdateDto;
import shane.blog.dto.response.member.MemberResponseDto;
import shane.blog.dto.response.member.MemberTokenDto;
import shane.blog.entity.Member;
import shane.blog.service.LoginService;
import shane.blog.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    // private final CustomOAuth2UserService googleService;
    // private final LoginService  loginService;

    @GetMapping("/checkId")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email) {
        memberService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterDto memberRegisterDTO) {
        MemberResponseDto successMember = memberService.register(memberRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberTokenDto> login(@RequestBody MemberLoginDto memberLoginDTO) {
        MemberTokenDto loginDTO = memberService.login(memberLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).header(loginDTO.getToken()).body(loginDTO);
    }

    // // GOOGLE 계정으로 로그인
    // // http://localhost:8989/user/code/google
    // @PostMapping("/code/{registrationId}")
    // public ResponseEntity<Void> googleLogin(@RequestBody MemberLoginDto memberLoginDTO, @RequestParam String code, @PathVariable String registrationId) {
    //     log.debug("googleLogin() email={}", memberLoginDTO.getEmail());
    //     log.debug("googleLogin() registrationId={}", registrationId);

    //     try {
    //         loginService.socialLogin(code, registrationId);
    //         return ResponseEntity.ok().build();
    //     } catch (Exception e) {
    //         log.error("Error during social login", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    @PostMapping("/checkPwd")
    public ResponseEntity<MemberResponseDto> check(
            @AuthenticationPrincipal Member member,
            @RequestBody Map<String, String> request) {
        String password = request.get("password");
        MemberResponseDto memberInfo = memberService.check(member, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> update(
            @AuthenticationPrincipal Member member,
            @RequestBody MemberUpdateDto memberUpdateDTO) {
        MemberResponseDto memberUpdate = memberService.update(member, memberUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }
}
