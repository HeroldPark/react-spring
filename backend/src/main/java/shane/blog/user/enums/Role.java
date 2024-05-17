package shane.blog.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // private 필드로 생성자 구성
public enum Role {
    GUEST("GUEST", "Guest"),
    USER("USER", "Common User"),
    ADMIN("ADMIN", "Admin User");
	
    private final String key;
    private final String title;
}