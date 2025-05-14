<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script type="text/javascript">

/* var mypage = ${member.memNo == mypage.memNo}; */

let admin = ${member.memNo};

function planListClick(){  
    $.ajax({
        url : "mypagePlanList.do",
        type : "POST", 
        data : {
            "memNo" : ${member.memNo},  
        },
        success : function(data) {
            $("#travelPlan").html(data);  
        	if(admin==1){
				$('#planInserttt').hide();        		
        	}
        },
        error : function(xhr, status, error) {
            alert("에러 발생: " + error);
        },
    })
}

function talkListClick(){  
    $.ajax({
        url : "mypageTalkList.do",
        type : "POST", 
        data : {
            "memNo" : ${member.memNo},
        },
        success : function(data) {
            $("#talk").html(data);
        },
        error : function(xhr, status, error) {
            alert("에러 발생: " + error);
        },
    })
}
function review(){  
    $.ajax({
        url : "mypageReviewList.do",
        type : "POST", 
        data : {
            "memNo" : ${member.memNo},
        },
        success : function(data) {
            $("#review").html(data);
        },
        error : function(xhr, status, error) {
            alert("에러 발생: " + error);
        },
    })
}
function love(){  
    $(".tab-pane").removeClass("active");
    $(".tab-pane").removeClass("show");
    $("#love").addClass("active show");
    $.ajax({
        url : "mypageLoveList.do",
        type : "POST", 
        data : {
            "memNo" : ${member.memNo},
        },
        success : function(data) {
            $("#love").html(data);
        },
        error : function(xhr, status, error) {
            alert("에러 발생: " + error);
        },
    })
}

function Payment() {
    console.log("Payment function called");
    $(".tab-pane").removeClass("active show");
    $("#payment").addClass("active show");
    $.ajax({
        url: "myPaymentList.do",
        type: "POST",
        data: {
            "memNo": ${member.memNo},
        },
        success: function(data) {
            console.log("AJAX success", data);
            $("#payment").html(data);
        },
        error: function(xhr, status, error) {
            console.error("AJAX error", error);
            $("#payment").html('<div class="alert alert-danger">결제 내역 조회 중 오류가 발생했습니다.</div>');
        },
    })
}
function coupon(){
	
	alert("CLICK");
	$(".tab-pane").removeClass("active");
	$(".tab-pane").removeClass("show");
	$("#coupon").addClass("active show");
	$.ajax({
	    url : "mypageCouponList.do",
	    type : "POST", 
	    data : {
	    	"memNo" : ${member.memNo},
	    		},
    success : 
    	function(data) {
    	alert("성공");
    	 $("#coupon").html(data);
    },
    
    error : function(xhr, status, error) {
        alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
    },
})
	
}
</script>



<title>회원 상세 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
.profile-img {
    max-width: 200px;
    max-height: 200px;
    border-radius: 50%;
    object-fit: cover;
}

.info-card {
    background-color: #f8f9fa;
    border-left: 4px solid #007bff;
    margin-bottom: 15px;
}

