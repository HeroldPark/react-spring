## React Study 시작하면서 ...

# 01. 2024-04-01
    - https://github.com/jhcode33/react-spring-blog-backend
    - https://github.com/jhcode33/react-spring-blog-frontend
    - 아인커뮤니케이션(http://www.anyro.com/) static.zip 복사

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
