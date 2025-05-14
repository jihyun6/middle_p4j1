<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 목록</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script type="text/javascript">

function contentNo(contentNo){
	$.ajax({
		url: "ReviewList.do",
		type: "GET",
		data: {
			"contentNo": contentNo
		},
		success: function (data) {
			alert("성공");
		},
		error: function () {
			alert("실패");
		}
	});
}

// 페이지 로드 시 contentNo 함수 호출
window.onload = function () {
	// JSP에서 전달된 contentNo 값을 가져옴
	let contentNo = '<%= request.getParameter("contentNo") != null ? request.getParameter("contentNo") : "" %>';
	if (contentNo !== "") {
		contentNo(contentNo); // contentNo 값이 있을 경우 함수 호출
	}
};
</script>
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">리뷰 목록</h2>
		<c:if test="${member.memNo != 1}">
		<button type="button" id="reviewInsert" class="btn btn-primary" href="#" onclick="getPath('/boardInsert.do?codeNo=3')">글등록</button>
		</c:if>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>별점</th>
					<th>작성일</th>
					<th>상세보기</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty boardList}">
						<p class="text-muted">등록된 리뷰가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td>${board.boardName}</td>
								<td>${board.boardContent}</td>
								<td>${board.memName}</td>
								<td><c:forEach begin="1" end="${board.boardStar}">★</c:forEach>
								</td>
								<td>${board.boardDate}</td>
								<td><a href="#" onclick="getPath('/boardDetail.do?boardNo=${board.boardNo}')"
									class="btn btn-primary btn-sm">상세보기</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>