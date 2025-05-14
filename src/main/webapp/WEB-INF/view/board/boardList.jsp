<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 추가됨 -->

<%-- <c:set var="paging" value="${paginationInfo}"/>
<c:set var="pagingOption" property="" value="${paginationInfo.getSearchVo()}"/> --%>
<!-- 추가됨 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<!-- 추가됨 -->
<%-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link  href="${pageContext.request.contextPath}/css/common.css"  type="text/css" rel="stylesheet">  --%> 
<!-- 추가됨 -->

</head>

<script type="text/javascript">

$(function(){
	$('#input').on('click', function(){
		location.href = "BoardInsert.do";
	})
	
	$('.update').on('click', function(){
		var tr = $(this).parent().parent();
		var td = tr.children();
		var boardNo = td.eq(0).text();
		
		location.href = "BoardUpdate.do?boardNo="+boardNo;
	})
	
	$('.delete').on('click', function(){
		var tr = $(this).parent().parent();
		var td = tr.children();
		var boardNo = td.eq(0).text();
		
		location.href = "BoardDelete.do?boardNo="+boardNo;
	})
	
})
</script>

<body>

<table border="1">
	<thead>
		<tr>
			<td>게시판글 번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList}">
		<tr>
				<td>${board.boardNo}</td>
				<td>${board.title}</td>
				<td>${board.content}</td>
				<td>${board.memNo}</td>
				<td>${board.regDate}</td>
				<td><input type="button" value="수정" class="update"></td>
				<td><input type="button" value="삭제" class="delete"></td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<input type="button" value="입력" id="input">


<!-- 로그인한 사용자에게만 댓글 작성 폼 표시 -->
		<c:if test="${not empty sessionScope.memId}">
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
								<c:if test="${sessionScope.memId == reply.memId}">
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




<!-- 추가됨 -->

<%--  <page-navi 
   		url="boardList.do?${pagingOption}"
   		current="${paging.getPageNo()}" 
   		total="${paging.getTotalPageCount()}"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script> --%>
<!-- 추가됨 -->

</body>
</html>