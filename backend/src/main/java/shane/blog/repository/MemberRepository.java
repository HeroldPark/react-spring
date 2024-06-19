package shane.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shane.blog.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String email);

    // 모든 Login Member 검색
    @Query(value = "SELECT m FROM Member m")
    Page<Member> findAllWithMembers(Pageable pageable);

}
