 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요</title>
<!--<script src="${pageContext.request.contextPath}/js/public_script.js"></script>-->

</head>
<body>
	
		<div class="container mt-5">
			<h2 class="mb-4">좋아요 목록</h2>
			<!--<input type="text" name="codeNumber" value="${codeNumber}">-->
			<input type="hidden" name="memNo" value="${member.memNo}">
			<table class="table table-hover">
						
					<thead>
					  <tr>
						<th>제목</th>
						<th>이미지</th>
					  </tr>
					</thead>
					<tbody>

				<c:choose>
					<c:when test="${empty loveList}">
						<p class="text-muted"> 좋아요 목록이 없습니다.</p>
					</c:when>
					<c:otherwise>

						<c:forEach var="love" items="${loveList}">
							<tr>
								<td>${love.conTitle}</td>
								<td><img src="${love.conFirstimage2}" alt="이미지 없음"
									width="100"></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
				</table>
			</div>
			
</body>
</html>


