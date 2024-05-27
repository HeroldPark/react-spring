package shane.blog.domain.picture;

import lombok.Getter;
import lombok.Setter;
import shane.blog.common.BaseTimeEntity;

@Getter
@Setter
public class PictureRequest extends BaseTimeEntity {

    private Long id;      // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private String filePath;     // 위치
    private int viewCnt;       // 조회수
    private Boolean deleteYn;    // 삭제 여부

}