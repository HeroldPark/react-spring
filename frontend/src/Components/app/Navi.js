import Menubar from "../menubar/Menubar"

import { useContext } from "react";
import React, { useState } from 'react';
import { AuthContext } from "../context/AuthProvider";

function Navi() {
	console.log("[Navi.js] render()");
	return (
		<>
			{/* 좌측 영역 */}
			<div className="lcontent">
				<div className="py-4">
					<div className="container">
						<Menubar></Menubar>
					</div>
				</div>
			</div>
		</>
	);
}

export default Navi;