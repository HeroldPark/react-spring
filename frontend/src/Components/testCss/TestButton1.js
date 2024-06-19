// [sass/scss] react-scss 사용법
// https://velog.io/@jin_jin_dev/sassscss-react-scss-%EC%82%AC%EC%9A%A9%EB%B2%95

import React from 'react';
import Button from "./components/Button";

function TestButton() {
  return (
    <>
      <h3>react-sass-scss 사용법 - Button 실습</h3>
      
      <Button size="small" color="blue">
        Button
      </Button>

      <Button size="large" color="red">
        Button
      </Button>

      <Button outline>Button</Button>

      
    </>
  );
}

export default TestButton;