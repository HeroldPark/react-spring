import React, { useState } from 'react';

function Menubar() {

	console.log("[Menubar.js] 시작");

	const [isOpenBbs, setIsOpenBbs] = useState(true);
	const [isOpenImageManager, setIsOpenImageManager] = useState(true);
	const [isOpenWebManager, setIsOpenWebManager] = useState(true);
	const [isOpenTestManager, setIsOpenTestManager] = useState(true);

	const toggleSubMenuBbs = (e) => {
		e.preventDefault();
		setIsOpenBbs(!isOpenBbs);
		setIsOpenWebManager(false); // 다른 메뉴의 상태 변경
	};
	
	const toggleSubMenuImageManager = (e) => {
		e.preventDefault();
		setIsOpenImageManager(!isOpenImageManager);
		setIsOpenBbs(false); // 다른 메뉴의 상태 변경
	};

	const toggleSubMenuWebManager = (e) => {
		e.preventDefault();
		setIsOpenWebManager(!isOpenWebManager);
		setIsOpenBbs(false); // 다른 메뉴의 상태 변경
	};

	const toggleSubMenuTestManager = (e) => {
		e.preventDefault();
		setIsOpenTestManager(!isOpenTestManager);
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
				<li className={`has_sub ${isOpenImageManager ? 'open' : ''}`}>
					<a href="#" className={isOpenImageManager ? 'on' : ''} onClick={toggleSubMenuImageManager}> <span>이미지 관리</span></a>
					<ul>
						<li><a href="/PictureList">리스트</a></li>
						<li><a href="/detailPicture">상세정보</a></li>
					</ul>
				</li>
				<li className={`has_sub ${isOpenWebManager ? 'open' : ''}`}>
					<a href="#" className={isOpenWebManager ? 'on' : ''} onClick={toggleSubMenuWebManager}> <span>웹서버 관리(MyBatis)</span></a>
					<ul>
						<li><a href="/postlist">게시판</a></li>
						<li><a href="/LoginList">계정리스트(tb_member)</a></li>
						<li><a href="/member">멤버리스트(tbl_member)</a></li>
						<li><a href="/SettingList">공통코드</a></li>
						
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>이력</a></li>
					</ul>
				</li>
				<li className={`has_sub ${isOpenTestManager ? 'open' : ''}`}>
					<a href="#" className={isOpenTestManager ? 'on' : ''} onClick={toggleSubMenuTestManager}> <span>테스트 베드</span></a>
					<ul>
						<li><a href="/OpenApi">인구통계 API</a></li>
						<li><a href="/OpenApiExample">좌표 API</a></li>
						<li><a href="/TestButton">Test CSS</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	);
}

export default Menubar;
