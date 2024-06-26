import axios from "axios";
import React , { useRef } from "react";
import { useContext, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";


/* 댓글 컴포넌트 */
function Feedback(props) {

	console.log("[Feedback.js] render()");

	const {auth, setAuth} = useContext(AuthContext);
	const {headers, setHeaders} = useContext(HttpHeadersContext);
	
	const page = props.page;
	const feedback = props.obj;
	const feedbackId = feedback.feedbackId;
	const { postId } = useParams(); // postId, 파라미터 가져오기

	const [show, setShow] = useState(false);

	const [content, setContent] = useState(feedback.content);
	const changeContent = (event) => {
		setContent(event.target.value);
	};

	const navigate = useNavigate();

	// 기본 설정을 포함한 axios 인스턴스 생성
	const axiosInstance = axios.create({
		baseURL: 'http://localhost:8989', // 기본 URL 설정
		headers: {
		  'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
		  'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
		}
	});

	/* 댓글 수정 */
	const updateFeedback = async (feedbackId) => {
		const req = {
			id: feedbackId,
			content: content
		};

		await axiosInstance.patch(`/feedback/update.do`, req, {headers: headers})
		.then((resp) => {
			console.log("[Feedback.js] updateFeedback() success :D");
			console.log(resp.data);

			alert("댓글을 성공적으로 수정했습니다 !");

			// 업데이트된 댓글 목록을 다시 불러오기
			props.getFeedbackList(page);
		}).catch((err) => {
			console.log("[Feedback.js] updateFeedback() error :<");
			console.log(err);

			alert(err);

		});
		updateToggle();
	}

	/* 댓글 삭제 */
	const deleteFeedback = async (feedbackId) => {
		const req = {
			id: feedbackId,
			content: content
		};

		await axiosInstance.post(`/feedback/delete.do`, req, {headers: headers})
			.then((resp) => {
				console.log("[Feedback.js] deleteFeedback() success :D");
				console.log(resp.data);

				alert("댓글을 성공적으로 삭제했습니다 :D");
				//삭제된 댓글 목록 다시 불러오기
				props.getFeedbackList(page);
			}).catch((err) => {
				console.log("[Feedback.js] deleteFeedback() error :<");
				console.log(err);
			});
	}

	function updateToggle() { 
		setShow(show => !show) 
	}

	

	// 삭제되지 않은 댓글의 경우
	//if (feedback.del == 0) {
		return (
			<>
				{/* 상단 영역 (프로필 이미지, 댓글 작성자, 댓글 작성시간) */}
				<div className="my-1 d-flex justify-content-center">
					<div className="col-1">
						<img src="/images/profile-placeholder.png" alt="프로필 이미지"
							className="profile-img" />
					</div>
					<div className="col-5">
						<div className="row">
							<span className="feedback-id">{feedback.writer}</span>
						</div>
						<div className="row">
							<span>{feedback.createdDate}</span>
						</div>
					</div>

					<div className="col-4 d-flex justify-content-end">
					{
						/* 자신이 작성한 댓글인 경우에만 수정 삭제 가능 */
						(localStorage.getItem("id") === feedback.writer) ?
							<>
								<button className="btn btn-outline-secondary" onClick={updateToggle}><i className="fas fa-edit"></i> 수정</button> &nbsp; 
								<button className="btn btn-outline-danger" onClick={() => deleteFeedback(feedback.id)}><i className="fas fa-trash-alt"></i> 삭제</button>
							
							</>
							:
							null
					}
					</div>
				</div>

				{
					/* 댓글 수정하는 경우 */
					show ?
						<>
							{/* 하단 영역 (댓글 내용 + 댓글 내용 편집 창) */}
							<div className="my-3 d-flex justify-content-center">
								<textarea className="col-10" rows="5" value={content} onChange={changeContent}></textarea>
							</div>
							<div className="my-1 d-flex justify-content-center">
							<button className="btn btn-dark" onClick={() => updateFeedback(feedback.id)}>
								<i className="fas fa-edit"></i> 수정 완료
							</button>
							</div>
						</>
					:
						<>
							{/* 하단 영역 (댓글 내용) */}
							<div className="my-3 d-flex justify-content-center">
								<div className="col-10 feedback" style={{ whiteSpace: 'pre-line' }}>{content}</div>
							</div>
						</>
				}

			</>
		);
	//}

	// // 삭제된 댓글의 경우
	// else {
	// 	return (
	// 		<>
	// 			<div className="my-5 d-flex justify-content-center">
	// 				<div className="feedback">
	// 					<span className="del-span">⚠️ 작성자에 의해 삭제된 댓글입니다.</span>
	// 				</div>
	// 			</div>
	// 		</>
	// 	);
	// }
}

export default Feedback;