<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고 리스트</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
function updateStatus(select, reNo, memNo) {
    if(select.value == "2") {
        $.ajax({
            url: 'updateReportStatus.do',
            type: 'POST',
            data: {
                reNo: reNo,
                memNo: memNo,
                reStatus: select.value
            },
            success: function(response) {
                console.log("서버 응답:", response);
                var countCell = select.closest('tr').querySelector('td:nth-child(7)');
                countCell.textContent = parseInt(countCell.textContent) + 1;
                alert('처리가 완료되었습니다.');
				getPath('/reportList.do');          
            },
            error: function(xhr, status, error) {
                console.error("에러 발생:", error);
                alert('처리 중 오류가 발생했습니다.');
            }
        });
    }
}

function insertBlacklist(memNo, memName, reason) {
    if(confirm('블랙리스트에 추가하시겠습니까?')) {
        $.ajax({
            url: 'checkBlacklistDuplicate.do',
            type: 'POST',
            data: { memNo: memNo },
            success: function(response) {
                if(response.isDuplicate) {
                    alert('이미 블랙리스트에 등록된 회원입니다.');
                } else {
                    $.ajax({
                        url: 'blacklistInsert.do',
                        type: 'POST',
                        data: {
                            memNo: memNo,
                            memName: memName,
                            blackReason: reason
                        },
                        success: function(response) {
                            console.log("응답:", response);
                            alert('블랙리스트에 추가되었습니다.');
   
                        },
                        error: function(xhr, status, error) {
                            console.error("에러:", error);
                            alert('처리 중 오류가 발생했습니다.');
                        }
                    });
                }
            },
            error: function(xhr, status, error) {
                console.error("에러:", error);
                alert('처리 중 오류가 발생했습니다.');
            }
        });
    }
}

$(document).ready(function() {
    $('.blacklist-btn').click(function(e) {
        e.preventDefault();
        var memNo = $(this).data('memno');
        var memName = $(this).data('memname');
        var reason = $(this).data('reason');
        insertBlacklist(memNo, memName, reason);
    });
});

</script>
</head>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">신고 목록</h2>
    <table class="table table-hover">
        <thead class="table-light">
            <tr>
                <th>신고번호</th>
                <th>회원명</th>
                <th>게시글번호</th>
                <th>상세내용</th>
                <th>처리결과</th>
                <th>신고일</th>
                <th>신고횟수</th>
                <th>블랙리스트 추가</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${reportList}">
                <!-- 메인 행 -->
                <tr>
                    <td>${report.reNo}</td>
                    <td>${report.memName}</td>
                    <td>${report.boardNo}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapse${report.reNo}"
                            aria-expanded="false"
                            aria-controls="collapse${report.reNo}">
                            내용보기
                        </button>
                    </td>
                    <td>
                        <div class="col-md-8">
                            <select name="reStatus" class="form-control"
                                onchange="updateStatus(this, ${report.reNo}, ${report.memNo})">
                                <option value="1" ${report.reStatus == '1' ? 'selected' : ''}>미처리</option>
                                <option value="2" ${report.reStatus == '2' ? 'selected' : ''}>처리완료</option>
                                <option value="3" ${report.reStatus == '3' ? 'selected' : ''}>신고반려</option>
                            </select>
                        </div>
                    </td>
                    <td>${report.reRecieptDate}</td>
                    <td>${report.reCount}</td>
                    <td>
                    <a href="#" 
					   data-memno="${report.memNo}" 
					   data-memname="${report.memName}" 
					   data-reason="${report.reReason} - ${report.reComment}" 
					   class="btn btn-primary btn-sm blacklist-btn">블랙리스트 추가</a>
                    </td>
                </tr>
                <!-- Collapse 상세내용 행 -->
                <tr>
                    <td colspan="8" class="p-0">
                        <div class="collapse" id="collapse${report.reNo}">
                            <div class="card card-body m-2">
                                <p><strong>신고 사유:</strong> ${report.reReason}</p>
                                <p><strong>상세 내용:</strong> ${report.reComment}</p>
                                <c:if test="${not empty report.groupedComments}">
                                    <hr>
                                    <h6>동일 게시글 신고 내역:</h6>
                                    <c:forEach var="comment" items="${report.groupedComments}" varStatus="status">
                                        ${comment}
                                        <c:if test="${!status.last}">
                                            <hr class="my-2">
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>