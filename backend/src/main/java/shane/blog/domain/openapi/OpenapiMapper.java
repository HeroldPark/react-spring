package shane.blog.domain.openapi;

import shane.blog.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OpenapiMapper {

    /**
     * 게시글 저장
     *
     * @param params - 게시글 정보
     */
    void save(OpenapiRequest params);

    /**
     * 게시글 상세정보 조회
     *
     * @param id - PK
     * @return 게시글 상세정보
     */
    OpenapiResponse findById(Long id);

    /**
     * 게시글 수정
     *
     * @param params - 게시글 정보
     */
    void update(OpenapiRequest params);

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
    List<OpenapiResponse> findList(SearchDto params);

}