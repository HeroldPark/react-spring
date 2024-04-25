package shane.blog.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shane.blog.entity.Employee;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 각 테스트 메소드가 실행되기전에 실행
    // @BeforeEach
    // public void cleanup() {
    //     employeeRepository.deleteAll();
    // }

    @Test
    public void findAll() {
        // given
        Long id = 2L;
        String name = "admin";
        String role = "administator";

        employeeRepository.save(Employee.builder()
                .name(name)
                .role(role)
                .build());

        // when
        List<Employee> employeeList = employeeRepository.findAll();

        // then
        Employee employee = employeeList.get(8);
        Assertions.assertEquals(name, employee.getName());
        Assertions.assertEquals(role, employee.getRole());
    }
}