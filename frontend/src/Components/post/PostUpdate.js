import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useContext, useState } from "react";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

import "../../css/postupdate.css";

function PostUpdate() {
    console.log("[PostUpdate.js] 시작");

    const { headers, setHeaders } = useContext(HttpHeadersContext);
    const { auth, setAuth } = useContext(AuthContext);
    const navigate = useNavigate();

    const location = useLocation();
    const { post } = location.state;
    
    const id = post.id;
    const [title, setTitle] = useState(post.title);
    const [content, setContent] = useState(post.content);
    const [files, setFiles] = useState([]);
    const [severFiles, setSeverFiles ] = useState(post.files || []);

    const changeTitle = (event) => {
        setTitle(event.target.value);
    }

    const changeContent = (event) => {
        setContent(event.target.value);
    }

    const handleChangeFile = (event) => {
        const selectedFiles = Array.from(event.target.files).slice(0, 5); // Limit to 5 files
        const filesWithProperties = selectedFiles.map((file) => {
            return {
                file: file, // Actual file object
                fileId: null, // Initially, fileId is null for new files
                originFileName: file.name // Assuming you want to store the original file name
            };
        });
        setFiles((prevFiles) => [...prevFiles, ...filesWithProperties]);
    };

    const handleRemoveFile = (index) => {
        setFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
    };

    const handleRemoveSeverFile = (index, postId, fileId) => {
        setSeverFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
        fileDelete(postId, fileId);
    }

    useEffect(() => {
        setHeaders({
            "Authorization": `Bearer ${localStorage.getItem("login_access_token")}`
        });
        findAllFileByPostId(post.id);
    }, []);
    
    // 기본 설정을 포함한 axios 인스턴스 생성
    const axiosInstance = axios.create({
        baseURL: 'http://localhost:8989', // 기본 URL 설정
        headers: {
          'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
          'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
        }
    });

    /* 파일 업로드 */
    const fileUpload = async (id) => {
        // 파일 데이터 저장
        const formData = new FormData();
        formData.append('id', id);
        files.forEach((file) => formData.append('file', file.file));
    
        try {
            const resp = await axiosInstance.post(`/post/upload.do`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            console.log("[PostUpdate.js] fileUpload() success :D");
            console.log(resp.data);
            alert("게시물과 파일을 성공적으로 수정했습니다. :D");
    
            // 새롭게 등록한 글 상세로 이동
            navigate(`/postdetail/${id}`);
        } catch (err) {
            console.log("[PostUpdate.js] fileUpload() error :<");
            console.log(err);
        }
    };

    /* 첨부파일 삭제 */
    const fileDelete = async (postId, fileId) => {
        await axiosInstance.get(`/file/delete.do?id=${fileId}`)
        .then(response => {
            console.log("[PostUpdate.js] fileDelete() success :" + response.data);
            alert(response.data);
        })
        .catch(error => {
            console.error(error);
            console.error("[PostUpdate.js] fileDelete() error :<");
        });
    };

    /* 게시판 수정 */
    const updatePost = async () => {
        const formData = new FormData();
        files.forEach((file) => {
            // 파일 데이터 저장
            formData.append('multipartFiles', file.file);
        });
        const req = {
            id: post.id, 
            title: title,
            content: content,
            removeFileIds: severFiles.map(file => file.id) // Extract file IDs for removal
        }
        formData.append('params', new Blob([JSON.stringify(req)], { type: 'application/json' })); // Blob으로 변환하여 전송

        await axiosInstance.post(`/post/update.do`, formData, {headers: headers})
        .then((resp) => {
            console.log("[PostUpdate.js] updatePost() success :D");
            const lists = resp.data.list;
            const updatedPost = lists.find((item) => item.id === post.id);
            const updatedPostId = updatedPost ? updatedPost.id : null;

            if (files.length > 0 && updatedPostId) {
                fileUpload(updatedPostId);
            } else {
                alert("게시글을 성공적으로 수정했습니다 :D");
                navigate(`/postdetail/${updatedPostId}`);
            }
        })
        .catch((err) => {
            console.log("[PostUpdate.js] updatePost() error :<");
            console.log(err);
        });
    }

    // Post 전체 조회
    const findAllFileByPostId = async (postId) => {
        try {
            const response = await axiosInstance.get(`/file/${postId}`);

            console.log("[PostUpdate.js] findAllFileByPostId() success :D");
            console.log(response.data);

            setPostList(response.data.list);
        } catch (error) {
            console.log("[PostUpdate.js] findAllFileByPostId() error :<");
            console.log(error);
        }
    };


    return (
        <div>
            <table className="table">
                <tbody>
                    <tr>
                        <th className="table-primary">작성자</th>
                        <td>
                            <input type="text" className="form-control"  value={post.writerName} size="50px" readOnly />
                        </td>
                    </tr>

                    <tr>
                        <th className="table-primary">제목</th>
                        <td>
                            <input type="text" className="form-control" value={title} onChange={changeTitle} size="50px" />
                        </td>
                    </tr>

                    <tr>
                        <th className="table-primary">내용</th>
                        <td>
                            <textarea className="form-control" value={content} onChange={changeContent} rows="10" ></textarea>
                        </td>
                    </tr>
                    <tr>
                    <th className="table-primary">파일</th>
                    <td>
                    {severFiles.length > 0 || files.length > 0 ? (
                        <div className='file-box'>
                            <ul>
                                {/* 기존의 파일 데이터, 삭제 로직 */}
                                {severFiles.map((file, index) => (
                                    <li key={`server_${file.id}`} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                        <span>
                                            <strong>File Name:</strong> {file.originalName} &nbsp;
                                            <button className="delete-button" type="button" onClick={() => handleRemoveSeverFile(index, id, file.id)}>
                                                x
                                            </button>
                                        </span>
                                    </li>
                                ))}
                                {/* 새로운 파일을 저장할 때 */}
                                {files.map((file, index) => (
                                    <li key={`new_${index}`} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                        <span>
                                            <strong>File Name:</strong> {file.originalName} &nbsp;
                                            <button className="delete-button" type="button" onClick={() => handleRemoveFile(index)}>
                                                x
                                            </button>
                                        </span>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ) : (
                        <div className='file-box'>
                            <p>No files</p>
                        </div>
                    )}

                    <div className='file-select-box'>
                            <input type='file' name='file' onChange={handleChangeFile} multiple="multiple" />
                    </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <div className="my-3 d-flex justify-content-center">
                <button className="btn btn-dark" onClick={updatePost}><i className="fas fa-pen"></i> 수정하기</button>
            </div>
        </div>
    );

}

export default PostUpdate;
