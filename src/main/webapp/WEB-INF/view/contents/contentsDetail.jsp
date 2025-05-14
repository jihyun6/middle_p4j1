<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://kit.fontawesome.com/0c69fdf2c0.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script type="text/javascript">

function loveCheck(){
	
	if (${lovedByMemCon}){
		$('#loveUpdate i').addClass('fa-solid')
	}else{
		$('#loveUpdate i').addClass('fa-regular')
	}
}
loveCheck();
//하트버튼 클릭시(좋아요 추가 또는 좋아요 제거)
	function loveUpdate(){
		 if( $('#loveUpdate i').hasClass('fa-solid')){
	    	 loveCancel();
	     }else{
				$.ajax({
				 url: "loveUpdate.do",
		         type: "POST",
		         data: {
		        	 "contentNo": ${contents.contentNo},
		        	 "memNo": ${member.memNo},
		        	 "loveCount":${loveCount},
		        	 "boardNo":"0"
		         },
		         success: function (data) {
				     alert("좋아요")   ;
		
				    
				    	//아니냐>
					        //red로 변경 
					        $('#loveUpdate i').removeClass('fa-regular')
					        $('#loveUpdate i').addClass('fa-solid')
					        //숫자 올려 
					        count =parseInt($(".loveAdd").text());
					        count++;
					        $(".loveAdd").text(count);
				  },
				})
		}
}
	
function loveCancel(){
	 if( $('#loveUpdate i').hasClass('black')){
	    	 loveUpdate();
	    	 }else{
					$.ajax({
						url: "loveCancel.do",
			         type: "POST",
			         data: {
			        	 "contentNo": ${contents.contentNo},
			        	 "memNo": ${member.memNo},
			        	 "loveCount":${loveCount},
			        	 "boardNo":"0"
			         },
			         success: function (data) {
					     alert("좋아요취소")   ;
			        	
					     if( $('#loveUpdate i').hasClass('fa-solid')){
					    	 //색상이 red이냐 
						        //기본으로 변경 
						        $('#loveUpdate i').removeClass('fa-solid')
						        $('#loveUpdate i').addClass('fa-regular')
						        //숫자 내려 
						        count =parseInt($(".loveAdd").text());
					    	    count--;
					    	    $(".loveAdd").text(count);
					     }
					},
				})
	   }
}
function planAdd() {

// 	preventDefault();
 	$.ajax({
		url: "planUpdate.do",
     type: "POST",
     data: {
    	
    	 "boardNo":$("option:selected").val(),
    	 "conTitle":"${contents.conTitle}"
     },
     success: function (data) {
    	/* var cont =$(data);
    	 cont.append("${contents.conTitle}");
    	 $("#conAdd").append(cont);  */
	     alert("${contents.conTitle} 추가완료 ");
	     $('#myModal').modal('hide')
    	 
     },
	}) 
}


function cartAdd(){
	
	$.ajax({
		url: "cartInsert.do",
     type: "POST",
     data: {
    	 "contentNo":${contents.contentNo},
		 "contentsTypeId":${contents.contentsTypeId},
    	 "conPrice":${contents.conPrice }
    		
     },
     success: function () {
    	
	     alert("장바구니에 담겼습니다");
    	 
     },
	}) 
	
}

</script>
	

<style>
img {
	width: 300px;
	height: 250px;
}

table {
	text-align: center;
}

.fa-heart {
	color: red;
}

</style>
</head>
<body>
		<input type="hidden" name="contentNo" value="${contents.contentNo}">
		<input type="hidden" name="loveNo" value="${contents.loveCount}">
		
