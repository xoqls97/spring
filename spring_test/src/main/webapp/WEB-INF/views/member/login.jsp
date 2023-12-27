<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp"></jsp:include>
<h1>Member login Page</h1>
<form action="/member/login" method="post">
 <label for="id" class="form-labe">아이디:</label>
    <input type="text" name="id" class="form-control" id="id" placeholder="ID">
        <div class="mb-3">
    <label for="pw" class="form-labe">비밀번호:</label>
    <input type="password" name="pw" class="form-control" id="pw" placeholder="ID">
    </div>
 <button type="submit" class="btn btn-primary">로그인</button>
</form>
<jsp:include page="../layout/footer.jsp"></jsp:include>