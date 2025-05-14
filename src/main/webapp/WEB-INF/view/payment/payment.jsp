<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>결제 확인</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

   <div class="container mt-5">
       <h2 class="mb-4">결제 확인</h2>
       
		      <form id="paymentForm" method="post">
		    <!-- 디버깅을 위한 데이터 출력 -->
		    <div style="display:none">
		        <p>cart 데이터: ${cartList}</p>
		        <p>첫번째 상품 contentNo: ${cartList[0].contentNo}</p>
		        <p>첫번째 상품 contentsTypeId: ${cartList[0].contentsTypeId}</p>
		    </div>
		
		    <!-- hidden 필드 -->
		    <input type="hidden" name="memNo" value="${member.memNo}" required>
		    <input type="hidden" name="contentNo" value="${cartList[0].contentNo}" required>
			<input type="hidden" name="contentsTypeId" value="${cartList[0].contentsTypeId}" required>
		    <input type="hidden" name="payPrice" value="${totalAmount}" required>
		    
           
           <!-- 상품 목록 -->
           <div class="card mb-4">
               <div class="card-header bg-primary text-white">
                   <h5 class="mb-0">예약 정보</h5>
               </div>
               <div class="card-body">
                   <c:forEach var="item" items="${cartList}" varStatus="status">
                       <div class="mb-3 border-bottom pb-2">
                           <div class="fw-bold">${item.orderName}</div>
                           <div class="text-muted">
                               <span class="me-3">가격: ${item.price}원</span>
                           </div>
                       </div>
                   </c:forEach>
                   <div class="mt-3 text-end fw-bold">
                       총 결제금액: ${totalAmount}원
                   </div>
               </div>
           </div>
   
           
           
           
           <!-- 결제 정보 -->
           <div class="row mb-4">
               <div class="col-md-6">
                   <div class="form-group">
                       <label class="form-label">결제일자</label>
                       <!-- JSTL fmt 라이브러리 추가하여 자바 코드 사용하지 않고 날짜 출력 -->
						<fmt:formatDate value="${pagecontext.request.time }" pattern="yyyy-MM-dd" var="currentDate"/>
						<input type="text" class="form-control bg-light" value="${currentDate}" readonly>
                   </div>
               </div>
               <div class="col-md-6">
                   <div class="form-group">
                       <label class="form-label">결제수단</label>
                       <input type="text" class="form-control bg-light" value="카카오페이" readonly>
                   </div>
               </div>
           </div>
           
           <!-- 버튼 영역 -->
           <div class="d-grid gap-2">
               <button type="button" class="btn btn-warning btn-lg" onclick="requestPay()">
                   <img src="image/payment_icon_yellow_medium.png" alt="카카오페이" height="24" class="me-2">
                   카카오페이로 결제하기
               </button>
               <button type="button" class="btn btn-secondary" onclick="history.back()">
                   이전 페이지로
               </button>
           </div>
       </form>
   </div>

   <script>
   function requestPay() {
	    console.log("cart 정보:", ${cartList});
	    console.log("form 데이터:", $('#paymentForm').serialize());

	    if(!checkFormData()) return;
	    
	    if(confirm('결제를 진행하시겠습니까?')) {
	        // 모든 hidden input 값 수집
	        var contentNos = $('input[name="contentNo"]').map(function() {
	            return $(this).val();
	        }).get();
	        
	        var contentsTypeIds = $('input[name="contentsTypeId"]').map(function() {
	            return $(this).val();
	        }).get();

	        $.ajax({
	            type: 'POST',
	            url: '${pageContext.request.contextPath}/payment.do',
	            data: {
	                orderName: '${cart.conTitle}' + 
	                    (${cartList.size()} > 1 ? ' 외 ' + (${cartList.size()-1}) + '건' : ''),
	                amount: '${totalAmount}',
	                contentNo: contentNos.join(','),
	                contentsTypeId: contentsTypeIds.join(','),
	                memNo: '${member.memNo}'
	            },
	            success: function(response) {
	                console.log("결제 성공 응답:", response);
	                window.location.href = response;
	            },
	            error: function(xhr, status, error) {
	                console.error('Payment Error:', xhr.responseText);
	                alert(xhr.responseText);
	            }
	        });
	    }
	}


	function checkFormData() {
	   var required = ['memNo', 'contentNo', 'contentsTypeId', 'payPrice'];
	   for(var i = 0; i < required.length; i++) {
	       if(!$('input[name="' + required[i] + '"]').val()) {
	           alert('필수 정보가 누락되었습니다.');
	           return false;
	       }
	   }
	   return true;
	}

	function savePaymentInfo(form, redirectUrl) {
	   $.ajax({
	       type: 'POST',
	       url: 'paymentInsert.do',
	       data: form.serialize() + '&payMethod=카카오페이&payStatus=결제대기&payDelyn=N',
	       success: function(response) {
	           window.location.href = redirectUrl;
	       },
	       error: function() {
	           alert('결제 정보 저장 중 오류가 발생했습니다.');
	       }
	   });
	}
   </script>
</body>
</html>