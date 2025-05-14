<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  
 <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#update').on('click', function() {
		let frm = $('#frm');
		postPath('/custormerUpdate.do?custNo=${custNo}',frm);
		
		//location.href = 'reviewDelete.jsp?boardNo=${board.boardNo}';
	});
});

$(function() {
	$('#list').on('click', function() {
    	
		getPath('/custormerList.do');
		//location.href = "noticeList.do";
	});
});




</script>
</head>
<body>

<div class="container mt-5">
		<h2 class="mb-4">질문 수정하기</h2>
	
	<form id="frm" action="custormerUpdate.do" method="post">
		<table class="table" border="1">
			<tr>
				<td>업데이트 날짜/</td><br>
 				<td>${custDate}</td>
			</tr>
		</table>
		<table border="1" class="table">
 			<thead>
 				<tr>
 					<div class="row mb-3">
 						
 							<div class="col-md-4">
								<label class="form-label">제목</label> <input type="text"
									class="form-control" name="custTitle" value="${custTitle}">
							</div>
 					</div>
 				</tr>
 			</thead>
 			<tbody>

 				<tr>
 					<label for="comment">내용:</label>
					<textarea class="form-control" rows="5" id="comment" name="custContent" >${custContent}</textarea>
 				</tr>
 			</tbody>
 			
 		</table>
 		<button type="button" class="btn btn-outline-dark" id="update">수정하기</button>
		<button type="button" class="btn btn-outline-secondary" id="list">목록</button>
	</form>
</div>


</body>
</html>