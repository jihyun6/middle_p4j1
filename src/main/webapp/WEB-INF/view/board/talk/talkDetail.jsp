<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome -->
    <script src="https://kit.fontawesome.com/0c69fdf2c0.js" crossorigin="anonymous"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
    // 전역 변수로 선언
    let currentBoardNo = 0;

    // 신고 관련 함수들
    function openReportModal(boardNo) {
        currentBoardNo = boardNo;
        var modal = new bootstrap.Modal(document.getElementById('reportModal'));
        modal.show();
    }

    function updateCharCount(textarea) {
        const charCount = textarea.value.length;
        document.getElementById('charCount').textContent = charCount;
    }

    function submitReport() {
        const reReason = $("#reReason").val();
        const reComment = $("#reComment").val().trim();
        
        if(!reReason) {
            alert("신고 사유를 선택해주세요.");
            return;
        }
        
        if(!reComment) {
            alert("신고 내용을 입력해주세요.");
            return;
        }
        
        $.ajax({
            url: '${pageContext.request.contextPath}/reportInsert.do',
            type: 'POST',
            data: {
                boardNo: currentBoardNo,
                reReason: reReason,
                reComment: reComment,
                boardMemNo: ${board.memNo}  // 게시글 작성자의 회원번호 추가
            },
            dataType: 'json',
            success: function(response) {
                if(response.status === 'success') {
                    alert('신고가 접수되었습니다.');
                    var modal = bootstrap.Modal.getInstance(document.getElementById('reportModal'));
                    modal.hide();
                } else {
                    alert(response.message || '신고 처리에 실패했습니다.');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                if(xhr.status === 401) {
                    alert("로그인이 필요합니다.");
                } else {
                    alert("신고 처리 중 오류가 발생했습니다.");
                }
            }
        });
    }
$(function(){ /* 페이지 로드시 초기화되는 함수 */
	path = function(){   /* path 함수를 정의하여 댓글 등록 폼 처리 */

		let frm = $('#frm');
		postPath('/replyInsert.do', frm);    /* postPath 함수를 사용하여 폼 데이터를 서버로 전송 '#frm'이라는 ID를 가진 폼의 데이터를 처리 */
	}
})
$(function(){
    $('form[action$="/replyDelete.do"]').on('submit', function(e) {   /* 삭제 폼(action이 replyDelete.do로 끝나는 폼)의 제출 이벤트를 처리 */
        e.preventDefault(); // 기본 폼 제출 방지

        if (confirm('정말 삭제하시겠습니까?')) {   /* 사용자에게 메세지 표시 */
            const formData = $(this).serialize();
            
            $.ajax({   /* 폼 데이터를 직렬화하여 전송 가능한 형식으로 변환 */
                url: $(this).attr('action'),   /* 현재 폼의 action 속성값을 URL로 사용 */
                type: 'POST',
                data: formData,
                dataType: 'json',
                success: function(response) {
                    if (response.status === 'success') {
                        alert('댓글이 삭제되었습니다.');
                        getPath('/boardDetail.do?boardNo=' + response.boardNo);
                    } else {
                        alert('댓글 삭제에 실패했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error(error);
                    alert('댓글 삭제 중 오류가 발생했습니다.');
                }
            });
        }
    });
});

function deleteReply(event, replyNo, boardNo) {
    event.preventDefault();
    if (confirm('정말 삭제하시겠습니까?')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/replyDelete.do',
            type: 'POST',
            data: { replyNo: replyNo, boardNo: boardNo },
            dataType: 'json',
            success: function(response) {
                if (response.status === 'success') {
                    alert('댓글이 삭제되었습니다.');
                    // 삭제된 댓글의 요소를 페이지에서 제거
                    $(event.target).closest('.reply-item').remove();
                } else {
                    alert('댓글 삭제에 실패했습니다.');
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert('댓글 삭제 중 오류가 발생했습니다.');
            }
        });
    }
}

function loveCheck(){
	
	if (${lovedByMem}){
		$('#loveUpdate i').addClass('red')
	}else{
		$('#loveUpdate i').addClass('black')
	}
}
loveCheck();
//하트버튼 클릭시(좋아요 추가 또는 좋아요 제거)
function loveup(){
		 if( $('#loveUpdate i').hasClass('red')){
	    	 loveCancel();
	     }else{
				$.ajax({
				 url: "loveUpdate.do",
		         type: "POST",
		         data: {
		        	 "boardNo": ${board.boardNo},
		        	 "memNo": ${member.memNo},
		        	 "loveCount":${board.loveCount}
		         },
		         success: function (data) {
				     alert("좋아요")   ;
				    	//아니냐>
					        //red로 변경 
					        $('#loveUpdate i').removeClass('black')
					        $('#loveUpdate i').addClass('red')
					        //숫자 올려 
					        count =parseInt($(".loveAdd").text());
					        count++;
					        $(".loveAdd").text(count);
				  },
				})
		}
}
	
function loveCancel(){
	 if( $('#loveUpdate i').hasClass('black')){
	    	 loveUpdate();
	    	 }else{
					$.ajax({
						url: "loveCancel.do",
			         type: "POST",
			         data: {
			        	 "boardNo": ${board.boardNo},
			        	 "memNo": ${member.memNo},
			        	 "loveCount":${board.loveCount}
			         },
			         success: function (data) {
					     alert("좋아요취소")   ;
			        	
					     if( $('#loveUpdate i').hasClass('red')){
					    	 //색상이 red이냐 
						        //기본으로 변경 
						        $('#loveUpdate i').removeClass('red')
						        $('#loveUpdate i').addClass('black')
						        //숫자 내려 
						        count =parseInt($(".loveAdd").text());
					    	    count--;
					    	    $(".loveAdd").text(count);
					     }
					},
				})
	   }
}

// 페이지 로드 시 실행
$(document).ready(function() {
    loveCheck();
});
</script>
<style type="text/css">
.red{
color: red;
}
.black{
color:black;
}
.fileimg{
	flex: 1;
 	width:200px; 
	height: 300px;
	
}
</style>

</head>
<body>
	<%-- <input type="hidden" name="boardNo" value="${talk.boardNo}"> --%>
	<div class="container mt-5">
		<h2 class="mb-4">여행톡목록</h2>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>제목</th>
					<td colspan="3">${board.boardName}&nbsp&nbsp&nbsp&nbsp</td>
					<th>작성자</th>
					<td>${board.memName}</td>
					<th>만족도</th>
					<td><c:forEach begin="1" end="${board.boardStar}">★</c:forEach></td>
					<th>작성일</th>
					<td>${board.boardDate}</td>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td colspan="10"><textarea name ="boardContent" id="conAdd" cols="5" rows="15" class="form-control" 
						style="resize: none;" readonly>${board.boardContent}</textarea></td>
					</tr>
					
			</tbody>
		</table>
					<div style="display: flex; gap: 20px; align-items: flex-start; ">
					
					<c:forEach var="file" items="${fileList}" begin="0" end="3" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img alt="경로잘못" src="download?fileNo=${file.fbNo}"
                                             class="card-img-top fileimg" style="height: 150px; object-fit: cover;">
                                        
                                    </div>
                                </div>
                            </c:forEach>
                    </div>
					<div>
						#${board.boardTag}
					</div>
		<div>
		
		
		<!-- 좋아요 섹션 -->
					<div class="d-flex justify-content-center mb-4" id="heart">
				        <c:if test="${member.memNo == null}">
				            <button class="btn btn-outline-light text-danger" onclick="alert('로그인후 사용하세요')" >
				                <i class="fa-solid fa-heart"></i> 
				                <span>${loveCount}</span>
				            </button>
				        </c:if>
				        <c:if test="${member.memNo != null}">
				            <c:choose>
				                <c:when test="${lovedByMem}" >
				                    <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveCancel()">
				                        <i class="fa-solid fa-heart"  >&nbsp;좋아요</i>
				                        <span class="loveAdd">${loveCount}</span>
				                    </button>
				                </c:when>
				                <c:otherwise>
				                    <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveup()">
				                        <i class="fa-solid fa-heart">&nbsp;좋아요</i>
				                        <span class="loveAdd">${loveCount}</span>
				                    </button>
				                </c:otherwise>
				            </c:choose>
				        </c:if>&nbsp;
				        </div>
				    
						<div>
						    <a href="#" onclick="getPath('/boardList.do?codeNo=${board.codeNumber}')">
						        <button class="btn btn-primary">목록보기</button>
						    </a>&nbsp;
						    
						    <c:if test="${member.memNo eq board.memNo}">
						        <a href="#" onclick="getPath('/boardUpdate.do?boardNo=${board.boardNo}')">
						            <button class="btn btn-primary">수정하기</button>
						        </a>&nbsp;
						    </c:if>
						    
						    <c:if test="${member.memNo eq board.memNo || member.memNo == 1}">
						        <a href="#" onclick="getPath('/boardDelete.do?boardNo=${board.boardNo}&codeNo=${board.codeNumber}')">
						            <button class="btn btn-danger">삭제하기</button>
						        </a>&nbsp;
						    </c:if>
						    
						    <!-- 신고하기 버튼 추가 -->
						    <button class="btn btn-danger" onclick="openReportModal(${board.boardNo})">
						        <i class="fas fa-flag"></i> 신고하기
						    </button>
						</div>
		<!-- 신고버튼 -->
	<div class="modal fade" id="reportModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="card-header bg-danger text-white">
                        <h5 class="modal-title mb-0">게시글 신고</h5>
                    </div>
                    <div class="card-body">
                        <form id="reportForm">
                             <input type="hidden" name="boardNo" id="boardNo" value="${board.boardNo}">
   							 <input type="hidden" name="boardMemNo" id="boardMemNo" value="${board.memNo}">   
                            <div class="mb-3">
                                <label for="reReason" class="form-label">신고 사유</label>
                                <select id="reReason" name="reReason" class="form-select" required>
                                    <option value="">신고 사유를 선택해주세요</option>
                                    <option value="1">불법 정보</option>
                                    <option value="2">욕설/비방</option>
                                    <option value="3">악성 광고</option>
                                    <option value="4">개인정보 노출</option>
                                    <option value="5">기타</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="reComment" class="form-label">상세 내용</label>
                                <textarea class="form-control" id="reComment" name="reComment" 
                                    rows="4" required placeholder="구체적인 신고 사유를 작성해주세요."
                                    maxlength="500" oninput="updateCharCount(this)"></textarea>
                                <small class="form-text text-muted">
                                    (<span id="charCount">0</span>/500)
                                </small>
                            </div>

                            <div class="text-center mt-3">
                                <button type="button" class="btn btn-danger" onclick="submitReport()">신고하기</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
		
		<!-- 로그인한 사용자에게만 댓글 작성 폼 표시 -->
			<c:if test="${not empty sessionScope.member}"> <!-- 세션에 member 정보가 있을 때만(로그인 상태) 댓글 작성 폼을 표시 -->
				<div class="card mt-3">
					<div class="card-header">댓글 작성</div>
					<div class="card-body">
						<form id="frm">  <!-- id="frm": JavaScript에서 폼을 식별하기 위한 ID -->
							<input type="hidden" name="boardNo" value="${board.boardNo}">
							<div class="mb-3">
								<textarea class="form-control" name="replyContent" rows="3"
									required placeholder="댓글을 작성해주세요."></textarea>  <!-- required: 필수 입력 필드로 지정 -->
							</div>
							<button type="button" onclick="path()" class="btn btn-primary">댓글   <!-- onclick="path()": 클릭 시 JavaScript path() 함수 호출 -->
								작성</button>
						</form>
					</div>
				</div>
			</c:if>
			<!-- 댓글 목록 섹션 -->
			<div class="card mt-3">
				<div class="card-header">댓글 목록</div>
				<div class="card-body">
					<c:choose>
						<c:when test="${empty replyList}">   <!-- mpty replyList: 댓글 목록이 비어있는지 확인 --> 
							<p class="text-muted">아직 댓글이 없습니다.</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="reply" items="${replyList}">  <!-- replyList의 각 항목을 순회하며 표시 댓글 목록 반복처리 -->
								<div class="reply-item border-bottom py-2">
									<div class="d-flex justify-content-between">  <!-- Flexbox를 사용하여 작성자와 날짜를 양쪽 정렬 -->
										<strong>${reply.memName}</strong> 
										<small class="text-muted">
											<fmt:formatDate value="${reply.replyDate}" pattern="yyyy-MM-dd HH:mm" />
										</small>
									</div>
									<p>${reply.replyContent}</p>

							
 									<!-- 본인 댓글인 경우에만 수정/삭제 버튼 표시 -->
								<c:if test="${sessionScope.member.memNo == reply.memNo}">  <!-- 현재 로그인한 사용자의 번호(memNo)와 댓글 작성자의 번호를 비교 -->
								    <div class="reply-actions">
								   <%--      <a href="${pageContext.request.contextPath}/replyUpdate.do?replyNo=${reply.replyNo}&boardNo=${board.boardNo}"
								            class="btn btn-sm btn-outline-secondary">수정</a> --%>
								        <form onsubmit="deleteReply(event, ${reply.replyNo}, ${board.boardNo})" style="display:inline;">
										    <input type="hidden" name="replyNo" value="${reply.replyNo}">
										    <input type="hidden" name="boardNo" value="${board.boardNo}">
										    <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
										</form>
								    </div>
								</c:if>
								
<%-- 								<!-- 수정삭제 하다가 포기 -->
								<c:if test="${sessionScope.member.memNo == reply.memNo}">
								    <div class="reply-actions">
								        <form action="${pageContext.request.contextPath}/replyUpdate.do" method="post"style="display:inline;" >
								            <input type="hidden" name="replyNo" value="${reply.replyNo}">
								            <input type="hidden" name="boardNo" value="${board.boardNo}">
								            <textarea class="form-control" name="replyContent" rows="3" required>${reply.replyContent}</textarea>
								            <button type="submit" class="btn btn-sm btn-outline-primary">수정</button>
								            <button type="button" class="btn btn-sm btn-outline-secondary" onclick="window.history.back()">취소</button>
								        </form>
								        <form action="${pageContext.request.contextPath}/replyDelete.do" method="post" style="display:inline;">
								            <input type="hidden" name="replyNo" value="${reply.replyNo}">
								            <input type="hidden" name="boardNo" value="${board.boardNo}">
								            <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
								        </form>
								    </div>
								</c:if> --%>
								
								
								
								
								
								
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>