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

# 13. 2024-05-02
    Parameter 0 of constructor in shane.blog.domain.user.UserService required a bean of type 'shane.blog.domain.user.UserMapper' that could not be found.

    The injection point has the following annotations:
        - @org.springframework.beans.factory.annotation.Autowired(required=true)
    => UserService.java
    - private final 로 설정하면 안된다(?)

    @RequiredArgsConstructor
    public class UserService {
        private UserMapper userMapper;
    }

    -
    npm install eslint-plugin-babel-eslint --save-dev

    npm install jquery

# 14. 2024-05-03
    - DB 테이블을 tb_*로 수정함.
    - Jpa에서는 테이블 명을 tb_*로 수정하더라도 Entity Name으로 입력하기 때문에 Repository에서 DB 이름은 뵨경하지 않아도 된다.
    - Parameter 0 of constructor in shane.blog.user.UserService required a bean of type 'shane.blog.user.UserMapper' that could not be found.
    - 여전히 Mybatis UserMapper에 대해서 UserService에서 의존성 주입을 하지 못한다.(?)

# 15. 2024-05-07
    - Spring boot : 3.1.11
    - mybatis-spring-boot-starter:3.0.1(반드시 3.x로 해 주어야 한다.)

    - application.properties에서 datasource는 아래처럼 두 자지 중 선택하여 사용하면 된다.(나는 첫번쨰 것 사용)
        spring.datasource.hikari.driver-class-name
        spring.datasource.driver-class-name

    - application.properties에서 mybatis 설정은 아래처럼 주석처리 하면된다.
        # mybatis.config-location=classpath:/mybatis/mybatis-config.xml
        # mybatis.mapper-locations:classpath:/mybatis/mappers/*.xml
    
    - DatabaseConfig.java에서
        @Bean
        public SqlSessionFactory sqlSessionFactory() throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource());
            factoryBean.setMapperLocations(context.getResources("classpath:/mappers/mybatis/*Mapper.xml"));
            // factoryBean.setConfiguration(mybatisConfig());

            // 추가: MyBatis 설정 파일을 대체하기 위한 설정
            factoryBean.setConfigLocation(context.getResource("classpath:/mappers/mybatis-config.xml"));

            return factoryBean.getObject();
        }
    => 이렇게 했더니 mybatis를 사용하는 UserService에서 UserMapper를 의존성 주입이 정상적으로 수행됨.
    => 지금까지 Mapper 주입이 안되었던 가장 큰 문제는 mybatis 버젼이었다.

    - /src/main/java/shane/blog : JPA java source
    - /src/main/java/shane/blog/domain : Mybatis source
    - JPA 소스와 중복되는 이름은 *Api*를 추가함.(예: MemberApiController.java)
    - mabatis와 연동되는 DB table은 tbl_*로 명명한다.
    - 중복 파일 제거(common/BaseTimeEntity.java, ...)

    - mybatis와 연동되는 spring boot backend source는 /src/main/java/shane/blog/domain 아래로 모두 가져감.
    - react-spring에 되어 있는 방식에 따라 paging 처리 진행 중.(domain/member)에서 처리 중

# 16. 2024-05-08
    - shane/blog/domain/member part를 MyBatis 조회하여 React 화면으로 출력.
    - MemberControllerTest.java에 대한 junit test할때 오류 발생한다.(?)

# 17. 2024-05-09
    - mybatis Board(domain/post) 항목 test
    - MemberControllerTest.java에 대한 junit test할때 오류 발생 원인은 Mock 객체를 주입하는 방식 때문이었다.
    - Mock 대신 Autowired를 사용하면 된다.
    - MyBatis Test DEBUG 할때 SQL 출력 안되는 문제(?)

    - build.gradle에서 오류 발생.
    "message": "Could not run phased build action using connection to Gradle distribution 'https://services.gradle.org/distributions/gradle-8.2.1-bin.zip'
    => Gradle 8.2.1 적용해도 마찬가지(gradle -v)
    => backup github에서 다운로드 후 복구

# 18. 2024-05-10
    - MyBatis Test DEBUG 할때 SQL 출력 안되는 문제 해결됨
    => 아마도 또다른 프로세서가 실행되고 있어서 안되었던 것으로 판단됨

