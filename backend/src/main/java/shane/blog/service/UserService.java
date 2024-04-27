package shane.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shane.blog.entity.User;
import shane.blog.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(e));
        return users;
    }

    public Optional<User> findById(Long mbrNo) {
        Optional<User> user = userRepository.findById(mbrNo);
        return user;
    }

    public void deleteById(Long mbrNo) {
        userRepository.deleteById(mbrNo);
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public void updateById(Long mbrNo, User user) {
        Optional<User> e = userRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setMbrNo(user.getMbrNo());
            e.get().setId(user.getId());
            e.get().setName(user.getName());
            userRepository.save(user);
        }
    }
}
