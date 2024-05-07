// package shane.blog.domain;

// import static org.mockito.Mockito.*;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import shane.blog.user.User;
// import shane.blog.user.UserMapper;
// import shane.blog.user.UserService;

// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest {

//     @Mock
//     private UserMapper userMapper; // Mock 객체 생성

//     @InjectMocks
//     private UserService userService; // Mock 객체를 주입하는 UserService 생성

//     @Test
//     void testSaveUser() {
//         // given
//         User user = new User();
//         user.setUserId(1L);
//         user.setName("Test User");
//         user.setRole("USER");

//         // when
//         userService.save(user);

//         // then
//         verify(userMapper).save(user); // userMapper의 save 메소드가 호출되었는지 확인

//         // 성공 메시지 출력
//         System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
//     }
// }
