package shane.blog.domain.picture;

import shane.blog.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PictureMapper {

    /**
     * 게시글 저장
     *
     * @param params - 게시글 정보
     */
    void save(PictureRequest params);

    /**
     * 게시글 상세정보 조회
     *
     * @param id - PK
     * @return 게시글 상세정보
     */
    PictureResponse findById(Long id);

    /**
     * 게시글 수정
     *
     * @param params - 게시글 정보
     */
    void update(PictureRequest params);

    /**
     * 게시글 삭제
     *
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 수 카운팅
     *
     * @return 게시글 수
     */
    int count(SearchDto params);

    /**
     * 게시글 리스트 조회
     *
     * @return 게시글 리스트
     */
    List<PictureResponse> findList(SearchDto params);

}
