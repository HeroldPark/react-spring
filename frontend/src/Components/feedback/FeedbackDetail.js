import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate, Link, useLocation } from "react-router-dom";
import FeedbackWrite from "./FeedbackWrite";
import FeedbackList from "./FeedbackList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/feedbackdetail.css";
import FileDisplay from "../file/FileDisplay";

// 답글 보기
function FeedbackDetail() {
  console.log("[FeedbackDetail.js] render()");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth } = useContext(AuthContext);
  const [feedback, setFeedback] = useState(null);	// 일반 객체인 경우와 배열인 경우를 구분하기 위해 null로 초기화
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

    if (location.state && location.state.feedback) {
      setFeedback(location.state.feedback);
    } else {
      getFeedbackDetail();
    }
  }, [location.state]);

  const getFeedbackDetail = async () => {
    try {
      const response = await axiosInstance.get(`/feedback/detail.do/${id}`);

      console.log("[FeedbackDetail.js] getFeedbackDetail() success :D");
      console.log(response.data);
      if(response.data.length == 0) { // 답글이 없을 경우
        response.data = null;
        navigate("/postlist");
      }

      setFeedback(response.data);
    } catch (error) {
      console.log("[FeedbackDetail.js] getFeedbackDetail() error :<");
      console.error(error);
    }
  };

  const deleteFeedback = async (feedbackId) => {
    try {
      const response = await axiosInstance.delete(`/feedback/${feedbackId}/delete.do`, { headers });

      console.log("[FeedbackDetail.js] deleteFeedback() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("게시글을 성공적으로 삭제했습니다 :D");
        if (Array.isArray(feedback)) {
          setFeedback(feedback.filter(p => p.id !== feedbackId));
        } else {
          navigate("/feedbacklist");
        }
      }
    } catch (error) {
      console.log("[FeedbackDetail.js] deleteFeedback() error :<");
      console.error(error);
    }
  };

  const renderFeedback = (feedback) => {
    const updateFeedback = {
      id: feedback.id,
      writer: feedback.writer,
      title: feedback.title,
      content: feedback.content,
      files: feedback.files
    };

    const parentFeedback = {
      id: feedback.id,
      writer: feedback.writer,
      title: feedback.title,
    };

    return (
      <div key={feedback.id}>
        <div className="my-3 d-flex justify-content-end">
          {/* <Link className="btn btn-outline-secondary" to="/feedbacklist"><i className="fas fa-list"></i> 글목록</Link> &nbsp;
          <Link className="btn btn-outline-secondary" to={{ pathname: `/feedbackanswer/${feedback.id}` }} state={{ parentFeedback }}>
            <i className="fas fa-pen"></i> 답글쓰기
          </Link> &nbsp;
          <Link className="btn btn-outline-secondary" to={{ pathname: `/feedbackdetail/${feedback.id}` }} state={{ parentFeedback }}>
            <i className="fas fa-pen"></i> 답글보기
          </Link> &nbsp; */}
          {
            localStorage.getItem("id") === feedback.writer &&
            <>
              <Link className="btn btn-outline-secondary" to="/feedbackupdate" state={{ feedback: updateFeedback }}><i className="fas fa-edit"></i> 수정</Link> &nbsp;
              <button className="btn btn-outline-danger" onClick={() => deleteFeedback(feedback.id)}><i className="fas fa-trash-alt"></i> 삭제</button>
            </>
          }
        </div>

        <table className="table table-striped">
          <tbody>
            <tr>
              <th className="col-3">작성자</th>
              <td><span>{feedback.writer}</span></td>
            </tr>
            <tr>
              <th>제목</th>
              <td><span>{feedback.title}</span></td>
            </tr>
            <tr>
              <th>작성일</th>
              <td><span>{feedback.createdDate}</span></td>
            </tr>
            {/* <tr>
              <th>조회수</th>
              <td><span>{feedback.detailCnt}</span></td>
            </tr> */}
            <tr>
              <th>내용</th>
              <td colSpan="3" className="content-box" style={{ whiteSpace: 'pre-line' }}>{feedback.content}</td>
            </tr>
          </tbody>
        </table>

        <div>
          <FileDisplay files={feedback.files} id={feedback.id} />
        </div>

        <FeedbackList id={feedback.id} />

        {auth && <FeedbackWrite id={feedback.id} />}
      </div>
    );
  };

  return (
    <div className="feedback-detail-container">
      {feedback
        ? (Array.isArray(feedback)
          ? feedback.map(renderFeedback)
          : renderFeedback(feedback))
        : <div>Loading...</div>
      }
    </div>
  );
}

export default FeedbackDetail;
