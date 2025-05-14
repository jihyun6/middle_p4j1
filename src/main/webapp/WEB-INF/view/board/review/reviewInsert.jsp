<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="kr.or.ddit.vo.BoardVo"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
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
				getPath('/contentsDetail.do?contentNo=${contents.contentNo}')
			}
		})
	}
}
</script>
</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">리뷰 등록하기</h2>
		<form id="frm" action="reviewInsert.do" method="post">
			<input type="hidden" name="codeNumber" value="${codeNumber}">
			<input type="hidden" name="contentNo" value="${contents.contentNo}">
			<div class="row mb-2">
				<div class="col-md-4">
					<label class="form-label">리뷰 제목</label> <input type="text" class="form-control" name="boardName"
					 		value="${board.boardName}" placeholder="제목을 입력하세요">
					<br>
					<label class="form-label">별점</label> <select name="boardStar" class="form-control">
						<option value="1" ${board.boardStar == 1 ? 'selected' : ''}>★</option>
						<option value="2" ${board.boardStar == 2 ? 'selected' : ''}>★★</option>
						<option value="3" ${board.boardStar == 3 ? 'selected' : ''}>★★★</option>
						<option value="4" ${board.boardStar == 4 ? 'selected' : ''}>★★★★</option>
						<option value="5" ${board.boardStar == 5 ? 'selected' : ''}>★★★★★</option>
					</select><br>
					<label class="form-label">컨텐츠</label> <input type="text" readonly="readonly" class="form-control" name="conTitle" value="${contents.conTitle}">
					<br>
					<label class="form-label">파일선택</label> <input type="file" id="uploadFile" name="uploadFile" multiple="multiple">				</div>
				<div class="col-md-8">
					<label class="form-label">리뷰 내용</label> 
					<textarea name ="boardContent" id="conAdd" cols="10" rows="10" class="form-control" 
						style="resize: none;" placeholder="내용을 입력하세요"></textarea>
				</div>
				</div>
				<div class="text-center mt-4">
					<input type ="button" onclick=path() value="등록" class="btn btn-primary">
					<a href="#" onclick="getPath('/contentsDetail.do?contentNo=${contents.contentNo}')" class="btn btn-dark">취소</a>
				</div>
		</form>
	</div>
</body>
</html>