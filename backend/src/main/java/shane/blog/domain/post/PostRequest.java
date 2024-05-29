package shane.blog.domain.post;

import lombok.Getter;
import lombok.Setter;
import shane.blog.domain.feedback.FeedbackRequest;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostRequest {

    private Long id;                                          // PK
    private String title;                                     // 제목
    private String content;                                   // 내용
    private String writer;                                    // 작성자
    private int viewCnt;                                      // 조회 수
    private Boolean noticeYn;                                 // 공지글 여부
    private Boolean deleteYn;                                 // 삭제 여부
    private List<MultipartFile> files = new ArrayList<>();    // 첨부파일 List
    private List<Long> removeFileIds = new ArrayList<>();     // 삭제할 첨부파일 id List
    
    private String createdDate;     // 생성일시
    private String modifiedDate;    // 최종 수정일시

    public List<FeedbackRequest> comments = new ArrayList<>();
}