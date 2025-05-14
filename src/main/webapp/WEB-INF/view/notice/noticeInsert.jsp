<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<script type="text/javascript">
$(function(){
	$('#main').on('click', function(){
		location.href = "noticeList.do";
	})
	
	$('#input').on('click', function() {
		let frm = $('#frm');
		postPath('/noticeInsert.do',frm);
		//location.href = 'reviewDelete.jsp?boardNo=${board.boardNo}';
	});
	
})
</script>

<body>
<div class="container mt-3">
		<form id="frm" action="noticeInsert.do" method="post">
			<table class="table">
				<thead>
				
				<tr>
					<label for="">제목</label>
						<input type="text" value="" name="noticeName"><p>
					
						<label for="">내용</label><br>
						<textarea rows="20" cols="50" name="noticeContent">
					</textarea>
					</tr>
				</thead>
					</p>
					<p>
				<input type="button" value="등록하기" id="input">
			<input type="button" value="돌아가기" id="main">
</p>
	</table>
</form>
</div>
</body>
</html>