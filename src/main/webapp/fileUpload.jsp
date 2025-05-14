<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/fileUpload.do" method="post" enctype="multipart/form-data"> 
		파일선택 : <input type="file" name="uploadFile" multiple="multiple">
		전송자 : <input type="text" name ="sender">
		<input type="submit" value="전송"> 
	</form>
</body>
</html>