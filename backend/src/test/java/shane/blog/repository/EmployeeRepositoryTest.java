package shane.blog.repository;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shane.blog.entity.Employee;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 각 테스트 메소드가 실행되기전에 실행
    @BeforeEach
    public void cleanup() {
        employeeRepository.deleteAll();
    }

    @Test
    public void findAll() {
        // given
        // Long id = 2L;
        String name = "admin";
        String role = "administator";

        // 여기서 저장하는 employee_id는 auto_increment로 자동 생성되므로 가장 큰 employee_id가 된다.
        // given
        employeeRepository.save(Employee.builder()
                .name(name)
                .role(role)
                .build());

        // when
        List<Employee> employeeList = employeeRepository.findAll();
        // employeeList.forEach(employee -> 
        //     System.out.println("employee_id=" + employee.getEmployee_id())
        // );

        // 가장 큰 employee_id를 가진 Employee 객체를 찾음
        Optional<Employee> employeeWithMaxId = employeeList.stream()
            .max(Comparator.comparingLong(Employee::getEmployee_id));

        if (employeeWithMaxId.isPresent()) {
            Employee employee = employeeWithMaxId.get();
            Assertions.assertEquals(name, employee.getName());
            Assertions.assertEquals(role, employee.getRole());
            System.out.println("가장 큰 employee_id=" + employee.getEmployee_id());

            // another test method
            String result = employee.getName();
            assertThat(result).isEqualTo(name);
        } else {
            System.out.println("해당하는 employee가 없습니다.");
        }

        // 성공 메시지 출력
        System.out.println("테스트 성공!"); // DEBUG CONSOLE에 출력
    }
}