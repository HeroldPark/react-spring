import axios from "axios";
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate로 변경

const CallBackGoogle_backup = () => {
    const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수를 가져옵니다.
    const [token, setToken] = useState("0");

    // const DOMAIN = 'http://localhost:8989';
    // const API_DOMAIN = `${DOMAIN}/api/v1`;
    // const SNS_SIGN_IN_URL = (type) => `${API_DOMAIN}/auth/oauth2/${type}`;
    // const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
    // const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

    const instance = axios.create({
        baseURL: 'http://localhost:3000',
        headers: {
            'Content-type': 'application/json',
        },
    });

    useEffect(() => {
        const token = new URL(window.location.href).searchParams.get('token');

        instance
            .get(`/googlecallback?token=${token}`)
            .then((response) => {
                // const token = {
                //     accessToken: response.data.accessToken,
                //     refreshToken: response.data.refreshToken,
                //     roles: response.data.roles,
                // };
                console.log("response: ", response.data.token);
                useState(response.data.token);
                if (token) setToken(token);
            })
            .then(() => navigate('/bbslist')) // navigate 함수를 사용하여 페이지를 이동합니다.
            .catch((err) => {
                console.error(err); // 에러 핸들링을 위해 console.error를 사용합니다.
            });
    }, [history, setToken]); // useEffect의 종속성 배열에 history와 setToken을 추가합니다.

    return (
		<div>
            <h1>로그인 중...</h1>
        </div>
    );
}

export default CallBackGoogle_backup;
