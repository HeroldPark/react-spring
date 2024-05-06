package shane.blog.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import shane.blog.user.User;
import shane.blog.user.UserMapper;
import shane.blog.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@DisplayName("User Domain 테스트")
@SpringBootTest
public class UserTest {
    static Logger logger = (Logger) LoggerFactory.getLogger(UserTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    
    @Test
    void test() {
        //given
        // Long id = 1L;
        String userId = "shane";
        String name = "shane park";
        String role = "USER";

        userService.save(User.builder()  // save 메소드는 User 객체를 저장
                .userId(userId)
                .name(name)
                .role(role)
                .build());

        logger.debug("save success: " + userId + ", " + name + ", " + role);

        //when
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setRole(role);
        List<User> list = userService.find(user);

        list.forEach((e) -> logger.debug("find success: " + e.getUserId() + ", " + e.getName() + ", " + e.getRole()));

        //then
        // 가장 큰 user_id를 가진 User 객체를 찾음
        Optional<User> userWithMaxId = list.stream()
            .max(Comparator.comparingLong(User::getId));

        if (userWithMaxId.isPresent()) {
            user = userWithMaxId.get();
            Assertions.assertEquals(userId, user.getUserId());
            Assertions.assertEquals(name, user.getName());
            System.out.println("가장 큰 id=" + user.getId());

            // another test method
            String result = user.getName();
            assertThat(result).isEqualTo(name);
        } else {
            System.out.println("해당하는 user가 없습니다.");
        }

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
        logger.debug("test success!");
    }

}
