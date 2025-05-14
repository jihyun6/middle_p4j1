<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function loginPage() {
		location.href = "http://localhost:8080/P4J1_Project/login.do";
	}
	
	function loginOutPage() {
		location.href = "logout.do";
	}
	
	function joinPage() {
		location.href = "http://localhost:8080/P4J1_Project/join.do";
	}
	
	
</script>


</head>
<body>


	<c:choose>
		<c:when test="${empty sessionScope.member}">
			<strong>로그인이 필요합니다</strong><br>	
			<button onclick="loginPage()">로그인</button>	
			<button onclick="joinPage()">회원가입</button>

		</c:when>
		<c:otherwise>
	
			${sessionScope.member.memName }
			<button onclick="loginOutPage()">로그아웃</button>
			<button onclick="updatePage()">정보수정</button>
			<button onclick="deletePage()">회원탈퇴</button>
		</c:otherwise>
	</c:choose>

	
	
		
		
</body>
</html>
