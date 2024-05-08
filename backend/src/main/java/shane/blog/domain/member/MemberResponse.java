package shane.blog.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponse {

    private Long id;                       // 회원 번호 (PK)
    private String loginId;                // 로그인 ID
    private String password;               // 비밀번호
    private String name;                   // 이름
    private String gender;                 // 성별
    private String birthday;            // 생년월일
    private Boolean deleteYn;              // 삭제 여부
    private String createdDate;     // 생성일시
    private String modifiedDate;    // 최종 수정일시

    public void clearPassword() {
        this.password = "";
    }

}
