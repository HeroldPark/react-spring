package shane.blog.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import shane.blog.dto.response.employee.ResEmployeeListDto;
import shane.blog.entity.Employee;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;

    public List<User> find(User user) {
        logger.debug("find start");

        List<User> users = new ArrayList<>();
        if (userMapper == null)
            logger.debug("userMapper is null");
        else
            users = userMapper.find(user);
        return users;
    }

    // 페이징 리스트
    public Page<User> getAllUsers(Pageable pageable) {
        Long userId = null; // or any userId value if applicable
        // Pageable pageable = PageRequest.of(pageNumber, pageSize); // Create a Pageable object
        Page<User> users = userMapper.findAllWithUsers(userId, pageable);
        List<User> list = users.getContent().stream()
                .map(User::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, users.getTotalElements());
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
