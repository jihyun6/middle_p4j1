<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제하기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>카카오페이 결제</h2>
    <form id="paymentForm" method="post">
        <input type="hidden" name="contentsTypeId" value="${payment.contentsTypeId}">
        <input type="hidden" name="contentNo" value="${payment.contentNo}">
        <input type="hidden" name="orderName" value="${payment.orderName}">
        <input type="hidden" name="memNo" value="${sessionScope.memNo}">
        <input type="hidden" name="payPrice" value="${payment.payPrice}">

        <div>
            <p>상품명: ${payment.orderName}</p>
            <p>결제금액: ${payment.payPrice}원</p>
        </div>

        <button type="button" onclick="requestPay()">카카오페이로 결제하기</button>
    </form>

    <script>
    function requestPay() {
        var form = $('#paymentForm');
        
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/payment.do',
            data: form.serialize(),
            dataType: 'text',
            success: function(redirectUrl) {
                window.location.href = redirectUrl;
            },
            error: function(xhr, status, error) {
                alert('결제 요청 중 오류가 발생했습니다.');
                console.error('Error:', error);
            }
        });
    }
    </script>
</body>
</html>