import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import CommentWrite from "../frontend/src/Components/comment/CommentWrite";
import CommentList from "../frontend/src/Components/comment/CommentList";
import { AuthContext } from "../frontend/src/Components/context/AuthProvider";
import { HttpHeadersContext } from "../frontend/src/Components/context/HttpHeadersProvider";

import "../../css/logindetail.css"; // 추가: 스타일 파일 import
import FileDisplay from "../frontend/src/Components/file/FileDisplay";

function LoginDetailTable() {
  console.log("[LoginDetail.js] 시작");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth, setAuth } = useContext(AuthContext);
  const [ login, setLogin] = useState({});
  const { memberId } = useParams(); // 파라미터 가져오기
  const navigate = useNavigate();

  // 기본 설정을 포함한 axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989', // 기본 URL 설정
    headers: {
      'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
    }
  });

  const getLoginDetail = async () => {
    try {
      const response = await axiosInstance.get(`/login/${memberId}`);

      console.log("[LoginDetail.js] getLoginDetail() success :D");
      console.log(response.data);

      setLogin(response.data);
    } catch (error) {
      console.log("[LoginDetail.js] getLoginDetail() error :<");
      console.error(error);
    }
  };

  const deleteLogin = async () => {
    try {
      const response = await axiosInstance.delete(`/login/${memberId}/delete`, {headers: headers});

      console.log("[LoginDetail.js] deleteLogin() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("게시글을 성공적으로 삭제했습니다 :D");
        navigate("/loginlist");
      }
    } catch (error) {
      console.log("[LoginDetail.js] deleteLogin() error :<");
      console.error(error);
    }
  };

  useEffect(() => {
	// 컴포넌트가 렌더링될 때마다 localStorage의 토큰 값으로 headers를 업데이트
	setHeaders({
		"Authorization": `Bearer ${localStorage.getItem("login_access_token")}`
	});
    getLoginDetail();
  }, []);

  const updateLogin = {
    memberId: login.memberId,
    writerName: login.writerName,
    title: login.title,
    content: login.content,
	files: login.files
  };

  const parentLogin = {
    memberId: login.memberId,
    title: login.title,
  };

	return (
		<div className="login-detail-container">
			<div>

				<div className="my-3 d-flex justify-content-end">
					<Link className="btn btn-outline-secondary" to="/userlist"><i className="fas fa-list">
						</i> 글목록</Link> &nbsp;

					<Link className="btn btn-outline-secondary" to={{pathname: `/useranswer/${login.memberId}` }} state={{ parentLogin: parentLogin }}>
						<i className="fas fa-pen"></i> 답글쓰기</Link> &nbsp;

					{
						/* 자신이 작성한 게시글인 경우에만 수정, 삭제 가능 */
						(localStorage.getItem("id") === login.writerName) ?
							<>
								<Link className="btn btn-outline-secondary"  to="/userupdate" state={{ login: updateLogin }}><i className="fas fa-edit"></i> 수정</Link> &nbsp;
								<button className="btn btn-outline-danger"  onClick={deleteLogin}><i className="fas fa-trash-alt"></i> 삭제</button>
							</>
						:
						null
					}

				</div>

				<table className="table table-striped">
					<tbody>
						<tr>
							<th className="col-3">성명</th>
							<td>
							<span>{login.username}</span>
							</td>
						</tr>

						<tr>
							<th>이메일</th>
							<td>
							<span>{login.email}</span>
							</td>
						</tr>

						<tr>
							<th>권한</th>
							<td>
							<span>{login.roles}</span>
							</td>
						</tr>

						<tr>
							<th>사진</th>
							<td>{login.picture ? (
								<img src={login.picture} alt="Profile" style={{ width: '50px', height: '50px' }} />
								) : (
								<a href={login.picture} className="btn btn-sm btn-success active" role="button">Profile</a>
								)}
							</td>
						</tr>

						<tr>
							<th>제공</th>
							<td><span>{login.provider}</span></td>
						</tr>
						<tr>
							<th>생성일</th>
							<td><span>{login.createdDate}</span></td>
						</tr>
						<tr>
							<th>수정일</th>
							<td><span>{login.modifiedDate}</span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	);
}

export default LoginDetail;