package shane.blog.domain.openapi;

import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.Pagination;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenapiService {

    private final OpenapiMapper postMapper;

    /**
     * 게시글 저장
     * 
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final OpenapiRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    @Transactional
    public Long write(final OpenapiRequest params, Member member) {
        params.setWriter(member.getUsername());
        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * 
     * @param id - PK
     * @return 게시글 상세정보
     */
    public OpenapiResponse findPostById(final Long id) {
        return postMapper.findById(id);
    }

    /**
     * 게시글 수정
     * 
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long update(final OpenapiRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * 
     * @param id - PK
     * @return PK
     */
    public Long delete(final Long id) {
        postMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * 
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<OpenapiResponse> stat(final SearchDto params) {
        // // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = postMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        pagination.setPageSize(params.getPageSize());

        // // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<OpenapiResponse> list = postMapper.findList(params);
        return new PagingResponse<>(list, pagination);
    }

    /**
     * 게시글 리스트 조회
     * 
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<OpenapiResponse> findList(final SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = postMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        pagination.setPageSize(params.getPageSize());

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<OpenapiResponse> list = postMapper.findList(params);
        return new PagingResponse<>(list, pagination);
    }

    // 게시글 상세 보기
    public OpenapiResponse detail(Long id) {
        OpenapiResponse postResponse = postMapper.findById(id);

        // 조회수 증가
        OpenapiRequest postRequest = new OpenapiRequest();
        postRequest.setId(id);
        postRequest.setViewCnt(postResponse.getViewCnt() + 1);
        postMapper.update(postRequest);

        return postResponse;
    }

    // 답글 상세 보기
    public OpenapiResponse view(Long id) {
        OpenapiResponse postResponse = postMapper.findById(id);

        // 조회수 증가
        OpenapiRequest postRequest = new OpenapiRequest();
        postRequest.setId(id);
        postRequest.setViewCnt(postResponse.getViewCnt() + 1);
        postMapper.update(postRequest);

        return postResponse;
    }
}