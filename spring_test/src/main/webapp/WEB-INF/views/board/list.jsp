<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="../layout/header.jsp"></jsp:include>
    <div class="container-md">
    <h1>Board List Page</h1>
    <!-- 검색라인 -->
    <div>
    <form action="/board/list" method="get">
    <div class="input-group mb-3">
     <select name="type" class="form-select" id="inputGroupSelect02">
       <option> 선택 </option>
       <option value="t">제목</option>
       <option value="w">작성자</option>
       <option value="c">내용</option>
       <option value="tw">제목+작성자</option>
       <option value="tc">제목+내용</option>
       <option value="wc">작성자+내용</option>
       <option value="twc">제목+작성자+내용</option>
     </select>
     <input type="search" class="form-control me-2" name="keyword" placeholder="Search...">
     <input type="hidden" name="pageNo" value="1">
     <input type="hidden" name="qty" value="10">
     
     <button type="submit" class="btn btn-primary position-relative">
     Search
     
     <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
      ${ph.totalCount }
      <span class="visually-hidden"></span>
     </span>
     </button>
    </div>
    </form>
    </div>
   
    <table class="table">
    
     <thead>
       <tr>
         <th scope="col">#</th>
         <th scope="col">제목</th>
         <th scope="col">작성자</th>
         <th scope="col">시간</th>
         <th scope="col">조회수</th>
       </tr>
     </thead>
     <tbody>
     
     <c:forEach items="${list }" var="bvo">
       <tr>
         <th scope="row">${bvo.bno }</th>
           <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
           <td>${bvo.writer }</td>
           <td>${bvo.reg_date }</td>
           <td>${bvo.read_count }</td>
     
       </tr>
     </c:forEach>
     </tbody>
    </table>
    <!-- 페이지네이션바 -->
    <nav aria-label="Page navigation example">
      <ul class="pagination">
      
      
      <!-- prev -->
      <c:if test="${ph.prev }">
        <li class="page-item">
          <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
          </a>
          </li>
      </c:if>
      <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
      <!-- 페이지번호 -->
      <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
      </c:forEach>
      
      <!-- next -->
      <c:if test="${ph.next }">
      <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
      <span aria=hidden="true">&raquo;</span>
      </a>
     
      </li> 
      </c:if>
      <li class="page-item">
      <a class="page-link" href="/board/list" aria-label="Next">
      <span aria=hidden="true">전체보기</span>
      </a>
     
      </li> 
      </ul>
    </nav>
    </div>
    <script>
       const isDel = `<c:out value="${isDel}" />`;
       if(isDel ==1){
    	   alert('게시글이 삭제 됨ㅋ ');
       }
       
    </script>
    <jsp:include page="../layout/footer.jsp"></jsp:include>