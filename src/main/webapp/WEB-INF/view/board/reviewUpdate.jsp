<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="kr.or.ddit.vo.BoardVo"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#main').on('click', function() {
			location.href = "reviewList.do";
		});
	});
	
	$(function() {
	    $('#deleteBtn').on('click', function() {
	        location.href = 'reviewDelete.jsp?boardNo=${board.boardNo}';
	    });
	});
</script>

</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">리뷰 수정하기</h2>

		<form action="reviewUpdate.do" method="post">
			<div class="row mb-3">
				<div class="col-md-4">
					<label class="form-label">리뷰번호</label> <input type="text"
						class="form-control" name="boardNo" value="${board.boardNo}">
				</div>
				<div class="col-md-4">
					<label class="form-label">작성자</label> <input type="text"
						class="form-control" name="writer" value="${board.memName}" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">작성일</label> <input type="text"
						class="form-control" name="date" value="${board.boardDate}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-4">
					<label class="form-label">컨텐츠 타입</label> <input type="text"
						class="form-control" name="contents" value="${board.contentsTypeId}" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">별점</label> <select name="boardStar"
						class="form-control">
						<option value="1" ${board.boardStar == 1 ? 'selected' : ''}>★</option>
						<option value="2" ${board.boardStar == 2 ? 'selected' : ''}>★★</option>
						<option value="3" ${board.boardStar == 3 ? 'selected' : ''}>★★★</option>
						<option value="4" ${board.boardStar == 4 ? 'selected' : ''}>★★★★</option>
						<option value="5" ${board.boardStar == 5 ? 'selected' : ''}>★★★★★</option>
					</select>
				</div>
				<div class="col-md-4">
					<label class="form-label">게시판유형</label> <input type="text"
						class="form-control" name="codeNum" value="${board.codeNumber}" readonly>
				</div>
			</div>

			<div class="mb-3">
				<label class="form-label">제목</label> <input type="text"
					class="form-control"  name="title" value="${board.boardName}">
			</div>

			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" name="content" rows="10">${board.boardContent}</textarea>
			</div>

			<div class="text-center mt-4">
				<input type="submit" class="btn btn-primary" value="완료">
				<%--             <a href="reviewDetail.do?boardNo=${board.boardNo}" class="btn btn-primary">수정하기</a> --%>
				<button type="button" class="btn btn-secondary" id="main">목록으로</button>
				<a href="#" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='reviewDelete.do?boardNo=${board.boardNo}'" class="btn btn-outline-danger">삭제</a>
			</div>

		</form>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>