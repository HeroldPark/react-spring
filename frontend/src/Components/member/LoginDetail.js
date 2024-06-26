import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";
import "../../css/LayerPopupLoginDetail.css"; // 스타일 파일 import

function LoginDetail() {
  console.log("[LoginDetail.js] 시작");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth, setAuth } = useContext(AuthContext);
  const [account, setAccount] = useState('');
  const [login, setLogin] = useState({});
  const [updatedUsername, setUpdatedUsername] = useState("");
  const [updatedEmail, setUpdatedEmail] = useState("");
  const [updatedPassword, setUpdatedPassword] = useState('');
  const [checkPassword, setCheckPassword] = useState('');
  const [updatedRoles, setUpdatedRoles] = useState("");
  const [editMode, setEditMode] = useState(false); // 수정 모드 상태 추가
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
      // const account = auth.split('@')[0];  // 'admin'을 추출합니다
      setAccount(auth.split('@')[0]);
      setUpdatedUsername(response.data.username);
      setUpdatedEmail(response.data.email);
      setUpdatedPassword('p@ssw0rd');
      setCheckPassword('p@ssw0rd');
      setUpdatedRoles(response.data.roles);
    } catch (error) {
      console.log("[LoginDetail.js] getLoginDetail() error :<");
      console.error(error);
    }
  };

  const loginUpdate = async () => {
    const req = {
      email: updatedEmail,
      password: updatedPassword,
      passwordCheck: checkPassword,
      username: updatedUsername,
      roles: updatedRoles
    }

    try {
        const resp = await axiosInstance.patch(`/login/update`, req, {headers: headers});
        console.log("[LoginUpdate.js] updateLogin() success :D");
        console.log(resp.data);

        alert("계정 정보를 성공적으로 수정했습니다 :D");
        navigate(`/loginlist`);
    } catch (err) {
        console.log("[LoginUpdate.js] updateLogin() error :<");
        console.error(err);
        alert("계정 정보 수정에 실패했습니다. 오류: " + err.message);
    }
  };

  const loginDelete = async () => {
    try {
      const response = await axiosInstance.delete(`/login/${memberId}/delete`, { headers: headers });

      console.log("[LoginDetail.js] loginDelete() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("회원 정보를 성공적으로 삭제했습니다 :D");
        navigate("/loginlist");
      }
    } catch (error) {
      console.log("[LoginDetail.js] loginDelete() error :<");
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

  const handleEditMode = () => {
    // 수정 모드 전환
    setEditMode(true);
  };

  return (
    <div className="layer-popup">
      <div className="popup-content">
        <div className="popup-header">
          <h2>Login Detail</h2>
          <button className="close-button" onClick={() => navigate("/loginlist")}>
            <i className="fas fa-times"></i>
          </button>
        </div>

        <div className="popup-body">
          <table className="table table-striped">
            <tbody>
              <tr>
                <th className="col-3">성명</th>
                <td>
                  {editMode ? (
                    <input
                      type="text"
                      value={updatedUsername}
                      onChange={(e) => setUpdatedUsername(e.target.value)}
                    />
                  ) : (
                    <span>{login.username}</span>
                  )}
                </td>
              </tr>

              <tr>
                <th>이메일</th>
                <td>
                  {editMode ? (
                    <input
                      type="text"
                      value={updatedEmail}
                      onChange={(e) => setUpdatedEmail(e.target.value)}
                    />
                  ) : (
                    <span>{login.email}</span>
                  )}
                </td>
              </tr>

              { editMode ? (
                <tr>
                  <th>비밀번호</th>
                  <td>
                    <input
                      type="password"
                      value={updatedPassword}  // 여기서 updatedPassword를 입력한다.
                      onChange={(e) => setUpdatedPassword(e.target.value)}
                    />
                  </td>
                </tr>
                ) : ('')
              }

              { editMode ? (
                <tr>
                  <th>비밀번호 재입력</th>
                  <td>
                    <input
                      type="password"
                      value={checkPassword}  // 여기서 checkPassword 입력한다.
                      onChange={(e) => setCheckPassword(e.target.value)}
                    />
                  </td>
                </tr>
                ) : ('')
              }

              
              <tr>
                <th>권한</th>
                <td>
                  {(editMode && account === 'admin') ? ( // 관리자인 경우에만 수정 가능
                    // <input
                    //   type="text"
                    //   value={updatedRoles}
                    //   onChange={(e) => setUpdatedRoles(e.target.value)}
                    // />
                    <select id="roles" name="roles" value={updatedRoles} onChange={(e) => setUpdatedRoles(e.target.value)} size="50px" >
                      <option value="ADMIN">관리자</option>
                      <option value="USER">사용자</option>
                      <option value="GUEST">방문자</option>
                    </select>
                  ) : (
                    <span>{login.roles}</span>
                  )}
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

          <div className="popup-footer">
            {
              /* 자신이 작성한 게시글인 경우, 관리자인 경우에 수정, 삭제 가능 */
              (localStorage.getItem("id") === login.email || account === 'admin') &&
              <>
                {editMode ? (
                  <button className="btn btn-outline-secondary" onClick={loginUpdate}>
                    <i className="fas fa-check"></i> 저장
                  </button>
                ) : (
                  <button className="btn btn-outline-secondary" onClick={handleEditMode}>
                    <i className="fas fa-edit"></i> 수정
                  </button>
                )}
                <button className="btn btn-outline-danger" onClick={loginDelete}>
                  <i className="fas fa-trash-alt"></i> 삭제
                </button>
              </>
            }
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginDetail;
