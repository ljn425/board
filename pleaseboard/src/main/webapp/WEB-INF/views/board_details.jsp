<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>MVC Model2 Board</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	.groove{
		border-style: groove;
	}
	.content{
		height: 300px;
	}
	.textBar{
		width: 100px;
	}
	
	#reply_btn{
	    height: 80px;
	    margin-bottom: 48px;
	    padding-bottom: 6px;
	    border-bottom-width: 10px;	
	}
</style>
</head>
<body>
<div >
 <div ></div>
  <div >
	<h1>BOARD VIEW</h1>
	<div class="groove">
     	<div class="bg-info"><h4>제목 : ${board.board_title}</h4></div>
    	    <div>게시판 번호 : ${boardNo}</div>                
         <div>작성자 : ${board.board_user}</div>
         <div>작성일 : ${board.board_date}</div>
         <div>조회수 : ${board.board_count}</div>
         <div>첨부파일 : ${board.board_file}</div>
	     </div>
	     <div class="groove content">     
	         <div>내용 :</div>
	         <div class="text-left">${board.board_content}</div>
	     </div>
	      
         <div class="text-right">
         	 <a href="${pageContext.request.contextPath}/boardAdd"><button type="button" class="btn btn-default">글쓰기</button></a>
             <a href="${pageContext.request.contextPath}/boardUpdate?boardNo=${boardNo}"><button type="button" class="btn btn-default">수정</button></a>
             <a href="${pageContext.request.contextPath}/boardDelete?boardNo=${boardNo}"><button type="button" class="btn btn-default">삭제</button></a>
        	 <a href="${pageContext.request.contextPath}/boardList"><button type="button" class="btn btn-default">목록</button></a>
         </div>   
         <hr/>
         <div>
         	댓글 수 : ${totalReply}
         </div>
         <div class="groove">
         <c:forEach var="reply" items="${replylist}">
         	<div>${reply.re_user} ${reply.re_date}</div>
         	<div>${reply.re_content}</div>
         	<hr/>
         </c:forEach>
         </div>
         <hr/>
         <form action="${pageContext.request.contextPath}/boardReply" method="post">
         <input type="hidden" name="board_no" value="${boardNo}">
         <span>이름 <input class="textBar" name="re_user" type="text"/> </span>
	     <span>비밀번호 <input class="textBar" name="re_pw" type="password"/></span>
          <div>
	     	<textarea name="re_content" rows="3" cols="50"></textarea><button id="reply_btn" type="submit" class="btn btn-default">답변</button>
	     </div>
	     		
	     </form>    
	</div>
</div>
</body>
</html>