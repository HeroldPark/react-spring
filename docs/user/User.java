package shane.blog.user;

import lombok.*;
import shane.blog.common.BaseTimeEntity;

@Data
@RequiredArgsConstructor
public class User  extends BaseTimeEntity {

    private Long userId;
    private String id;
    private String name;
    private String role;

    // Test에서 사용하기 위해 추가
    @Builder
    public User(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // Entity -> DTO
    public static User fromEntity(User user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

}