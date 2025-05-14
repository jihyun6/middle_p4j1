<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

function calendar(){
	
	location.href="Calendar.do";
}
	
</script>

<style>

#text{
	border : 3px dotted thistle;
	width : 200px;
	padding : 5px;
}

</style>
</head>

<body>

	<form action="planInsert.do" method="post">
		<div id='text'>
		여행 이름 : <input type="text" name="boardName"><br>
		내용 : <input type="text" name="boardContent">
		</div><br>
		 <div id='calendar'>
			<button type="button" onclick="calendar()">날짜 선택</button>
		</div> 
		<br>
		<input type="submit" value="등록">
	</form>
	
	
	
</body>
</html>