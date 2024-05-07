package shane.blog.domain.member;

import lombok.Builder;
import lombok.Getter;
import shane.blog.entity.Employee;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
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

    // Entity -> DTO
    public static MemberResponse fromEntity(MemberRequest member) {
        return MemberResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .gender(member.getGender())
                .gender(member.getGender())
                .birthday(member.getBirthday())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate())
                .build();
    }

}
