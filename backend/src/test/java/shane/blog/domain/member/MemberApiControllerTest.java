package shane.blog.domain.member;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.PagingResponse;
import java.util.Date;
import java.text.SimpleDateFormat;

// MyBatis를 이용하는 SQL Query가 출력되지 않는다.(?)
// 오류는 없는것 같은데 출력이 안되서 확인이 어렵다.
// 파일에서 우클릭 => Debug Test in Current File + 정상 디버깅 출력됨 => 테스트 성공 => 
@DisplayName("Member Controller 테스트")
@SpringBootTest
public class MemberApiControllerTest {
    static Logger logger = (Logger) LoggerFactory.getLogger(MemberApiControllerTest.class);

    // @Mock
    // Mock 객체를 주입하는 방식은 안된다.
    @Autowired
    private MemberMapper memberMapper; // Mock 객체 생성

    // @InjectMocks
    // Mock 객체를 주입하는 방식은 안된다.
    @Autowired
    private MemberApiService memberApiService;

    SearchDto params = new SearchDto();

    @Before(value = "")
    public void setup(){

    }

    @Test
    void testSaveMember() {
        Date now = new Date();
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now);
        // given
        MemberRequest memberReq = new MemberRequest();
        memberReq.setId(13L);   // 존재하지 않는 ID에 대한 테스트
        memberReq.setLoginId("test");
        memberReq.setPassword("1");
        memberReq.setName("테스트");
        memberReq.setGender(0); // 0: 남자, 1: 여자
        memberReq.setBirthday("2024-05-08");
        memberReq.setDeleteYn(false);
        memberReq.setCreatedDate(current);
        memberReq.setModifiedDate(current);

        // when
        Long result = memberApiService.saveMember(memberReq);
        logger.debug("insert id=" + result);

        // then
        PagingResponse<MemberResponse> responseList = memberApiService.findList(params);
        List<MemberResponse> members = responseList.getData(); // 페이지별 멤버 응답 리스트 가져오기
        members.forEach(member ->
            logger.debug("loginId={}", member.getLoginId())
        );

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}
