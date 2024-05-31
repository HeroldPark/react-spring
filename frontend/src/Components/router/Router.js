import { Routes, Route } from "react-router-dom";

import Home from "../app/Home"
import BbsList from "../bbs/BbsList"
import BbsWrite from "../bbs/BbsWrite"
import BbsDetail from "../bbs/BbsDetail"
import BbsUpdate from "../bbs/BbsUpdate"
import BbsAnswer from "../bbs/BbsAnswer"

import Join from "../member/Join"
import Login from "../member/Login"
import Logout from "../member/Logout"
import Update from "../member/MemberUpdate"
import MemberUpdate from "../member/MemberUpdate";
import CheckPwd from "../member/CheckPwd";

import EmployeeList from "../employee/EmployeeList"
import MemberList from "../member/MemberList"

import PictureList from "../picture/PictureList"

import PostList from "../post/PostList"
import PostWrite from "../post/PostWrite"
import PostDetail from "../post/PostDetail"
import PostUpdate from "../post/PostUpdate"
import PostAnswer from "../post/PostAnswer"

import FeedbackList from "../feedback/FeedbackList"
import FeedbackWrite from "../feedback/FeedbackWrite"
import FeedbackDetail from "../feedback/FeedbackDetail"
import FeedbackUpdate from "../feedback/FeedbackUpdate"


import CallBackGoogle from "../member/CallBackGoogle"

function Router() {

	console.log("[Router.js] render()");
	
	return (
		<Routes>
			<Route path="/" element={<Home />}></Route>

			<Route path="/bbslist" element={<BbsList />}></Route>
			<Route path="/bbswrite" element={<BbsWrite />}></Route>
			<Route path="/bbsdetail/:boardId" element={<BbsDetail />}></Route>
			<Route path="/bbsupdate" element={<BbsUpdate />}></Route>
			<Route path="/bbsanswer/:parentSeq" element={<BbsAnswer />}></Route>

			<Route path="/login" element={<Login />}></Route>
			<Route path="/join" element={<Join />}></Route>
			<Route path="/checkpwd" element={<CheckPwd />}></Route>
			<Route path="/update" element={<MemberUpdate />}></Route>
			<Route path="/logout" element={<Logout />}></Route>

			<Route path="/employees" element={<EmployeeList />}></Route>
			<Route path="/member" element={<MemberList />}></Route>

			<Route path="/PictureList" element={<PictureList />}></Route>

            <Route path="/postlist" element={<PostList />}></Route>
			<Route path="/postwrite" element={<PostWrite />}></Route>
			<Route path="/postdetail/:id" element={<PostDetail />}></Route>
			<Route path="/postupdate" element={<PostUpdate />}></Route>
			<Route path="/postanswer/:parentSeq" element={<PostAnswer />}></Route>

			<Route path="/feedbacklist" element={<FeedbackList />}></Route>
			<Route path="/feedbackwrite" element={<FeedbackWrite />}></Route>
			<Route path="/feedbackdetail/:id" element={<FeedbackDetail />}></Route>
			<Route path="/feedbackupdate" element={<FeedbackUpdate />}></Route>

			{/* <Route path="/oauth-response/:token/:expirationTime" element={<OAuth />}></Route> */}
			<Route path="/googlecallback" element={<CallBackGoogle />}></Route>
		</Routes>
	);
}

export default Router;