<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                            <strong>${reply.memName}</strong>
                            <small class="text-muted">
                                <fmt:formatDate value="${reply.replyDate}" pattern="yyyy-MM-dd HH:mm"/>
                            </small>
                        </div>
                        <p>${reply.replyContent}</p>

                        <!-- 본인 댓글인 경우에만 수정/삭제 버튼 표시 -->
                        <c:if test="${sessionScope.memNo == reply.memNo}">
                            <div class="reply-actions">
                                <a href="${pageContext.request.contextPath}/replyUpdate.do?replyNo=${reply.replyNo}&boardNo=${reply.boardNo}" 
                                   class="btn btn-sm btn-outline-secondary">수정</a>
                                <form action="${pageContext.request.contextPath}/replyDelete.do" 
                                      method="post" class="d-inline">
                                    <input type="hidden" name="replyNo" value="${reply.replyNo}">
                                    <input type="hidden" name="boardNo" value="${reply.boardNo}">
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

<!-- 디버깅용 콘솔 로그 -->
<script>
    console.log('댓글 목록:', ${not empty replyList});
    console.log('댓글 개수:', ${replyList.size()});
</script>