<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
<h1>Board Modify Page</h1>
<form action="/board/modify" method="post">
<div class="mb-3">
  <label for="Bno" class="form-label">번호</label>
  <input type="text" name="Bno" class="form-control" id="Bno" value="${bvo.bno }" readonly="readonly">
</div>

<div class="mb-3">
  <label for="title" class="form-label">제목</label>
  <input type="text" name="title" class="form-control" id="title" value="${bvo.title }" >
</div>

<div class="mb-3">
  <label for="writer" class="form-label">작성자</label>
  <input type="text" name="writer" class="form-control" id="writer" value="${bvo.writer }" readonly="readonly">
</div>

<div class="mb-3">
  <label for="reg_date" class="form-label">작성일</label>
<span class="badge text-bg-primary">${bvo.read_count }</span>
  <input type="text" name="reg_date" class="form-control" id="reg_date" value="${bvo.reg_date }" readonly="readonly">
</div>

<div class="mb-3">
  <label for="content" class="form-label">내용</label>
  <textarea name="content" class="form-control" id="content" rows="3" >${bvo.content }</textarea>
</div>

<!-- file 입력 라인 추가 -->
<div class="mb-3">
  <label for="file" class="form-label"> Files.... </label>
  <input type="file" name="files" class="form-control" id="file"  multiple="multiple" style="diplay:none">
  <button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
</div>
<!-- 파일 목록 표시 라인 -->
<div class="mb-3" id="fileZone">

</div>

<a href="/board/list"><button type="button" class="btn btn-primary"> 리스트 </button></a>
<button type="submit" class="btn btn-success" id="regBtn"> 수정 </button>
</form>
</div>
<script src="/resources/js/boardRegister.js">
	</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>