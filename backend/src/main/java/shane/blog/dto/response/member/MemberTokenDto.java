package shane.blog.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.Role;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * -Response-
 * 사용자 정보 반환 + token Dto
 */

@Getter
@Setter
@NoArgsConstructor
public class MemberTokenDto {
    private String email;
    private Role roles;
    private String token;

    @Builder
    public MemberTokenDto(String email, Role roles, String token) {
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    // Entity -> DTO
    public static MemberTokenDto fromEntity(UserDetails member, Role roles, String token) {
        return MemberTokenDto.builder()
                .email(member.getUsername())
                .roles(roles)
                .token(token)
                .build();
    }
}
