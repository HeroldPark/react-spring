import React, { useState } from 'react';

function Menubar() {

	console.log("[Menubar.js] render()");

	const [isOpenBbs, setIsOpenBbs] = useState(true);
	const [isOpenWebServer, setIsOpenWebServer] = useState(true);

	const toggleSubMenuBbs = (e) => {
		e.preventDefault();
		setIsOpenBbs(!isOpenBbs);
		setIsOpenWebServer(false); // 다른 메뉴의 상태 변경
	};
	
	const toggleSubMenuWebServer = (e) => {
		e.preventDefault();
		setIsOpenWebServer(!isOpenWebServer);
		setIsOpenBbs(false); // 다른 메뉴의 상태 변경
	};
	
	return (
		<nav>
			<ul>
				<li className={`has_sub ${isOpenBbs ? 'open' : ''}`}>
					<a href="#" className={isOpenBbs ? 'on' : ''} onClick={toggleSubMenuBbs}> <span>게시판 관리(JPA)</span></a>
					<ul>
						<li><a href="/bbslist">게시판</a></li>
						<li><a href="/employees">직원리스트</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>갤러리형</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>카렌다형</a></li>
					</ul>
				</li>
				<li className={`has_sub ${isOpenWebServer ? 'open' : ''}`}>
					<a href="#" className={isOpenWebServer ? 'on' : ''} onClick={toggleSubMenuWebServer}> <span>웹서버 관리(MyBatis)</span></a>
					<ul>
						<li><a href="/post">게시판</a></li>
						<li><a href="/member">사용자리스트</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>이력</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	);
}

export default Menubar;