# 19. 2024-05-11
    - JWT 관련 수정 : BbsList.js에서 const axiosInstance 설정 부분이다.
    - Login.js에서 서버로부터 받은 정보를 localStorage에 저장한다.
    - localStorage에 저장된 JWT 정보를 Header에 설정하여 서버로 보내면 서버에서는 이 정보를 보고 client에 대한 JWT 인증(Authentication)을 수행한다.
    - 로그인을 하지 않은 상태에서 메뉴를 통해 데이터를 조회하면 인증 오류가 발생한다.
    - 인증은 JwtAuthenticationFilter.java에서 수행된다.(현재 인증에 오류가 발생하더라도 조회 결과는 출력된다.)
    - 그렇다면 권한 인증(Authorization)은 어떻게 수행되지?
    
    <JWT를 보내는 다른 방법 -  static 변수>
    - BbsAnswer.js에서 
    const createBbsAnswer = async () => {
        await axios.post(`http://localhost:3000/bbs/${parentSeq}/answer`, req, {headers: headers})
    }

# 20. 2024-05-13
    - Source map(backend/src/main/resources/static/static/css or js/main.xxxxx.map.js, ...) 추가 생성 금지
    - package.json : "build": "GENERATE_SOURCEMAP=false react-scripts build" 추가
    - .gitignore 수정
    - 로그인 오류 메시지 출력 수정(MemberService.java, Login.js)
    
    - JWT Authorization : tb_member.roles="USER"인 경우 filterChain에서 .requestMatchers("/employees").hasRole("USER")으로 설정하면 EmployeeController의 "/employees"가 정상적으로 호출된다.
    - JwtAuthenticationFilter.java에서 출력되는 auth가 tb_member.roles와 동일하다.
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(
                auth -> log.info("auth: " + auth.getAuthority())
        );
    - 로그인 하지 않은 상태에서는 auth=null 이므로 메뉴 결과 가 출력되지 않는다.
    - client에서 호출하는 URL header에 JWT 인증 정보가 서버로 보내져야 Authority(인가, 권한)가 정상적으로 실행한다.

# 21. 2024-05-14
    - SNS 로그인 : 구글 OAuth2 를 이용한 로그인
    - PrincipalDetails.java, PrincipalDetailsService.java, IndexController.java 추가
    - MemberRepository.findByUsername 추가
    => 삭제 해야 함.

    - config.auth : CustomOAuth2UserService.java, SecurityConfig.java
        config.auth.dto : OAuthAttributes.java, SessionUser.java
    - user/entries, user/enums, user/repositories
    => application.properties에서 oauth2 정보 삭제

# 22. 2024-05-16
    - application.yml에서 Google OAuth2 정보 설정(github 로딩이 안되어 .gitignore에서 제외처리)
    - BFG Repo-Cleaner 사용 : 다운로드한 bfg-1.13.0.jar 파일을 사용하여 민감한 정보를 제거
    => java -jar path\to\bfg-1.13.0.jar --delete-files 'application.yml'
    => git reflog expire --expire=now --all
    => git gc --prune=now --aggressive
    => git push origin master --force

    - OAuth2 정보는 로컬에서 환경변수로 등록하여 사용.
    - windows 환경 변수 설정:
        GOOGLE_CLIENT_ID="your_client_id"
        GOOGLE_CLIENT_SECRET="your_client_secret"
    - 확인
        PS C:\Users\DeltaX_20> echo $env:GOOGLE_CLIENT_ID
        PS C:\Users\DeltaX_20> echo $env:GOOGLE_CLIENT_SECRET
    
    - Git CMD 창에서 설정(set)/확인(echo)
        C:\Users\DeltaX_20>set GOOGLE_CLIENT_ID=XYZ-9ua5q9ub0llqmqo81ciitbu1fg73lgd4.apps.googleusercontent.com
        C:\Users\DeltaX_20>echo %GOOGLE_CLIENT_ID%
        XYZ-9ua5q9ub0llqmqo81ciitbu1fg73lgd4.apps.googleusercontent.com

        C:\Users\DeltaX_20>set GOOGLE_CLIENT_SECRET=GOCSPX-wODcxGB7ta-XYZ
        C:\Users\DeltaX_20>echo %GOOGLE_CLIENT_SECRET%
        GOCSPX-wODcxGB7ta-XYZ

