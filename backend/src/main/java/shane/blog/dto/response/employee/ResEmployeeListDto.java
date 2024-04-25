package shane.blog.dto.response.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.entity.Employee;

/**
 * -Response-
 * list 요청에 대한 정보를 반환, 양방향 관계로 인해 JSON 직렬화가 반복되는 문제를 해결하기 위한 DTO
 */

@Getter
@Setter
@NoArgsConstructor
public class ResEmployeeListDto {
    // 작성일, 수정일, 작성자, 댓글 개수만 전체 목록에 대한 데이터로 받으면 됨
    // 상세한 댓글 내용 등은 상세보기에서 처리
    private Long employeeId;
    private String name;
    private String role;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public ResEmployeeListDto(Long employeeId, String name, String role, String createdDate, String modifiedDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    // Entity -> DTO
    public static ResEmployeeListDto fromEntity(Employee employee) {
        return ResEmployeeListDto.builder()
                .employeeId(employee.getEmployee_id())
                .name(employee.getName())
                .role(employee.getRole())
                .createdDate(employee.getCreatedDate())
                .modifiedDate(employee.getModifiedDate())
                .build();
    }
}
