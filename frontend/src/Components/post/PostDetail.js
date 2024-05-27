import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import CommentWrite from "../comment/CommentWrite";
import CommentList from "../comment/CommentList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/postdetail.css"; // 추가: 스타일 파일 import
import FileDisplay from "../file/FileDisplay";

function PostDetail() {
  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth, setAuth } = useContext(AuthContext);
  const [ post, setPost] = useState({});
  const { id } = useParams(); // 파라미터 가져오기
  const navigate = useNavigate();

  const getPostDetail = async () => {
    try {
      const response = await axios.get(`http://localhost:8989/post/${id}`);

      console.log("[PostDetail.js] getPostDetail() success :D");
      console.log(response.data);

      setPost(response.data);
    } catch (error) {
      console.log("[PostDetail.js] getPostDetail() error :<");
      console.error(error);
    }
  };

  const deletePost = async () => {
    try {
      const response = await axios.delete(`http://localhost:8989/post/${id}/delete`, {headers: headers});

      console.log("[PostDetail.js] deletePost() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("게시글을 성공적으로 삭제했습니다 :D");
        navigate("/postlist");
      }
    } catch (error) {
      console.log("[PostDetail.js] deletePost() error :<");
      console.error(error);
    }
  };

  useEffect(() => {
	// 컴포넌트가 렌더링될 때마다 localStorage의 토큰 값으로 headers를 업데이트
	setHeaders({
		"Authorization": `Bearer ${localStorage.getItem("post_access_token")}`
	});
    getPostDetail();
  }, []);

  const updatePost = {
    id: post.id,
    writerName: post.writerName,
    title: post.title,
    content: post.content,
	files: post.files
  };

  const parentPost = {
    id: post.id,
    title: post.title,
  };

	return (
		<div className="post-detail-container">
			<div>

				<div className="my-3 d-flex justify-content-end">
					<Link className="btn btn-outline-secondary" to="/postlist"><i className="fas fa-list">
						</i> 글목록</Link> &nbsp;

					<Link className="btn btn-outline-secondary" to={{pathname: `/postanswer/${post.id}` }} state={{ parentPost: parentPost }}>
						<i className="fas fa-pen"></i> 답글쓰기</Link> &nbsp;

					{
						/* 자신이 작성한 게시글인 경우에만 수정, 삭제 가능 */
						(localStorage.getItem("id") === post.writerName) ?
							<>
								<Link className="btn btn-outline-secondary"  to="/postupdate" state={{ post: updatePost }}><i className="fas fa-edit"></i> 수정</Link> &nbsp;
								<button className="btn btn-outline-danger"  onClick={deletePost}><i className="fas fa-trash-alt"></i> 삭제</button>
							</>
						:
						null
					}

				</div>

				<table className="table table-striped">
					<tbody>
						<tr>
							<th className="col-3">작성자</th>
							<td>
							<span>{post.writerName}</span>
							</td>
						</tr>

						<tr>
							<th>제목</th>
							<td>
							<span>{post.title}</span>
							</td>
						</tr>

						<tr>
							<th>작성일</th>
							<td>
							<span>{post.createdDate}</span>
							</td>
						</tr>

						<tr>
							<th>조회수</th>
							<td>
							<span>{post.viewCount}</span>
							</td>
						</tr>

						<tr>
							<th>내용</th>
							<td></td>
						</tr>
					</tbody>
				</table>

				<div className="content-box" style={{ whiteSpace: 'pre-line' }}>{post.content}</div>
				<div>
					<FileDisplay files={post.files} id={id} />
				</div>
				
				 {/* 댓글 리스트 컴포넌트 */}
				 <CommentList id={id} />

				{/* 댓글 작성 컴포넌트 */}
				{
					(auth) ? // 로그인한 사용자만 댓글 작성 가능
						<CommentWrite id={id}/>
					:
						null
				}

			</div>
		</div>
	);
}

export default PostDetail;