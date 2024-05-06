package shane.blog.user;

import lombok.*;

@Data
@RequiredArgsConstructor
public class User {

    private Long id;
    private String userId;
    private String name;
    private String role;

    // Test에서 사용하기 위해 추가
    @Builder
    public User(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

}