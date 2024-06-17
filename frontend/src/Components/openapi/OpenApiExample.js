import React, { useState, useEffect } from 'react';
import axios from 'axios';

const OpenApiExample = () => {
  const [accessToken, setAccessToken] = useState('none');
  const [errCnt, setErrCnt] = useState(0);
  const [geoData, setGeoData] = useState([]);

  const serviceName = process.env.REACT_APP_OPENAPI_SERVICE_NAME;
  const serviceId = process.env.REACT_APP_OPENAPI_SERVICE_ID;
  const secretKey = process.env.REACT_APP_OPENAPI_SECRET_KEY;

  useEffect(() => {
    getAccessToken(serviceId, secretKey);
  }, [serviceId, secretKey]);

  const getAccessToken = async (serviceId, secretKey) => {
    try {
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json', {
        params: {
          consumer_key: serviceId,
          consumer_secret: secretKey
        },
      });
      setErrCnt(0);
      const token = response.data.result.accessToken;
      setAccessToken(token);
      alert("accessToken: " + token);
      geoCode(token);
    } catch (error) {
      console.error('Error fetching access token:', error);
    }
  };

  const geoCode = async (token) => {
    const address = encodeURIComponent('대전광역시 서구 청사로 189');
    const pagenum = '0';
    const resultcount = '5';

    try {
      const response = await axios.get('https://sgisapi.kostat.go.kr/OpenAPI3/addr/geocode.json', {
        params: {
          accessToken: token,
          address,
          pagenum,
          resultcount,
        },
      });

      switch (parseInt(response.data.errCd)) {
        case 0:
          setGeoData(response.data.result.resultdata);
          break;
        case -401:
          setErrCnt((prevErrCnt) => {
            const newErrCnt = prevErrCnt + 1;
            if (newErrCnt < 200) {
              getAccessToken();
            }
            return newErrCnt;
          });
          break;
        case -100:
          console.error('Error -100:', response.data.errMsg);
          break;
        default:
          console.error('Unexpected error code:', response.data.errCd);
      }
    } catch (error) {
      console.error('Error fetching geocode data:', error);
    }
  };

  return (
    <div>
      <div id="chart" style={{ width: '100%', height: '500px' }}>
        {geoData.length > 0 ? (
          <ul>
            {geoData.map((data, index) => (
              <li key={index}>
                X: {data.x}, Y: {data.y}
              </li>
            ))}
          </ul>
        ) : (
          <p>No geocode data available</p>
        )}
      </div>
    </div>
  );
};

export default OpenApiExample;
