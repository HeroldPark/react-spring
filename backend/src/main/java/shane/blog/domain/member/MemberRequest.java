package shane.blog.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shane.blog.common.BaseTimeEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

@Getter
@NoArgsConstructor
public class MemberRequest extends BaseTimeEntity {

    private Long id;                // 회원 번호 (PK)
    private String loginId;         // 로그인 ID
    private String password;        // 비밀번호
    private String name;            // 이름
    private String gender;          // 성별
    private String birthday;     // 생년월일

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if (StringUtils.isEmpty(password)) {
            return;
        }
        password = passwordEncoder.encode(password);
    }

    @Builder
    public void MemberResponse(Long id, String loginId, String name, String gender, String birthday) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        // this.deleteYn = deleteYn;
        // this.createdDate = createdDate;
        // this.modifiedDate = modifiedDate;
    }
}
