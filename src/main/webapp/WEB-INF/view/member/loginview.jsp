<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#proimg {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

</style>

<script type="text/javascript">
// $(function(){
// 	$('.main').hover(function() {
// 		$('.dropdown').hide();
// 	})
	
// 	$('.main').mouseout(function() {
// 		$('.dropdown').show();
// 	})
// })
</script>

</head>
<body>

<c:choose>
	<c:when test="${empty sessionScope.member.memId}"> 
		<li><a href="#" onclick="getPath('/login.do?')">Login</a></li>
	</c:when>
	
	<c:when test="${sessionScope.member.memAuth == '0'}">
		<li class="dropdown"><a href="#"><span>관리자</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
			<ul>
		    	<li><a href="#" onclick="getPath('/reportList.do')"><span>신고게시판</span></a></li>
		        <li><a href="#" onclick="getPath('/blacklistList.do')">블랙리스트</a></li>
		        <li><a href="#" onclick="getPath('/chart.do')">매출확인</a></li>
		        <li><a href="#" onclick="getPath('/memberList.do')">회원목록</a></li>
		    </ul>
	   	</li>
	   	
<!-- 			<li class="dropdown"><a href="#"><span>투어</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a> -->
<!-- 			<ul> -->
<!-- 				<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=12')"><span>관광지</span></a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=14')">문화시설</a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=15')">행사</a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=28')">레포츠</a></li>    	 -->
<!-- 			</ul>	     -->
<!-- 		</li> -->
		
		<img id="proimg" src="${pageContext.request.contextPath}/image/adminpro.png" alt="사진이 없습니다."> ${sessionScope.member.memName}님<br/>
		
		<!-- logout -->	
		<form action="${pageContext.request.contextPath}/logout.do" method="get">
	    	<button type="submit" name="logout" class="btn btn-outline-white text-dark">로그아웃</button>
		</form>
	</c:when>
	
	<c:when test="${ not empty sessionScope.member.memId }" >
		<li><a href="#" onclick="getPath('/mypage.do?memNo='+${sessionScope.member.memNo})">마이페이지</a></li>
<!-- 			<li class="dropdown"><a href="#"><span>투어</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a> -->
<!-- 			<ul> -->
<!-- 				<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=12')"><span>관광지</span></a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=14')">문화시설</a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=15')">행사</a></li> -->
<!-- 			    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=28')">레포츠</a></li>    	 -->
<!-- 			</ul>	     -->
<!-- 		</li> -->
		
		<img id="proimg" src="${pageContext.request.contextPath}/image/P_pro.jpg" alt="사진이 없습니다.">${sessionScope.member.memName}님<br/>
		
		<!-- logout -->	
		<form action="${pageContext.request.contextPath}/logout.do" method="get">
	    	<button type="submit" name="logout" class="btn btn-outline-white text-dark">로그아웃</button>
		</form>
	</c:when>
</c:choose>
</body>
</html>