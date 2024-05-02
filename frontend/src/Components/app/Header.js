import React, { useContext } from "react";
import { AuthContext } from "../context/AuthProvider";
import { Link } from "react-router-dom";

import logo from '../../static/images/logo192.png';

function Header() {

  const { auth, setAuth } = useContext(AuthContext);  // eslint-disable-line no-unused-vars

  return (
    <header>
      <nav className="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
        <div className="container">
          <div
            className="navbar-collapse collapse justify-content-between"
            id="navbar-content"
          >
            <ul className="navbar-nav mr-auto">
              {/* React 로고 */}
              <li className="nav-item">
                <Link className="nav-link" to="/">
                  <img src={logo} alt="logo" className="logo-image" style={{ width: '18px', height: 'auto' }} />
                </Link>
              </li>

              {/* 메인 화면 */}
              <li className="nav-item">
                <Link className="nav-link" to="/">
                  <i className="fas fa-home"></i> Home
                </Link>
              </li>
          
              {/* 게시판  관리 */}
              <li className="nav-item dropdown">
                <div
                  className="nav-link dropdown-toggle"
                  id="navbarDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  게시판 관리
                </div>

                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <Link className="dropdown-item" to="/bbslist">
                    글목록
                  </Link>
                  <Link className="dropdown-item" to="/bbswrite">
                    글추가
                  </Link>
                </div>
              </li>

              {/* 웹서버 관리 */}
              <li className="nav-item dropdown">
                <div
                  className="nav-link dropdown-toggle"
                  id="navbarDropdown"
                  role="button"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  웹서버 관리
                </div>

                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                  <Link className="dropdown-item" to="/member">
                    사용자
                  </Link>
                  <Link className="dropdown-item" to="/code">
                    코드
                  </Link>
                  <Link className="dropdown-item" to="/history">
                    이력
                  </Link>
                </div>
              </li>

            </ul>
            <ul className="navbar-nav ml-auto">
              {auth ? (
                <>
                  {/* 회원 정보 */}
                  <li className="nav-item">
                      <Link className="nav-link" to="/checkpwd">
                        <i className="fas fa-sign-out-alt"></i> {auth} 님 반갑습니다 <i className="fab fa-ello"></i>{" "} &nbsp;{" "}
                      </Link>
                  </li>

                  {/* 로그아웃 */}
                  <li className="nav-item">
                    <Link className="nav-link" to="/logout">
                      <i className="fas fa-sign-out-alt"></i> 로그아웃
                    </Link>
                  </li>
                </>
              ) : (
                <>
                  {/* 로그인 */}
                  <li className="nav-item">
                    <Link className="nav-link" to="/login">
                      로그인
                    </Link>
                  </li>

                  {/* 회원가입 */}
                  <li className="nav-item">
                    <Link className="nav-link" to="/join">
                      회원가입
                    </Link>
                  </li>
                </>
              )}
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
}

export default Header;
