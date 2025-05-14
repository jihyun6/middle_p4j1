
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script src="/P4J1_Project/js/jquery-3.7.1.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/P4J1_Project/js/jquery.serializejson.min.js"></script>

<style type="text/css">
body {
	margin: 0;
}

* {
	box-sizing: border-box;
}

p, span {
	margin: 0;
}

a {
	color: black;
}

img {
	display: block;
	width: 80%;
	height: 80px;
	margin: auto;
}

.cart {
	width: 80%;
	margin: auto;
	padding: 30px;
}

.cart ul {
	background-color: whitesmoke;
	padding: 30px;
	margin-bottom: 50px;
	border: whitesmoke solid 1px;
	border-radius: 5px;
	font-size: 13px;
	font-weight: 300;
}

.cart ul :first-child {
	color: limegreen;
}

table {
	border-top: solid 1.5px black;
	border-collapse: collapse;
	width: 100%;
	font-size: 14px;
}

thead {
	text-align: center;
	font-weight: bold;
}

tbody {
	font-size: 12px;
}

td {
	padding: 15px 0px;
	border-bottom: 1px solid lightgrey;
}

.cart_list_detail :nth-child(3) {
	vertical-align: top;
}

.cart_list_detail :nth-child(3) a {
	font-size: 12px;
}

.cart_list_detail :nth-child(3) p {
	margin-top: 6px;
	font-weight: bold;
}

.cart_list_smartstore {
	font-size: 12px;
	color: gray;
}

.cart_list_option {
	vertical-align: top;
	padding: 5px;
}

.cart_list_option p {
	margin-bottom: 25px;
	position: relative;
}

.cart_list_option p::after {
	content: "";
	width: 90%;
	height: 1px;
	background-color: lightgrey;
	left: 0px;
	top: 25px;
	position: absolute;
}

.cart_list_optionbtn {
	background-color: white;
	font-size: 10px;
	border: lightgrey solid 1px;
	padding: 7px;
}

.cart_list_detail :nth-child(4), .cart_list_detail :nth-child(5),
	.cart_list_detail :nth-child(6) {
	border-left: 2px solid whitesmoke;
}

.cart_list_detail :nth-child(5), .cart_list_detail :nth-child(6) {
	text-align: center;
}

.cart_list_detail :nth-child(5) button {
	background-color: limegreen;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 4px 8px;
	font-size: 12px;
	margin-top: 5px;
}

.price {
	font-weight: bold;
}

.cart_mainbtns {
	width: 420px;
	height: 200px;
	padding-top: 40px;
	display: block;
	margin: auto;
}

.cart_bigorderbtn {
	width: 200px;
	height: 50px;
	font-size: 16px;
	margin: auto;
	border-radius: 5px;
}

.cart_bigorderbtn.left {
	background-color: white;
	border: 1px lightgray solid;
}

.cart_bigorderbtn.right {
	background-color: limegreen;
	color: white;
	border: none;
}

.total-summary {
        background-color: #f8f9fa;
        font-weight: bold;
    }

    .total-amount-cell {
        background-color: #28a745 !important;
        color: white !important;
        text-align: center !important;
        padding: 15px !important;
    }

    .total-amount-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 5px;
    }

    .total-amount-wrapper .price {
        font-size: 1.5rem;
        font-weight: bold;
        color: white !important;
    }

    .total-amount-wrapper .currency {
        font-size: 1rem;
        margin-left: 5px;
        color: white !important;
    }


</style>
<script type="text/javascript">
	
	//처음 로딩 시 총합계값 구하기
	cartSum();
	
	//총합계를 계산하는 로직 구현
