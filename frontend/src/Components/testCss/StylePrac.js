// [React] 리액트에서 동적으로 스타일 적용하기
// https://caddyspoon.github.io/posts/2022/10/1003_react_style/

import React, { useState } from "react";

import style from "./StylePrac.module.css"

const StylePrac = () => {
  const [isSwitched, setIsSwitced] = useState(false);
  const switchHandler = () => {
    setIsSwitced(!isSwitched);
  };

  return (
    <div>
      <h1 className={`${style["header-text"]} ${isSwitched && style["switched"]}`}>
        Make our world colourful!
      </h1>
      <button className={style["StylePrac__button"]} onClick={switchHandler}>Switch</button>
    </div>
  );
};

export default StylePrac;
