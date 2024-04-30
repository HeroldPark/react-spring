package shane.blog.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> find(@IgnoreIfNull User user) {
        List<User> users = new ArrayList<>();
        users = userMapper.find(user);
        return users;
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }

    public int save(User user) {
        int result = userMapper.save(user);
        return result;
    }

    public void update(User user) {
        int result = userMapper.count(user);

        if (result == 0)
            userMapper.save(user);
        else    
            userMapper.update(user);
    }
}
