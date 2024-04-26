package shane.blog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import shane.blog.dto.request.employee.SearchEmployee;
import shane.blog.dto.response.employee.ResEmployeeListDto;
import shane.blog.entity.Employee;
import shane.blog.repository.EmployeeRepository;
import shane.blog.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
// @RequiredArgsConstructor
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    // private final MemberRepository memberRepository;

    // public List<Employee> findAll() {
    //     List<Employee> employees = new ArrayList<>();
    //     employeeRepository.findAll().forEach(e -> employees.add(e));
    //     return employees;
    // }

    // 페이징 리스트
    public Page<ResEmployeeListDto> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllWithEmployees(pageable);
        List<ResEmployeeListDto> list = employees.getContent().stream()
                .map(ResEmployeeListDto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, employees.getTotalElements());
    }

    // 직원 검색, isEmpty() == ""
    public Page<ResEmployeeListDto> search(SearchEmployee searchEmployee, Pageable pageable) {
        Page<Employee> result = null;
        if (!searchEmployee.getName().isEmpty()) {
            result = employeeRepository.findAllNameContaining(searchEmployee.getName(), pageable);
        } else if (!searchEmployee.getRole().isEmpty()) {
            result = employeeRepository.findAllRoleContaining(searchEmployee.getRole(), pageable);
        }

        List<ResEmployeeListDto> list = result.getContent().stream()
                .map(ResEmployeeListDto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, result.getTotalElements());
    }

    // // 직원 등록
    // public ResEmployeeWriteDto write(EmployeeWriteDto employeeDTO, Member member) {

    //     Employee employee = EmployeeWriteDto.ofEntity(employeeDTO);
    //     Member writerMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(
    //             () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail())
    //     );
    //     employee.setMappingMember(writerMember);
    //     Employee saveEmployee = employeeRepository.save(employee);
    //     return ResEmployeeWriteDto.fromEntity(saveEmployee, writerMember.getUsername());
    // }

    // // 직원 상세보기
    // public ResEmployeeDetailsDto detail(Long employeeId) {
    //    Employee findEmployee = employeeRepository.findByIdWithMemberAndCommentsAndFiles(employeeId).orElseThrow(
    //            () -> new ResourceNotFoundException("Employee", "Employee Id", String.valueOf(employeeId))
    //    );
    //    // 조회수 증가
    //    findEmployee.upViewCount();
    //    return ResEmployeeDetailsDto.fromEntity(findEmployee);
    // }

    // // 직원 수정
    // public ResEmployeeDetailsDto update(Long employeeId, EmployeeUpdateDto employeeDTO) {
    //     Employee updateEmployee = employeeRepository.findByIdWithMemberAndCommentsAndFiles(employeeId).orElseThrow(
    //             () -> new ResourceNotFoundException("Employee", "Employee Id", String.valueOf(employeeId))
    //     );
    //     updateEmployee.update(employeeDTO.getTitle(), employeeDTO.getContent());
    //     return ResEmployeeDetailsDto.fromEntity(updateEmployee);
    // }

    // 직원 삭제
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}