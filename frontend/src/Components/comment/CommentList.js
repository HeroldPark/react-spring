import React , { useRef } from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import Pagination from "react-js-pagination";
import Comment from "./Comment.js"
import "../../css/commentList.css"; // 스타일 파일 import

function CommentList(props) {

	console.log("[CommentList.js] render()");

	const boardId = props.boardId;

	// Paging
	const [page, setPage] = useState(1);
	const [pageSize, setPageSize] = useState(5);
	const [totalPages, setTotalPages] = useState(5);
	const [totalCnt, setTotalCnt] = useState(0);
	const [commentList, setCommentList] = useState([]);

	// comment에서 참조
	const getCommentListRef  = useRef(null);

	const changePage = (page) => {
		setPage(page);
		getCommentList(page);
		getCommentListRef.current(page);
	}

	// 기본 설정을 포함한 axios 인스턴스 생성
	const axiosInstance = axios.create({
		baseURL: 'http://localhost:8989', // 기본 URL 설정
		headers: {
		  'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
		  'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
		}
	});

	const getCommentList = async (page) => {
		await axiosInstance.get(`/board/${boardId}/comment/list`, { params: {"page": page - 1} })
			.then((resp) => {
				console.log("[CommentList.js] getCommentList() success :D");
				console.log(resp.data);

				setPageSize(resp.data.pageSize);
				setTotalPages(resp.data.totalPages);
				setTotalCnt(resp.data.totalElements);
				setCommentList(resp.data.content);
			}).catch((err) => {
				console.log("[CommentList.js] getCommentList() error :<");
				console.log(err);

			});
	}

	useEffect(() => {
		getCommentListRef.current = getCommentList;
		getCommentList(1);
	}, [boardId]);

	return (
		<>
			<div className="my-1 d-flex justify-content-center">
			</div>

			<Pagination
				activePage={page}
				itemsCountPerPage={5}
				totalItemsCount={totalCnt}
				pageRangeDisplayed={5}
				prevPageText={"‹"}
				nextPageText={"›"}
				onChange={changePage} />
				{
					commentList.map(function (comment, idx) {
						return (
							<div className="my-5" key={idx}>
								<Comment obj={comment} key={idx} page={page} getCommentList={getCommentListRef.current}/>
							</div>
						);
					})
				}

		</>

	);
}


export default CommentList;