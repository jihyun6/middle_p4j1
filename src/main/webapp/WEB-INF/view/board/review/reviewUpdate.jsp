<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="kr.or.ddit.vo.BoardVo"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>

<script type="text/javascript">
$(function() {
	//수정하기
	$("#btnUpdate").on("click",function(){
		console.log("수정하기!!");
		
		/*
		gson은 json구조를 띄는 직렬화된 데이터를 JAVA의 객체로 역직렬화, 
		직렬화 해주는 자바 라이브러리 입니다. 즉, JSON Object -> 
		JAVA Object 또는 그 반대의 행위를 돕는 라이브러리 입니다.
		
		//1. serialize : 오브젝트 ->GSON라이브러리-> JSON String 
		//JSON 오브젝트
		let obj = {
			"id":"a001"	
		};
		
		=> GSON라이브러리
		
		JSON.stringify(obj);
		
		=> 
		
		문자열{"id":"a001"}
		
		//2. deserialize : JSON String ->GSON라이브러리-> 오브젝트 
		*/
		
		//serialize() 
		/* jquery form serialize 검색해보기
		ajax 를 사용하기위해서 form 안에 있는 값들을 하나하나 전송 했었다.
		그런데 하나하나 하다보니 귀찮음이 있었는데
		serialize()를 사용하면 form안에 데이터들을 한번에 보내줄 수 있다.
		보내줄 데이터가 많을수록 더 편하다고 느낄 것 이다.
		*/
		$.ajax({
			url:"/P4J1_Project/boardUpdateAjax.do",
			type:"post",
			data:$("#frm").serialize(),
			success:function(result){
				//서버 -> JSON 타입의 String문자{"boardNo":3} -> 클라이언트
				//jObj.put("boardNo", 2);
				//result : {"boardNo":3}
				console.log("result : ", result);
				
				viewPath('/boardDetail.do','get', '?boardNo='+result.boardNo);//result.boardNo : 3
			},
			error:function(){
				alert("Error!");
			}
		})
	});
	
	$('#main').on('click', function() {
		location.href = "boardList.do";
	});
	
	$('#delYn').on('click',function(){
		if(confirm('정말 삭제하시겠습니까?')){
			viewPath('/boardDelete.do','get', '?boardNo=${board.boardNo}&codeNo=${board.codeNumber}&contentNo=${board.contentNo}');
		}
	});
});
</script>

</head>
<body>
	<div class="container mt-5">
		<h2 class="mb-4">리뷰 수정하기</h2>
		<form id="frm" action="boardUpdate.do" method="post">
			<div class="row mb-3">
				<div class="col-md-4">
					<label class="form-label">리뷰번호</label> <input type="text"
						class="form-control" name="boardNo" value="${board.boardNo}">
				</div>
				<div class="col-md-4">
					<label class="form-label">작성자</label> <input type="text"
						class="form-control" name="writer" value="${board.memName}" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">작성일</label> <input type="text"
						class="form-control" name="date" value="${board.boardDate}">
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-4">
					<label class="form-label">컨텐츠 타입</label> <input type="text"
						class="form-control" name="contents" value="${contents.conTitle}" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">별점</label> <select name="boardStar"
						class="form-control">
						<option value="1" ${board.boardStar == 1 ? 'selected' : ''}>★</option>
						<option value="2" ${board.boardStar == 2 ? 'selected' : ''}>★★</option>
						<option value="3" ${board.boardStar == 3 ? 'selected' : ''}>★★★</option>
						<option value="4" ${board.boardStar == 4 ? 'selected' : ''}>★★★★</option>
						<option value="5" ${board.boardStar == 5 ? 'selected' : ''}>★★★★★</option>
					</select>
				</div>
				<div class="col-md-4">
					<label class="form-label">게시판유형</label> <input type="text"
						class="form-control" name="codeName" value="${board.codeName}" readonly>
				</div>
			</div>

			<div class="mb-3">
				<label class="form-label">제목</label> <input type="text"
					class="form-control"  name="title" value="${board.boardName}">
			</div>

			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" name="content" rows="10">${board.boardContent}</textarea>
			</div>

			<div class="text-center mt-4">
				<!-- action="boardUpdate.do"
				   아이디 속성의 값이 btnUpdate인 요소를 클릭했을 때 콜백함수를 실행
				달러("#btnUpdate").on("click",function(){
				-->
				
				<button type="button" id="btnUpdate" class="btn btn-primary" >수정</button>
				<button type="button" class="btn btn-outline-danger" id="delYn">삭제</button>
				<a href="#" onclick="getPath('/contentsDetail.do?contentNo=${contents.contentNo}')" class="btn btn-primary">목록으로</a>
			</div>
		</form>
	</div>
</body>
</html>