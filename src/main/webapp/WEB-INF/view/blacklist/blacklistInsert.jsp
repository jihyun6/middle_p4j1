<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container mt-5">
		<h2 class="mb-4">블랙리스트 등록</h2>
		<form action="blacklistInsert.do" method="post">
			<div class="mb-3">
				<label class="form-label">회원번호</label> <input type="text"
					class="form-control" name="memNo" value="${param.memNo}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">회원명</label> <input type="text"
					class="form-control" name="memName" value="${param.memName}"
					readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">사유</label>
				<textarea class="form-control" name="blackReason" rows="3" readonly>${param.blackReason}</textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">시작일</label> <input type="text"
					class="form-control" name="blackStartDate" value="${startDate}"
					readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">종료일</label> <input type="text"
					class="form-control" name="blackEndDate" value="${endDate}"
					readonly>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='blacklistList.do'">취소</button>
			</div>
		</form>
	</div>

</body>
</html>