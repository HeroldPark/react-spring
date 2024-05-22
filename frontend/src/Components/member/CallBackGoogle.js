import { useEffect } from 'react';

const CallBack = () => {
    const router = useRouter();
    const { setToken } = useToken();
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
            .get(`/login/oauth2/code/google?code=${code}&state=${state}`)
            .then((response) => {
                const token = {
                    accessToken: response.data.accessToken,
                    refreshToken: response.data.refreshToken,
                    roles: response.data.roles,
                };
                if (token) setToken(token);
            })
            .then(() => router.push('/bbslist'))
            .catch((err) => {
                err;
            });
      }, []);

    return (
		<div>
            <h1>로그인 중...</h1>
        </div>
    );
}
export default CallBack;
