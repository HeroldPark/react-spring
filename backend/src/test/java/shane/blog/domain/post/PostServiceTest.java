package shane.blog.domain.post;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.member.MemberApiControllerTest;
import shane.blog.domain.member.MemberResponse;


import java.util.Date;
import java.text.SimpleDateFormat;

// PostService.java 우클릭 -> Generate JUnit Testfile
@DisplayName("Post Service 테스트")
@SpringBootTest
public class PostServiceTest {

    static Logger logger = (Logger) LoggerFactory.getLogger(MemberApiControllerTest.class);

    // @Mock
    @Autowired
    private PostMapper postMapper; // Mock 객체 생성

    // @InjectMocks
    // private PostService postService; // Mock 객체를 주입하는 PostService 생성

    @Autowired
    private PostService postService;

    SearchDto params = new SearchDto();

    // 각 테스트 메소드가 실행되기전에 실행
    // @BeforeEach
    // public void cleanup() {
    //     System.out.println("cleanup() Pass"); // DEBUG CONSOLE에 출력
    //     // postMapper.deleteById();
    // }

    @Test
    void testSavePost() {
        Date now = new Date();
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now);

        // given
        PostRequest postReq = new PostRequest();
        postReq.setId(0L);
        postReq.setTitle("Test Title");
        postReq.setContent("Test Content");
        postReq.setWriter("tester");
        postReq.setViewCnt(11);
        postReq.setNoticeYn(true);
        postReq.setDeleteYn(false);
        postReq.setCreatedDate(current);
        postReq.setModifiedDate(current);

        // when
        postService.savePost(postReq);

        // then
        // verify(postMapper).save(postReq); // postMapper의 save 메소드가 호출되었는지 확인
        // then
        PagingResponse<PostResponse> postList = postService.findList(params);
        List<PostResponse> posts = postList.getData(); // 페이지별 멤버 응답 리스트 가져오기
        posts.forEach(post -> {
            logger.debug("PostResponse = {}, {}", post.getId(), postMapper.findById(post.getId()));
        });

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}
