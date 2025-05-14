<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행일정계획 목록 ♣</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>


<script>
/* function planInsert(){
	
	location.href="planInsert.do?memNo=1"
	
} */

</script>

<style>

#view a:link{
	color:black;
}

#view a:visited{
	color:black;
}

#view a:hover{
	color:thistle;
}

th, td{
	width : 200px;
}

	
</style>

</head>
<body>

<%-- 	<form action="planList.do" method="get">
		<div class="container mt-5">
			<h2 class="mb-4">여행일정 목록</h2>
			<table class="table table-hover">

				<table border="1" id="table">
					<thead>
						<th>게시판 번호</th>
						<th>일정 이름</th>
						<th>일정 내용</th>
						<th>등록일자</th>
						<th>날씨</th>
						<th>여행기간</th>
					</thead>

					<tbody>
						<c:forEach var="plan" items="${planList}">
							<tr>
								<td>${plan.boardNo}</td>
								<td>${plan.boardName}</td>
								<td id='view'><a href="planView.do?boardNo=${plan.boardNo}">${plan.boardContent}</a></td>
								<td>${plan.boardDate}</td>
								<td>${plan.boardWeather}</td>
								<td>${plan.travelStart}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<button type="button" onclick="planInsert()">일정 등록 Go!</button>

				</form> --%>
</body>
</html>