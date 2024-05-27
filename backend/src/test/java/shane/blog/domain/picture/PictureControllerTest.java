package shane.blog.domain.picture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.member.MemberApiControllerTest;
import shane.blog.domain.picture.PictureRequest;

@DisplayName("Picture Service 테스트")
@SpringBootTest
public class PictureControllerTest {

    static Logger logger = (Logger) LoggerFactory.getLogger(MemberApiControllerTest.class);

    @Autowired
    private PictureMapper pictureMapper; // Mock 객체 생성

    @Autowired
    private PictureService pictureService;

    SearchDto params = new SearchDto();

    // 각 테스트 메소드가 실행되기전에 실행
    // @BeforeEach
    // public void cleanup() {
    //     System.out.println("cleanup() Pass"); // DEBUG CONSOLE에 출력
    //     // pictureMapper.deleteById();
    // }

    @Test
    void testSavePicture() {
        Date now = new Date();
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now);
        String filePath = "C:\\Users\\DeltaX_20\\Documents\\Workspace\\upload\\picture\\john-lee.jpg";

        // given
        PictureRequest pictureReq = new PictureRequest();
        pictureReq.setId(0L);
        pictureReq.setTitle("Test Title 1");
        pictureReq.setContent("Test Content 1");
        pictureReq.setFilePath(filePath);
        pictureReq.setWriter("tester");
        pictureReq.setViewCnt(2);
        pictureReq.setDeleteYn(false);
        pictureReq.setCreatedDate(current);
        pictureReq.setModifiedDate(current);

        // when
        pictureService.savePicture(pictureReq);

        // then
        // verify(pictureMapper).save(pictureReq); // pictureMapper의 save 메소드가 호출되었는지 확인
        // then
        PagingResponse<PictureResponse> pictureList = pictureService.findList(params);
        List<PictureResponse> pictures = pictureList.getData(); // 페이지별 멤버 응답 리스트 가져오기
        pictures.forEach(picture -> {
            logger.debug("PictureResponse = {}, {}", picture.getId(), pictureMapper.findById(picture.getId()));
        });

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}
    