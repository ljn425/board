<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
		console.log('Ȯ��');
		$('#btn').click(function(){
			if($('#board_pw').val() == ''){
				alert('��й�ȣ�� �Է����ּ���.');
				$('#board_pw').focus();
			}
			  $('#boardDeleteForm').submit();
		});
	});
</script>
</head>
<body>
<div class="container">
<div class="row">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
        <form id="boardDeleteForm" action="${pageContext.request.contextPath}/boardDelete" method="post">
            <!-- boardPw�� �Բ� boardNo���� ���ܼ�(hidden������) �ѱ� -->
            <input name="board_no" value="${param.boardNo}" type="hidden"/>
            <div class="form-group">
				<label for="board_pw">boardPw :</label>
		  		<input id="board_pw" name="board_pw" type="password" class="form-control">
			</div>
            <div>
                <button id="btn" type="button" class="btn btn-default">����</button>
				<button type="reset" class="btn btn-default">�ʱ�ȭ</button>
            </div>
        </form>
        </div>
	</div>
</div>
	
</body>
</html>