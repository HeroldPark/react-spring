package shane.blog.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import shane.blog.entity.Board;
import shane.blog.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // 각 테스트 메소드가 실행되기전에 실행
    // @BeforeEach
    // public void cleanup() {
    //     boardRepository.deleteAll();
    // }

    @Test
    public void findAll() {
        // given
        Long id = 2L;
        String title = "테스트 게시글";
        String content = "테스트 본문";
        int viewCount = 0;
        Member member = Member.builder()
                .username("admin")
                // .password("1234")
                .email("admin@deltax.ai")
                // .roles(Role.ADMIN)
                .build();

        // org.springframework.dao.InvalidDataAccessResourceUsageException: could not extract ResultSet [(conn=1018) 'react-spring.board_seq' is not a SEQUENCE] [n/a]; SQL [n/a]
        // 이 오류는 Board.java에서 ID를 생성하는 방법에 문제가 있음을 의미한다.
        boardRepository.save(Board.builder()
                .board_id(id)
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .member(member)
                .build());

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
        Board board = boardList.get(1);
        Assertions.assertEquals(title, board.getTitle());
        Assertions.assertEquals(content, board.getContent());
    }
}