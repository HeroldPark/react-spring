package shane.blog.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shane.blog.entity.Board;

//  · JpaRepository는 PagingAndSortingRepository, QueryByExampleExecutor 인터페이스를 상속받고 있음
//  · PagingAndSortingRepository는 CrudRepository 인터페이스를 상속받고 있음
//  · CrudRepository 인터페이스에는 기본적인 CRUD 메소드 제공
//   → save(), findById(), existsById(), count(), deleteById(), delete(), deleteAll()
//  · QueryByExampleExecutor 인터페이스에는 더 다양한 CRUD 메소드 제공
//   → findOne(), findAll(), count(), exists()


public interface BoardRepository extends JpaRepository<Board, Long> {

    // 게시글 상세 조회, @BatchSize : Comments와 Files는 필요할 때 in 절로 가져옴
    @Query(value = "SELECT DISTINCT b FROM Board b JOIN FETCH b.member WHERE b.id = :boardID")
    Optional<Board> findByIdWithMemberAndCommentsAndFiles(Long boardID);
    
    // @Query(value = "SELECT DISTINCT b FROM Board b " +
    //                 "JOIN FETCH b.member " +
    //                 "LEFT JOIN FETCH b.comments " +
    //                 "LEFT JOIN FETCH b.files " +
    //                 "WHERE b.board_id = :boardID")
    // Optional<Board> findByIdWithMemberAndCommentsAndFiles(@Param("boardID") Long boardID);

    // 첫 페이징 화면("/")
    @Query(value = "SELECT DISTINCT b FROM Board b JOIN FETCH b.member")
    Page<Board> findAllWithMemberAndComments(Pageable pageable);

    // 제목 검색
    @Query(value = "SELECT DISTINCT b FROM Board b JOIN FETCH b.member WHERE b.title LIKE %:title%")
    Page<Board> findAllTitleContaining(String title, Pageable pageable);

    // 내용 검색
    @Query(value = "SELECT DISTINCT b FROM Board b JOIN FETCH b.member WHERE b.content LIKE %:content%")
    Page<Board> findAllContentContaining(String content, Pageable pageable);

    // 작성자 검색
    @Query(value = "SELECT DISTINCT b FROM Board b JOIN FETCH b.member WHERE b.member.username LIKE %:username%")
    Page<Board> findAllUsernameContaining(String username, Pageable pageable);

}