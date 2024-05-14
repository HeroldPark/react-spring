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
    // 사용자 DB 인덱스 값을 굳이 사용자에게 노출시킬 필요는 없다고 생각
    private String email;
    private String username;
    private Role roles;

    @Builder
    public MemberResponseDto(String email, String username, Role roles) {
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    // Entity -> DTO
    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .roles(member.getRoles())
                .build();
    }
}