# 23. 2024-05-17
    - Google OAuth2를 사용하기 위헤서는 Google Cloud에 가입한 후 Google OAuth2.0 세팅을 해야 한다.
    - 참고 : https://darrenlog.tistory.com/38

    - 오류 메시지
    Parameter 0 of method setFilterChains in org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration required a bean of type 'org.springframework.security.oauth2.client.registration.ClientRegistrationRepository' that could not be found.
    => application.yml에서 들여쓰기 오류이다.(chatGPT에서는 전혀 다른 가이드를 제시한다.)

    - google OAuth2 로그인 안된다.
    - Login.js => SecurityConfig.java => LoginController.java 로 진행이 안된다.(?)
    => GET http://localhost:8989/login?error 405 (Method Not Allowed)

# 24. 2024-05-21
    - Login.js
        const form = document.createElement("form");
        form.method = "POST";
        form.action = "http://localhost:8989/login/oauth2/google";
    - SecurityConfig.java
        // OAuth2 로그인을 사용
        .oauth2Login(login -> login     // OAuth2 로그인을 이용한다.
            .userInfoEndpoint()             // 로그인된 유저의 정보를 가져온다.
            .userService(customOAuth2UserService))
    - ApiRestController.java
        // 구글 로그인창 호출
        @PostMapping(value = "/login/oauth2/google")
        public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {
            ......
        }

        // 구글에서 리다이렉션
        public String oauth_google_check(HttpServletRequest request,
                                     @RequestParam(value = "code") String authCode,
                                     HttpServletResponse response) throws Exception{
            ..............
        }
    - 구글에서 리다이렉션으로 넘어오지 못하는 장애(?)
![oauth2 로그인](./docs/images/oauth2로그인.png)
<h3 align="center">
  oauth2 로그인
</h3>
        -> 이 상태에서 계속을 클릭하면 오류 발생.
        -> Failed to load resource: the server responded with a status of 401 ()

    - SecurityConfig.java 에서 OAuthController.java로 넘어갈때 오류
    => Cannot invoke "org.springframework.security.core.Authentication.getPrincipal()" because "authentication" is null

# 25. 2024-05-22
    - 기존 로그인 정상 복구 확인
    - google OAuth2 로그인 프로시져 완성
    - accessToken, refreshToken 사용 방법 더 해야 함.
    - SecurityConfig.successHandler를 사용하면 client로 전달되는 URL이 다르다.
        /code/google?state=KG0ZIHh8weoK4rss2Yqp4ERi13jS-RgH0QHA_y6TGhQ%3D&code=4%2F0AdLIrYc_BLB0qjlp13xON-s1WU2ONEww5WFAmBh9G1TmYmnbTgM3gLqAsC_o1SYk3fKc7g&scope=email+profile+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none
    - 여기에서 필요한 값을 client에서 사용하면 될 것 같다.
  
# 26. 2024-05-23
    - 드디어 로그인 성공.
    - React - Springboot - mariadb - Springboot - React
    - Google OAuth2 인증 받은 정보 member db 저장 관련 수정 필요
    - 불필요한 Source 정리
  
# 27. 2024-05-27
    - 일반 로그인, Google OAuth2 로그인 모두 정상
    - kakao, naver 에 대한 SNS 로그인을 추가하여 사용할 수도 있음
    - Google OAuth2 개발 후 소스 코드 정리 완료(blog/user는 추후 테스트를 위해 남겨둠)
    - 오늘부터 TV 화면에 이미지 또는 동영상 출력
    - coding Mybatis Posting 기본 기능 for image display on the TV Screen

# 28. 2024-05-28
    - Spring Boot - React - Mybatis - Post 수정 중

# 29. 2024-05-29
    - React - MyBatis 연동은 Post + Feedback으로 명명함.
    - Spring boot+React+MyBatis+Post+Feedback 개발 중

# 30. 2024-05-30
    - 상세조회에서 댓글 출력 안되는 장애 발생.
    - github에서 가져와 수정 필요하다.
    - security 도 봐야할 것 같다.

# 31. 2024-05-31
    - multipart file 전달 중