.text-muted {
    font-size: 1rem;
    font-weight: normal;
    color: #6c757d;
} 
</style>
</head>
<body>
	<div class="container-fluid py-4">
		<div class="row">
			<!-- 프로필 섹션 -->
			<div class="col-md-4">
				<div class="card shadow-sm">
					<div class="card-body text-center">
						<!-- 프로필 이미지 -->
						<img src="${member.memProUrl != null ? member.memProUrl : 'image/P_pro.jpg'}"
							alt="프로필 이미지" class="profile-img mb-3">
						<h3 class="card-title">${member.memName}</h3>
						<p class="text-muted">${member.memAlias}| ${member.memMbti}</p>
					</div>
				</div>

				<!-- 기본 정보 카드 -->
				<div class="card mt-3 shadow-sm">
					<div class="card-header bg-primary text-white">기본 정보</div>
					<div class="card-body">
						<div class="info-card p-2 mb-2">
							<strong>회원번호:</strong> ${member.memNo}
						</div>
						<div class="info-card p-2 mb-2">
							<strong>아이디:</strong> ${member.memId}
						</div>
						<div class="info-card p-2 mb-2">
							<strong>성별:</strong> ${member.memGender}
						</div>
						<div class="info-card p-2 mb-2">
							<strong>나이:</strong> ${member.memAge}세
						</div>
					</div>
				</div>
			</div>

			<!-- 연락처 및 계정 정보 섹션 -->
			<div class="col-md-8">
				<div class="card shadow-sm mb-3">
					<div class="card-header bg-primary text-white">연락처 및 계정 정보</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="info-card p-2 mb-2">
									<strong>전화번호:</strong> ${member.memTel}
								</div>
								<div class="info-card p-2 mb-2">
									<strong>이메일:</strong> ${member.memEmail}
								</div>
								<div class="info-card p-2 mb-2">
									<strong>마일리지:</strong>
									<fmt:formatNumber value="${member.memMileage}" type="number" />
									원
								</div>
							</div>
							<div class="col-md-6">
								<div class="info-card p-2 mb-2">
									<strong>가입일자:</strong>
									<fmt:formatDate value="${member.memJoinDate}"
										pattern="yyyy-MM-dd" />
								</div>
								<div class="info-card p-2 mb-2">
									<strong>회원 권한:</strong> ${member.memAuth == 1 ? '일반 회원' : '관리자'}
								</div>
								<div class="info-card p-2 mb-2">
									<strong>탈퇴 여부:</strong> ${member.memDelyn == 'N' ? '활동 중' : '탈퇴'}
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 추가 정보 섹션-->
				<div class="card shadow-sm">
					<div class="card-header bg-primary text-white">조회하기</div>
					<div class="card-body">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="btn btn-outline-primary" id="reservation-tab"
									data-bs-toggle="tab" data-bs-target="#reservation"
									type="button" role="tab" onclick="planListClick()">여행 일정</button>
							</li>
							
							<li class="nav-item" role="presentation">
								<button class="btn btn-outline-primary" id="purchase-tab" data-bs-toggle="tab"
									data-bs-target="#purchase" type="button" role="tab" onclick="talkListClick()">여행톡</button>
										
							</li>
							<li class="nav-item" role="presentation">
								<button class="btn btn-outline-primary" id="board-tab" data-bs-toggle="tab"
									data-bs-target="#board" type="button" role="tab" onclick="review()">리뷰</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="btn btn-outline-primary" id="love-tab" data-bs-toggle="tab"
									data-bs-target="#board" type="button" role="tab" onclick="love()">좋아요</button>
							</li>
							    <li class="nav-item" role="presentation">
						        <button class="btn btn-outline-primary" id="payment-tab" data-bs-toggle="tab"
						            data-bs-target="#payment" type="button" role="tab" onclick="Payment()">예약 내역</button>
						    </li>
<!-- 							<li class="nav-item" role="presentation"> -->
<!-- 								<button class="btn btn-outline-primary" id="love-tab" data-bs-toggle="tab" -->
<!-- 									data-bs-target="#board" type="button" role="tab" onclick="coupon()">쿠폰함</button> -->
<!-- 							</li> -->
						</ul>
						<div class="tab-content mt-3" id="myTabContent">
							<div class="tab-pane fade show active" id="reservation"
								role="tabpanel">
								<div id="travelPlan">
									<!--여행일정계획 리스트 출력-->
								</div>
							</div>
							
							<div class="tab-pane fade" id="purchase" role="tabpanel">
								<div id="talk">
									<!--여행톡 리스트 출력-->
								</div>
							</div>
							
							<div class="tab-pane fade" id="board" role="tabpanel">
								<div id="review">
									<!--리뷰 리스트 출력-->
								</div>
							</div>
							
							<div class="tab-pane fade" id="love" role="tabpanel">
								<div id="love">
									<!--좋아요 리스트 출력-->
								</div>
							</div>

							<div class="tab-pane fade" id="payment" role="tabpanel">
						        <div id="payment">
						            <!--결제 내역 리스트 출력-->
						        </div>
						    </div>
							</div>
							<div class="tab-pane fade" id="love" role="tabpanel">
								<div id="coupon">
									<!--쿠폰 리스트 출력-->
								</div>
							</div>


							<div class="tab-pane fade" id="love" role="tabpanel">
								<div id="reservation">
									<!--예약내역 리스트 출력-->
								</div>
							</div>
							<div class="tab-pane fade" id="love" role="tabpanel">
								<div id="coupon">
									<!--쿠폰 리스트 출력-->
								</div>
							</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>