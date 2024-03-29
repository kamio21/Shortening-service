# 소개
URL을 입력하면 짧게 줄여주고, 줄여진 URL 접근하면 원래 URL로 리다이렉트하는 서비스

# 문제해결 전략
- URL을 입력하면 DB에 1씩 증가는 숫자를 아이디로 가지는 url을 저장
- 아이디(10진수)를 62진법을 활용하여 영문소문자/대문자, 숫자0-9로 이루어진 short URL로 줄여 제공 (218,340,105,584,896 번째 URL까지 8자리 이내 고유한 줄임값을 제공할 수 있음)
- URL을 입력했을 때 DB에 이미 있는 URL이면 해당 URL 정보를 가져와 아이디 값을 얻고, 아이디 값을 62진법으로 계산하여 제공 (DB URL은 unique 키가 걸려 있음)

# 실행 방법 명시
- 어플리케이션 실행 ```Srping Boot Run```
- host 파일에 ```127.0.0.1 sso.so``` 설정
	- MAC : \etc\hosts
	- Windows : C:\Windows\System32\drivers\etc\hosts
- 첫페이지 이동 
	- sso.so 주소창에 입력
- DB조회
	- http://localhost/h2-console
	
# ScreenShot
- 첫 페이지
<br/><img src="screenshot1.png"/>
- 줄임 URL 구하기
<br/><img src="screenshot2.png"/>
- 정상적인 URL을 입력하지 않은 경우
<br/><img src="screenshot3.png"/>
- 정상적인 줄임 URL이 아닌 경우
<br/><img src="screenshot4.png"/>