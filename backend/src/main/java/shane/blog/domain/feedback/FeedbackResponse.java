package shane.blog.domain.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Response-
 * 댓글 등록, 수정 응답
 */

@Getter
@Setter
@NoArgsConstructor
public class FeedbackResponse {

    private Long id;                // PK
    private Long postId;            // 게시글 FK
    private String title;           // 댓글 제목
    private String content;         // 댓글 내용
    private String writer;          // 댓글 작성자
    private Boolean deleteYn;       // 삭제 여부
    private String createdDate;     // 생성일시
    private String modifiedDate;    // 최종 수정일시


    @Builder
    public FeedbackResponse(Long id, String content, String createdDate, String modifiedDate, String writer) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.writer = writer;
    }
}
