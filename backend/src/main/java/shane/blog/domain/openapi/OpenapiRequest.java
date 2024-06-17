package shane.blog.domain.openapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenapiRequest {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int viewCnt; // 조회 수
    private Boolean noticeYn; // 공지글 여부
    private Boolean deleteYn; // 삭제 여부
    private String createdDate; // 생성일시
    private String modifiedDate; // 최종 수정일시
}