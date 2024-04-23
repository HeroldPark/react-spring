package shane.blog.entity;

import java.util.Objects;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.BaseTimeEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseTimeEntity {

    @Id
    // @GeneratedValue
    // @Column(name = "EMPLOYEE_ID")    // 'react-spring.employee_seq' is not a SEQUENCE 오류 발생
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    
    private String name;
    private String role;

    @Builder
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.employee_id, employee.employee_id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.employee_id, this.name, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.employee_id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}