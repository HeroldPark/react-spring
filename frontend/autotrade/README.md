## Autotrade system

1. This script demonstrates how to use the SerpApi to get Google search results using the GoogleSearch class.
    C:\Program Files\Python312\python.exe
    pip install google-search-results
    usage : python serpApiExample.py

2. serpAPI needs

    - test 방법
    1) api_key.txt 파일을 여기에 가져다 놓는다.(all 파일 내용은 카톡에 있다.)
    OPENAI_API_KEY="sk-6BXB62E4gvBTMZiVRSjAT3BlbkFJ5Aee7pTrIS2dm....."
    UPBIT_ACCESS_KEY="YourKey"
    UPBIT_SECRET_KEY="YourKey"
    SERPAPI_API_KEY="a476ac7b83c15024ff765e9928183545e7d9cb09c2961c4ff6408da09......."

    2) serpApiExample.py에서  아래와 같이 SERPAPI_API_KEY를 fully 입력한다.
    "api_key": "a476ac7b83c15024ff765e9928183545e7d9cb09c2961c4ff6408da09......."

    3) python serpApiExample.py 실행하면 화면에 아래와 같이 출력된다.
    params에 입력한 "Coffee"에 대한 검색 결과가 출력된다.
    C:\Program Files\Python312\python.exe
    {'search_metadata': {'id': '667f51fbca968f998c9c970b', 'status': 'Success', ....

    4) 비트코인 거래까지 하려면 UPBIT 관련 KEY값도 api_key.txt에 입력해야 한다.

3. UPBIT API 발급
    - Open API 관리 : 자산조회, 주문조회, 주문하기
    - Open API Key 발급받기 : Google에서 What's my IP ? 222.112.19.116(Home), 3.36.92.27(centos), 3.38.214.4(ubuntu)
    - 222.112.19.116,220.76.218.152,3.38.214.4,3.34.141.109
    - 카카오 페이 인증하기
    Access key = "PRxRGT50Add4NHtzmUkLXIh8HM450T6kwgg...."
    Secret key = "ghhEXwWUWjOwCKNhBSiepYPAxaAtacVip......"


4. 사전 준비 강의
    - python 설치
    - UPBIT API 키 발급 & 다운로드(README.md)
    - test.py

5. YouTube 강의(1강)
    - OpenAI API 다운로드
    - AWS 가입 : EC2 python만 사용
    - 챗GPT 성능 높이는 26가지 개꿀팁(유튜브)
    - 랭체인으로 수익형 AI 웹서비스 만들기 with ChatGPT, LLaMA, RAG(유튜브)
    - OpenAI API Key : 등록 후 신용카드까지 등록해야 API로 조회 가능하다.
    - 등록 후 한시간 가까이 지나니 동작하더라.
    - 2:59:30 => 인스턴스 시작
    - sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
    - sudo apt update
    - sudo apt upgrade
    - python3 --version
        Python 3.12.3   # 기본으로 설치되어 있다.
    - sudo apt install python3-pip
    - git clone https://github.com/youtube-jocoding/gpt-bitcoin.git
  
    - 아래 명령어로 하면 오류 발생한다.
    - pip3 install -r requirements.txt
    - 그래서 다음과 같이 한다.
        # 가상환경 생성(2회 반복)
        python3 -m venv venv

        # 가상환경 활성화
        source venv/bin/activate

        # 그리고 나서 아래 실행한다. 그러면 가상환경(venv에 설치된다.
        pip install -r requirements.txt

        # sample 코드 테스트
        (venv) ubuntu@ip-172-31-0-151:~/gpt-bitcoin$ python3 test.py
        gpt-4o-2024-05-13
        Hello! How can I assist you today?
        UPBIT Balance:  0

    - fetch data and prepare data
    => charGPT에게 도움 요청 : panads_ta(tachnical anasysis)
    => 한달 + 24시간 데이터를 만들고 그걸 pandas로 합치고 json으로 제작
    1) 시장 상황(역할, 예시 중요)
    2) 나의 현재 투자 상황
    3) Technical Indicator Glossary
    4) Instruction Work flow

    - code 전체구조 설명(2:11:55)
    - 
    AWS 설정
    - MFA device name: shanepark@aws-ec2-user
    - MFA device : 인증 관리자 앱 : Google Authenticator(폰에서 마지막 숫자 6개 입력하면 된다.)
    - 로그인 할때 password + 인증 코드 (6자리 숫자)
    - AWS에서 로그인 후 source myenv/bin/activate 하여 library를 활성화 시켜야 한다.
    - 그리고 나서 python3로 python file을 실행해야 한다.
    - python3 test.py

    - pip3 install -r requirements.txt --break-system-packages

    nohup python3 -u autotrade_v3.py > output.log 2>&1
  
    