# 32. 2024-06-03
    - MultipartFile로 client에서 server로 데이터 전달 후 파일 저장

# 33. 2024-06-04
    - MultipartFile 방식으로 file upload/download 완료

# 34. 2024-06-05
    - Spring boot + React + Security + Google OAuth2 Login
    - Spring boot + React + Security + JPA + JWT + Board + Comment
    - Spring boot + React + Security + MyBatis + JWT + Post + Feedback

# 35. 2024-06-05
    - commoncode porting
    - npm install react-treebeard
    - yarn add react-modal

# 36. 2024-06-11
    - yarn add react-simple-tree-menu
    - commonCode 메뉴 트리 구성

# 37. 2024-06-12
    - yarn add react-resizable
    - commonCode layerPopup 창 크기 조절 기능

# 38. 2024-06-14
    - react tree menu 완료(공통코드)

# 39. 2024-06-16(javascript)
    - 비트코인 GPT 인공지능 AI 업비트 자동매매 시스템 만들기
    - https://github.com/youtube-jocoding/gpt-bitcoin?tab=readme-ov-file
    - Bitcoin 거래 : chatGPT-4o + serpApi + Upbit
    - javascript 로 하려니 아래 오류 발생으로 python으로 전환

    ERROR in ./node_modules/serpapi/script/src/utils.js 13:32-48
    Module not found: Error: Can't resolve 'https' in 'D:\Workspace\Workspace-react\react-spring\frontend\node_modules\serpapi\script\src'
    => yarn add https-browserify

    ERROR in ./node_modules/serpapi/script/src/utils.js 14:38-60
    Module not found: Error: Can't resolve 'querystring' in 'D:\Workspace\Workspace-react\react-spring\frontend\node_modules\serpapi\script\src'
    => yarn add querystring-es3

# 40. 2024-06-16 (C:\Program Files\Python312\python.exe)
    - 비트코인 GPT 인공지능 AI 업비트 자동매매 시스템 만들기
    - pip install google-search-results
    - $ pwd
        /d/Workspace/Workspace-react/react-spring/frontend/autotrade
    - $ python search_example.py

# 41. 2024-06-17
    - 공공 API 사용 : 인구통계, 날씨 정보 등
    - GSIS Open API(https://sgis.kostat.go.kr/)
  
    - npm install dotenv
    - GEOCode : 위치정보
    - OpenApi : 인구통계

# 42. 2024-06-18

Failed to parse source map from 'C:\Users\DeltaX_20\Documents\Workspace\react-spring\frontend\node_modules\react-simple-tree-menu\src\sass\main.scss' file: <span style="color: red;">Error:</span> ENOENT: no such file or directory, open 'C:\Users\DeltaX_20\Documents\Workspace\react-spring\frontend\node_modules\react-simple-tree-menu\src\sass\main.scss'

    => frontend\node_modules\react-simple-tree-menu\src\sass\main.scss 생성

        // eslint-disable-next-line
    
    => package.json에서 아래 라인 추가

        "devDependencies": {
            "react-scripts": {
            "sourceMaps": false
            }
        },
    
    => webpack.config.js 추가

    - styled component 사용
        $ npm install styled-components

    - bootstrap
        $ yarn add react-bootstrap bootstrap

# 43. 2024-06-19
    - 계정리스트를 JPA 방식으로 추가
    - bootstrap-custom-styles.scss에서 container의 max-width 추가

# 44. 2024-06-24
    - compile 오류
    Description:
    Parameter 0 of method setFilterChains in org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration required a bean of type 'org.springframework.security.oauth2.client.registration.ClientRegistrationRepository' that could not be found.
    => OAuth2 관련 오류 : /resources/application.yml 없어서 발생하는 오류

    - 로그인 관련 기능은 LoginController.java에서 구현.(controller 이외에는 그대로 유지)
    - 오류 발생.(LoginDetail.js)
        await axiosInstance.patch(`/login/update`, req, {headers: headers})
        PATCH http://localhost:8989/login/update 401 (Unauthorized)

# 45. 2024-06-26
    - 온라인 예약관리 시스템(/online/event)
    - npm install @fullcalendar/core@latest @fullcalendar/react@latest @fullcalendar/daygrid@latest @fullcalendar/interaction@latest