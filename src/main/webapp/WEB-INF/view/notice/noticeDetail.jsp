<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#list').on('click', function() {
    	
		getPath('/noticeList.do');
		//location.href = "noticeList.do";
	});
});



</script>

</head>
<body>

<div class="container mt-3">
	<form id="frm" action="noticeInsert.do" method="post">
		<table border="1" class="table">
 			<thead>
 			<h2>공지사항</h2>
 				<tbody>
 				
 				<tr>
 					<td>no.${noticeNo}</td>
 					<td>${noticeName}</td>
 					<td>${noticeDate}</td>
 		
 				</tr>
 		</table>
 		
		<table border="1" class="table">
 				<tr>
 					<label for="comment">내용:</label>
					<textarea class="form-control" rows="15" id="comment" name="noticeContent" >${noticeContent}</textarea>
 				</tr>
	
 				</tbody>
 			</thead>
 		</table>
  	<button type="button" class="btn btn-outline-secondary" id="list">목록</button>
  </form>
</div>
</body>
</html>