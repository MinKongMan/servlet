
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %><%--JSP를 쓰기 위해 사용--%>
<%
    //request, response는 그냥 사용 가능
    // JSP도 결국 servlet으로 바뀌기 때문
    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);

    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>

</html>
