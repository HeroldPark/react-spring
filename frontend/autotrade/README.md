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
    - Open API Key 발급받기 : Google에서 What's my IP ? 222.112.19.116
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
    - 랭체인으로 수익형 AI 웹서시브 만들기 with ChatGPT, LLaMA, RAG(유튜브)

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
