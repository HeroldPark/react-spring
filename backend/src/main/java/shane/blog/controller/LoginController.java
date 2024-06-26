package shane.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import shane.blog.dto.request.member.MemberRegisterDto;
import shane.blog.dto.request.member.MemberUpdateDto;
import shane.blog.dto.response.member.MemberResponseDto;
import shane.blog.entity.Member;
import shane.blog.service.LoginService;
import shane.blog.service.MemberService;

@Slf4j
@Controller
@RequestMapping(value = "/login", produces = "application/json")
public class LoginController {

    @Autowired
    private LoginService  loginService;

    @Autowired
    private MemberService  memberService;
   
    @GetMapping("/oauth2/info")
    public String googleLogin() {
        log.debug("LoginController.googleLogin() 시작");

        // loginService.socialLogin(code, registrationId);
        return "/Components/login/Login";
    }

    @GetMapping("/oauth2/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        log.debug("LoginController.googleLogin() 시작");

        loginService.socialLogin(code, registrationId);
    }

    // 계정 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<Page<MemberResponseDto>> loginList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        // JWT 인증 토큰 확인을 위해서 추가
        log.debug("Authorization Header: " + authorizationHeader);

        Page<MemberResponseDto> listDTO = memberService.getAllMembers(pageable);

        log.debug("loginList: " + listDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @PostMapping("/write")
    public ResponseEntity<MemberResponseDto> write(@RequestBody MemberRegisterDto memberDTO, @AuthenticationPrincipal Member member) {

        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        MemberResponseDto memberResponseDto = memberService.write(memberDTO, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> detail(@PathVariable("memberId") Long memberId) {
        log.debug("LoginController.detail: 시작");

        MemberResponseDto memberResponseDto = memberService.detail(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<MemberResponseDto> update2(
            @RequestBody MemberUpdateDto memberUpdateDTO) {

        log.debug("LoginController.update2() start");

        MemberResponseDto memberUpdate = memberService.update2(memberUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }

    // 상세보기 -> 삭제
    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<Long> delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}