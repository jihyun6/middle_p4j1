<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제 완료</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-success text-white">
                <h2 class="mb-0">결제 완료</h2>
            </div>
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-6">
                        <h5 class="text-muted">결제 상세 정보</h5>
                    </div>
                </div>
                
                <div class="row mb-3">
				    <div class="col-md-4">
				        <strong>컨텐츠명:</strong>
				        <p>${payment.contentTitle} 외 ${cartList.size() - 1}건</p>
				    </div>
				    <div class="col-md-4">
				        <strong>결제번호:</strong>
				        <p>${payment.payNo}</p>
				    </div>
				    <div class="col-md-4">
				        <strong>결제일자:</strong>
				        <p><fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				    </div>
				</div>
                
                <div class="row mb-3">
                    <div class="col-md-4">
                        <strong>결제수단:</strong>
                        <p>${payment.payMethod}</p>
                    </div>
                    <div class="col-md-4">
                        <strong>결제상태:</strong>
                        <p>${payment.payStatus}</p>
                    </div>
                    <div class="col-md-4">
                        <strong>결제금액:</strong>
                        <p><fmt:formatNumber value="${payment.payPrice}" pattern="#,###"/>원</p>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <button class="btn btn-primary" onclick="location.href='main.do'">메인으로</button>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>