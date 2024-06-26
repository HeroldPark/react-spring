import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useContext, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/loginupdate.css";

function LoginUpdate() {
	const { headers, setHeaders } = useContext(HttpHeadersContext);
	const { auth, setAuth } = useContext(AuthContext);
	const navigate = useNavigate();

	const location = useLocation();
	const { login } = location.state;
	
	const memberId = login.memberId;
	const [title, setTitle] = useState(login.title);
	const [content, setContent] = useState(login.content);

	const changeTitle = (event) => {
		setTitle(event.target.value);
	}

	const changeContent = (event) => {
		setContent(event.target.value);
	}

	useEffect(() => {
		setHeaders({
			"Authorization": `Bearer ${localStorage.getItem("login_access_token")}`
		});
	}, []);

	/* 게시판 수정 */
	const updateLogin = async () => {

		const req = {
			id: auth, 
			title: title, 
			content: content
		}

		await axios.patch(`http://localhost:8989/member/${login.memberId}/update`, req, {headers: headers})
		.then((resp) => {
			console.log("[LoginUpdate.js] updateLogin() success :D");
			console.log(resp.data);
			const memberId = resp.data.memberId;

			alert("게시글을 성공적으로 수정했습니다 :D");
			navigate(`/logindetail/${resp.data.memberId}`); // 새롭게 등록한 글 상세로 이동
		})
		.catch((err) => {
			console.log("[LoginUpdate.js] updateLogin() error :<");
			console.log(err);
		});

	}

	return (
		<div>
			<table className="table">
				<tbody>
					<tr>
						<th className="table-primary">작성자</th>
						<td>
							<input type="text" className="form-control"  value={login.writerName} size="50px" readOnly />
						</td>
					</tr>

					<tr>
						<th className="table-primary">제목</th>
						<td>
							<input type="text" className="form-control" value={title} onChange={changeTitle} size="50px" />
						</td>
					</tr>

					<tr>
						<th className="table-primary">내용</th>
						<td>
							<textarea className="form-control" value={content} onChange={changeContent} rows="10" ></textarea>
						</td>
					</tr>
					<tr>
				</tr>
				</tbody>
			</table>

			<div className="my-3 d-flex justify-content-center">
				<button className="btn btn-dark" onClick={updateLogin}><i className="fas fa-pen"></i> 수정하기</button>
			</div>
		</div>
	);

}

export default LoginUpdate;