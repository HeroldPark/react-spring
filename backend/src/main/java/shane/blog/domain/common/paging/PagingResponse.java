package shane.blog.domain.common.paging;

import lombok.Getter;
import shane.blog.domain.member.MemberResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }

    // test 에서 사용 : 페이지별 멤버 응답 리스트 가져가기
    // @SuppressWarnings("unchecked")
    // public List<MemberResponse> getData() {
    //     return (List<MemberResponse>) list;
    // }

    public List<T> getData() {
        return (List<T>) list;
    }
}
