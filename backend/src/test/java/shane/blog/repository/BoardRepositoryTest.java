package shane.blog.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import shane.blog.entity.Board;
import shane.blog.entity.Member;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Board Repository 테스트")
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
        Long id = 3L;
        String title = "테스트 게시글 제목 3";
        String content = "테스트 본문 3";
        int viewCount = 0;

        // Member member = Member.builder()
        //     .username("guest")
        //     // .password("1234")
        //     .email("guest@deltax.ai")
        //     // .roles(Role.ADMIN)
        //     .build();

        Member member = new Member();
        member.setMember_id(1L);

        // given
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
        Board board = boardList.get(3);
        Assertions.assertEquals(title, board.getTitle());
        Assertions.assertEquals(content, board.getContent());

        // when : AssertJ 사용
        // Board findBoard = boardList.get(0);
        assertThat(board.getTitle()).isEqualTo(title);

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}