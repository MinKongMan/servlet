
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


### HTTP 요청 데이터 - GET 파라미터
1. 메시지 바디 없이 URL의 '쿼리 파라미터'를 사용해서 데이터를 전달하자.
- ex) 검색, 필터, 페이징 등에서 많이 사용됨
2. 쿼리 파라미터는 URL에 ?를 시작으로 보낼 수 있다. 추가 파라미터는 &로 구분해야 한다.
- ex) ~~/request-param?username=hello&age=20
3. 서버에서는 HttpServletRequest가 제공하는 메서드를 통해 쿼리 파라미터를 편리하게 조회할 수 있다.
4. 복수 파라미터로 값이 넘어올 때 getParameterValues()로 받아줘야함.


### HTTP 요청 데이터 - POST HTML Form
1. 메시지 바디에 쿼리 파라미터 형식으로 데이터를 전달한다.(키, 밸류값)
2. GET 방식은 메시지 바디가 없기 때문에 content-type이 존재하지 않는다.
3. POST 방식은 메시지 바디를 사용하기 때문에 바디에 포함된 데이터가 어떤 형식인지 content-type을 지정해야 한다.

# 회원 관리 앱 요구사항
- 회원 정보
> > 이름 : username<br/>
> > 나이 : age<br/>
- 기능 요구사항
1. 회원 저장
2. 회원 목록 조회


# HttpServletResponse - 기본 사용법
### HttpServletResponse 역할
1. Http 응답 메시지 생성
- Http 응답 코드 지정
- 헤더 생성
- 바디 생성
2. 편의 기능 제공
- ContentType, 쿠키, Redirect


# JSP - 기본 사용법
```
    라이브러리 추가
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
    implementation 'javax.servlet:jstl' 
    
    페이지 가장 위에 작성
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

### HttpServlet과 JSP의 한계
1. 서블릿으로 개발할 때는 뷰화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여 복잡했음.
2. 하지만 JSP를 사용하면서 뷰를 생성하는 HTML 작업을 깔끔하게 가져가고, 중간중간 동적으로 필요한 부분에만 자바 코드 삽입
3. 하지만 결국 JSP도 뷰와 비즈니스 로직을 한 페이지에 작성하여 결과적으로 유지보수를 하기 힘들어짐
4. 위의 이유로 MVC 패턴 등장


# MVC 패턴
- Model : 뷰에 출력할 데이터를 담아둔다. 뷰가 필요한 데이터를 모두 모델에 담아서 전달해주는 덕분에 뷰는 비즈니스 로직이나 데이터 접근을 몰라도 되고, 화면 렌더링에만 집중할 수 있게 된다.
- Controller : Http 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행한다. 그리고 뷰에 전달할 결과 데이터를 조회해서 Model에 담는다.
- View : 모델에 담겨 있는 데이터를 사용해서 화면에 표출한다. 
```
    redirect vs forward
    리다이렉트는 실제 클라이언트 응답이 나갔다가 클라이언트가 redirect 경로로 다시 요청한다. 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다.
    반면에 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 인지하지 못한다.
```
### v1 프론트 컨트롤러 도입
1. 각 컨트롤러에 매핑시킬 매핑 정보를 따로 담아둠(어떤 URL은 어떤 컨트롤러에 매핑되어야 함)
2. 매핑 정보를 바탕으로 컨트롤러 호출
3. 컨트롤러에서 JSP forward를 통해 JSP를 호출하여 HTML응답을 내려줌

### v2 
1. 컨트롤러에서 바로 JSP 를 forward하지 않고 Controller에서 Front Controller에 My View 객체를 반환을 함
2. Front Controller가 My View를 렌더링 하면 My View가 JSP를 forward하도록 설정