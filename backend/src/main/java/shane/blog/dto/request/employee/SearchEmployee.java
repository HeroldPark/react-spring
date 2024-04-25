package shane.blog.dto.request.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * -Request-
 * 검색 요청에 대한 정보 DTO, 검색 조건이 늘어날 수 있어 객체로 만듬
 */

@Getter
@Setter
public class SearchEmployee {

    String name;
    String role;

    @Builder
    public SearchEmployee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public static SearchEmployee createdSearchEmployee(String name, String role) {
        return SearchEmployee.builder()
                .name(name)
                .role(role)
                .build();
    }
}
