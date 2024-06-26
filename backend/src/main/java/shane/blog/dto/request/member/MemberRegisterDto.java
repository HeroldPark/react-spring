package shane.blog.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.Role;
import shane.blog.entity.Member;

/**
 * -Request-
 * 회원 가입 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {

    private Long memberId;
    private String email;
    private String password;
    private String passwordCheck;
    private String username;
    private Role roles;

    @Builder
    public MemberRegisterDto(Long memberId, String email, String password, String passwordCheck, String username, Role roles) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
        this.roles = roles;
    }

    // DTO -> Entity
    public static Member ofEntity(MemberRegisterDto dto) {
        return Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(dto.getRoles())  // .roles(Role.ADMIN)
                .build();
    }
}
