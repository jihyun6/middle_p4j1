<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
</head>

<script type="text/javascript">
$(function(){
	$('#main').on('click', function(){
		location.href = "BoardList.do";
	})
})
</script>

<body>

<form action="BoardInsert.do" method="post">
	<label for="">제목</label>
	<input type="text" value="" name="title"><p>
	
	<label for="">내용</label><br>
	<textarea rows="20" cols="20" name="content">
	</textarea>
	
	<p>
	<input type="submit" value="등록하기" id="input">
	<input type="button" value="돌아가기" id="main">
</form>
</body>
</html>