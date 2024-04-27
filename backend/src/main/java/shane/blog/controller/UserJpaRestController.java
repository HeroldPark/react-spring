package shane.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import shane.blog.entity.User;
import shane.blog.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("userTest")
public class UserJpaRestController {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    // 모든 회원 조회
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<User>> getAllusers() {
        List<User> user = userService.findAll();

        logger.debug("getAllusers: " + user);

        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> getMember(@PathVariable("mbrNo") Long mbrNo) {
        Optional<User> user = userService.findById(mbrNo);
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
        userService.deleteById(mbrNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, name로 수정함)
    @PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> updateMember(@PathVariable("mbrNo") Long mbrNo, User user) {
        userService.updateById(mbrNo, user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping
    public ResponseEntity<User> save(User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }

    // 회원 입력
    @RequestMapping(value="/saveMember", method = RequestMethod.GET)
    public ResponseEntity<User> save(HttpServletRequest req, User user){
        return new ResponseEntity<User>(userService.save(user), HttpStatus.OK);
    }

}