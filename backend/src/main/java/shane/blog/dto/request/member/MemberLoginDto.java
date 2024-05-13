package shane.blog.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.Role;

/**
 * -Request-
 * 로그인 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {

    private String email;
    private String password;
    private Role roles;

    @Builder
    public MemberLoginDto(String email, String password, Role roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
