import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate, Link, useLocation } from "react-router-dom";
import FeedbackWrite from "../feedback/FeedbackWrite";
import FeedbackList from "../feedback/FeedbackList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/pictureview.css";
import FileDisplay from "../file/FileDisplay";

function PictureView() {
  console.log("[PictureView.js] render()");

  const { headers, setHeaders } = useContext(HttpHeadersContext);
  const { auth } = useContext(AuthContext);
  const [picture, setPicture] = useState(null);  // 일반 객체인 경우와 배열인 경우를 구분하기 위해 null로 초기화
  const [attach, setAttach] = useState(null); // 첨부 파일
  const navigate = useNavigate();
  const location = useLocation();
  const [imageData, setImageData] = useState(null);

  const { filePath } = useParams();
  const decodedFilePath = decodeURIComponent(filePath || '');

  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}`
    }
  });

  useEffect(() => {
    if (decodedFilePath) {
      getPictureView(decodedFilePath);
    }
  }, [decodedFilePath]);

  useEffect(() => {
    if (picture && picture.filePath) {
      loadImageData(picture.filePath);
    }
  }, [picture]);

  const getPictureView = async (filePath) => {
    try {
      const params = { filePath: filePath };
      const response = await axiosInstance.post(`/picture/view.do`, params);
  
      console.log("[PictureView.js] getPictureView() success :D");
      console.log(response.data);
  
      if (typeof response.data === 'string') {
        // 파일 경로에서 파일 이름만 추출
        const fileName = response.data.split('\\').pop();
        setPicture({ 
          filePath: response.data,
          fileName: fileName
        });
      } else {
        setPicture(response.data);
      }
    } catch (error) {
      console.log("[PictureView.js] getPictureView() error :<");
      console.error(error);
    }
  };

  const loadImageData = async (filePath) => {
    try {
      const response = await axiosInstance.post('/picture/image', null, {
        params: { filePath: filePath },
        responseType: 'arraybuffer'
      });
      const base64 = btoa(
        new Uint8Array(response.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          '',
        ),
      );
      setImageData(base64);
    } catch (error) {
      console.error("Error fetching image:", error);
    }
  };

  const renderPicture = (picture) => {
    return (
      <div key={picture.id || picture.filePath}>
        <div className="my-3 d-flex justify-content-end">
          <Link className="btn btn-outline-secondary" to="/picturelist"><i className="fas fa-list"></i> 글목록</Link> &nbsp;
          {picture.id && (
            <Link className="btn btn-outline-secondary" to={{ pathname: `/pictureanswer/${picture.id}` }} state={{ parentPicture: picture }}>
              <i className="fas fa-pen"></i> 답글쓰기
            </Link>
          )}
        </div>
        {imageData && (
          <div>
            <h2>이미지 보기</h2>
            <img 
              src={`data:image/jpeg;base64,${imageData}`} 
              alt="Picture" 
              style={{maxWidth: '100%', height: 'auto'}}
            />
          </div>
        )}
        <p>File Path: {picture.filePath}</p>
      </div>
    );
  };

  return (
    <div className="picture-view-container">
      {picture
        ? (Array.isArray(picture)
          ? picture.map(renderPicture)
          : renderPicture(picture))
        : <div>Loading...</div>
      }
    </div>
  );

}

export default PictureView;
