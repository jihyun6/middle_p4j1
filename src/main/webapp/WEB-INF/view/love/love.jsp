<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<script type="text/javascript">
//하트버튼 클릭시(좋아요 추가 또는 좋아요 제거)
	function loveUpdate(){
		 if( $('#loveUpdate i').hasClass('red')){
	    	 loveCancel();
	     }else{
		$.ajax({
			url: "loveUpdate.do",
       type: "POST",
       data: {
      	 "contentNo": ${contents.contentNo},
      	 "memNo": ${member.memNo},
      	 "loveNo":${contents.loveNo}
       },
       success: function (data) {
		     alert("좋아요")   ;

		    
		    	//아니냐>
			        //red로 변경 
			        $('#loveUpdate i').addClass('red')
			        //숫자 올려 
			        count =parseInt($(".loveCount").text());
			        count++;
			        $(".loveCount").text(count);
		     },

      	 
       })
		}
}
	
function loveCancel(){
		$.ajax({
			url: "loveCancel.do",
       type: "POST",
       data: {
      	 "contentNo": ${contents.contentNo},
      	 "memNo": ${member.memNo},
      	 "loveNo":${contents.loveNo}
       },
       success: function (data) {
		     alert("좋아요취소")   ;
      	 
		     
		     
		     if( $('#loveUpdate i').hasClass('red')){
		    	 //색상이 red이냐 
			        //기본으로 변경 
			        $('#loveUpdate i').removeClass('red')
			        //숫자 내려 
			        count =parseInt($(".loveCount").text());
		    	    count--;
		    	    $(".loveCount").text(count);
		     }

      	 
       },
		})

}
</script>

</head>
<body>
<!-- 좋아요버튼 -->
			<div>
				<div class="w3-border w3-center w3-padding">
					<c:if test="${member.memNo == null }">

						<i class="fa-solid fa-heart" style="font-size: 16px;"></i>
						<span>${contents.loveNo}</span>&nbsp;로그인 후 좋아요 가능					
				</c:if>
					<c:if test="${member.memNo != null }">
						<button class="w3-button w3-black w3-round" id="loveUpdate"
							onclick="loveUpdate()">
							<i class="fa-solid fa-heart" style="font-size: 16px;"></i> &nbsp;<span
								class="loveCount">${contents.loveNo}</span>
						</button>
					</c:if>
				</div>
			</div>
</body>
</html>