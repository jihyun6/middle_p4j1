<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header bg-primary text-white">댓글 작성</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/replyInsert.do" method="post">  
							<!-- 게시판 번호를 hidden으로 전달 -->
							<input type="hidden" name="boardNo" value="${param.boardNo}">  <!-- URL파라미터로 전달된 게시글 번호를 가져옴 -->
							<!-- 댓글 내용 텍스트 에어리어 -->
							<div class="mb-3">
								<label for="replyContent" class="form-label">댓글 내용</label>
								<textarea class="form-control" id="replyContent"
									name="replyContent" rows="4" required placeholder="댓글을 작성해주세요."
									maxlength="500"  oninput="updateCharCount(this)"></textarea>  <!-- 최대 글자수 제한 -->
									<!-- 글자 수 카운트 -->
								<small class="form-text text-muted"> 
								(<span id="charCount">0</span>/500)
								</small>
							</div>

							<div class="text-center">
								<button type="submit" class="btn btn-primary">댓글 등록</button>
								<button type="button" class="btn btn-secondary" onclick="window.history.back()">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 폼 제출 방지
    
    /* 댓글 내용과 게시글 번호를 가져옴 */
    const content = document.getElementById('replyContent').value.trim();
    const boardNo = document.querySelector('input[name="boardNo"]').value;
    
    if (content === '') {
        alert('댓글 내용을 입력해주세요.');
        return;
    }
    
    $.ajax({
        url: '${pageContext.request.contextPath}/replyInsert.do',
        type: 'POST',
        data: {    /* 게시글 번호와 댓글 내용을 데이터로 전송 */
            boardNo: boardNo,  
            replyContent: content
        },
        dataType: 'json',   /* 응답은 JSON 형식으로 받음 */
        success: function(response) {
            if (response.status === 'success') {   /* 응답의 status가 success면 성공 메세지 표시 */
                alert('댓글이 작성되었습니다.');
                // 페이지 새로고침 또는 댓글 목록 갱신
                location.reload();
            } else {
                alert('댓글 작성에 실패했습니다.');
            }
        },
        error: function(xhr, status, error) {
            console.error(error);
            alert('댓글 작성 중 오류가 발생했습니다.');
        }
    });
});
</script>
</body>
</html>