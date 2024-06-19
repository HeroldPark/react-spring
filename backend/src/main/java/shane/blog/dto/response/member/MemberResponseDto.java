package shane.blog.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.Role;
import shane.blog.entity.Member;

/**
 * -Response-
 * 사용자 정보 반환 Dto
 */

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String username;
    private Role roles;
    private String picture;
    private String provider;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public MemberResponseDto(Long memberId, String email, String username, Role roles, String picture, String provider, String createdDate, String modifiedDate) {
        this.memberId = memberId;
        this.email = email;
        this.username = username;
        this.roles = roles;
        this.picture = picture;
        this.provider = provider;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    // Entity -> DTO
    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getMemberld())
                .email(member.getEmail())
                // .username(member.getUsername())     // 이것을 사용하면 username에 email이 입력된다.
                .username(member.getUsernameField()) // getUsername() 대신 필드 접근
                .roles(member.getRoles())
                .picture(member.getPicture())
                .provider(member.getProvider())
                .createdDate(member.getCreatedDate())
                .modifiedDate(member.getModifiedDate())
                .build();
    }
}
