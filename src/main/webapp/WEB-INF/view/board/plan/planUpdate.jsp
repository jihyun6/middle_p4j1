<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	path = function(){
		let frm = $('#frm');
		postPath('/boardUpdate.do?boardNo=${board.boardNo}', frm);
	}
})
</script>

<style type="text/css">
.modal-content{
	width : 200px;
}

</style>


</head>
<body>

	<form id="frm">
		<div class="container mt-12">
			<h2 class="mb-4">여행일정 계획</h2>
				<input type="hidden" value="${board.boardNo}" readonly="readonly">

				<div class="col-md-12">
					<label class="form-label">작성자</label>
						<input type="text" value="${board.memName}" class="form-control w-50" readonly="readonly"> <br> 
						<label class="form-label">여행이름</label>
						<input type="text" value="${board.boardName}" class="form-control" name="boardName"> <br> <label class="form-label">내용</label>
				<div style="display: flex; gap: 20px; align-items: flex-start;">
				    <!-- 텍스트 영역 -->
				    <textarea name="boardContent" cols="5" rows="10" class="form-control" style="resize: none; flex: 1;">${board.boardContent}
				    </textarea>
				
				    <!-- 리스트 그룹 -->
				    <div class="list-group" style="flex: 1;">
				        <c:forEach var="planCon" items="${planConList}">
				            <a href="#" class="list-group-item list-group-item-action">${planCon.conTitle}</a>
				        </c:forEach>
				    </div>
				</div>
					
					<div class="col-md-12">
						<h5>여행 시작일: <input type="text" id="travelStartDate"name="travelStart" value="${board.travelStart}" readonly="readonly">
						여행 종료일: <input type="text" id="travelEndDate" name="travelEnd" value="${board.travelEnd}" readonly="readonly">&nbsp;&nbsp;
						<button type="button" onclick="updateCal()" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
							<i class="bi bi-calendar2-check"></i> 날짜선택
						</button></h5>
					</div>
					
					<div class="col-md-12" id="cont">
					<br>
						<label class="form-label"><i class="bi bi-bookmark-heart"></i> 대표장소</label>
						<input type="hidden" id="contentNo" name="contentNo" value="${plan.contentNo}">
						<input type="hidden" id="contentsTypeId" name="contentsTypeId" value="${plan.contentsTypeId}">
						<input type="text" id="conTitle" name="conTitle" value="${board.conTitle}">
						
						<c:forEach var="plan" items="${contentsList}" varStatus="status">
							<div class="btn-group">
								<input type="hidden" value="${contentsList[status.index].contentsTypeId}">
								<button type="button" value="${plan.contentsTypeId}" onclick="cont(${plan.contentsTypeId})"
									class="btn btn-outline-primary" data-bs-toggle="collapse"
									data-bs-target="#demo">${plan.contentsTypeName}</button>
							</div>

							<div class="collapse" id="updateContents${plan.contentsTypeId}"></div>
						</c:forEach>
					</div>
				</div>
				
				<a href="#" onclick="path()"><button type="button"
					class="btn btn-primary">수정완료</button></a>
			</form>
			<br>
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
									<div id="updateCalendar"></div>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer" style="width:800px;">
									<button onclick="updateSave()" type="button" class="btn btn-danger"data-bs-dismiss="modal" id="planSave">등록</button>
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

<script type="text/javascript">
var prevConcon = null;
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
	 				// 이전 컨텐츠콘 있는 경우 삭제하기
	 				if (prevConcon != null) {
	 					prevConcon.hide();
	 				}
	 				
	 				$('#updateContents' + contentsTypeId).html(data);
	 	            $('#updateContents' + contentsTypeId).show();
	 	            /* $('#concon' + contentsTypeId).next().hide(); */
	 	           	prevConcon = $('#updateContents' + contentsTypeId);

	 	       		$('#updateContents').html("");
	 		 
	 		 },
	 		error : function(xhr, status, error) {
	 				 alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
	 			},
	 	 }) 
	}



function updateCal(){

 $.ajax({
	 		 url : "Calendar.do",
	 		 type : "POST",
	 		 data : {
	 			 "memNo" : ${board.memNo}
	 		 	},
	 		 success : 
	 			function(data){
	 				console.log(data);
	 				$('#updateCalendar').html(data);
	 				$('.btn-close').hide();
	 			 },
	 		error : function(xhr, status, error) {
	 				 alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
	 			},
	 	 }) 
}

function updateSave(){

	sd = $('#date').val();
	ed = $('#endDate').val();  
	
	$("#travelStartDate").val(sd);
	$("#travelEndDate").val(ed);
	
	$('#updateCalendar').html("");
	
}


</script>


</html>