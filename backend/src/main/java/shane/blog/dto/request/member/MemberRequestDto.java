package shane.blog.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shane.blog.common.Role;

/**
 * -Request-
 * 검색 요청에 대한 정보 DTO, 검색 조건이 늘어날 수 있어 객체로 만듬
 */

// @Getter
// @Setter
// public class MemberRequestDto {

//     private String email;
//     private String username;
//     private Role roles;
//     private String picture;
//     private String provider;

//     @Builder
//     public MemberRequestDto(String email, String username, Role roles, String picture, String provider) {
//         this.email = email;
//         this.username = username;
//         this.roles = roles;
//         this.picture = picture;
//         this.provider = provider;
//     }

//     public static MemberRequestDto createdMemberSelect(String email, String username, Role roles, String picture, String provider) {
//         return MemberRequestDto.builder()
//                 .email(email)
//                 .username(username)
//                 .roles(roles)
//                 .picture(picture)
//                 .provider(provider)
//                 .build();
//     }
// }
