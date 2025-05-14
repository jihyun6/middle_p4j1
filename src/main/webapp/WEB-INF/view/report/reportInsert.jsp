<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- 신고 모달 -->
    <div class="modal fade" id="reportModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="card">
                    <div class="card-header bg-danger text-white">
                        <h5 class="modal-title mb-0">게시글 신고</h5>
                    </div>
                    <div class="card-body">
                        <form id="reportForm">
                            <input type="hidden" name="boardNo" id="boardNo">
                            
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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
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
                reComment: reComment
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
    </script>
</body>
</html>