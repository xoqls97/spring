<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
    <div class="container-md">
    
    <h1>회원가입 페이지</h1>
    
    <form action="/member/modify" method="post">
    <div class="mb-3">
    <label for="id" class="form-labe">아이디:</label>
    <input type="text" name="id" class="form-control" id="id"  value="${ses.id }" readonly="readonly" placeholder="ID">
    </div>
        <div class="mb-3">
    <label for="pw" class="form-labe">비밀번호:</label>
    <input type="password" name="pw" class="form-control" id="pw" placeholder="비밀번호를 입력해주세요." >
    </div>
        <div class="mb-3">
    <label for="name" class="form-labe">이름:</label>
    <input type="text" name="name" class="form-control" id="name" value="${ses.name }" placeholder="Name">
    </div>
        <div class="mb-3">
    <label for="email" class="form-labe">이메일:</label>
    <input type="text" name="email" class="form-control" id="email" value="${ses.email }" placeholder="E-mail">
    </div>
        <div class="mb-3">
    <label for="home" class="form-labe">주소:</label>
    <input type="text" name="home" class="form-control" id="home"  value="${ses.home }" placeholder="Home">
    </div>
        <div class="mb-3">
    <label for="age" class="form-labe">나이:</label>
    <input type="text" name="age" class="form-control" id="age"  value="${ses.age }" placeholder="AGE">
    </div>
    <button type="submit" class="btn btn-primary">수정</button>
    
    </form>
    <a href="/member/remove?id=${ses.id }"><button type="button">삭제</button></a>
    </div>
    <jsp:include page="../layout/footer.jsp"></jsp:include>
