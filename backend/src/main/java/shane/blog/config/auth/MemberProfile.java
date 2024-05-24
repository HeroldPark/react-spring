package shane.blog.config.auth;

import lombok.Getter;
import lombok.Setter;
import shane.blog.entity.Member;

@Getter
@Setter
public class MemberProfile {
    private String username;
    private String password;
    private String email;
    private String provider;
    private String nickname;

    public Member toMember() {
        return Member.builder()
                     .username(username)
                     .password(password)
                     .email(email)
                     .provider(provider)
                     .build();
    }

}