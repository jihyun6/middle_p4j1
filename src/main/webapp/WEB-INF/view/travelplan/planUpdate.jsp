<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="planUpdate.do" method="post">
	<table border="1">
	
		<thead>
			<th>게시판 번호</th>
			<th>여행 이름</th>
			<th>여행 내용</th>
			<th>날씨</th>
			<th>작성일</th>
			<th>작성자</th>
		</thead>
	
		<tbody>
			<td><input type="text" name="boardNo" value="${board.boardNo}" readonly="readonly"></td>
			<td><input type="text" name="planName"></td>
			<td><input type="text" name="planContent"></td>
			<td>${board.boardWeather}</td>
			<td>${board.boardDate}</td>
			<td>${board.memName}</td>
		</tbody>
	</table>
		<input type="submit" value="수정완료">
	</form>

</body>
</html>