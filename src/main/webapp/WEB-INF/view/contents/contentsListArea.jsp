<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
 img{
 	width: 200px;
 	height: 150px;
 }

</style>
</head>
<body>
<table border="1">
<input type="hidden" name="trialAreacode" value="${contents.trialAreacode}">
<tr>
	<c:forEach var="contents" items="${contentsList}" varStatus="status">
		<td><img src="${contents.conFirstimage2}"><br>
		<a href="#" onclick="getPath('/contentsDetail.do?contentNo=${contents.contentNo }')">${contents.conTitle}</a></td>
	     <c:if test="${status.count % 5 == 0}">
	</tr><tr>
	</c:if>
	</c:forEach>
</tr>
</table>
</body>
</html>