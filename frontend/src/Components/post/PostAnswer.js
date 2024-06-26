import axios from "axios";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useContext, useState, useEffect } from "react";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

function PostAnswer() {

	console.log("[PostAnswer.js] render()");

	const { auth, setAuth } = useContext(AuthContext)
	const { headers, setHeaders } = useContext(HttpHeadersContext);

	const navigate = useNavigate();

	const { parentSeq } = useParams(); // 부모 글 번호

	const location = useLocation();
	const { parentPost } = location.state;

	const [title, setTitle] = useState("");
	const [content, setContent] = useState("");

	const changeTitle = (event) => {
		setTitle(event.target.value);
	}

	const changeContent = (event) => {
		setContent(event.target.value);
	}

	// 기본 설정을 포함한 axios 인스턴스 생성
	const axiosInstance = axios.create({
		baseURL: 'http://localhost:8989', // 기본 URL 설정
		headers: {
		  'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
		  'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
		}
	});

	const createPostAnswer = async () => {

		const req = {
			id: parentSeq,
			writer: localStorage.getItem("id"),	
			title: "Re: " + title,
			content: content
		}
		
		await axiosInstance.post(`/post/answer.do`, req, {headers: headers})
		.then((resp) => {
			console.log("[PostAnswer.js] createPostAnswer() success :D");
			console.log(resp.data);

			alert("답글을 성공적으로 등록했습니다 :D");
			// navigate(`/postdetail/${resp.data.seq}`); // 새롭게 등록한 답글 상세로 이동
			navigate(`/postdetail/${resp.data.seq}`, { state: { post: resp.data } }); // 전달
		})
		.catch((err) => {
			console.log("[PostAnswer.js] createPostAnswer() error :<");
			console.log(err);
		});

	}

	useEffect(() => {
		if (!auth) {
			alert("로그인 한 사용자만 게시글에 대한 답글을 작성할 수 있습니다 !");
			navigate(-1);
		}
	}, []);

	return (
		<div>
			{/* 부모 게시글 정보 */}
			<table className="table">
				<tbody>
					<tr>
						<th className="table-primary">작성자</th>
						<td>
							<input type="text" className="form-control" value={parentPost.writer} size="50px" readOnly />
						</td>
					</tr>

					<tr>
						<th className="table-primary">제목</th>
						<td>
							<input type="text" className="form-control" value={parentPost.title} size="50px" readOnly />
						</td>
					</tr>
				</tbody>
			</table><br/><br/>
			
			{/* 답글 작성 */}
			<h3>📌 Reply</h3>
			<table className="table">
				<tbody>
					<tr>
						<th className="table-primary">작성자</th>
						<td>
							<input type="text" className="form-control"  value={localStorage.getItem("id")} size="50px" readOnly />
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
							<textarea className="form-control" value={content} onChange={changeContent} rows="10"></textarea>
						</td>
					</tr>
				</tbody>
			</table>

			<div className="my-5 d-flex justify-content-center">
				<button className="btn btn-outline-secondary" onClick={createPostAnswer}><i className="fas fa-pen"></i> 답글달기</button>
			</div>
		</div>
	);
}

export default PostAnswer;