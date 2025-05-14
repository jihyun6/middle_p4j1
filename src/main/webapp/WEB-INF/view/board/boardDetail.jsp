<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
	$(function() {
		$('#main').on('click', function() {
			location.href = "BoardList.do";
		})
	})
</script>

</head>
<body>
	<form action="BoardUpdate.do" method="post">
		<input type="hidden" value="${board.boardNo}" name="boardNo">
		<label for="">제목</label> <input type="text" value="${board.title}"
			name="title">
		<p>

			<label for="">내용</label><br>
			<textarea rows="20" cols="20" name="content">
		${board.content}
	</textarea>


		<p>
			<input type="submit" value="수정하기" id="update"> <input
				type="button" value="돌아가기" id="main">
	
	</form>
<!-- 로그인한 사용자에게만 댓글 작성 폼 표시 -->
		<c:if test="${not empty sessionScope.member}">
		    <div class="card mt-3">
		        <div class="card-header">댓글 작성</div>
		        <div class="card-body">
		            <form action="${pageContext.request.contextPath}/replyInsert.do" method="post">
		                <input type="hidden" name="boardNo" value="${board.boardNo}">
		                <div class="mb-3">
		                    <textarea class="form-control" name="replyContent" rows="3" 
		                              required placeholder="댓글을 작성해주세요."></textarea>
		                </div>
		                <button type="submit" class="btn btn-primary">댓글 작성</button>
		            </form>
		        </div>
		    </div>
		</c:if>
			<!-- 댓글 목록 섹션 -->
		<div class="card mt-3">
			<div class="card-header">댓글 목록</div>
			<div class="card-body">
				<c:choose>
					<c:when test="${empty replyList}">
						<p class="text-muted">아직 댓글이 없습니다.</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="reply" items="${replyList}">
							<div class="reply-item border-bottom py-2">
								<div class="d-flex justify-content-between">
									<strong>${reply.memName}</strong> <small class="text-muted">
										<fmt:formatDate value="${reply.replyDate}"
											pattern="yyyy-MM-dd HH:mm" />
									</small>
								</div>
								<p>${reply.replyContent}</p>

								<!-- 본인 댓글인 경우에만 수정/삭제 버튼 표시 -->
								<c:if test="${sessionScope.member == reply.member}">
									<div class="reply-actions">
										<a
											href="${pageContext.request.contextPath}/replyUpdate.do?replyNo=${reply.replyNo}&boardNo=${board.boardNo}"
											class="btn btn-sm btn-outline-secondary">수정</a>
										<form
											action="${pageContext.request.contextPath}/replyDelete.do"
											method="post" class="d-inline">
											<input type="hidden" name="replyNo" value="${reply.replyNo}">
											<input type="hidden" name="boardNo" value="${board.boardNo}">
											<button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
										</form>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>