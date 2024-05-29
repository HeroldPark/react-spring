package shane.blog.domain.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request-
 * 댓글 등록, 수정 요청, <br>
 * -Member, Board는 URI Resource로 받음
 */

@Getter
@Setter
@NoArgsConstructor
public class FeedbackRequest {

    private Long id;                // PK
    private Long postId;            // 게시글 FK
    private String content;         // 댓글 내용
    private String writer;          // 댓글 작성자
    private Boolean deleteYn;       // 삭제 여부
    private String createdDate;     // 생성일시
    private String modifiedDate;    // 최종 수정일시
}
