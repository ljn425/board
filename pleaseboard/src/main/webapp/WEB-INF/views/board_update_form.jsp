<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC Model2 Board</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		console.log('헬로우');
		$('#ModifyBtn').click(function(){
			console.log('클릭');
			if($('#boardPw').val() == ''){
				alert('비밀번호를 입력해주세요');
				$('#boardPw').focus();
			}else if($('#boardTitle').val() == ''){
				alert('제목을 입력해주세요.');
				$('#boardTitle').focus();
			}else if($('#boardContent').val() == ''){
				alert('내용을 입력해주세요.');
				$('#boardContent').focus();
			}else{
				$('#boardModifyForm').submit();
			}
		});
	});
</script>
</head>
<body>
<div class="container">
<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
<h1>BOARD MODIFY</h1>
        <form id="boardModifyForm" action="${pageContext.request.contextPath}/boardUpdate" method="post">
            <div class="form-group">
				<label for="boardNo">번호 :</label>
		  		<input id="boardNo" name="board_no" value="${board.board_no}" type="text" class="form-control" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="boardPw">비밀번호 :</label>
		  		<input id="boardPw" name="board_pw" type="password" class="form-control">
			</div>
			<div class="form-group">
				<label for="boardTitle">제목 :</label>
		  		<input id="boardTitle" name="board_title" value="${board.board_title} " type="text" class="form-control">
			</div>
			<div class="form-group">
			  <label for="boardContent">내용 :</label>
			  <textarea  id="boardContent" class="form-control" name="board_content" rows="5" cols="50">${board.board_content}</textarea>
			</div>
            <div>
              	<button id="ModifyBtn" type="button" class="btn btn-default">글수정</button>
				<button type="reset" class="btn btn-default">초기화</button>
            </div>
        </form>
        
        </div>
  <div class="col-sm-3"></div>
</div>
</div>
</body>
</html>