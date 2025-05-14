<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container mt-5">
		<h4 class="mb-3"><i class="bi bi-chat-dots-fill"></i> 여행톡목록</h4>
		<c:if test="${member.memNo != 1 && member.memNo != null}">
		<button type="button" id="talkInsert" class="btn btn-primary" href="#" onclick="getPath('/boardInsert.do?codeNo=2')">글쓰기</button>
		</c:if>
		<input type="hidden" name="memNo" value="${member.memNo}">
		
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th></th>
					<th width="200px">제목 </th>
					<th width="700px">내용 </th>
					<th>작성자</th>
					<th>작성일 </th>
				</tr>
			</thead>
			<tbody>

				<c:choose>
					<c:when test="${empty boardList}">
						<p class="text-muted">등록된 여행톡이 없습니다.</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td></td>
								<td width="200px">${board.boardName}</td>
								<td width="700px"><a href="#"
									onclick="getPath('/boardDetail.do?boardNo=${board.boardNo}')">${board.boardContent}</a></td>
								<td>${board.memName}</td>
								</td>
								<td>${board.boardDate}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>