package shane.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import shane.blog.entity.Employee;

//  · JpaRepository는 PagingAndSortingRepository, QueryByExampleExecutor 인터페이스를 상속받고 있음
//  · PagingAndSortingRepository는 CrudRepository 인터페이스를 상속받고 있음
//  · CrudRepository 인터페이스에는 기본적인 CRUD 메소드 제공
//   → save(), findById(), existsById(), count(), deleteById(), delete(), deleteAll()
//  · QueryByExampleExecutor 인터페이스에는 더 다양한 CRUD 메소드 제공
//   → findOne(), findAll(), count(), exists()

// public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//     @Override
//     List<Employee> findAll();
// }

// 이 인터페이스를 상속받는 인터페이스가 레포지토리로 인식되지 않도록 설정한다.
// @NoRepositoryBean    
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // 조건없이 테이블의 전체 레코드 조회
    // 실제 서비스에서는 잘 사용되지 않음
    @Override
    List<Employee> findAll();

    // ID(Long) 값을 리스트 형식으로 받아 레코드 조회
    @Override
    List<Employee> findAllById(Iterable<Long> ids);

    // 엔티티를 리스트 형식으로 받아 테이블에 한번에 저장
    @Override
    <S extends Employee> List<S> saveAll(Iterable<S> entities);

    // 현재 JPA Context 가지고 있는 값을 DB에 반영
    void flush();

    // 엔티티를 리스트 형식으로 받아 레코드를 한번에 삭제
    @Deprecated
    default void deleteInBatch(Iterable<Employee> entities) { deleteAllInBatch(entities); }

    // 조건없이 테이블의 전체 레코드 삭제
    void deleteAllInBatch();

    // ID 값으로 해당 엔티티 하나 조회
    @Deprecated
    Employee getOne(Long id);
}