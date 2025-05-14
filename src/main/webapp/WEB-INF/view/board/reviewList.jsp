<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>리뷰 목록</title>
   <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
   <h2 class="mb-4">리뷰 목록</h2>
   <table class="table table-hover">
       <thead class="table-light">
           <tr>
               <th>리뷰번호1231231231231</th>
               <th>제목</th>
               <th>내용</th>
               <th>작성자</th>
               <th>별점</th>
               <th>생성일</th>
               <th>컨텐츠타입</th>
               <th>상세보기</th>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="board" items="${reviewList}">
               <tr>
                   <td>${board.boardNo}</td>
                   <td>${board.boardName}</td>
                   <td>${board.boardContent}</td>
                   <td>${board.memName}</td>
                   <td>
                       <c:forEach begin="1" end="${board.boardStar}">★</c:forEach>
                   </td>
                   <td>${board.boardDate}</td>
                   <td>${board.contentsTypeId}</td>
                   <td>
                       <a href="reviewDetail.do?boardNo=${board.boardNo}" 
                          class="btn btn-primary btn-sm">상세보기</a>
                   </td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>