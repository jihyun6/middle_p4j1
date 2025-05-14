<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제 정보 입력</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>결제 정보 입력</h2>
        <form id="paymentForm" method="post">
            <div class="mb-3">
                <label class="form-label">회원번호</label>
                <input type="text" class="form-control" name="memNo" value="${sessionScope.memNo}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">컨텐츠 번호</label>
                <input type="text" class="form-control" name="contentNo" required>
            </div>
            <div class="mb-3">
                <label class="form-label">컨텐츠 타입</label>
                <input type="text" class="form-control" name="contentsTypeId" required>
            </div>
            <div class="mb-3">
                <label class="form-label">결제금액</label>
                <input type="number" class="form-control" name="payPrice" required>
            </div>
            <input type="hidden" name="payMethod" value="카카오페이">
            <input type="hidden" name="payStatus" value="결제대기">
            
            <div class="text-center">
                <button type="button" class="btn btn-primary" onclick="submitPayment()">결제하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">취소</button>
            </div>
        </form>
    </div>

    <script>
    function submitPayment() {
        var form = $('#paymentForm');
        
        $.ajax({
            type: 'POST',
            url: 'paymentInsert.do',
            data: form.serialize(),
            success: function(response) {
                if(response === 'success') {
                    alert('결제 정보가 저장되었습니다.');
                    location.href = 'paymentList.do';  // 결제 목록으로 이동
                } else {
                    alert('결제 정보 저장에 실패했습니다.');
                }
            },
            error: function() {
                alert('처리 중 오류가 발생했습니다.');
            }
        });
    }
    </script>
</body>
</html>