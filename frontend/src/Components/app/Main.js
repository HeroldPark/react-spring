import Router from "../router/Router"
import "../../css/main.css";

function Main() {
    console.log("[Main.js] render()");
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