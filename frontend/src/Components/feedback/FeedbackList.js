import React , { useRef } from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import Pagination from "react-js-pagination";
import Feedback from "./Feedback.js"
import "../../css/feedbacklist.css"; // 스타일 파일 import

/* 댓글 컴포넌트 */
function FeedbackList(props) {

	console.log("[FeedbackList.js] render() : props.id: ", props.id);
	const id = props.id;

	// Paging
	const [page, setPage] = useState(1);
	const [pageSize, setPageSize] = useState(5);
	const [totalPages, setTotalPages] = useState(5);
	const [totalCnt, setTotalCnt] = useState(0);
	const [feedbackList, setFeedbackList] = useState([]);

	// feedback에서 참조
	const getFeedbackListRef  = useRef(null);

	const changePage = (page) => {
		setPage(page);
		getFeedbackList(page);
		getFeedbackListRef.current(page);
	}

	// 기본 설정을 포함한 axios 인스턴스 생성
	const axiosInstance = axios.create({
		baseURL: 'http://localhost:8989', // 기본 URL 설정
		headers: {
		  'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
		  'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
		}
	});

	const getFeedbackList = async (page) => {
		await axiosInstance.get(`/feedback/${id}/feedback/list`, { params: {"page": page - 1} })
			.then((resp) => {
				console.log("[FeedbackList.js] getFeedbackList() success :D");
				console.log(resp.data);

				setPageSize(resp.data.pageSize);
				setTotalPages(resp.data.totalPages);
				setTotalCnt(resp.data.totalElements);
				setFeedbackList(resp.data.content);
			}).catch((err) => {
				console.log("[FeedbackList.js] getFeedbackList() error :<");
				console.log(err);

			});
	}

	useEffect(() => {
		getFeedbackListRef.current = getFeedbackList;
		if (id) { // id가 존재할 때만 호출하도록 추가
			getFeedbackList(1);
		}
	}, [id]);

	return (
		<>
			<div className="my-1 d-flex justify-content-center"> </div>

			<Pagination
				activePage={page}
				itemsCountPerPage={pageSize}
				pageRangeDisplayed={5}
				// totalItemsCount={totalCnt}
				totalItemsCount={0}
				prevPageText={"‹"}
				nextPageText={"›"}
				onChange={changePage} 
			/>
			{
				feedbackList && feedbackList.map(function (feedback, idx) {
					return (
						<div className="my-5" key={idx}>
							<Feedback obj={feedback} key={idx} page={page} getFeedbackList={getFeedbackListRef.current}/>
						</div>
					);
				})
			}
		</>
	);
}

export default FeedbackList;