import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate, Link, useLocation } from "react-router-dom";
import FeedbackWrite from "../feedback/FeedbackWrite";
import FeedbackList from "../feedback/FeedbackList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/picturedetail.css";
import FileDisplay from "../file/FileDisplay";

function PictureDetail() {
  console.log("[PictureDetail.js] render()");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth } = useContext(AuthContext);
  const [account, setAccount] = useState('');
  const [picture, setPicture] = useState(null);  // 일반 객체인 경우와 배열인 경우를 구분하기 위해 null로 초기화
  const [attach, setAttach] = useState(null); // 첨부 파일
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setAccount(auth.split('@')[0]);
  }, [auth]);

  useEffect(() => {
    if (location.state && location.state.picture) {
      setPicture(location.state.picture);
    } else {
      getPictureDetail();
    }
  }, [location.state]);

  
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}`
    }
  });

  const getPictureDetail = async () => {
    try {
      const response = await axiosInstance.get(`/picture/detail.do/${id}`);

      console.log("[PictureDetail.js] getPictureDetail() success :D");
      console.log(response.data);

      setPicture(response.data);
    } catch (error) {
      console.log("[PictureDetail.js] getPictureDetail() error :<");
      console.error(error);
    }
  };

  const deletePicture = async (pictureId) => {
    try {
      const response = await axiosInstance.delete(`/picture/${pictureId}/delete.do`, { headers });

      console.log("[PictureDetail.js] delete() success :D");
      console.log(response.data);

      if (response.status === 200) {
        alert("게시글을 성공적으로 삭제했습니다 :D");
        if (Array.isArray(picture)) {
          setPicture(picture.filter(p => p.id !== pictureId));
        } else {
          navigate("/picturelist");
        }
      }
    } catch (error) {
      alert("게시글을 삭제 샐패 :<");
      console.log("[PictureDetail.js] delete() error :<");
      console.error(error);
    }
  };

  const renderPicture = (picture) => {
    const updatePicture = {
      id: picture.id,
      writer: picture.writer,
      title: picture.title,
      content: picture.content,
      // files: picture.files
      files: attach && attach.files ? attach.files : null
    };

    const parentPicture = {
      id: picture.id,
      writer: picture.writer,
      title: picture.title,
    };

    return (
      <div key={picture.id}>
        <div className="my-3 d-flex justify-content-end">
          <Link className="btn btn-outline-secondary" to="/picturelist"><i className="fas fa-list"></i> 글목록</Link> &nbsp;
          {
            // localStorage.getItem("id") === picture.writer &&
            account === picture.writer &&
            <>
              <Link
                className="btn btn-outline-secondary"
                to="/pictureupdate"
                state={{ picture: updatePicture }}
              >
                <i className="fas fa-edit"></i> 수정
              </Link> &nbsp;
              <button className="btn btn-outline-danger" onClick={() => deletePicture(picture.id)}><i className="fas fa-trash-alt"></i> 삭제</button>
            </>
          }
        </div>

        <table className="table table-striped">
          <tbody>
            <tr>
              <th className="col-3">작성자</th>
              <td><span>{picture.writer}</span></td>
            </tr>
            <tr>
              <th>제목</th>
              <td><span>{picture.title}</span></td>
            </tr>
            <tr>
              <th>작성일</th>
              <td><span>{picture.createdDate}</span></td>
            </tr>
            <tr>
              <th>조회수</th>
              <td><span>{picture.viewCnt}</span></td>
            </tr>
            <tr>
              <th>내용</th>
              <td colSpan="3" className="content-box" style={{ whiteSpace: 'pre-line' }}>{picture.content}</td>
            </tr>
          </tbody>
        </table>

        {/* 첨부 파일 리스트 */}
        {/* <div>
          {attach && attach.files ? (
            <FileDisplay files={attach.files} id={attach.id} />
          ) : (
            <p>첨부 파일이 없습니다.</p>
          )}
        </div> */}
      </div>
    );
  };

  return (
    <div className="picture-detail-container">
      {picture
        ? (Array.isArray(picture)
          ? picture.map(renderPicture)
          : renderPicture(picture))
        : <div>Loading...</div>
      }
    </div>
  );

}

export default PictureDetail;