6. YouTube 강의(2강)

7. YouTube 강의(3강)

8. YouTube 강의(4강)
    - 거래소 가격 데이터는 이미 입력되고 있다.
    - Serp API : 1달 동안 가져오는 양 때문에 하루 3번 거래로 제한한다.
    - upbit 차트 이미지 캡쳐 => 인식 => 추세를 참고하기 위해서.
    - rust 써야함.
    - 셀레니움으로 스크린 캡쳐 : pip install selenium
    - 사이트 접속 => 트레이드 버튼 클릭 => 
    - 이전 강의 : jocondig.net/bitcoin
    - 박대건 : 에드 세이코다 추종세력 전략을 카피한 전략
    - 전략 : 프롬프트 답변을 기반으로 매수, 매도, 유보를 정해서 투자할 예정이라는 점도 이력하면 좋겠다
    - Take deep breath and Think step by step

    - AWS Server(강좌 :Youtube 4:51:00 부터)
    - 저렴한 클라우드 서버(VULTR) : 8200원/월 => 5달러로 가능
    - AWS t4g.nano : 3.8달러

    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
    sudo apy install ./google-chrome-stable_current_amd64.deb

    Install Chrome Driver

9. 실행 오류
    1) pip3 install -r requirements.txt
    seleniumubuntu@ip-172-31-0-151:~/gpt-bitcoin$ pip3 install -r requirements.txt
    error: externally-managed-environment

    × This environment is externally managed
    ╰─> To install Python packages system-wide, try apt install
        python3-xyz, where xyz is the package you are trying to
        install.
        
        If you wish to install a non-Debian-packaged Python package,
        create a virtual environment using python3 -m venv path/to/venv.
        Then use path/to/venv/bin/python and path/to/venv/bin/pip. Make
        sure you have python3-full installed.
        
        If you wish to install a non-Debian packaged Python application,
        it may be easiest to use pipx install xyz, which will manage a
        virtual environment for you. Make sure you have pipx installed.
        
        See /usr/share/doc/python3.12/README.venv for more information.

    note: If you believe this is a mistake, please contact your Python installation or OS distribution provider. You can override this, at the risk of breaking your Python installation or OS, by passing --break-system-packages.
    hint: See PEP 668 for the detailed specification.
    => venv 가상 환경 사용하여 설치

    2) 
    (venv) ubuntu@ip-172-31-0-151:~/gpt-bitcoin$ python3 autotrade_v3.py
    Traceback (most recent call last):
    File "/home/ubuntu/gpt-bitcoin/autotrade_v3.py", line 6, in <module>
        import pandas_ta as ta
    File "/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/__init__.py", line 7, in <module>
        from pkg_resources import get_distribution, DistributionNotFound
    ModuleNotFoundError: No module named 'pkg_resources'
    => pip3 install setuptools

    3)
    (venv) ubuntu@ip-172-31-0-151:~/gpt-bitcoin$ python3 autotrade_v3.py
    Traceback (most recent call last):
    File "/home/ubuntu/gpt-bitcoin/autotrade_v3.py", line 6, in <module>
        import pandas_ta as ta
    File "/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/__init__.py", line 116, in <module>
        from pandas_ta.core import *
    File "/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/core.py", line 18, in <module>
        from pandas_ta.momentum import *
    File "/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/momentum/__init__.py", line 34, in <module>
        from .squeeze_pro import squeeze_pro
    File "/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/momentum/squeeze_pro.py", line 2, in <module>
        from numpy import NaN as npNaN
    ImportError: cannot import name 'NaN' from 'numpy' (/home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/numpy/__init__.py). Did you mean: 'nan'?
    
    => nano /home/ubuntu/gpt-bitcoin/venv/lib/python3.12/site-packages/pandas_ta/momentum/squeeze_pro.py
    => from numpy import nan as npNaN

    4)
    (venv) ubuntu@ip-172-31-0-151:~/gpt-bitcoin$ python3 autotrade_v3.py
    Making decision and executing...
    Error making current image: Message: Unable to obtain driver for chrome; For documentation on this error, please visit: https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/driver_location

    Error: cannot access local variable 'driver' where it is not associated with a value
    ^CTraceback (most recent call last):
    
    =>
    wget https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip
    unzip chromedriver_linux64.zip
    sudo mv chromedriver /usr/local/bin/
    sudo chmod +x /usr/local/bin/chromedriver

    pip3 install selenium

    => autotrade_v3.py 수정
    ```
    from selenium import webdriver
    from selenium.webdriver.chrome.service import Service
    from webdriver_manager.chrome import ChromeDriverManager

    # ChromeDriver 경로 설정
    service = Service('/usr/local/bin/chromedriver')
    driver = webdriver.Chrome(service=service)
    ```

