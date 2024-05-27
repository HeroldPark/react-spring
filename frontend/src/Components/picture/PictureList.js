import axios from "axios";
import React, { useEffect, useState } from "react";
import Pagination from "react-js-pagination";
import { Link } from "react-router-dom";

import "../../css/page.css";
import "../../css/picturelist.css";

// mybatis-spring-boot-starter
function PictureList() {
  const [pictureList, setPictureList] = useState([]);

  // 검색용 Hook
  const [choiceVal, setChoiceVal] = useState("");
  const [searchVal, setSearchVal] = useState("");

  // Paging
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCnt, setTotalCnt] = useState(0);

  // 파일 로딩
  const [pos, setPos] = useState("C:/Users/DeltaX_20/Documents/Workspace/upload");

  // 기본 설정을 포함한 axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989', // 기본 URL 설정
    headers: {
      'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
      'Authorization': `Bearer ${localStorage.getItem('bbs_access_token')}` // JWT 토큰 포함
    }
  });

  // Post 전체 조회
  const getPictureList = async (page) => {
    try {
		  const response = await axiosInstance.get('/picture/list', {
			  params: {"page": page},
		  });

      console.log("[PictureList.js] useEffect() success :D");
      console.log(response.data);

      setPictureList(response.data.list);
      setPageSize(response.data.pagination.pageSize);
      setTotalPages(response.data.pagination.totalPageCount);
      setTotalCnt(response.data.pagination.totalRecordCount);
    } catch (error) {
      console.log("[PictureList.js] useEffect() error :<");
      console.log(error);
    }
  };

  // 게시글 검색
  const search = async () => {
    try {
      const response = await axiosInstance.get('/picture/list', {
        params: {
          page: page,
          name: choiceVal === "name" ? searchVal : "",
          role: choiceVal === "role" ? searchVal : "",
        },
      });

      console.log("[PictureList.js searchBtn()] success :D");
      console.log(response.data);

      setPictureList(response.data.content);
      setTotalCnt(response.data.totalElements);
    } catch (error) {
      console.log("[PictureList.js searchBtn()] error :<");
      console.log(error);
    }
  };

  // 첫 로딩 시, 한 페이지만 가져옴
  useEffect(() => {
    getPictureList(1);
  }, []);

  // 검색 조건 저장
  const changeChoice = (event) => { setChoiceVal(event.target.value);};
  const changeSearch = (event) => { setSearchVal(event.target.value);};

  // 페이징 보여주기 
  const changePage = (page) => {
    setPage(page);
    getPictureList(page);
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
            <th style={{textAlign:"center"}} className="col-1">제목</th>
            <th style={{textAlign:"center"}} className="col-2">내용</th>
            <th style={{textAlign:"center"}} className="col-2">파일위치</th>
            <th style={{textAlign:"center"}} className="col-1">작성자</th>
            <th style={{textAlign:"center"}} className="col-1">조회수</th>
            <th style={{textAlign:"center"}} className="col-2">생성일시</th>
            <th style={{textAlign:"center"}} className="col-2">수정일시</th>
          </tr>
        </thead>

        <tbody>
          {Array.isArray(pictureList) && pictureList.map(function (picture, idx) {
            return <TableRow obj={picture} key={idx} cnt={idx + 1} pos={pos}/>;
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
  const picture = props.obj;
  return (
    <tr>
      <th style={{textAlign:"center"}} >{props.cnt}</th>
      <td style={{textAlign:"center"}} >
        <Link to={{ pathname: `/pictureDetail/${picture.id}` }}>
          <span className="underline user-name">{picture.title}</span>
        </Link>
      </td>
      <td style={{textAlign:"center"}} >{picture.content}</td>
      {/* <td style={{textAlign:"center"}} >{picture.filePath}</td> */}
      <td style={{textAlign:"center"}} >
        <Link to={{ pathname: `/pictureView/${props.pos}${picture.filePath}` }}>
          <span className="underline user-name">{picture.filePath}</span>
        </Link>
      </td>
      <td style={{textAlign:"center"}} >{picture.writer}</td>
      <td style={{textAlign:"center"}} >{picture.viewCnt}</td>
      <td style={{ textAlign: 'center' }}>{picture.createdDate}</td>
      <td style={{ textAlign: 'center' }}>{picture.modifiedDate}</td>
    </tr>
  );
}

export default PictureList;
