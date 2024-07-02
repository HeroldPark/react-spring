import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useParams, useNavigate, Link, useLocation } from "react-router-dom";
import FeedbackWrite from "../feedback/FeedbackWrite";
import FeedbackList from "../feedback/FeedbackList";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/pictureview.css";
import FileDisplay from "../file/FileDisplay";

// 이 버젼은 동영상 파일이 크지 않을때는 가능하다.
// 파일이 커지면 서버에서 URL을 알려주는 방식 또는 스트리밍 서버를 사용해야 한다.
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

  const getFileType = (fileName) => {
    const extension = fileName.split('.').pop().toLowerCase();
    if (['jpg', 'jpeg', 'png', 'gif'].includes(extension)) return 'image';
    if (['mp4', 'webm', 'ogg'].includes(extension)) return 'video';
    return 'unknown';
  };

  useEffect(() => {
    if (decodedFilePath) {
      getPictureView(decodedFilePath);
    }
  }, [decodedFilePath]);

  useEffect(() => {
    if (picture && picture.filePath) {
      loadFileData(picture.filePath);
    }
  }, [picture]);

  const getPictureView = async (filePath) => {
    try {
      const params = { filePath: filePath };  // "picture/john-lee.jpg"
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

  const loadFileData = async (filePath) => {
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
      console.error("Error fetching file:", error);
    }
  };

  const renderPicture = (picture) => {
    const fileType = getFileType(picture.fileName);
  
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
            <h2>파일 보기</h2>
            {fileType === 'image' && (
              <img 
                src={`data:image/jpeg;base64,${imageData}`} 
                alt="Picture" 
                style={{maxWidth: '100%', height: 'auto'}}
              />
            )}
            {fileType === 'video' && (
              <video controls style={{maxWidth: '100%', height: 'auto'}}>
                <source src={`data:video/mp4;base64,${imageData}`} type="video/mp4" />
                Your browser does not support the video tag.
              </video>
            )}
            {fileType === 'unknown' && (
              <p>Unsupported file type</p>
            )}
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
