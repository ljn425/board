<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MVC Model2 Board</title>
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		//console.log("하이");
		$('#searchBtn').click(function(){
			if($('#selectValue').val().length < 2){
				alert("검색어는 2자 이상 입력하셔야 합니다.");
			}else if($('#selectValue').val().length >= 2){
				$('#searchFrom').submit();
			}
		});
		
	});
</script>
</head>
<style>
	.pager .disabled>a, .pager .disabled>a:focus, .pager .disabled>a:hover, .pager .disabled>span{
		color: green;
	}
</style>
<body>
<div class="container">
<div class="row">
	<h1>게시판 목록</h1>
	<div>전체 게시물 수 : ${totalRowCount}</div>
	<table class="table table-bordered">
        <thead>
            <tr>
            	<th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>등록일</th>
                <th>조회</th>
            </tr>
        </thead>
        <tbody>
   	<c:forEach var="board" items="${boardlist}">
    		<tr>
                <td>${board.board_no}</td>
                <td><a href="${pageContext.request.contextPath}/boardView?boardNo=${board.board_no}">${board.board_title}</a></td>
                <td>${board.board_user}</td>
                <td>${board.board_date}</td>
                <td>${board.board_count}</td>
            </tr>
    </c:forEach>    
        </tbody>
    </table>
    
    <div class="text-right">
        <a href="${pageContext.request.contextPath}/boardAdd"><button id="addButton" type="button" class="btn btn-default">게시글 입력</button></a>
    </div>
    <div>
		<ul class="pager">
		<c:if test="${currentPage>0}">
            <li><a href="${pageContext.request.contextPath}/boardList?currentPage=${previousPage}"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
            <li><a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage-1}">이전</a></li>
		</c:if>

		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<c:if test="${i == currentPage}">
				<li class="disabled"><a href="#">${i}</a></li>
			</c:if>
			<c:if test="${i != currentPage}">
				<li><a href="${pageContext.request.contextPath}/boardList?currentPage=${i}">${i}</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${currentPage < lastPage}">
            <li><a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage+1}">다음</a></li>
            <li><a href="${pageContext.request.contextPath}/boardList?currentPage=${nextPage}"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
		</c:if>
		 </ul>
    </div>
    
    <!-- 검색 -->
    <form id="searchFrom" action="${pageContext.request.contextPath}/boardSearch" method="post">
	 <div class="form-group">
	 <div class="row">
	  <div class="col-sm-3">
	  </div>
	  <div class="col-sm-2">
	   <select name="so" class="form-control" id="sel1">
	    <option value="board_hap">제목+내용</option>
	    <option value="board_title">제목</option>
	    <option value="board_user">글쓴이</option>
	  </select>
	  </div>
	   <div class="col-sm-3">
	   		<input name="sv" id="selectValue" type="text" class="form-control" placeholder="검색어를 입력해주세요."/>
	   </div>
	   <div class="col-sm-4">
	   		<button type="button" id="searchBtn" class="btn btn-default">검색</button>
	   </div>
	   </div>
	</div>
	</form>
</div>
</div>
</body>
</html>