import axios from "axios";
import { useContext, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

import { HttpHeadersContext } from "../context/HttpHeadersProvider";


function FeedbackWrite(props) {

	console.log("[FeedbackWrite.js] render()");

	const { headers, setHeaders } = useContext(HttpHeadersContext);
	const { postId } = useParams(); // 파라미터 가져오기

	const id = localStorage.getItem("id");

	const [content, setContent] = useState("");
	const navigate = useNavigate();

	const chageContent = (event) => {
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

	const createFeedback = async() => {

		const req = {
			content: content,
		}

		await axiosInstance.post(`/feedback/${postId}/feedback/write`, req, {headers: headers})
		.then((resp) => {
			console.log("[FeedbackWrite.js] createFeedback() success :D");
			console.log(resp.data);
			alert("댓글을 성공적으로 등록했습니다 :D");
			navigate(0);

		}).catch((err) => {
			console.log("[FeedbackWrite.js] createFeedback() error :<");
			console.log(err);

		});
	}

	return (
		<>
				{/* 상단 영역 (프로필 이미지, 댓글 작성자) */}
				<div className="my-1 d-flex justify-content-center">
					<div className="col-1">
						<img src="/images/profile-placeholder.png" alt="프로필 이미지"
							className="profile-img"/>
					</div>

					<div className="col-7">
						<span className="feedback-id" >{id}</span>
					</div>
					<div className="col-2 my-1 d-flex justify-content-end">
						<button className="btn btn-outline-secondary" onClick={createFeedback}><i className="fas fa-feedback-dots"></i> 댓글 추가</button>
					</div>
				</div>
				{/* 하단 영역 (댓글 내용) */}
				<div className="my-3 d-flex justify-content-center">
					<textarea className="col-10" rows="1" value={content} onChange={chageContent}></textarea>
				</div><br/><br/>
		</>
	)
}

export default FeedbackWrite;