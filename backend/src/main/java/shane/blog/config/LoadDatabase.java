package shane.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shane.blog.entity.Employee;
import shane.blog.repository.EmployeeRepository;

@Configuration
class LoadDatabase {

    // private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // // 재시작 할때마다 추가되어 일단 주석 처리한다.
    // @Bean
    // CommandLineRunner initDatabase(EmployeeRepository repository) {

    //     return args -> {
    //         log.info("Preloading " + repository.save(new Employee("Bilbo 첫 번째", "burglar")));
    //         log.info("Preloading " + repository.save(new Employee("Frodo 두 번째", "thief")));
    //     };
    // }
}