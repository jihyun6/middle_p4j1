<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script src="https://kit.fontawesome.com/0c69fdf2c0.js" crossorigin="anonymous"></script>
<script type="text/javascript">
function loveCheck(){
	
	if (${lovedByMem}){
		$('#loveUpdate i').addClass('red')
	}else{
		$('#loveUpdate i').addClass('black')
	}
}
loveCheck();
//하트버튼 클릭시(좋아요 추가 또는 좋아요 제거)
function loveup(){
		 if( $('#loveUpdate i').hasClass('red')){
	    	 loveCancel();
	     }else{
				$.ajax({
				 url: "loveBoardUpdate.do",
		         type: "POST",
		         data: {
		        	 "boardNo": ${board.boardNo},
		        	 "memNo": ${member.memNo},
		        	 "loveCount":${loveCount}
		         },
		         success: function (data) {
				     alert("좋아요")   ;
				    	//아니냐>
					        //red로 변경 
					        $('#loveUpdate i').removeClass('black')
					        $('#loveUpdate i').addClass('red')
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
						url: "loveBoardCancel.do",
			         type: "POST",
			         data: {
			        	 "boardNo": ${board.boardNo},
			        	 "memNo": ${member.memNo},
			        	 "loveCount":${loveCount}
			         },
			         success: function (data) {
					     alert("좋아요취소")   ;
			        	
					     if( $('#loveUpdate i').hasClass('red')){
					    	 //색상이 red이냐 
						        //기본으로 변경 
						        $('#loveUpdate i').removeClass('red')
						        $('#loveUpdate i').addClass('black')
						        //숫자 내려 
						        count =parseInt($(".loveAdd").text());
					    	    count--;
					    	    $(".loveAdd").text(count);
					     }
					},
				})
	   }
}

</script>
<style type="text/css">
.red{
color: red;
}
.black{
color:black;
}
</style>
</head>
<body>

	<div class="container mt-5">
		<h2 class="mb-4">여행일정</h2>	
			<p>여행기간&nbsp&nbsp♪&nbsp${board.travelStart}~${board.travelEnd}&nbsp♪</p>
			<table class="table table-hover">
				<thead>
				<tr>
					<th>여행 이름</th>
					<th>${board.boardName}</th>
					
					
					<th>작성자</th>
					<td>${board.memName}</td>
					
					<th>작성일</th>
					<td>${board.boardUpdate}</td>
				</tr>
				</thead>
			<th><i class="bi bi-bookmark-heart"></i>&nbsp; 대표장소</th>
					<td>${board.conTitle}</td>
			</table>
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
	</div>
	<br>
	
	
	<div class="text-center mt-4">
	
	
	
	<!-- 본인 댓글인 경우에만 수정/삭제 버튼 표시 -->
	
	
	<!-- 좋아요 섹션 -->
					<div class="d-flex justify-content-center mb-4" id="heart">
				        <c:if test="${member.memNo == null}">
				            <button class="btn btn-outline-light text-danger" onclick="alert('로그인후 사용하세요')" >
				                <i class="fa-solid fa-heart"></i> 
				                <span>${loveCount}</span>
				            </button>
				        </c:if>
				        <c:if test="${member.memNo != null}">
				            <c:choose>
				                <c:when test="${lovedByMem}" >
				                    <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveCancel()">
				                        <i class="fa-solid fa-heart"  >&nbsp;좋아요</i>
				                        <span class="loveAdd">${loveCount}</span>
				                    </button>
				                </c:when>
				                <c:otherwise>
				                    <button class="btn btn-outline-primary text-dark" id="loveUpdate" onclick="loveup()">
				                        <i class="fa-solid fa-heart">&nbsp;좋아요</i>
				                        <span class="loveAdd">${loveCount}</span>
				                    </button>
				                </c:otherwise>
				            </c:choose>
				        </c:if>
	<c:if test="${member.memNo == board.memNo || member.memNo == 1}">
		<a href="#" onclick="getPath('/boardUpdate.do?boardNo=${board.boardNo}')"><button type="button" class="btn btn-outline-danger">수정</button></a>
		<a href="#" onclick="getPath('/boardDelete.do?boardNo=${board.boardNo}&codeNo=${board.codeNumber}')"><button type="button" class="btn btn-outline-danger">삭제</button></a>
	</c:if>
		<a href="#" onclick="viewPath('/boardList.do','get', '?codeNo=${board.codeNumber}')" class="btn btn-outline-primary">목록으로</a>
		<%-- <a href="#" onclick="getPath('/boardList.do?codeNo=${board.codeNumber}')"><button type="button" class="btn btn-outline-primary">목록보기</button></a> --%>
	</div>
	</div>
</body>



</html>