<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC Model2 Board</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <style>
		.contents{
			background-color:#D7FFFF;
		}
		.backBody{
		    background-color: #FCBA46;
		    border-top-width: 30px;
		    margin-top: 100px;
		}
	</style>  
	<script>
	//jquery 사용
	//github test...
		$(document).ready(function(){
			//console.log("안녕");
			$('#addButton').click(function(){
				if($('#board_pw').val().length < 4){
					alert("패스워드는 4자 이상이어야 합니다.");
					$('#board_pw').focus();
				}else if($('#board_title').val() == ''){
					alert("제목을 입력해 주세요.");
					$('#board_title').focus();
				}else if($('#board_content').val() == ''){
					alert("내용을 입력해 주세요.");
					$('#board_content').focus();	
				}else if($('#board_user').val() == ''){
					alert("작성자를 입력해 주세요.");
					$('#board_user').focus();
				}else{
					$('#addFrom').submit();
				}
			});
			
		});
	</script>
</head>
<body class="contents">
<div class="container">
<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6 backBody">
	<h1>게시판 글쓰기</h1>
	<form id="addFrom" action="${pageContext.request.contextPath}/boardAdd" method="post">
		<div class="form-group">
		<div>작성자 : </div>
		<div><input name="board_user" id="board_user" type="text" class="form-control"/></div>
		<div>비밀번호 : </div>
		<div><input name="board_pw" id="board_pw" type="password" class="form-control"/></div>
		<div>제목 : </div>
		<div><input name="board_title" id="board_title" type="text" class="form-control"/></div>
		<div>내용 : </div>
		<div><textarea name="board_content" id="board_content" rows="5" cols="50" class="form-control"></textarea></div>
		<!-- <div>첨부 : <input name="board_file" id="board_file" type="file" class="form-control"/></div> -->
		<br/>
		<div class="text-right">
	    	<button id="addButton" type="button" class="btn btn-default">글입력</button>
			<a href="${pageContext.request.contextPath}/boardList"><button type="button" class="btn btn-default">목록</button></a>
	    </div>
	    </div>
	</form>
  </div>
</div>
</div>
</body>
</html>


