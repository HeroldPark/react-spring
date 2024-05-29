package shane.blog.domain.feedback;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.Pagination;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.entity.Member;
import java.util.Collections;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackService {

    private final FeedbackMapper feedbackMapper;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long saveFeedback(final FeedbackRequest params) {
        feedbackMapper.save(params);
        return params.getId();
    }

    @Transactional
    public Long write(final FeedbackRequest params, Member member) {
        params.setWriter(member.getUsername());
        feedbackMapper.save(params);
        return params.getId();
    }

    @Transactional
    public List<FeedbackResponse> write2(final FeedbackRequest params) {
        feedbackMapper.save(params);
        List<FeedbackResponse> feedbackResponse = feedbackMapper.findByPostId(params.getPostId());
        return feedbackResponse;
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public FeedbackResponse findFeedbackById(final Long id) {
        return feedbackMapper.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updateFeedback(final FeedbackRequest params) {
        feedbackMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deleteFeedback(final Long id) {
        feedbackMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<FeedbackResponse> findList(final SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = feedbackMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        pagination.setPageSize(params.getPageSize());

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<FeedbackResponse> list = feedbackMapper.findList(params);
        return new PagingResponse<>(list, pagination);
    }

    // 게시글 상세 보기
    public FeedbackResponse detail(Long id) {
        FeedbackResponse feedbackResponse = feedbackMapper.findById(id);
        return feedbackResponse;
    }
}
