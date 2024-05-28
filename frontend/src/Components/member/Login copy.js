/* 로그인 컴포넌트 */

import axios from "axios";
import { useState, useContext } from "react";
import { useNavigate } from "react-router";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";
import { Link } from "react-router-dom";

function Login() {
	console.log("[Login.js] Login() start");
	
	const { auth, setAuth } = useContext(AuthContext);
	const { headers, setHeaders } = useContext(HttpHeadersContext);

	const navigate = useNavigate();

	const [id, setId] = useState("admin@deltax.ai");
	const [pwd, setPwd] = useState("1");

	const changeId = (event) => {
		setId(event.target.value);
	}

	const changePwd = (event) => {
		setPwd(event.target.value);
	}

	const login = async () => {

		const req = {
			email: id,
			password: pwd,
			withCredentials: true
		}

		await axios.post("http://localhost:8989/user/login")
		.then((resp) => {
			console.log("[Login.js] login() success :D");
			console.log(resp.data);

				alert(resp.data.email + "님, 성공적으로 로그인 되었습니다 🔐");

				// JWT 토큰 저장
				localStorage.setItem("login_access_token", resp.data.token);
				localStorage.setItem("id", resp.data.email);

				setAuth(resp.data.email); // 사용자 인증 정보(아이디 저장)
				setHeaders({"Authorization": `Bearer ${resp.data.token}`}); // 헤더 Authorization 필드 저장

				navigate("/bbslist");
		})
		.catch((err) => {
			console.log("[Login.js] login() error :<");
			console.log(err);

			if (err.response) {
				// 서버로부터 응답이 도착한 경우
				alert("⚠️ " + err.response.data);
				// 오류 응답의 상태 코드와 메시지를 확인할 수 있음
				console.log("Status:", err.response.status);
				console.log("Message:", err.message);
			} else if (err.request) {
				// 서버로의 요청이 실패한 경우
				console.log("Request error:", err.request);
			} else {
				// 요청을 설정하는 과정에서 예외가 발생한 경우
				console.log("Error:", err.message);
			}
		});
	}

	/*
	const googleLogin = async () => {
		const req = {
			email: id,
			password: pwd
		};
	
		try {
			const url = "http://localhost:8989/login/oauth2/google"; // "//"를 사용하지 않음
			const resp = await axios.get(url, req, 
				{ withCredentials: true }	// 쿠키와 같은 인증 정보를 공유할 수 있도록 설정
			);
			console.log("[Login.js] googleLogin() success :D");
			console.log(resp.data);
	
			if (resp.data.token == null) {
				alert("⚠️ 계정 정보가 없습니다. 다시 시도해주세요.");
				return;
			}
			alert(resp.data.email + "님, 성공적으로 로그인 되었습니다 🔐");
	
			// JWT 토큰 저장
			localStorage.setItem("login_access_token", resp.data.token);
			localStorage.setItem("id", resp.data.email);
	
			setAuth(resp.data.email); // 사용자 인증 정보(아이디 저장)
			setHeaders({ "Authorization": `Bearer ${resp.data.token}` }); // 헤더 Authorization 필드 저장
	
			navigate("/bbslist");
		} catch (err) {
			console.log("[Login.js] googleLogin() error :<");
			console.log(err);
	
			if (err.response) {
				// 서버로부터 응답이 도착한 경우
				alert("⚠️ " + err.response.data);
				// 오류 응답의 상태 코드와 메시지를 확인할 수 있음
				console.log("Status:", err.response.status);
				console.log("Message:", err.message);
			} else if (err.request) {
				// 서버로의 요청이 실패한 경우
				console.log("Request error:", err.request);
			} else {
				// 요청을 설정하는 과정에서 예외가 발생한 경우
				console.log("Error:", err.message);
			}
		}
	}
	*/

	const googleLogin = async () => {
		const id = document.getElementById("changeId").value; // 이메일 입력란
		const pwd = document.getElementById("changePwd").value; // 비밀번호 입력란
		
		const form = document.createElement("form");
		form.method = "POST";
		form.action = "http://localhost:8989/login/oauth2/google"; // 폼의 action 속성에 요청할 URL을 설정
		
		const emailField = document.createElement("input");
		emailField.type = "hidden"; // 숨겨진 input 필드
		emailField.name = "email"; // 서버에서 기대하는 파라미터 이름
		emailField.value = id; // 사용자 입력 값
		
		const passwordField = document.createElement("input");
		passwordField.type = "hidden"; // 숨겨진 input 필드
		passwordField.name = "password"; // 서버에서 기대하는 파라미터 이름
		passwordField.value = pwd; // 사용자 입력 값
		
		form.appendChild(emailField);
		form.appendChild(passwordField);
		
		document.body.appendChild(form); // 폼을 문서에 추가
		form.submit(); // 폼 제출
	}
	

	/*
	* Create form to request access token from Google's OAuth 2.0 server.
	*/
	/*
	function googleLogin() {
		// Google's OAuth 2.0 endpoint for requesting an access token
		var oauth2Endpoint = 'https://accounts.google.com/o/oauth2/auth';
	
		// Create <form> element to submit parameters to OAuth 2.0 endpoint.
		var form = document.createElement('form');
		form.setAttribute('method', 'GET'); // Send as a GET request.
		form.setAttribute('action', oauth2Endpoint);
	
		// Parameters to pass to OAuth 2.0 endpoint.
		var params = {'client_id': '33063057275-5rtjgjlas7ia19fdrkfvrh4sm4gc92oi.apps.googleusercontent.com',
					'redirect_uri': 'http://localhost:8989/login/oauth2/code/google',
					'response_type': 'token',
					'scope': 'https://www.googleapis.com/auth/drive.metadata.readonly',
					'include_granted_scopes': 'true',
					'state': 'pass-through value'};
	
		// Add form parameters as hidden input values.
		for (var p in params) {
			var input = document.createElement('input');
			input.setAttribute('type', 'hidden');
			input.setAttribute('name', p);
			input.setAttribute('value', params[p]);
			form.appendChild(input);
		}
	
		// Add form to page and submit it to open the OAuth 2.0 endpoint.
		document.body.appendChild(form);
		form.submit();
	}
	*/

	return (
		<div>
			<table className="table">
				<tbody>
					<tr>
						<th className="col-3">아이디</th>
						<td>
							<input type="text" value={id} onChange={changeId} size="50px" />
						</td>
					</tr>

					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" value={pwd} onChange={changePwd} size="50px" />
						</td>
					</tr>
				</tbody>
			</table><br />

			<div className="my-1 d-flex justify-content-center">
				<button className="btn btn-outline-secondary" onClick={login}><i className="fas fa-sign-in-alt"></i> 로그인</button>
				<button className="btn btn-outline-secondary" onClick={googleLogin}><i className="fas fa-sign-in-alt"></i> 구글 로그인</button>
			</div>

			{/* Naver 소셜을 통한 로그인 */}
			{/*
			<Link href={`https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${CONFIG.API_KEYS.NAVER}&state=${STATESTRING}&redirect_uri=${CONFIG.DOMAIN}/auth/naver/callback`}>
				<NaverButton {...rest}>
					<Image src={naver} width={20} height={20} alt="kakao" />
					<span className="ml-2 text-white">네이버로 로그인</span>
				</NaverButton>
			</Link>
			*/}

		</div>
	);
}

export default Login;