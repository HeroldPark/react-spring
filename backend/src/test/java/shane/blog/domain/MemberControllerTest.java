package shane.blog.domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import shane.blog.domain.member.MemberRequest;
import shane.blog.domain.member.MemberResponse;
import shane.blog.domain.member.MemberMapper;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.member.MemberApiService;

// MyBatis를 이용하는 SQL Query가 출력되지 않는다.(?)
// 오류는 없는것 같은데 출력이 안되서 확인이 어렵다.
@DisplayName("Member Controller 테스트")
@SpringBootTest
public class MemberControllerTest {
    static Logger logger = (Logger) LoggerFactory.getLogger(MemberControllerTest.class);

    @Mock
    private MemberMapper memberMapper; // Mock 객체 생성

    @InjectMocks
    private MemberApiService memberApiService; // Mock 객체를 주입하는 MemberApiService 생성

    @Mock
    PasswordEncoder passwordEncoder; // Mock PasswordEncoder 생성

    SearchDto params = new SearchDto();

    @Test
    void testSaveMember() {
        // Date now = new Date();
        // String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now);
        // given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setId(2L);
        memberRequest.setLoginId("test");
        memberRequest.setPassword("1");
        memberRequest.setName("테스트");
        memberRequest.setGender(0); // 0: 남자, 1: 여자
        memberRequest.setBirthday("2024-05-08");
        memberRequest.setDeleteYn(false);
        // memberRequest.setCreatedDate(current);
        // memberRequest.setModifiedDate(current);

        // when
        Long result = memberApiService.saveMember(memberRequest);
        logger.debug("id=" + result);

        // then
        List<MemberResponse> memberList = memberMapper.findList(params);
        memberList.forEach(member -> 
            System.out.println("login_id=" + member.getLoginId())
        );

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}
