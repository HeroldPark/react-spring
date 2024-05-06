package shane.blog.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    // 모든 회원 조회
    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> list() {
        User user = new User();
        List<User> users = userService.find(user);

        logger.debug("getUsers: " + users);

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = new User();
        List<User> users = userService.find(user);
        return new ResponseEntity<User>((User) users, HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원번호로 회원 수정(User 객체의 id)
    @PutMapping(value = "/user/update/{id}")
    public ResponseEntity<User> update(User user) {
        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping(value = "/user/save")
    public ResponseEntity<Integer> save(User user) {
        int result = userService.save(user);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }

}