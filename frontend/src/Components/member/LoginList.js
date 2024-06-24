import axios from "axios";
import React, { useEffect, useState } from "react";
import Pagination from "react-js-pagination";
import { Link } from "react-router-dom";

import "../../css/page.css";
import "../../css/loginlist.css";

// JPA-spring-boot-starter
// 로그인 login가 저장된 테이블
function LoginList() {
  const [loginList, setMemberList] = useState([]);

  // 검색용 Hook
  const [choiceVal, setChoiceVal] = useState("");
  const [searchVal, setSearchVal] = useState("");

  // Paging
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCnt, setTotalCnt] = useState(0);

  // 기본 설정을 포함한 axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989', // 기본 URL 설정
    headers: {
      'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
    }
  });

  // Member 전체 조회
  const getMemberList = async (page) => {
    try {
		  const response = await axiosInstance.get("/login/list", {
			  params: {"page": page - 1},
		  });

      console.log("[LoginList.js] useEffect() success :D");
      console.log(response.data);

      setMemberList(response.data.content);
      setPageSize(response.data.pageSize);
      setTotalPages(response.data.totalPages);
      setTotalCnt(response.data.totalElements);
    } catch (error) {
      console.log("[LoginList.js] useEffect() error :<");
      console.log(error);
    }
  };

  // 게시글 검색
  const search = async () => {
    try {
      const response = await axiosInstance.get("/login/search", {
        params: {
          page: page,
          name: choiceVal === "name" ? searchVal : "",
          role: choiceVal === "role" ? searchVal : "",
        },
      });

      console.log("[LoginList.js searchBtn()] success :D");
      console.log(response.data);

      setMemberList(response.data.content);
      setTotalCnt(response.data.totalElements);
    } catch (error) {
      console.log("[LoginList.js searchBtn()] error :<");
      console.log(error);
    }
  };

  // 첫 로딩 시, 한 페이지만 가져옴
  useEffect(() => {
    getMemberList(1);
  }, []);

  // 검색 조건 저장
  const changeChoice = (event) => { setChoiceVal(event.target.value);};
  const changeSearch = (event) => { setSearchVal(event.target.value);};

  // 페이징 보여주기 
  const changePage = (page) => {
    setPage(page);
    getMemberList(page);
  };

  return (
    <div>
      {/* 검색 */}
      <table className="search">
        <tbody>
          <tr>
            <td>
              <select
                className="custom-select"
                value={choiceVal}
                onChange={changeChoice}
              >
                <option>검색 옵션 선택</option>
                <option value="email">이메일</option>
                <option value="username">성명</option>
              </select>
            </td>
            <td>
              <input
                type="text"
                className="form-control"
                placeholder="검색어"
                value={searchVal}
                onChange={changeSearch}
              />
            </td>
            <td>
              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={search}
              >
                <i className="fas fa-search"></i> 검색
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <br />

      <table className="table table-hover">
        <thead>
          <tr>
            <th style={{textAlign:"center"}} className="col-1">번호</th>
            <th style={{textAlign:"center"}} className="col-1">이메일</th>
            <th style={{textAlign:"center"}} className="col-1">성명</th>
            <th style={{textAlign:"center"}} className="col-1">권한</th>
            <th style={{textAlign:"center"}} className="col-1">사진</th>
            <th style={{textAlign:"center"}} className="col-1">제공</th>
            <th style={{textAlign:"center"}} className="col-1">생성일시</th>
            <th style={{textAlign:"center"}} className="col-1">수정일시</th>
          </tr>
        </thead>

        <tbody>
          {Array.isArray(loginList) && loginList.map(function (login, idx) {
            return <TableRow obj={login} key={idx} cnt={idx + 1} />;
          })}
        </tbody>
      </table>

      <Pagination
        className="pagination"
        activePage={page}
        itemsCountPerPage={pageSize}
        totalItemsCount={totalCnt}
        pageRangeDisplayed={totalPages}
        prevPageText={"‹"}
        nextPageText={"›"}
        onChange={changePage}
      />

      <div className="my-5 d-flex justify-content-center">
        <Link className="btn btn-outline-secondary" to="/userwrite">
          <i className="fas fa-pen"></i> &nbsp; 글쓰기
        </Link>
      </div>
    </div>
  );
}

/* 글 목록 테이블 행 컴포넌트 */
function TableRow(props) {
  const login = props.obj;

  return (
    <tr>
      <th style={{textAlign:"center"}} >{props.cnt}</th>
      <td style={{ textAlign: "center" }}>
        <Link to={{ pathname: `/LoginDetail/${login.memberId}` }}>
          <span className="underline user-name">{login.email}</span>
        </Link>
      </td>
      <td style={{textAlign:"center"}} >{login.username}</td>
      <td style={{textAlign:"center"}} >{login.roles}</td>
      <td style={{ textAlign: "center" }}>
        {login.picture ? (
          <img src={login.picture} alt="Profile" style={{ width: '50px', height: '50px' }} />
        ) : (
          <a href={login.picture} className="btn btn-sm btn-success active" role="button">Profile</a>
        )}
      </td>
      <td style={{textAlign:"center"}} >{login.provider}</td>
      <td style={{textAlign:'center'}}>{login.createdDate}</td>
      <td style={{textAlign:'center'}}>{login.modifiedDate}</td>
    </tr>
  );
}

export default LoginList;
