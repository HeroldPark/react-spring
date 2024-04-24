import Router from "../router/Router"
import Footer from "./Footer";
import "../../css/main.css";

function Main() {

	return (
    <>
      {/* 우측 영역 */}
      <div className="rcontent">
        <div className="py-4">
          <div className="container">
            <Router></Router>
          </div>
        </div>
      </div>
    </>
	);
}

export default Main;