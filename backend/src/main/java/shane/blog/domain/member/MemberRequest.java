package shane.blog.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.BaseTimeEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest extends BaseTimeEntity {

    private Long id;                // 회원 번호 (PK)
    private String loginId;         // 로그인 ID
    private String password;        // 비밀번호
    private String name;            // 이름
    private int gender;          // 성별
    private String birthday;        // 생년월일
    private Boolean deleteYn;       // 생년월일

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        // 비밀번호가 null이 아닌 경우에만 암호화
        this.password = passwordEncoder.encode(this.password);
    }
}
