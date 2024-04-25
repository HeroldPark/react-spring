## React Study 시작하면서 ...

# 01. 2024-04-01
    - https://github.com/jhcode33/react-spring-blog-backend
    - https://github.com/jhcode33/react-spring-blog-frontend
    - 아인커뮤니케이션(http://www.anyro.com/) static.zip 복사 : Warning 라이선스

    PS C:\Users\DeltaX_20\Documents\Workspace\react-spring> git remote -v
    origin  git@github.com:HeroldPark/react-spring.git (fetch)
    origin  git@github.com:HeroldPark/react-spring.git (push)

# 02. 2024-04-02
    - mariadb 사용으로 수정
    - backend : port=8989
    - frontend : port=3000
    - 로그인 : admin@deltax.ai / 1
    - react-spring.sql 생성 및 백업 : docs/react-spring.sql
    - 답글 달기 오류 : await axios.post(`http://localhost:3000/bbs/${parentSeq}/answer`, req, {headers: headers})

# 03. 2024-04-05
    - dir path 수정

    - [ERROR] o.h.i.e.TableStructure - could not read a hi value - you need to populate the table: member_seq
    => 시퀀스 초기화
    ALTER SEQUENCE member_seq RESTART WITH 1;

# 04. 2024-04-21
    - test JPA
    - Employee 관련 추가
    - LoadDatabase.java, Employee.java, EmployeeRepository.java, EmployeeController.java
    - EmployeeList.js, employeelist.css

# 05. 2024-04-22
    - https://covenant.tistory.com/279 참고
    - id 'org.springframework.boot' version '3.1.4'
    - javax.persistence.* => jakarta.persistence.* 수정해야 한다.
    - public class Employee 으로 설정해야 한다.

# 06. 2024-04-23
    - JPQL(java Persistence Query Language) : Fetch Join
    - 이 부분은 시간을 두고 좀 더 봐야겠다.

    - frontend-backend 연동 : docs/frontend-backend.md 참조
    - build.gradle에서 다음과 같이 해야 frontend - backend가 연결된다.
        def frontendDir = "$projectDir/../frontend"
    
    - 연결 확인 : http://localhost:3000/employees
    - App.js => main.js => Router.js => EmployeeList.js => SecurityConfig.java => EmployeeController.java

    - test Junit : EmployeeRepositoryTest.java
        => class에 마우스 우클릭 후 Debug Test in Current File

# 07. 2024-04-24
    - Employee.java에서 아래와 같이 설정하면 DB에서 employee_seq 테이블이 필요치 않다.
    - 또한 Junit test에서 오류도 발생하지 않는다.
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long employee_id;
    - JWT 기능 분석
    - frontend/src/static/css, js, images : for make a left nav menu

    - jQuery 설치
        npm install jquery jquery-ui-dist
        npm install bootstrap --save
        npm install sass --save

    - 왼쪽 메뉴(아인) static/css, js, images 적용
    - 왼쪽 메뉴 : Nav.js
    - 오른쪽 내용 : Main.js

# 08. 2024-04-25
    - 헤드 로고 추가
    - 왼쪽, 헤드 메뉴 정리
    - entity에서 GeneratedValue 수정 : DB 테이블에서 *_seq 로 등록된 테이블 모두 삭제(예: board_seq)
    - 테스트로 employee(직원) 관련 메뉴 등록

