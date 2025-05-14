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
			<td>${planView.boardNo}</td>
			<td>${planView.boardName}</td>
			<td>${planView.boardContent}</td>
			<td>${planView.boardWeather}</td>
			<td>${planView.boardDate}</td>
			<td>${planView.memName}</td>
		</tbody>
	</table>
	<br>
	<button type="button"><a href="planUpdate.do?boardNo=${planView.boardNo}">수정</a></button>
	<button type="button"><a href="planDelete.do?boardNo=${planView.boardNo}">삭제</a></button>


</body>
</html>