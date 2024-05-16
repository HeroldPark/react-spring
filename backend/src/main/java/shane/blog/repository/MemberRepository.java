package shane.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shane.blog.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    // // OAuth2 로그인을 위한 추가
    // Member findByUsername(String username);

}
