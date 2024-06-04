import axios from "axios";
import React from 'react';
import "../../css/fileDisplay.css"; // 추가: 스타일 파일 import

const FileDisplay = ({ files, id }) => {
  console.log("[FileDisplay.js] 시작: ", { files, id });

  // Check if files is null or undefined, or if id exists
  if (!files || files.length === 0 || id) {
    console.log("[FileDisplay.js] files is null or undefined, or id: " + id);
    return (
      <div className='file-box'>
        <p>No files</p>
      </div>
    );
  }

  // 기본 설정을 포함한 axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989', // 기본 URL 설정
    headers: {
      'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
    }
  });

  const Download = async (postId, fileId) => {
    try {
      const request = {
        id: fileId
      }

      const response = await axiosInstance.post(`/file/${postId}/download`, request);

      console.log("[FileDisplay.js] Download() success :D");
      console.log(response.data);

      // Log all headers
      console.log("Headers: ", response.headers);

      // Create a link element to download the file
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;

      const contentDisposition = response.headers['content-disposition'];
      let fileName = 'downloadedFile';

      if (contentDisposition) {
        const matches = contentDisposition.match(/filename="?([^"]*)"?/i);
        if (matches && matches[1]) {
          fileName = matches[1];
        }
      }

      link.setAttribute('download', fileName);
      document.body.appendChild(link);
      link.click();
      link.remove();
      
    } catch (error) {
      console.log("[FileDisplay.js] Download() error :<");
      console.log(error);
    }
  };

  return (
    <div className='file-box'>
      <ul>
        {files.map((file) => (
          <li key={file.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <span>
              {/* 파일 다운로드 버튼 */}
              <div className="my-1 d-flex justify-content-center">
                <button onClick={() => Download(file.postId, file.id)}>Download</button> &nbsp;
                File Name: &nbsp;
                <strong>{file.originalName}</strong>
              </div>
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FileDisplay;
