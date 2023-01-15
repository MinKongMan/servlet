
# HTTP 요청 데이터 - 개요

### GET - 쿼리 파라미터
- /url?username=hello&age=20
- 메시지 바디 없이 URL의 쿼리 파라미터에 데이터를 포함해서 전달
- 예) 검색, 필터, 페이징등에서 많이 사용하는 방식

### POST - HTML Form
- content-type : application/x-www-form-urlencoded
- 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
- 예) 회원 가입, 상품 주문, HTML Form 사용

### HTTP message body에 데이터를 직접 담아서 요청
- Http API에 주로 사용, JSON, XML, TEXT
- 데이터 형식은 주로 JSON 사용
- POST, PUT, PATCH


# HTTP 요청 데이터 - GET 파라미터
1. 메시지 바디 없이 URL의 '쿼리 파라미터'를 사용해서 데이터를 전달하자.
- ex) 검색, 필터, 페이징 등에서 많이 사용됨
2. 쿼리 파라미터는 URL에 ?를 시작으로 보낼 수 있다. 추가 파라미터는 &로 구분해야 한다.
- ex) ~~/request-param?username=hello&age=20
3. 서버에서는 HttpServletRequest가 제공하는 메서드를 통해 쿼리 파라미터를 편리하게 조회할 수 있다.
4. 복수 파라미터로 값이 넘어올 때 getParameterValues()로 받아줘야함.


# HTTP 요청 데이터 - POST HTML Form
1. 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전달한다.(키, 밸류값)
2. GET 방식은 메시지 바디가 없기 때문에 content-type이 존재하지 않는다.
3. POST 방식은 메시지 바디를 사용하기 때문에 바디에 포함된 데이터가 어떤 형식인지 content-type을 지정해야 한다.