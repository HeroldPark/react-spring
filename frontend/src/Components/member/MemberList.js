import axios from "axios";
import React, { useEffect, useState } from "react";
import Pagination from "react-js-pagination";
import { Link } from "react-router-dom";

import "../../css/page.css";
import "../../css/memberlist.css";

// mybatis-spring-boot-starter
function MemberList() {
  const [memberList, setMemberList] = useState([]);

  // 검색용 Hook
  const [choiceVal, setChoiceVal] = useState("");
  const [searchVal, setSearchVal] = useState("");

  // Paging
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCnt, setTotalCnt] = useState(0);

  // Member 전체 조회
  const getMemberList = async (page) => {
    try {
		  const response = await axios.get("http://localhost:8989/member/list", {
			  params: {"page": page - 1},
		  });

      console.log("[MemberList.js] useEffect() success :D");
      console.log(response.data.list);

      setMemberList(response.data.list);
      setPageSize(response.data.pageSize);
      setTotalPages(response.data.totalPages);
      setTotalCnt(response.data.totalElements);
    } catch (error) {
      console.log("[MemberList.js] useEffect() error :<");
      console.log(error);
    }
  };

  // 게시글 검색
  const search = async () => {
    try {
      const response = await axios.get("http://localhost:8989/member/search", {
        params: {
          page: page - 1,
          name: choiceVal === "name" ? searchVal : "",
          role: choiceVal === "role" ? searchVal : "",
        },
      });

      console.log("[MemberList.js searchBtn()] success :D");
      console.log(response.data);

      setMemberList(response.data.content);
      setTotalCnt(response.data.totalElements);
    } catch (error) {
      console.log("[MemberList.js searchBtn()] error :<");
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
                <option value="name">성명</option>
                <option value="role">권한</option>
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
            <th style={{textAlign:"center"}} className="col-2">아이디</th>
            <th style={{textAlign:"center"}} className="col-2">성명</th>
            <th style={{textAlign:"center"}} className="col-2">성별</th>
            <th style={{textAlign:"center"}} className="col-2">생년월일</th>
            <th style={{textAlign:"center"}} className="col-2">생성일시</th>
            <th style={{textAlign:"center"}} className="col-2">수정일시</th>
          </tr>
        </thead>

        <tbody>
          {Array.isArray(memberList) && memberList.map(function (member, idx) {
            return <TableRow obj={member} key={idx} cnt={idx + 1} />;
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
  const member = props.obj;

  return (
    <tr>
      <th style={{textAlign:"center"}} >{props.cnt}</th>
      <td style={{textAlign:"center"}} >
        <Link to={{ pathname: `/memberdetail/${member.id}` }}>
          <span className="underline user-name">{member.name}</span>
        </Link>
      </td>
      <td style={{textAlign:"center"}} >{member.loginId}</td>
      <td style={{textAlign:"center"}} >{member.gender}</td>
      <td style={{textAlign:"center"}} >{member.birthday}</td>
      <td style={{ textAlign: 'center' }}>{member.createdDate}</td>
      <td style={{ textAlign: 'center' }}>{member.modifiedDate}</td>
    </tr>
  );
}

export default MemberList;