<div class="container mt-5" style="max-width: 1100px;">
	    <!-- 콘텐츠 제목 -->
		<div class="mb-4 text-center">
		    <h2 class="fw-bold">${contents.conTitle}</h2>
		</div>
	
		<!-- 이미지 섹션 -->
		<div class="card mb-4">
		    <img src="${contents.conFirstimage}" class="card-img-top img-fluid" alt="Content Image">
		</div>
		<!-- 좋아요 섹션 -->
		<div class="d-flex justify-content-center mb-4" id="heart">
		    <c:if test="${member.memNo == null}">
		        <button class="btn btn-outline-light text-danger black" onclick="alert('로그인후 사용하세요')" >
		            <i class="fa-regular fa-heart"></i> 
		            <span>${loveCount}</span>
		        </button>
		    </c:if>
		    <c:if test="${member.memNo != null}">
		        <c:choose>
		            <c:when test="${lovedByMemCon}" >
		                <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveCancel()">
		                    <i class="fa-solid fa-heart red" style="color:red;" >&nbsp;좋아요</i>
		                    <span class="loveAdd">${loveCount}</span>
		                </button>
		            </c:when>
		            <c:otherwise>
		                <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveUpdate()">
		                    <i class="fa-regular fa-heart" style="color:black;">&nbsp;좋아요</i>
		                    <span class="loveAdd">${loveCount}</span>
		                </button>
		            </c:otherwise>
		        </c:choose>
		    </c:if>
		</div>

    <!-- 상세 정보 섹션 -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title fw-bold">개요</h5>
            <p>${contents.conOverview}</p>
        </div>
    </div>

    <!-- 주소 및 홈페이지 -->
    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <h6 class="fw-bold">주소</h6>
                    <p>${contents.conAddr1} ${contents.conAddr2}</p>
                </div>
                <div class="col-md-6">
                    <h6 class="fw-bold">홈페이지</h6>
                    ${contents.conHomepage}
                </div>
            </div>
        </div>
    </div>

    <!--관광지 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 12}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">관광지 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>인포:</strong> ${contents.tourInfocenter}</p>
                        <p><strong>주차:</strong> ${contents.tourParking}</p>
                        <p><strong>휴무일:</strong> ${contents.tourRestdate}</p>
                        <p><strong>이용시간:</strong> ${contents.tourUsetime }</p>
                        <p><strong>가격:</strong> ${contents.conPrice}</p>
                        
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <!-- 전시 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 14}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">전시 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>인포:</strong> ${contents.exhibInfocenter }</p>
                        <p><strong>주차:</strong> ${contents.exhibParking }</p>
                        <p><strong>주차요금:</strong> ${contents.exhibParkingfee }</p>
                        <p><strong>가격:</strong> ${contents.conPrice }</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>이용시간:</strong> ${contents.exhibUsetime}</p>
                        <p><strong>휴무일:</strong> ${contents.exhibRestdate}</p>
                        <p><strong>할인정보:</strong> ${contents.exhibDiscount}</p>
                        <p><strong>소요시간:</strong> ${contents.exhibSpendtime}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <!-- 이벤트 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 15}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">이벤트 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>행사장소:</strong> ${contents.eventBookingplace }</p>
                        <p><strong>연령:</strong> ${contents.eventAgelimit}</p>
                        <p><strong>이용시간:</strong> ${contents.eventBookingplace }</p>
                        <p><strong>가격:</strong> ${contents.eventPrice }</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>시작일:</strong> ${contents.eventStartdate}</p>
                        <p><strong>종료일:</strong> ${contents.eventEnddate}</p>
                        <p><strong>소요시간:</strong> ${contents.eventPlaytime}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <!-- 레포츠 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 28}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">레포츠 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>인포:</strong> ${contents.lepoInfocenter}</p>
                        <p><strong>주차:</strong> ${contents.lepoParking}</p>
                        <p><strong>주차요금:</strong> ${contents.lepoParkingfee}</p>
                        <p><strong>가격:</strong> ${contents.lepoPrice}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>이용시간:</strong> ${contents.lepoUsetime}</p>
                        <p><strong>휴무일:</strong> ${contents.lepoRestdate}</p>
                        <p><strong>오픈기간:</strong> ${contents.lepoOpenperiod}</p>
                        <p><strong>예약방법:</strong> ${contents.lepoOpenperiod}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- 숙박 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 32}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">숙박 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>인포:</strong> ${contents.stayInfocenter}</p>
                        <p><strong>주차:</strong> ${contents.stayParking}</p>
                        <p><strong>체크인:</strong> ${contents.stayCheckintime}</p>
                        <p><strong>체크아웃:</strong> ${contents.stayCheckouttime}</p>
                        <p><strong>예약방법:</strong> ${contents.stayReservation }</p>
                        
                    </div>
                    <div class="col-md-6">
                        <p><strong>가격:</strong> ${contents.conPrice}</p>
                        <p><strong>룸타입:</strong>${contents.stayRoomtype }</p>
                        <p><strong>룸 수:</strong> ${contents.stayRoomcount}</p>
                        <p><strong>수용인원:</strong> ${contents.stayAccomcount }</p>
                        <p><strong>조리시설:</strong> ${contents.stayChkcooking }</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
     <!-- 음식점 정보 (조건부) -->
    <c:if test="${contents.contentsTypeId eq 39}">
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="fw-bold">음식점 정보</h5>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>인포:</strong> ${contents.resInfocenter}</p>
                        <p><strong>주차:</strong> ${contents.resParking}</p>
                        <p><strong>포장여부:</strong> ${contents.resPacking }</p>
                        <p><strong>가격:</strong> ${contents.resPrice}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>대표메뉴:</strong> ${contents.resFirstmenu}</p>
                        <p><strong>오픈시간:</strong> ${contents.resOpentime}</p>
                        <p><strong>휴무일:</strong> ${contents.resRestdate}</p>
                        <p><strong>예약방법:</strong> ${contents.resReservation}</p>
                        <p><strong>전체메뉴:</strong> ${contents.resTreatmenu}</p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
	
	<!-- 이미지 지도를 표시할 div 입니다 -->
	<div id="map" style="width:900px;height:350px; margin:auto;">
		
	</div>

    <!-- 액션 버튼 -->
    <div class="d-flex justify-content-between">
    	<c:if test="${empty member.memNo}">
	        <button type="button" class="btn btn-outline-primary" onclick="alert('로그인 후 사용 가능합니다')">장바구니 담기</button>
	        <button type="button" class="btn btn-primary" data-bs-toggle="modal" onclick="alert('로그인 후 사용 가능합니다')">내 일정에 추가</button>
        </c:if>
        <c:if test="${not empty member.memNo && member.memNo != 1}">
	        <button type="button" class="btn btn-outline-primary" onclick="cartAdd()">장바구니 담기</button>
	        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">내 일정에 추가</button>
      	</c:if>
        <c:if test="${member.memNo == 1}">
            <a href="#" onclick="getPath('/contentsDelete.do?contentNo=${contents.contentNo}&contentsTypeId=${contents.contentsTypeId}')">
                <button class="btn btn-danger" onclick="alert(삭제되었습니다!)">삭제하기</button>
            </a>
        </c:if>
    </div>
   <!-- 모달 창 -->
   <c:if test="${member.memNo != 1}">
    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- 모달 헤더 -->
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">일정 리스트</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- 모달 바디 -->
                <div class="modal-body">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>선택하기</option>
                        <c:forEach var="plan" items="${planList}">
                            <option value="${plan.boardNo}" selected>${plan.boardName}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- 모달 푸터 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="planAdd()">추가</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</div>


    <script>
    	// 여기서 직접 다운로드 받고, 다 받을때까지 기다렸다가 그려줍니다
    	const script = document.createElement("script"); // html에 script라는 태그(Element)를 만든다
    	script.async =true;
    	script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=bbbb5ddb701600ead3a6e1ca8df07686&autoload=false";
    	document.head.appendChild(script);

    	script.onload = () => {
    		window.kakao.maps.load(() => {
    			const container = document.getElementById("map");
    			const options = {
    				center: new window.kakao.maps.LatLng(${contents.conMapy}, ${contents.conMapx}),
    				level: 3,
    				
    			};
              
          		const map = new window.kakao.maps.Map(container, options);
          		
          		// 마커가 표시될 위치입니다 
          		const markerPosition  = new kakao.maps.LatLng(${contents.conMapy}, ${contents.conMapx}); 

          		// 마커를 생성합니다
          		const marker = new kakao.maps.Marker({
          		    position: markerPosition,
          		});

          		// 마커가 지도 위에 표시되도록 설정합니다
          		marker.setMap(map);
          		
          		// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
          		const iwContent = '<div style="padding:5px; text-align:center;">${contents.conTitle}</div>', 
          	    iwPosition = new kakao.maps.LatLng(${contents.conMapx}, ${contents.conMapy}); //인포윈도우 표시 위치입니다

	          	// 인포윈도우를 생성합니다
	          	const infowindow = new kakao.maps.InfoWindow({
	          	    position : iwPosition, 
	          	    content : iwContent 
	          	});
	          	  
	          	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	          	infowindow.open(map, marker); 
          		

    		})
    	}
    
    </script>
    <div>
    
    <div class="container mt-5" style="max-width: 1100px;">
		<h2 class="mb-4">리뷰 목록</h2>
		<c:if test="${not empty member.memNo || member.memNo==1}">
		<button type="button" id="reviewInsert" class="btn btn-primary" href="#" onclick="getPath('/boardInsert.do?codeNo=3&conTitle=${contents.conTitle}&contentNo=${contents.contentNo}')">글등록</button>
		</c:if>
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>별점</th>
					<th></th>
					<th></th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty boardList}">
						<p class="text-muted">등록된 리뷰가 없습니다.</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td>${board.boardName}</td>
								<td>${board.boardContent}</td>
								<td>${board.memName}</td>
								<td><c:forEach begin="1" end="${board.boardStar}">★</c:forEach>
								</td>
								<td>${board.boardDate}</td>
								<td>${board.contentsTypeName}</td>
								<td><a href="#" onclick="getPath('/boardDetail.do?boardNo=${board.boardNo}')"
									class="btn btn-primary btn-sm">상세보기</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
  
    <%-- <jsp:include page="../board/review/reviewList.jsp" flush="true">
	<jsp:param value="${contents.contentNo}" name="contentNo"/>
	</jsp:include> --%>
	</div>
</body>
</html>