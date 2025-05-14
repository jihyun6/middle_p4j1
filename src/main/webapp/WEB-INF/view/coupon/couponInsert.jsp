<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>couponList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">쿠폰추가</h2>
    <table class="table table-hover">
        <thead class="table-light">
            <tr>
                <th>쿠폰명</th>
                <th>할인율</th>
                <th>쿠폰생성자</th>
                <th>선택한 회원한테 넣어주기 셀렉트박스</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td><input type="text" name="cupName"></td>
                    <td><input type="text" name="cupDiscount">%</td>
                    <td>${member.memName}</td>
                    <td>선택한 회원한테 넣어주기 셀렉트박스</td>
                    <td>
                    	<button type="button" class="btn btn-primary" onclick="getPath('/couponList.do')">등록</button>
                    </td>
                </tr>
        </tbody>
    </table>
</div>
</body>
</html>