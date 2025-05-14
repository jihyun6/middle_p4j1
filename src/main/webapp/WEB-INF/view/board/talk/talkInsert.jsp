<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Jquery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>

<script type="text/javascript">

path = function(){
	let frm = $('#frm');
	
	//파일 등록 여부 확인
	if( $('#uploadFile').get(0).files.length === 0 ){
		postPath('/boardInsert.do', frm);
	}
	
	else{
		//파일 등록 입니다.
		var formData = new FormData($('#frm')[0]);
		
		$.ajax({
			url : '/P4J1_Project/boardInsert.do',
			enctype:'multipart/form-data',
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			success:function(data){
					getPath('/boardList.do?codeNo=${codeNumber}')
					$('#file-container').html(html);
			}
		})
		
		
	}
}
</script>

</head>
<body>
<form id="frm">
<div class="container mt-5">
		<h2 class="mb-4">여행톡 작성하기</h2>
		<input type="hidden" value="${codeNumber}" name="codeNumber">
		<input type="hidden" value="${codeNumber}" name="codeNo">
		<input id="boardDate" type="hidden" value="" name="boardDate">
		<table class="table table-hover">
			<thead class="table-light">
				<tr>
					
					<th>제목</th> 
					<th  colspan="2"><input type="text" name="boardName" placeholder="제목을 입력하세요"></th>
					<th>작성자 </th>
					<td>${member.memName}</td>
					<th>여행 만족도</th>
					<td><select name="boardStar" class="form-control">
						<option value="1" ${board.boardStar == 1 ? 'selected' : ''}>★</option>
						<option value="2" ${board.boardStar == 2 ? 'selected' : ''}>★★</option>
						<option value="3" ${board.boardStar == 3 ? 'selected' : ''}>★★★</option>
						<option value="4" ${board.boardStar == 4 ? 'selected' : ''}>★★★★</option>
						<option value="5" ${board.boardStar == 5 ? 'selected' : ''}>★★★★★</option>
					</select></td>
					<th>작성일&nbsp&nbsp</th>
					<td><div id="current_date">
					    <script>
					        date = new Date();
					        year = date.getFullYear();
					        month = date.getMonth() + 1;
					        day = date.getDate();
					        document.getElementById("current_date").innerHTML = year+"/"+month + "/" + day ;
					        document.getElementById("boardDate").value = document.getElementById("current_date").innerText;
					    </script>
					</div></td>
				</tr>
			</thead>
			<tbody>
					<tr>
						<th>내용</th>
						<td colspan="8"><textarea name ="boardContent" id="conAdd" cols="5" rows="10" class="form-control" 
						style="resize: none;" placeholder="내용을 입력하세요"></textarea></td>
					</tr>
					<tr>
						<th>tag</th>
						<td><input type="text" name="boardTag"></td>
					</tr>
			</tbody>
		</table>
	<div>
	<!-- file upload 추가 -->
	파일선택 : <input type="file" id="uploadFile" name="uploadFile" multiple="multiple">
	</div>
	<input type ="button" onclick=path() value="등록" class="btn btn-primary">
	<a href="#" onclick="getPath('/boardInsert.do?codeNo=2')" class="btn btn-dark">취소</a>
</div>
</form>
</body>
</html>