package shane.blog.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import shane.blog.common.Role;
import shane.blog.entity.Member;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String password;
    private String email;
    private Role roles;
    private String picture;
	
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String username, String password, String email, Role roles, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.picture = picture;
    }
	
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }
	
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("username"))
                .password((String) attributes.get("password"))
                .email((String) attributes.get("email"))
                .roles((Role) attributes.get("roles"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
	}
	
	public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .roles(roles)
                .picture(picture)
                .build();
    }
}