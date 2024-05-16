package shane.blog.config.auth.dto;

import lombok.Getter;
import shane.blog.common.Role;
import shane.blog.entity.Member;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String username;
    private String email;
    private Role roles;
    private String picture;
	
    public SessionUser(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.roles = member.getRoles();
        this.picture = member.getPicture();
    }
}