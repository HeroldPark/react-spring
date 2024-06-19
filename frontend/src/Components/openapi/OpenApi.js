import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Pagination from "react-js-pagination";
import { Link } from "react-router-dom";

// First, include Moment.js in your project
const moment = require('moment');

const OpenApi = () => {
  console.log("[OpenApi.js] 시작");

  const [accessToken, setAccessToken] = useState('none');
  const [errCnt, setErrCnt] = useState(0);
  const [allStatData, setAllStatData] = useState([]);
  const [currentStatData, setCurrentStatData] = useState([]);

  // 검색용 Hook
  const [choiceVal, setChoiceVal] = useState("");
  const [searchVal, setSearchVal] = useState("");

  // Paging
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCnt, setTotalCnt] = useState(0);

  const spacing = 20; // em 단위로 공백을 추가하려는 값

  const serviceName = process.env.REACT_APP_OPENAPI_SERVICE_NAME;
  const serviceId = process.env.REACT_APP_OPENAPI_SERVICE_ID;
  const secretKey = process.env.REACT_APP_OPENAPI_SECRET_KEY;

  useEffect(() => {
    getAccessToken(serviceId, secretKey);
  }, [serviceId, secretKey]);

  useEffect(() => {
    paginateData(page);
  }, [page, allStatData]);

  const getAccessToken = async (serviceId, secretKey) => {
    try {
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json', {
        params: {
          consumer_key: serviceId,
          consumer_secret: secretKey
        },
      });
      setErrCnt(0);
      const token = response.data.result.accessToken;
      setAccessToken(token);
      fetchAllData(token);
    } catch (error) {
      console.error('Error fetching access token:', error);
    }
  };

  const fetchAllData = async (token) => {
    const year = '2022';
    const low_search = '1';
    const adm_cd = '1';

    try {
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/stats/population.json', {
        params: {
            accessToken: token,
            year,
            low_search,
            adm_cd
        },
      });

      responseProc(response);
    } catch (error) {
      console.error('Error fetching stat data:', error);
    }
  };

  const responseProc = (response) => {
    switch (parseInt(response.data.errCd)) {
      case 0:
          if (response.data.result) {
              setAllStatData(response.data.result);
              const totalCnt = response.data.result.length;
              const totalPages = Math.ceil(totalCnt / pageSize);
              setTotalPages(totalPages);
              setTotalCnt(totalCnt);
              paginateData(1, response.data.result); // Set initial data for page 1
          } else {
            setAllStatData([]);
            setCurrentStatData([]);
          }
        break;
      case -401:
        setErrCnt((prevErrCnt) => {
          const newErrCnt = prevErrCnt + 1;
          if (newErrCnt < 200) {
            getAccessToken();
          }
          return newErrCnt;
        });
        break;
      case -100:
        console.error('Error -100:', response.data.errMsg);
        break;
      default:
        console.error('Unexpected error code:', response.data.errCd);
    }
  };

  const paginateData = (page, data = allStatData) => {
    const offset = (page - 1) * pageSize;
    const paginatedData = data.slice(offset, offset + pageSize);
    setCurrentStatData(paginatedData);
    setPage(page);
  };

  const changeChoice = (event) => { setChoiceVal(event.target.value);};
  const changeSearch = (event) => { setSearchVal(event.target.value);};

  const changePage = (page) => {
    paginateData(page);
  };

  // 검색용
  const search = async () => {
    try {
      const date = new Date();
      const year = moment(date).format('YYYY') - 2;
      const low_search = '1';
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/stats/population.json', {
        params: {
            accessToken: accessToken,
            year,
            low_search,
            adm_cd : choiceVal === "code" ? searchVal : "1"
        },
      });

      console.log("[OpenApi.js searchBtn()] success :D");
      console.log(response.data);

      responseProc(response);
    } catch (error) {
      console.log("[OpenApi.js searchBtn()] error :<");
      console.log(error);
    }
  };

  // 차트용
  const chart = async () => {
    try {
      const date = new Date();
      const year = moment(date).format('YYYY') - 2;
      const low_search = '1';
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/stats/population.json', {
        params: {
            accessToken: accessToken,
            year,
            low_search,
            adm_cd : choiceVal === "code" ? searchVal : "1"
        },
      });

      console.log("[OpenApi.js chartBtn()] success :D");
      console.log(response.data);

      responseProc(response);
    } catch (error) {
      console.log("[OpenApi.js chartBtn()] error :<");
      console.log(error);
    }
  };

  return (
    <div>
        <header className="page_tits">
            <h3>React-Spring</h3>
            <p className="path"><strong>현재 위치 :</strong> <span>테스트 베드</span> <span>OpenAPI</span> <span>인구통계</span></p>
        </header>

        {/* 조회 */}
        <table className="search">
        <tbody>
        <tr>
            {/* 검색 */}
            <td>
            <select
                className="custom-select"
                value={choiceVal}
                onChange={changeChoice}
            >
                <option>검색 옵션 선택</option>
                <option value="code">지역코드</option>
                <option value="role">지역명</option>
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

            {/* 차트 */}
            <td style={{marginRight: spacing + 'px'}}>&nbsp;&nbsp;</td>
            <td>
            <select
                className="custom-select"
                value={choiceVal}
                onChange={changeChoice}
            >
                <option>차트 선택</option>
                <option value="chart">막대차트</option>
                <option value="chart">파이차트</option>
            </select>
            </td>
            <td>
            <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={chart}
            >
                <i className="fas fa-search"></i> 그리기
            </button>
            </td>
        </tr>
        </tbody>
    </table>
    <br />

    <table className="table table-hover">
        <thead>
        <tr>
            <th style={{textAlign:"center"}} className="col-1">index</th>
            <th style={{textAlign:"center"}} className="col-1">adm_cd</th>
            <th style={{textAlign:"center"}} className="col-1">tot_house</th>
            <th style={{textAlign:"center"}} className="col-1">ppltn_dnsty</th>
            <th style={{textAlign:"center"}} className="col-1">tot_ppltn</th>
            <th style={{ textAlign: 'center' }}>aged_child_idx</th>
            <th style={{ textAlign: 'center' }}>corp_cnt</th>
            <th style={{ textAlign: 'center' }}>imga_cnt</th>
            <th style={{ textAlign: 'center' }}>employee_cnt</th>
            <th style={{ textAlign: 'center' }}>haesuoga_cnt</th>
            <th style={{ textAlign: 'center' }}>juv_suprt_per</th>
            <th style={{ textAlign: 'center' }}>haesuoga_ppltn</th>
            <th style={{ textAlign: 'center' }}>tot_family</th>
            <th style={{ textAlign: 'center' }}>adm_nm</th>
            <th style={{ textAlign: 'center' }}>nongga_cnt</th>
            <th style={{ textAlign: 'center' }}>naesuoga_ppltn</th>
            <th style={{ textAlign: 'center' }}>avg_age</th>
            <th style={{ textAlign: 'center' }}>avg_fmember_cnt</th>
            <th style={{ textAlign: 'center' }}>nongga_ppltn</th>
            <th style={{ textAlign: 'center' }}>naesuoga_cnt</th>
            <th style={{ textAlign: 'center' }}>oldage_suprt_per</th>
        </tr>
        </thead>

        <tbody>
        {Array.isArray(currentStatData) && currentStatData.map(function (data, index) {
            return <TableRow obj={data} key={index} cnt={index + 1} page={page} size={pageSize}/>;
        })}
        </tbody>
    </table>

    <Pagination
        className="pagination"
        activePage={page}
        itemsCountPerPage={pageSize}
        totalItemsCount={totalCnt}
        pageRangeDisplayed={5}
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
};

