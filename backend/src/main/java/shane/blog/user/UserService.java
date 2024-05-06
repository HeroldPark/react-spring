package shane.blog.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserMapper userMapper;

    // // @Autowired
    // public UserService(UserMapper userMapper) {
    // this.userMapper = userMapper;
    // }

    public List<User> find(User user) {
        logger.debug("find start");

        List<User> users = new ArrayList<>();
        if (userMapper == null)
            logger.debug("userMapper is null");
        else
            users = userMapper.find(user);
        return users;
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Transactional
    public int save(User user) {
        int result = userMapper.save(user);
        return result;
    }

    @Transactional
    public void update(User user) {
        int result = userMapper.count(user);

        if (result == 0)
            userMapper.save(user);
        else    
            userMapper.update(user);
    }
}
