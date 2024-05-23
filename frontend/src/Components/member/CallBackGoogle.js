import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate로 변경

const CallBackGoogle = () => {
    const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수를 가져옵니다.
    const { token, setToken } = useToken();

    const instance = axios.create({
        baseURL: 'http://localhost:8989',
        headers: {
            'Content-type': 'application/json',
        },
    });

    useEffect(() => {
        const code = new URL(window.location.href).searchParams.get('code');
        const state = new URL(window.location.href).searchParams.get('state');

        instance
            .get(`/login/oauth2/code/google?state=${state}&code=${code}`)
            .then((response) => {
                const token = {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    roles: response.data.roles,
                };
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

export default CallBackGoogle;
