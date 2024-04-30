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

# 09. 2024-04-26
    vsCode Installed Packages(Ctrl + Shift-X => INSTALLED)
    - Debugger for Java, Decompiler, Decompiler Java, Dev Containers, Django, Docker, Eclipse Keymap, Extension Pack for Java, Extension Pack for Java + Spring
    - Extension Pack for Java + Spring, Git Graph, Git History, GitHub Copilot, GitHub Copilot, GitLens, Gradle Extension Pack, Gradle Java, Gradle Language Support
    - IntelliCode, IntelliCode API Usage Examples, IntelliCode Completions, IntelliCode Insiders, IntelliJ IDEA Key Bindings, Java IDE, Java IDE Pack, Java Language Support,
    - Java prettier formatter, Java Properties, Java Snippets, Java Tests, Jinja, JSON Tools, Junit Testfile Generator, Kubernetes, Kubernetes Support, Language Support for Java(TM) by Red Hat
    - learn-yaml, Live Preview, Lombok Annotations Support, Material Icon Theme, Maven for Java, MicroProfile Starter, MybatisX, npm Intellisense, Output Colorizer, PowerShell, Prettier
    - Project Manager for Java, Pylance, Python, Python Debugger, Python Environment Manager, Python Extension Pack, Python Indent, Reactjs code snippets, Red Hat Commons, Remote - SSH
    - Remote - SSH: Editing Configuration Files, Remote - Tunnels, Remote Development, Remote Explorer, REST Client, shell-format, Spring Boot Dashboard, Spring Boot Gradle Extension Pack
    - Spring Boot Gradle Extension Pack, Spring Boot Tools, Spring Initializr Java Support, SpringBoot Extension Pack, Sublime Text Keymap and Settings Importer, Task Explorer
    - Test Runner for Java, Todo Tree, Tools for MicroProfile, WSL, XML, YAML, ESLint, markdownlint, XML Tools, Notepad++ keymap

    - JPA에서 Query 출력 안되는 장애 발생. : Spring boot 버젼 문제인가?
    - User 관련 추가 : UserJpaRestController.java, User.java, UserRepository.java, UserService.java

# 10. 2024-04-27
    - Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.1.4.
    => in vsCode CTL+ SHIFT+P > Java : clean java langage server workspace

    - Unresolved dependency: org.bgee.log4jdbc-log4j2 log4jdbc-log4j2-jdbc4.1
    => implementation 'org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16'

    - build.gradle에서 버젼 수정 : Query 과정 console에 출력된다.
    - id 'org.springframework.boot' version '3.1.11'

# 11. 2024-04-29
    - cd backend
    - ./gradlew build
    - junit test에서 오류 발생.
    org.springframework.dao.InvalidDataAccessApiUsageException: 
    org.hibernate.TransientPropertyValueException: 
        object references an unsaved transient instance - save the transient instance before flushing : shane.blog.entity.Board.member -> shane.blog.entity.Member
    => Board.member를 CASCADE로 선언

    - Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
    - 2024-04-29 14:38:54.753 [main] [ERROR] j.sqltiming - 1. PreparedStatement.getMaxRows() 
        java.sql.SQLSyntaxErrorException: (conn=2845) Cannot do an operation on a closed statement

    - EmployeeRepositoryTest.java 테스트 완료

    - Parsing error: The keyword 'import' is reserved eslint
    => typescript 기반으로 개발을 하는데 javascript으로 된 설정 파일에서 에러를 뱉는다.
    - npm install babel-eslint --save-dev
    => /frontend/.eslintrc.js 추가, 수정

# 12. 2024-04-30
    - Error creating bean with name 'userController': Unsatisfied dependency expressed through field 'userService': Error creating bean with name 'userService': 
        Unsatisfied dependency expressed through field 'userMapper': Error creating bean with name 'userMapper' defined in file 
        [C:\Users\DeltaX_20\Documents\Workspace\react-spring\backend\build\classes\java\main\shane\blog\domain\user\UserMapper.class]: 
        Cannot resolve reference to bean 'sqlSessionTemplate' while setting bean property 'sqlSessionTemplate'

    - Factory method 'sqlSessionFactory' threw exception with message: Property 'configuration' and 'configLocation' can not specified with together
    => implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0'
    => 안맞는 버젼이 있다.

    - package loading 중 발생
    Browserslist: caniuse-lite is outdated. Please run:
    npx update-browserslist-db@latest
    Why you should do it regularly: https://github.com/browserslist/update-db#readme
