package shane.blog.domain.comment;

import lombok.Getter;
import lombok.Setter;
import shane.blog.domain.common.dto.SearchDto;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {

    private Long postId;    // 게시글 번호 (FK)

}