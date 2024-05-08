package shane.blog.domain.member;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import shane.blog.domain.common.dto.SearchDto;

@Mapper
public interface MemberMapper {

    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     */
    void save(MemberRequest params);

    /**
     * 회원 상세정보 조회
     * @param loginId - UK
     * @return 회원 상세정보
     */
    MemberResponse findByLoginId(String loginId);

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     */
    void update(MemberRequest params);

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param loginId - UK
     * @return 회원 수
     */
    int countByLoginId(String loginId);

    /**
     * 회원 수 카운팅
     *
     * @return 회원 수
     */
    int count(SearchDto params);
    
    /**
     * 회원정보 목록 조회
     * @param empty
     * @return 회원 리스트
     */
    List<MemberResponse> findList(SearchDto params);

}
