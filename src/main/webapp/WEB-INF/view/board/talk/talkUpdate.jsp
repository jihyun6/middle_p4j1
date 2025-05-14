<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript">


$(function(){
	path = function(){
		let frm = $('#frm');
		postPath('/boardUpdate.do?boardNo=${board.boardNo}', frm);
	}
}) 
/* $(function(){
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
				url : '/P4J1_Project/boardUpdate.do',
				enctype:'multipart/form-data',
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				success:function(data){
					postPath('/boardUpdate.do?boardNo=${board.boardNo}')
				}
			})
		}
	}
}) */

</script>

</head>
<body>
	<form id="frm">
		<input id="boardDate" type="hidden" value="" name="boardDate">
		<div class="container mt-5">
			<h2 class="mb-4">여행톡 작성하기</h2>
			<table class="table table-hover">
				<thead class="table-light">
					<tr>
						<th>${talk.boardNo}</th>
						<th colspan="2">제목 <input type="text" name="boardName"
							value="${board.boardName}"></th>
						<th>작성자</th>
						<td>${board.memName}</td>
						<th>여행만족도</th>
						<td><select name="boardStar" class="form-control">
							<option value="1" ${board.boardStar == 1 ? 'selected' : ''}>★</option>
							<option value="2" ${board.boardStar == 2 ? 'selected' : ''}>★★</option>
							<option value="3" ${board.boardStar == 3 ? 'selected' : ''}>★★★</option>
							<option value="4" ${board.boardStar == 4 ? 'selected' : ''}>★★★★</option>
							<option value="5" ${board.boardStar == 5 ? 'selected' : ''}>★★★★★</option>
						</select></td>
						<th>수정일&nbsp&nbsp</th>
						<td><div id="current_date">
								<script>
									date = new Date();
									year = date.getFullYear();
									month = date.getMonth() + 1;
									day = date.getDate();
									document.getElementById("current_date").innerHTML = year
											+ "/" + month + "/" + day;
									document.getElementById("boardDate").value = document.getElementById("current_date").innerText;
								</script>
							</div></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>내용</th>
						<td colspan="8">
						<textarea name ="boardContent" id="conAdd" cols="5" rows="5" class="form-control" 
						style="resize: none;">${board.boardContent}</textarea></td>
					</tr>
					
				</tbody>
				
			</table>
				<div style="display: flex; gap: 20px; align-items: flex-start; ">
					
					<c:forEach var="file" items="${fileList}" begin="0" end="2" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img alt="경로잘못" src="download?fileNo=${file.fbNo}"
                                             class="card-img-top fileimg" style="height: 200px; object-fit: cover;">
                                        
                                    </div>
                                </div>
                            </c:forEach>
                </div>
                <div>
                	#<input type="text" name="boardTag" value="${board.boardTag}">
                </div>    
			<a href="#" onclick="path()"><button type="button" class="btn btn-primary">수정하기</button></a>
		</div>
	</form>
</body>
</html>