function cartSum(){
    sum = 0;
    let i=0;
    $('span[id^=cartPrice]').each(function(){
        temp = $(this).text().replace(/,/g, '');  // 쉼표 제거
        sum += parseInt(temp);
        i++;
    })
    
    // 3자리마다 쉼표 추가하는 함수
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    
    //총합계 구하기
    $('#totalSum').text(numberWithCommas(sum)); 
    $('#amount').val(sum);
    
    //콘텐츠 목록의 값 구하기
    cartName(i);
}
	
	function cartName(  i ){
		orderName = '';
		orderName = $('tbody >  tr:first > td:eq(2)').text();
		orderName = orderName + '외' + i + '건';
		
		$('#orderName').val(orderName);
	}
	
	//등급별에 따른 상품금액 변화
	$('.cartRank').on('change', function(){
		
		//현재 선택된 등급의 값 구하기
		cartRankVal = $(this).val();
		
		//현재 선택된 행의 값 가져오기
		cartRankNo = $(this).data('idx');
		
		//인원수 구하기
		headCountStr = $('#headCount' + cartRankNo).val();
		
		//현재 인원수(String -> int로 변환)
		headCountVal = parseInt(headCountStr);
		
		//DB에서 넘어온 상품금액 구하기
		cartDefaultPrice = $('#cartDefault' + cartRankNo).val();
		
		//현재 상품금액(String -> int로 변환)
		cartDefaultPrice = parseInt(cartDefaultPrice);
		
		//등급변화에 따른 상품금액 변경
		if(cartRankVal == 1) cartPriceVal = cartDefaultPrice + 60000;
		if(cartRankVal == 2) cartPriceVal = cartDefaultPrice + 40000;
		if(cartRankVal == 3) cartPriceVal = cartDefaultPrice;
		
		//변경된 상품금액 값 구하기
		cartChange = headCountVal * cartPriceVal;
		
		$('#cartPrice' + cartRankNo).text(cartChange);
		
		//총합계 구하기
		cartSum();
	})
	
	$('.cartHeadcount').on('change', function(){
		
		//현재 선택된 행의 값 가져오기
		cartRankNo = $(this).data('idx');
		
		//현재 선택된 등급의 값 구하기
		cartRankVal = $('#cartRank' + cartRankNo).val();
		
		//인원수 구하기
		headCountStr = $('#headCount' + cartRankNo).val();
		
		//현재 인원수(String -> int로 변환)
		headCountVal = parseInt(headCountStr);
		
		
		//DB에서 넘어온 상품금액 구하기
		cartDefaultPrice = $('#cartDefault' + cartRankNo).val();
		
		//현재 상품금액(String -> int로 변환)
		cartDefaultPrice = parseInt(cartDefaultPrice);
		
		//등급변화에 따른 상품금액 변경
		if(cartRankVal == 1) cartPriceVal = cartDefaultPrice + 60000;
		if(cartRankVal == 2) cartPriceVal = cartDefaultPrice + 40000;
		if(cartRankVal == 3) cartPriceVal = cartDefaultPrice;
		
		//변경된 상품금액 값 구하기
		cartChange = headCountVal * cartPriceVal;
		
		$('#cartPrice' + cartRankNo).text(cartChange);
		
		//총합계 구하기
		cartSum();
		
	})
	
	$('#main').on('click', function() {
		var tr = $(this).parent().parent();
		var td = tr.children();
		var memId = td.eq(0).text();

		location.href = "main.do";		
	})

	//비동기로 값을 찍는거
	function cartPro() {
		$.ajax({
			url : "cartList.do",
			method : "POST",
			dataType : "json",
			data : {
				memNo : 5
			},
			success : function(res) {

				console.log(res.list);

			},

			error : function(xhr) {
				alert("오류 발생 : " + xhr.status);
			},

		});
		
		$('#send').on('click', function() {
			var tr = $(this).parent().parent();
			var td = tr.children();
			var memId = td.eq(0).text();
			let amount = '';

			//amount 값 가져오기
			amount = $('#totalSum').text(); 
			location.href = "payment.do?amount=" + amount + "&orderName=외 7건";
		})
		
		$(function() {
			$('#input').on('click', function() {
				location.href = "cart"
			})
		})
	
		
	}

	/* function deleteButton() {
	 //체크여부 확인
	 var checkName = $('input:checkbox[name="checkIs"]').is(':checked');
	
	 //체크가 되어이는 체크박스의 tr를 찾아서 지운다.
	 deltr =  $('input:checkbox[name="checkIs"]').parent();
	 console.log(deltr);
	
	 //해당 요소를 지운다.
	 deltr.remove("delyn");
	
	 console.log(deltr);
	 //cartNo = $('#cartNo').text().trim();  
	 //location.href = "cartDelete.do?cartNo="+cartNo;
	 } */

	 function deleteButton() {
		    // 체크된 체크박스 찾기
		    $('input:checkbox[name="checkIs"]:checked').each(function() {
		        const $tr = $(this).closest('tr');
		        const cartRankNo = $(this).data('idx');
		        const cartNo = $('#cartNo' + cartRankNo).text().replace('no.', '').trim();

		        $.ajax({
		            url: "cartDelete.do",
		            type: "GET",
		            data: { "cartNo": cartNo },
		            success: function () {
		                $tr.remove(); // 현재 행만 제거
		                cartSum(); // 삭제 후 총계 재계산
		            },
		            error: function(xhr) {
		                console.error("삭제 실패:", xhr.status);
		                alert("삭제 중 오류가 발생했습니다.");
		            }
		        });
		    });
		}
