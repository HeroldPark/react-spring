package shane.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shane.blog.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String email);
}
