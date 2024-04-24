import { useContext } from "react";
import React, { useState } from 'react';
import { AuthContext } from "../context/AuthProvider";
import { Link } from "react-router-dom";

function Nav() {

	const { auth, setAuth } = useContext(AuthContext);

	const [isOpen, setIsOpen] = useState(false);

	const toggleSubMenu = (e) => {
		e.preventDefault();
		setIsOpen(!isOpen);
	};

	return (
		<>
			{/* 좌측 영역 */}
			<div className="lcontent">
				{/* 메뉴 */}
				<nav>
					<ul>
						<li className={`has_sub ${isOpen ? 'open' : ''}`}>
							<a href="#" className={isOpen ? 'on' : ''} onClick={toggleSubMenu}> <span>게시판 관리</span></a>
							<ul>
								<li><a href="/employees" className={isOpen ? 'on' : ''}>리스트형</a></li>
								<li><a href="#" onClick={() => alert('준비 중입니다.')}>갤러리형</a></li>
								<li><a href="#" onClick={() => alert('준비 중입니다.')}>카렌다형</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>	{/* .lcontent */}
		</>
	);
}

export default Nav;