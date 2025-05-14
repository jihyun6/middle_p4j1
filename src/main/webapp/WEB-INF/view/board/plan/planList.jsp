<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행일정계획 목록 ♣</title>

<!-- <script>
var memNo = 

function planInsert(){
 		if(memNo == null){
			alert("로그인 해주세요");
		}
	}

</script> -->

<script>

var memNo = ${member.memNo};

$(function(){
	path = function(){
		let frm = $('#frm');
		postPath('/boardInsert.do', frm);
	}
})


function myPlanList(){
	var mymyPlanList = $('#mymyPlanList').text();
	
 	$.ajax({
		url : "mypagePlanList.do",
		type : "POST",
		data : {
			"memNo" : ${member.memNo},
				},
		success :
			function(data){
			$('#myPlanList').html(data);
			
			if(memNo){
				$('#mymyPlanList').text("내 여행일정 목록!");
				$('#myTravelPlanList').hide();
			}
		},
    error : function(xhr, status, error) {
        alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
    },

})
}


</script>	


</head>
<body>

	<div id="myPlanList">
	</div>
	
	<div class="container mt-12">
		<div>
				<h4 class="mb-4 text-body-primary" id="mymyPlanList "><i class="bi bi-calendar2-check"></i> 여행일정 목록</h4>
			<br>
			<div id="allPlanList">
				<a href="#"
					onclick="getPath('/boardInsert.do?codeNo=${boardList[0].codeNumber}')">
					<c:if test="${not empty member.memNo}">
						<button type="button" id="planInserttt" class="btn btn-primary"
							onclick=path()">일정 등록하기</button>
				</a>
				</c:if >
				<c:if test="${not empty member.memNo}">
					<button type="button" id="myTravelPlanList" class="btn btn-primary" onclick="myPlanList()">내 일정 조회하기</button>
				</c:if>
			</div>

			<input type="hidden" name="codeNumber" value="${codeNumber}">
			<input type="hidden" name="memNo" value="${member.memNo}">
			<table class="table table-hover">
							<tr>
						<th width="250px">일정 이름</th>
						<th width="900px">일정 내용</th>
						<th width="150px">작성자</th>
						<th width="250px">여행기간</th>
					</tr>
	
					<c:choose>
						<c:when test="${empty boardList}">
							<p class="text-muted">등록된 일정이 없습니다.</p>
						</c:when>

						<c:otherwise>
							<c:forEach var="board" items="${boardList}">
								<tr>
									<td width="250px"><a href="#" onclick="getPath('/boardDetail.do?boardNo=${board.boardNo}')">${board.boardName}</a></td>
									<td width="900px">
									<a href="#" onclick="getPath('/boardDetail.do?boardNo=${board.boardNo}')">
									<textarea id='view' name="boardContent" cols="7" rows="3" class="form-control" >${board.boardContent}</textarea></a></td>
									<td width="150px">${board.memName}</td>
									<td width="250px">${board.travelStart}~${board.travelEnd}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</table>
		</div>
	</div>


</body>
</html>