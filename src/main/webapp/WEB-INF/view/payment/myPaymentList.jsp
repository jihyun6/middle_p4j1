<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>나의 결제 내역</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">나의 결제 내역</h2>
    
    <c:if test="${empty paymentList}">
        <div class="alert alert-info">
            결제 내역이 없습니다.
        </div>
    </c:if>
    
    <c:if test="${not empty paymentList}">
        <div class="row">
            <c:forEach var="payment" items="${paymentList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="${payment.contentFirstImage}" 
                             class="card-img-top" 
                             alt="${payment.contentTitle}"
                             style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${payment.contentTitle}</h5>
                            <p class="card-text">
                                ${payment.contentOverview}
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <span class="text-muted">
                                    <fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>
                                </span>
                                <strong>
                                    <fmt:formatNumber value="${payment.payPrice}" pattern="#,###"/>원
                                </strong>
                            </div>
                            <div class="mt-2">
                                <span class="badge bg-${payment.payStatus == '결제완료' ? 'success' : 'warning'}">
                                    ${payment.payStatus}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>