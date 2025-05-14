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

	<div class="container mt-5">
		<h2 class="mb-4">쿠폰 목록</h2>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>쿠폰번호</th>
					<th>쿠폰명</th>
					<th>할인율</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="coupon" items="${couponList}">
					<tr>
						<td>${coupon.cupNo}</td>
						<td>${coupon.cupName}</td>
						<td>${coupon.cupDiscount}%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



</body>
</html>