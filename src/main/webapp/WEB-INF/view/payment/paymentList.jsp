<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제 내역</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>결제 내역</h2>
    <div>
        <label>컨텐츠타입:</label>
        <input type="text" name="contentsTypeId" value="${payment.contentsTypeId}" readonly>
    </div>
        <div>
        <label>컨텐츠ID:</label> 
        <input type="text" name="contentNo" value="${payment.contentNo}" readonly>
    </div>
    <div>
        <label>컨텐츠명:</label> 
        <input type="text" name="orderName" value="${payment.orderName}" readonly>
    </div>
    <div>
        <label>회원번호:</label> 
        <input type="text" name="memNo" value="${sessionScope.memNo}" readonly>
    </div>
    <div>
        <label>결제번호:</label> 
        <input type="text" name="payNo" value="${payment.payNo}" readonly>
    </div>
    <div>
        <label>결제일자:</label> 
        <input type="text" name="payDate" value="${payment.payDate}" readonly>
    </div>
    <div>
        <label>결제수단:</label> 
        <input type="text" name="payMethod" value="카카오페이" readonly>
    </div>
    <div>
        <label>결제상태:</label> 
        <input type="text" name="payStatus" value="${payment.payStatus}" readonly>
    </div>
    <div>
        <label>결제금액:</label> 
        <input type="text" name="payPrice" value="${payment.payPrice}" readonly>
    </div>
    <div>
        <label>환불여부:</label> 
        <input type="text" name="payDelyn" value="${payment.payDelyn eq 'Y' ? '환불완료' : '결제완료'}" readonly>
    </div>
    
    <button type="button" onclick="location.href='paymentForm.do'">결제하기</button>
</body>
</html>