package shane.blog.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request-
 * 회원 정보 변경 요청 dto
 */

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {

    private String password;
    private String passwordCheck;
    private String username;
    private String email;

    @Builder
    public MemberUpdateDto(String password, String passwordCheck, String username, String email) {
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
        this.email = email;
    }
}
