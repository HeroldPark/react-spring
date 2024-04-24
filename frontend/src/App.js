import { BrowserRouter } from "react-router-dom";

import Header from "./Components/app/Header"
import Nav from "./Components/app/Nav"
import Main from "./Components/app/Main"
import Footer from "./Components/app/Footer"
import AuthProvider from "./Components/context/AuthProvider"
import HttpHeadersProvider from "./Components/context/HttpHeadersProvider";
import "./css/style.css"
import "./css/main.css"


// 아인 파일을 이용하여 왼쪽 메뉴를 만들어보자.
import "./static/css/button.css";
import "./static/css/common.css";
import "./static/css/content.css";
import "./static/css/default.css";
import "./static/bootstrap-custom-styles.scss";

import $ from 'jquery';
import './static/js/jquery-3.6.0.min.js';
import './static/js/function.js';
import './static/js/common.js';

function App() {

  return (
    <div>
      <BrowserRouter>
        <AuthProvider>
          <HttpHeadersProvider>
              <Header />
              <div id="container">
				        <div className="menu_toggle"><span></span></div>
                <Nav />
                <Main />
              </div> {/* #container */}
              <Footer />
          </HttpHeadersProvider>
        </AuthProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
