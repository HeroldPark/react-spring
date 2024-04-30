package shane.blog.domain.user;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     */
    int save(User params);

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     */
    void update(User params);

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param id - PK
     */
    void delete(Long id);

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param params - UK
     * @return 회원 수
     */
    int count(User params);

    /**
     * 회원정보 목록 조회
     * @param empty
     * @return 회원 리스트
     */
    List<User> find(User params);

}
