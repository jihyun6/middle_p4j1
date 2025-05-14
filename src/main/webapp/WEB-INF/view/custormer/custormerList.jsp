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
   <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$('#input').on('click', function(){
		getPath('/custormerInsert.do');
		//location.href = "noticeInsert.do";
	})
	
	//게시판 글 업데이트
	$('.update').on('click', function(){
		var tr = $(this).parent().parent();
		var td = tr.children();
		var custNo = td.eq(0).text();
		getPath('/custormerUpdate.do?custNo='+custNo);
		//location.href = "noticeUpdate.do?noticeNo="+noticeNo;
	})
	
	//게시판 글 삭제
	$('.delete').on('click', function(){
			var tr = $(this).parent().parent();
			var td = tr.children();
			var custNo = td.eq(0).text();
			getPath('/custormerDelete.do?custNo='+custNo);
			//location.href = "noticeDelete.do?noticeNo="+noticeNo;
	})
})

</script>

<body>
	<br><center><h1>고객님 무엇을 도와드릴까요?</h1></center><br><br>
	
	
	<div class="container">
	
	<h3>자주 묻는 질문</h3>
	
		<table border="1" class="table" >
			<thead>
				<tr class="table-primary">
					<td>NO</td>
					<td>공지내용</td>
					<td>등록일</td>
					<td></td>
					<td></td>
				</nav>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="custormer" items="${custormerList}">
					<tr>
						<td>${custormer.custNo}</td>
						<td><a href="#" onclick="getPath('/custormerDetail.do?custNo=${custormer.custNo}')">${custormer.custTitle}</a></td>
						<td>${custormer.custDate}</td>
						<c:if test="${sessionScope.member.memAuth == '0'}">
						<td><input type="button" value="수정" id="update" class="update"></td>
						<td><input type="button" value="삭제" id="delete" class="delete" ></td>
					</c:if>
						
					</tr>
					
					
				</c:forEach>
				
			</tbody>
			
		</table>
		<c:if test="${sessionScope.member.memAuth == '0'}">
			<input type="button" value="글쓰기" id="input">
		</c:if>
		<br><br>
	
		
	</div>
</body>
</html>