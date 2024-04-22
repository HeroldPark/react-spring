package shane.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shane.blog.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}