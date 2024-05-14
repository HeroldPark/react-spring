package shane.blog.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import shane.blog.user.entries.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}