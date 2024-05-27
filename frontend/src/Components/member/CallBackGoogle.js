import React, { useEffect } from 'react';
import { useCookies } from 'react-cookie';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { useState, useContext } from "react";
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";

export default function CallBackGoogle() {
    console.log("[CallBackGoogle.js] CallBackGoogle() start");

    const { auth, setAuth } = useContext(AuthContext);
    const { headers, setHeaders } = useContext(HttpHeadersContext);

    // const {token, expirationTime } = useParams();
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const token = searchParams.get("token");
    const name = searchParams.get("name");
    const email = searchParams.get("email");
    const expirationTime = searchParams.get("expirationTime");

    const [cookies, setCookie] = useCookies();
    const navigate = useNavigate();

    console.log("token: ", token);
    console.log("name: ", name);
    console.log("email: ", email);
    console.log("expirationTime: ", expirationTime);

    useEffect(() => {
        if(!token || !expirationTime) { return; }

        const now = (new Date().getTime()) * 1000;
        const expires = new Date(now + Number(expirationTime));

        setCookie('accessToken', token, { path: '/', expires });

        // JWT 토큰 저장
		localStorage.setItem("bbs_access_token", token);
		localStorage.setItem("id", email);

		setAuth(email); // 사용자 인증 정보(아이디 저장)
		setHeaders({"Authorization": `Bearer ${token}`}); // 헤더 Authorization 필드 저장

        navigate('/bbslist');

    }, [token, expirationTime]);

    return (
        <div></div>
    );
}