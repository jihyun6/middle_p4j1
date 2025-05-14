<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            댓글 수정
        </div>
        <div class="card-body">
            <form id="replyUpdateForm">
                <input type="hidden" name="replyNo" value="${reply.replyNo}">
                <input type="hidden" name="boardNo" value="${param.boardNo}">
                <div class="mb-3">
                    <label for="replyContent" class="form-label">댓글 내용</label>
                    <textarea 
                        class="form-control" 
                        id="replyContent" 
                        name="replyContent" 
                        rows="4" 
                        required
                    >${reply.replyContent}</textarea>
                </div>
                <div class="text-center">
                    <button type="submit" id="updateBtn" class="btn btn-primary">수정</button>
                    <button type="button" class="btn btn-secondary" onclick="window.history.back()">취소</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    $('#updateBtn').on('click', function() {
        // 입력 검증
        const replyContent = $('#replyContent').val().trim();
        if (replyContent === '') {
            alert('댓글 내용을 입력해주세요.');
            return;
        }

        // AJAX 요청
        $.ajax({
            url: '${pageContext.request.contextPath}/replyUpdate.do',  
            type: 'POST',
            data: $('#replyUpdateForm').serialize(), // serialize() 데이터 한번에 보내줌
            success: function(response) {
                alert('댓글이 성공적으로 수정되었습니다.');
                // 부모 창 새로고침 후 현재 창 닫기
                opener.location.reload();
                window.close();
            },
            error: function(xhr, status, error) {
                console.error("에러 발생:", error);
                alert('댓글 수정 중 오류가 발생했습니다.');
            }
        });
    });
});
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>