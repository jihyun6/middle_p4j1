<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
    <c:if test="${empty paymentList}">
        <div class="alert alert-info text-center">
            결제 내역이 없습니다.
        </div>
    </c:if>

    <c:if test="${not empty paymentList}">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <c:forEach var="payment" items="${paymentList}" varStatus="status">
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">
                                ${payment.contentTitle}
                            </h5>
                            <p class="card-text">
                                <strong>결제 날짜:</strong>
                                <fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd HH:mm"/>
                            </p>
                            <p class="card-text">
                                <strong>결제 금액:</strong>
                                <fmt:formatNumber value="${payment.payPrice}" pattern="#,###"/>원
                            </p>
                            <span class="badge ${payment.payStatus == '결제완료' ? 'bg-success' : 'bg-warning'}">
                                ${payment.payStatus}
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>