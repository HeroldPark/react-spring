package shane.blog.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 권한 타입
 */
@Getter
@RequiredArgsConstructor // private 필드로 생성자 구성
public enum Role {
    GUEST, USER, ADMIN;
    // GUEST("ROLE_GUEST", "Guest"),
    // USER("ROLE_USER", "Common User"),
    // ADMIN("ROLE_ADMIN", "Admin User");
	
    // private final String key;
    // private final String title;

}
