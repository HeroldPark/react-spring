package shane.blog.sample;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import shane.blog.entity.User;

public class UserTest {

    @Test
    void test() {
        //given
        String id = "shane park 1";
        String name = "shanepark1";
        User user = new User(id, name);

        //when
        String result = user.getName();

        //then
        assertThat(result).isEqualTo(name);

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }

}
