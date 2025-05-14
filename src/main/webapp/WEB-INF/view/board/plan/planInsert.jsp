<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>여행일정 등록</title>

<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script>

path = function(){
	let frm = $('#frm');
	postPath('/boardInsert.do', frm);
	alert("등록되었습니다!");
}




let prevConcon = null;
function cont(contentsTypeId){
		console.log(contentsTypeId);
		
	  	 $.ajax({
	 		 url : "contentsListType.do",
	 		 type : "POST",
	 		 data : {
	 			 "contentsTypeId" : contentsTypeId
	 		 	},
	 		 success : 
	 			function(data){
	 				/* alert("성공 : " + JSON.stringify(data)); */
	 				// 이전 컨텐츠콘 있는 있는 경우 삭제하기
	 				if (prevConcon != null) {
	 					prevConcon.hide();
	 				}
	 				
	 				$('#concon' + contentsTypeId).html(data);
	 	            $('#concon' + contentsTypeId).show();
	 	            /* $('#concon' + contentsTypeId).next().hide(); */
	 	            
	 	           	prevConcon = $('#concon' + contentsTypeId);
	 			 },
	 		error : function(xhr, status, error) {
	 				 alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
	 			},
	 	 }) 
	}





function cal(){	
	
 $.ajax({
	 		 url : "Calendar.do",
	 		 type : "POST",
	 		 data : {
	 			 "memNo" : ${member.memNo}
	 		 	},
	 		 success : 
	 			function(data){
	 				console.log(data);
	 				$('#cal').html(data);
	 				$('.btn-close').hide();
	 			 },
	 		error : function(xhr, status, error) {
	 				 alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
	 			},
	 	 }) 

}


function save(){

	sd = $('#date').val();
	ed = $('#endDate').val();  
	
	$("#travelStartDate").val(sd);
	$("#travelEndDate").val(ed);
	
	$('#cal').html("");
	
}


</script>	


<body>


	
	<div class="container mt-12">
		<h2 class="mb-4">여행일정 계획</h2>
		<form id="frm" method="post">
			<input type="hidden" name="codeNumber" value="${codeNumber}">
			<input type="hidden" name="memNo" value="${member.memNo}">

				<div class="col-md-12">
										
				
				
					<label class="form-label">여행이름</label> <input type="text"
						class="form-control" name="boardName"> <br> 
					
						<label class="form-label">여행메모</label>
						<textarea name="boardContent" cols="5" rows="5" class="form-control" style="resize: none;"></textarea>
				
					<br>
					<br>
					<div class="col-md-12">
						<h5>여행 시작일: <input type="text" id="travelStartDate"name="travelStart">
						여행 종료일: <input type="text" id="travelEndDate" name="travelEnd">&nbsp;&nbsp;
						<button type="button" onclick="cal()" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#myModal">
							<i class="bi bi-calendar2-check"></i> 날짜선택
						</button></h5>
					</div>
					<div class="col-md-12" id="cont">
					<br>
						<h4><label class="form-label"><i class="bi bi-bookmark-heart"></i>&nbsp; 대표장소</label>
						<input type="hidden" id="contentNo" name="contentNo">
						<input type="hidden" id="contentsTypeId" name="contentsTypeId">
						<input type="text" id="conTitle" name="conTitle"></h4>
						
						<c:forEach var="plan" items="${contentsList}" varStatus="status">
							<div class="btn-group">
								<input type="hidden"
									value="${contentsList[status.index].contentsTypeId}">
								<button type="button" value="${plan.contentsTypeId}"
									onclick="cont(${plan.contentsTypeId})"
									class="btn btn-outline-primary " data-bs-toggle="collapse"
									data-bs-target="#demo">${plan.contentsTypeName}</button>
							</div>

							<div class="collapse" id="concon${plan.contentsTypeId}"></div>
						</c:forEach>
					</div>
				</div>
		</form>
		
		<input type="button" onclick=path() value="일정등록" class="btn btn-primary w-20">
	</div>
</body>

	<!-- The Modal -->
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content" style="width:850px; , overflow:hidden">

								<!-- Modal Header -->
								<div class="modal-header" style="width:800px;">
									<h4 class="modal-title">날짜선택</h4>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>

								<!-- Modal body -->
								<div class="modal-body" style="width:700px;">
									<div id="cal"></div>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer" style="width:800px;">
									<button onclick="save()" type="button" class="btn btn-danger"data-bs-dismiss="modal" id="planSave">등록</button>
								</div>
						
							</div>
						</div>
					</div>
					<br> <br>

<jsp:include page="/WEB-INF/view/template/prescript_.jsp"></jsp:include>


<!-- 화면 해상도에 따라 글자 크기 대응(모바일 대응) -->
<!-- jquery CDN -->
 <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- fullcalendar CDN -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar ì¸ì´ CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>



</html>