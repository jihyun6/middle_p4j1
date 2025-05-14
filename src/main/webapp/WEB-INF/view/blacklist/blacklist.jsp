<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>블랙 리스트</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">블랙리스트</h2>
        <table class="table table-hover">
            <thead class="table-light">
                <tr>
                    <th>블랙리스트번호</th>
                    <th>회원번호</th>
                    <th>회원명</th>
                    <th>사유</th>
                    <th>시작기간</th>
                    <th>종료기간</th>
                    <th>블랙리스트 삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="blacklist" items="${blacklistList}">
                    <tr>
                        <td>${blacklist.blackNo}</td>
                        <td>${blacklist.memNo}</td>
                        <td>${blacklist.memName}</td>
                        <td>${blacklist.blackReason}</td>
                        <td>${blacklist.blackStartDate}</td>
                        <td>${blacklist.blackEndDate}</td>
                        <td>
                            <a href="#" onclick="if(confirm('정말 삭제하시겠습니까?')) getPath('/blacklistDelete.do?blackNo=${blacklist.blackNo}')" 
                               class="btn btn-outline-danger">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>