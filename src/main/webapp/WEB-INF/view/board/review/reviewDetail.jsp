<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세보기</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script src="https://kit.fontawesome.com/0c69fdf2c0.js" crossorigin="anonymous"></script>
<script>
$(function(){
	$('#main').on('click', function(){
		//${pageContext.request.contextPath}: JSTL ServletContextPath return;
		location.href="${pageContext.request.contextPath}" + "/boardList.do";
	});
	
	$('#delYn').on('click',function(){
		if(confirm('정말 삭제하시겠습니까?')){
			viewPath('/boardDelete.do','get', '?boardNo=${board.boardNo}&codeNo=${board.codeNumber}');
		}
	});
});

//주석처리
// function loveCheck(){
// 	let lovedByMemBoard = ${lovedByMemBoard};
	
// 	if (lovedByMemBoard){
// 		$('#loveUpdate i').addClass('red')
// 	}else{
// 		$('#loveUpdate i').addClass('black')
// 	}
// }
//loveCheck();

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
    <h4 class="mb-4">리뷰 상세보기</h4>
    
    <form>
        <div class="row mb-2">
            
            <div class="col-md-3">
                <label class="form-label">작성자</label>
                <input type="text" class="form-control" value="${board.memName}" readonly>
            </div>
            <div class="col-md-3">
                <label class="form-label">작성일</label>
                <input type="text" class="form-control" value="${board.boardDate}" readonly>
            </div>
            <div class="col-md-3">
                <label class="form-label">컨텐츠 이름</label>
                <input type="text" class="form-control" value="${board.conTitle}" readonly>
            </div>
            <div class="col-md-3">
                <label class="form-label">별점</label>
                <div class="form-control">
                    <c:forEach begin="1" end="${board.boardStar}">★</c:forEach>
                </div>
            </div>
            
        </div>

        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" class="form-control" value="${board.boardName}" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea class="form-control" rows="10" readonly>${board.boardContent}</textarea>
        </div>

        <div class="text-center mt-4">
        	<c:if test="${member.memNo eq board.memNo}">
            	<a href="#" onclick="getPath('/boardUpdate.do?boardNo=${board.boardNo}&conTitle=${board.conTitle}&contentNo=${board.contentNo}')" class="btn btn-primary">수정하기</a>
			</c:if>
			
			<c:if test="${member.memNo == 1}">
            	<button type="button" class="btn btn-outline-danger" id="delYn">삭제</button>
			</c:if>
			<c:if test="${member.memNo != 1}">
			<a href="#" onclick="getPath('/contentsDetail.do?contentNo=${board.contentNo}')" class="btn btn-primary">목록으로</a>
			</c:if>
			<c:if test="${member.memNo == 1}">
            	<a href="#" onclick="getPath('/boardList.do?codeNo=3')" class="btn btn-primary">목록으로</a>
			</c:if>
			
			
			<c:if test="${ not empty fileList}">
				<c:forEach var="file" items="${fileList}" begin="0" end="2" varStatus="status">
	            	 <div class="col-lg-3 col-md-6">
	                 	<div class="card h-100">
	                    	<img alt="경로잘못" src="download?fileNo=${file.fbNo}"
	                     		 class="card-img-top fileimg" style="height: 200px; object-fit: cover;">
	                    </div>
	                 </div>
            	</c:forEach>		
			</c:if>
        </div>
    </form>
</div>
</body>
</html>