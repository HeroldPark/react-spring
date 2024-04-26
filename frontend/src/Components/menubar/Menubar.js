import React, { useState } from 'react';

function Menubar() {

	console.log("[Menubar.js] render()");

	const [isOpen, setIsOpen] = useState(false);

	const toggleSubMenu = (e) => {
		e.preventDefault();
		setIsOpen(!isOpen);
	};
	
	return (
		<nav>
			<ul>	{/* href로 등록된 정보는 Router.js에 정의되어야 한다. */}
				<li className={`has_sub ${isOpen ? 'open' : ''}`}>
					<a href="#" className={isOpen ? 'on' : ''} onClick={toggleSubMenu}> <span>게시판 관리</span></a>
					<ul>
						<li><a href="/bbslist" className={isOpen ? 'on' : ''}>게시판</a></li>
						<li><a href="/employees" className={isOpen ? 'on' : ''}>샘플</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>갤러리형</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>카렌다형</a></li>
					</ul>
				</li>
				<li className={`has_sub ${isOpen ? 'open' : ''}`}>
					<a href="#" className={isOpen ? 'on' : ''} onClick={toggleSubMenu}> <span>웹서버 관리</span></a>
					<ul>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>사용자</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>코드</a></li>
						<li><a href="#" onClick={() => alert('준비 중입니다.')}>이력</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	);
}

export default Menubar;