/* 글 목록 테이블 행 컴포넌트 */
function TableRow(props) {
    const data = props.obj;
    const minCnt = props.size * (props.page-1);
    const maxCnt = props.size * props.page;
    if(!(minCnt < minCnt+props.cnt && props.cnt < maxCnt))
        return;
  
    return (
      <tr>
        <th style={{textAlign:"center"}} >{props.cnt}</th>
        <td style={{textAlign:"center"}} >
          <Link to={{ pathname: `/statdetail.do/${data.adm_cd}` }}>
            <span className="underline user-name">{data.adm_cd}</span>
          </Link>
        </td>
        <td style={{textAlign:"center"}} >{data.tot_house}</td>
        <td style={{textAlign:"center"}} >{data.ppltn_dnsty}</td>
        <td style={{textAlign:"center"}} >{data.tot_ppltn}</td>
        <td style={{ textAlign: 'center' }}>{data.aged_child_idx}</td>
        <td style={{ textAlign: 'center' }}>{data.corp_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.imga_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.employee_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.haesuoga_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.juv_suprt_per}</td>
        <td style={{ textAlign: 'center' }}>{data.haesuoga_ppltn}</td>
        <td style={{ textAlign: 'center' }}>{data.tot_family}</td>
        <td style={{ textAlign: 'center' }}>{data.adm_nm}</td>
        <td style={{ textAlign: 'center' }}>{data.nongga_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.naesuoga_ppltn}</td>
        <td style={{ textAlign: 'center' }}>{data.avg_age}</td>
        <td style={{ textAlign: 'center' }}>{data.avg_fmember_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.nongga_ppltn}</td>
        <td style={{ textAlign: 'center' }}>{data.naesuoga_cnt}</td>
        <td style={{ textAlign: 'center' }}>{data.oldage_suprt_per}</td>
      </tr>
    );
}

export default OpenApi;
