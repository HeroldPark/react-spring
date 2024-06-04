import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate, Link, useLocation } from "react-router-dom";
import FeedbackWrite from "../feedback/FeedbackWrite";
import FeedbackList from "../feedback/FeedbackList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/postdetail.css";
import FileDisplay from "../file/FileDisplay";

function PostDetail() {
  console.log("[PostDetail.js] render()");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth } = useContext(AuthContext);
  const [post, setPost] = useState(null);  // 일반 객체인 경우와 배열인 경우를 구분하기 위해 null로 초기화
  const [attach, setAttach] = useState(null);
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}`
    }
  });

  useEffect(() => {
    setHeaders({
      "Authorization": `Bearer ${localStorage.getItem("login_access_token")}`
    });

    if (location.state && location.state.post) {
      setPost(location.state.post);
    } else {
      getPostDetail();
    }
  }, [location.state]);

  const getPostDetail = async () => {
    try {
      const response = await axiosInstance.get(`/post/detail.do/${id}`);

      console.log("[PostDetail.js] getPostDetail() success :D");
      console.log(response.data);

      setPost(response.data);

      // 첨부 파일 조회
      const postId = response.data.id;
      try {
        const response = await axiosInstance.get(`/file/${postId}`);
        console.log("[PostDetail.js] getPostDetail() files success :D");

        setAttach({
          files: response.data
        });
      } catch (error) {
        console.log("[PostDetail.js] getPostDetail() error :<<");
        console.error(error);
      }
    } catch (error) {
      console.log("[PostDetail.js] getPostDetail() error :<");
      console.error(error);
    }
  };

  const deletePost = async (postId) => {
    try {
      const response = await axiosInstance.delete(`/post/${postId}/delete.do`, { headers });

      console.log("[PostDetail.js] delete() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("게시글을 성공적으로 삭제했습니다 :D");
        if (Array.isArray(post)) {
          setPost(post.filter(p => p.id !== postId));
        } else {
          navigate("/postlist");
        }
      }
    } catch (error) {
      alert("게시글을 삭제 샐패 :<");
      console.log("[PostDetail.js] delete() error :<");
      console.error(error);
    }
  };

  const renderPost = (post) => {
    const updatePost = {
      id: post.id,
      writer: post.writer,
      title: post.title,
      content: post.content,
      files: post.files
    };

    const parentPost = {
      id: post.id,
      writer: post.writer,
      title: post.title,
    };

    return (
      <div key={post.id}>
        <div className="my-3 d-flex justify-content-end">
          <Link className="btn btn-outline-secondary" to="/postlist"><i className="fas fa-list"></i> 글목록</Link> &nbsp;
          <Link className="btn btn-outline-secondary" to={{ pathname: `/postanswer/${post.id}` }} state={{ parentPost }}>
            <i className="fas fa-pen"></i> 답글쓰기
          </Link> &nbsp;
          <Link className="btn btn-outline-secondary" to={{ pathname: `/feedbackdetail/${post.id}` }} state={{ parentPost }}>
            <i className="fas fa-pen"></i> 답글보기
          </Link> &nbsp;
          {
            localStorage.getItem("id") === post.writer &&
            <>
              <Link className="btn btn-outline-secondary" to="/postupdate" state={{ post: updatePost }}><i className="fas fa-edit"></i> 수정</Link> &nbsp;
              <button className="btn btn-outline-danger" onClick={() => deletePost(post.id)}><i className="fas fa-trash-alt"></i> 삭제</button>
            </>
          }
        </div>

        <table className="table table-striped">
          <tbody>
            <tr>
              <th className="col-3">작성자</th>
              <td><span>{post.writer}</span></td>
            </tr>
            <tr>
              <th>제목</th>
              <td><span>{post.title}</span></td>
            </tr>
            <tr>
              <th>작성일</th>
              <td><span>{post.createdDate}</span></td>
            </tr>
            <tr>
              <th>조회수</th>
              <td><span>{post.viewCnt}</span></td>
            </tr>
            <tr>
              <th>내용</th>
              <td colSpan="3" className="content-box" style={{ whiteSpace: 'pre-line' }}>{post.content}</td>
            </tr>
          </tbody>
        </table>

        {/* 첨부 파일 리스트 */}
        <div>
          {attach && attach.files && (
            <FileDisplay files={attach.files} id={attach.id} />
          )}
        </div>

        {/* 댓글 리스트 */}
        <FeedbackList postId={post.id} />

        {/* 댓글 작성 컴포넌트 */}
        {
          (auth) ? // 로그인한 사용자만 댓글 작성 가능
          <FeedbackWrite postId={post.id} title={post.title} />
          :
            null
        }
      </div>
    );
  };

  return (
    <div className="post-detail-container">
      {post
        ? (Array.isArray(post)
          ? post.map(renderPost)
          : renderPost(post))
        : <div>Loading...</div>
      }
    </div>
  );

}

export default PostDetail;