</script> 

</head>
<body class="sijunBody">
	<br>
	<br>
	<section class="cart">
		<div class="cart_information">
			<ul>
				<li>즐거운 여행을 위해서 모두가 배려해주세요.</li>
				<li>가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.</li>
				<li>mbti별 여행지입니다 취향에 맞게 골라주세요.</li>
			</ul>
		</div>

		<form action="payment.do" method="post">
			<table border="1" class="cart_list">


				<thead>
					
					<tr>
						<td></td>
						<td colspan="2">상품정보</td>
						<td>옵션</td>
						<td>인원수</td>
						<td>상품금액</td>
						<!-- <td>등급</td> -->
					</tr>

				</thead>
				<tbody>
				    <c:forEach var="cart" items="${cartList}">
				        <tr class="cart_list_detail" name="checkIs" id="checkIs">
				            <td><input type="checkbox" name="checkIs" id="checkIs" data-idx="${cart.cartNo}"></td>
				            <td id="cartNo${cart.cartNo}" name="cartNo" data-idx="${cart.cartNo}">no.${cart.cartNo}</td>
				            <td><a href="#" id="cartName${cart.cartNo}">${cart.conTitle}</a><span
				                class="contentNo"></span></td>
				
				            <td class="cart_list_option">
				                <p>등급별</p>
				                <input type="number" class="cartRank" data-idx="${cart.cartNo}" id="cartRank${cart.cartNo}"
				                name="cartRank" min="1" max="3"
				                value="${cart.cartRank}">
				            </td>
				            
				            <td class="cart_list_option">
				                <p>인원수</p>
				                <input type="number" class="cartHeadcount"
				                 name="cartHeadcount" min="1" max="100"  id="headCount${cart.cartNo}" data-idx="${cart.cartNo}"
				                 value="${cart.cartHeadcount}">
				            </td>
				
				            <td>
							    <span class="price cartPrice" id="cartPrice${cart.cartNo}" name="cartPrice">
							        <fmt:formatNumber value="${cart.cartPrice}" pattern="#,###"/>
							    </span><br>
							    <input type="hidden" id="cartDefault${cart.cartNo}" value="${cart.cartPrice}">
							</td>
				        </tr>
				    </c:forEach>
				    
					<tr class="cart_list_detail total-summary">
					    <td colspan="5" class="text-end pe-4">
					        <strong class="fs-5">총 결제 금액</strong>
					    </td>
					    <td class="total-amount-cell">
					        <div class="total-amount-wrapper">
					            <span class="price cartPrice" id="totalSum">
					                <fmt:formatNumber value="${totalAmount}" pattern="#,###"/>
					            </span>
					            <span class="currency">원</span>
					        </div>
					        <input type="hidden" id="amount" name="amount" value="${totalAmount}">
					    </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<button type="button" class="cart_list_optionbtn" value="삭제"
								id="delete" onclick="deleteButton()">선택상품 삭제</button>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>

			</table>
			<div class="cart_mainbtns">
				<button type="button" id="main" value="메인" class="cart_bigorderbtn left">쇼핑 계속하기</button>
				<form action="${pageContext.request.contextPath}/payment.do" method="post">
			    <!-- 장바구니의 모든 항목에 대한 정보 추가 -->
			    <c:forEach var="cart" items="${cartList}" varStatus="status">
			        <input type="hidden" name="contentNo" value="${cart.contentNo}">
			        <input type="hidden" name="contentsTypeId" value="${cart.contentsTypeId}">
			    </c:forEach>
			    <input type="hidden" name="amount" id="amount" value="${totalAmount}">
			    <input type="hidden" name="orderName" id="orderName" value="${cart.conTitle} 외 ${cartList.size()-1}건">
			    <button type="submit" class="cart_bigorderbtn right">결제하기</button>
			</form>
			</div>
		</form>
		<!-- 장바구니 내용 출력  -->
		<div id="cartlist"></div>
	</section>
</body>
</html>
