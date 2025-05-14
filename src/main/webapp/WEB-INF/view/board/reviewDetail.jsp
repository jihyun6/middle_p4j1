<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 상세보기</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(function(){
        $('#main').on('click', function(){
            location.href = "reviewList.do";
        });
    });
    </script>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">리뷰 상세보기</h2>
    
    <form>
        <div class="row mb-3">
            <div class="col-md-4">
                <label class="form-label">리뷰번호</label>
                <input type="text" class="form-control" value="${board.boardNo}" readonly>
            </div>
            <div class="col-md-4">
                <label class="form-label">작성자</label>
                <input type="text" class="form-control" value="${board.memName}" readonly>
            </div>
            <div class="col-md-4">
                <label class="form-label">작성일</label>
                <input type="text" class="form-control" value="${board.boardDate}" readonly>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-4">
                <label class="form-label">컨텐츠 타입</label>
                <input type="text" class="form-control" value="${board.contentsTypeId}" readonly>
            </div>
            <div class="col-md-4">
                <label class="form-label">별점</label>
                <div class="form-control">
                    <c:forEach begin="1" end="${board.boardStar}">★</c:forEach>
                </div>
            </div>
            <div class="col-md-4">
                <label class="form-label">게시판유형</label>
                <input type="text" class="form-control" value="${board.codeNumber}" readonly>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" class="form-control" value="${board.boardName}" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea class="form-control" rows="10" readonly>${board.boardContent}</textarea>
        </div>

        <div class="text-center mt-4">
            <a href="reviewUpdate.do?boardNo=${board.boardNo}" class="btn btn-primary">수정하기</a>
            <button type="button" class="btn btn-secondary" id="main">목록으로</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>