package shane.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shane.blog.dto.response.employee.ResEmployeeListDto;
import shane.blog.entity.Employee;
import shane.blog.repository.EmployeeRepository;
import shane.blog.service.EmployeeService;

@RestController
public class EmployeeController {
    static Logger logger = (Logger) LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService employeeService;

    // // Aggregate root
    // @GetMapping("/employees")
    // public List<Employee> all() {
    //     return repository.findAll();
    //     // return employeeService.findAll();
    // }

    @GetMapping("/employees")
    public ResponseEntity<Page<ResEmployeeListDto>> employeeList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ResEmployeeListDto> listDTO = employeeService.getAllEmployees(pageable);
        
        logger.debug("employeeList: " + listDTO.getContent().get(0).getName());

        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @PostMapping("/employees")
    public Object newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item
    @GetMapping("/employees/{id}")
    public Object one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return (Employee) repository.findById(id)
                .map(employee -> {
                    ((Employee) employee).setName(newEmployee.getName());
                    ((Employee) employee).setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setEmployee_id(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}