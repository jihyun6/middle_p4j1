<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>

<html>
<head>
<link rel="shortcut icon" href="#">

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/P4J1_Project/js/jquery-3.7.1.js"></script>


 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  
  <script src="/P4J1_Project/js/jquery.serializejson.min.js"></script>
<style>
  .container input {
	width: 300px;
}
   .container input#add1, .container input#add2{
    width :  500px;
 }
 
 .container input#idck, .container input#zipbtn, .container input#nickck , .container input#emailck{
    width : 100px;
 }

</style>

<script type="text/javascript">
$(function(){
	
	//id입력시 형식체크
	$('#memId').on('keyup', function(){
	//입력한 값을 가져온다
	idValue = $('#memId').val().trim();
	
	//규칙 설정
	regid = /^[a-zA-Z][a-zA-Z0-9]{3,7}$/;
	
	//입력한 값이 규칙에 맞는지 비교 test() - true/ false 리턴
	if(regid.test(idValue)){
		$(this).css('border', '2px solid blue');
		$('#idck').prop('disabled', false);
		
	}else{
		$(this).css('border', '2px solid red');
		//사용불가능상태 설정
		$('#idck').prop('disabled', true);
		/* alert("아이디는 대소문자 숫자만 가능합니다"); */
	}
	})


$(function(){
 	//아이디 중복검사
 	$('#idck').on('click', function(){
 		//<input type="text" class="form-control" id="memId" name="memId">
 		let idValue = $('#memId').val().trim();
 		
 		//idValue :  jadasff
 		console.log("idValue : ",idValue);
 		
 		if(idValue.length < 1){
 			alert("id를 입력");
 			return false;
 		}
 		
 		//			WebServlet("/idcheck.do")
 		//요청URI : /P4J1_Project/idCheck.do?id=jadasff
 		fetch('/P4J1_Project/idcheck.do?id=' + idValue)
 		.then(res=>{//HTTP Status
 			console.log("res : ", res);
 			if(res.ok){
				return res.json();
			}else{
				throw new Error("오류 발생: " + res.status);
			}
 		})
 		.then(data =>{//데이터
 			/*
 			data{"flag": "JAVA"}
 			*/
			console.log("data : ", data);
			
 			if(data.flag==null){
				$('#idspan').html("가능한 아이디입니다.").css('color', 'blue');
 			}else{
 				$('#idspan').html("다시 입력하세요").css('color', 'red');
 			}
		})
		.catch(err=>{
			alert(err);
		}) 
	}) 
})
	$(function(){
 	//닉네임 중복검사
 	$('#nickck').on('click', function(){

 		let nickValue = $('#memAlias').val().trim();
 		
 		
 		console.log("nickValue : ",nickValue);
 		
 		if(nickValue.length < 1){
 			alert("닉네임을 입력하세요");
 			return false;
 		}
 		
 		
 		fetch('/P4J1_Project/nickcheck.do?nick=' + nickValue)
 		.then(res2=>{
 			console.log("res2 : ", res2);
 			if(res2.ok){
				return res2.json();
			}else{
				throw new Error("오류 발생: " + res2.status);
			}
 		})
 		//data가 무슨 의미인지
 		.then(data =>{
 		
			console.log("data : ", data);
			
 			if(data.flag2==null){
				$('#idspan2').html("가능한 닉네임입니다.").css('color', 'blue');
 			}else{
 				$('#idspan2').html("다시 입력하세요").css('color', 'red');
 			}
		})
		.catch(err=>{
			alert(err);
		
		})
	})
})
	//id입력시 형식체크
	$('#memEmail').on('keyup', function(){
	//입력한 값을 가져온다
	emailValue = $('#memEmail').val().trim();
	
	//규칙 설정
	regid = /^[a-zA-Z0-9]+@[a-zA-Z]+(\.{a-z}#){1,3}$/;
	
	//입력한 값이 규칙에 맞는지 비교 test() - true/ false 리턴
	if(regid.test(emailValue)){
		$(this).css('border', '2px solid blue');
		$('#emailck').prop('disabled3', false);
		
	}else{
		$(this).css('border', '2px solid red');
		//사용불가능상태 설정
		$('#emailck').prop('disabled3', true);
		/* alert("아이디는 대소문자 숫자만 가능합니다"); */
	}
	})
	
	
	
	
	$(function(){
		//이메일 중복검사
		$('#emailck').on('click', function(){
	 	
	 		let emailValue = $('#memEmail').val().trim();
	 		
	 		//emailValue :  JJ@GC.COM
	 		console.log("emailValue : ",emailValue);
	 		
	 		if(emailValue.length < 1){
	 			alert("email를 입력하세요");
	 			return false;
	 		}
	 		
	 		//			WebServlet("/idcheck.do")
	 		//요청URI : /P4J1_Project/idCheck.do?email=JJ@GC.COM
	 		fetch('/P4J1_Project/emailckeck.do?email=' + emailValue)
	 		.then(res3=>{//HTTP Status
	 			console.log("res3 : ", res3);
	 			if(res3.ok){
					return res3.json();
				}else{
					throw new Error("오류 발생: " + res3.status);
				}
	 		})
	 		.then(data =>{//데이터
	 			/*
	 			data{"flag3": "JJ@GC.COM"}
	 			*/
				console.log("data : ", data);
				
	 			if(data.flag3==null){
					$('#idspan3').html("가능한 이메일입니다.").css('color', 'blue');
	 			}else{
	 				$('#idspan3').html("다시 입력하세요").css('color', 'red');
	 			}
			})
			.catch(err=>{
				alert(err);
			}) 
		}) 
	})
	
//	비밀번호 - 비밀번호 확인 일치 체크
	function checkPassword() {
		var inputPassword = $('#memPwd').val();
		var inputPasswordCheck = $('#memPwd2').val();
		
		if(inputPassword != inputPasswordCheck){	//	비밀번호-확인이 같지 않으면
			$('#password-alert-false').css("display", "block");
			$('#password-alert-true').css("display", "none");
			$('#passwordCheck').css("background-color", "lightpink");
			passwordCheck = false;
		}else{										//	비밀번호-확인이 같으면
			$('#password-alert-false').css("display", "none");
			$('#password-alert-true').css("display", "block");
			$('#passwordCheck').css("background-color", "lightgreen");
			passwordCheck = true;
		}
			joinBtnControll();
		
	}
	//전송하기
	$('#send').on('click', function(){
		
		//입력한 모든 값을 전부 가져오기 - 9 개
	      
		//자동으로 한번에 
		fdata1 = $('#ff').serialize();
		fdata2 = $('#ff').serializeArray();
		fdata3 = $('#ff').serializeJSON();
		
		console.log(fdata1);
		console.log(fdata2);
		console.log(fdata3);
		
		fetch('/P4J1_Project/InsertMember.do', {
			method : 'post',
			hearders : {  "Content-type" : "application/json;charset=UTF-8" },
		 body  :  JSON.stringify(fdata3) 
		})
		.then(res3=>{
			if(res3.ok){
				/* return res3.json(); */
			}else{
				throw new Error("오류 발생: " + res3.status);
			}
		})
		.then(data=>{
			console.log(data);
		})
		.catch( err=>{
			alert(err);
		});
	});
	
	//취소 버튼 누르면 뒤로가기
	/* document.getElementById('btnCancel').onclick = function() {
		history.go(-1);
	} */

});//end 달러function
</script>

</head>
<body>

 <div class="box">
  <div id="result1">
	
	  <div class="container mt-3">
		  <h2>회원가입</h2>
<hr>
<form action="/member/join" method="post" id="ff">
	 <div class="mb-3 mt-3">
		      <label for="id">아이디:</label>
		       <input type="button"  class="btn btn-outline-success btn-sm" value="중복검사" id="idck">
		      <span id="idspan"></span>
		    <input type="text" class="form-control" id="memId"  name="memId">
		     </div>
		     
	 
	
	 <div>
			<label class="col-sm-2" for="memPwd">비밀번호 :</label><br>
			 <div class="col-sm-3">
			 <input type="password" class="form-control" id="memPwd" name="memPwd" required placeholder="비밀번호"/><br>
	</div>
	
	<div>
			  <label class="col-sm-2" for="memPwd2">비밀번호 확인 :</label><br>
			   <div class="col-sm-3" style="display:flex">
        <input class="form-control" type="password" id="memPwd2" name="memPwd2" required placeholder="비밀번호 확인" oninput="checkPassword()">
    </div>
    <div class="col-sm-3" id="password-alert-false" style="display:none; color:red">비밀번호가 일치하지 않습니다.</div>
    <div class="col-sm-3" id="password-alert-true" style="display:none; color:green">비밀번호가 일치합니다.</div>
    </div>
	</div>
	
	 <div class="mb-3 mt-3">
	 	    <label for="memName">이름:</label>
			 <input type="text" class="form-control" id="memName" name="memName" /><br>
	</div>
	
	<div class="mb-3 mt-3">
			<label for="nick">닉네임:</label>
			 <input type="button"  class="btn btn-outline-success btn-sm" value="중복검사" id="nickck">
		    <span id="idspan2"></span>
		  <input type="text" class="form-control" id="memAlias" name="memAlias" /><br>
	</div>
	
	 <div class="mb-3 mt-3">
			<label for="memNbti">MBTI:</label>
			 <input type="text" class="form-control" id="memMbti" name="memMbti" /><br>
	 </div>
	
	 <div class="mb-3 mt-3">
			<label for="memReg">주민번호 앞자리 -</label> <label for="username">주민번호 뒷자리:</label><br>
			 <input type="text" id="memReg" name="memReg" />&nbsp-&nbsp
	
			<input type="text" id="memReg2" name="memReg2" /><br>
	</div>
	
	 <div class="mb-3 mt-3">
			<label for="memAge">나이:</label>
			 <input type="date" class="form-control" id="memAge"  name="memAge"/><br>

	</div>
	
	<div>
			<label for="memGender">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp성별</label><br>
	 		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp남자 <br>
	 		<input type="radio" id="memGender" name="memGender"/><br>
	 		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp여자 <br><input type="radio" id="memGender" name="memGender"/><br>
	</div>
	
	 <div class="mb-3 mt-3">
			<label for="memTel">휴대폰</label>
			 <input type="text" class="form-control" id="memTel" name="memTel" /><br>
	</div>
	
	 <div class="mb-3 mt-3">
			<label for="memEmail">이메일</label>
			 <input type="button"  class="btn btn-outline-success btn-sm" value="중복검사" id="emailck">
		    <span id="idspan3"></span>
	 		<input type="text" class="form-control" id="memEmail" name="memEmail" /><br>
	</div>
	
	
	
			<button type="button" id="send" class="btn btn-primary btn-lg">가입</button>	
			<button type="button" id="btnCancel">취소</button>
</form>
		</div>
	  
	</div>
</div>
</body>
</html>