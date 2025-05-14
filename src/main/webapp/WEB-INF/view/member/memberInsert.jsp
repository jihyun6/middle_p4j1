<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>

<html>
<head>
<link rel="shortcut icon" href="#">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/member.js"></script>
<!-- Jquery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>
  
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
 
 .container input.mbit, input.gender {
 	width: 30px;
 }
 
 .container input.req {
 	width: 200px;
 }
 
 .container input.age {
 	width: 50px;
 }
 
 .form-control2 {
  display: inline-block;
  width: 100%;
  height: calc(1.5em + 0.75rem + 2px);
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #6e707e;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #d1d3e2;
  border-radius: 0.35rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
 
</style>

<script type="text/javascript">

//전화번호 - 정규식 체크
//const: 블록범위내에서 선언됨.
//const를 사용하지 않을 경우 전역함수로 사용
phoneAutoHyphen = (target) => {
	 target.value = target.value
	  .replace(/[^0-9]/g, '')
	  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

$(function(){
	path = function(){
		
		allValid = true;
		$('span[id^="idspan"]').each(function() {
		    if (!$(this).text().startsWith('가능한')) {
		        allValid = false;
		        return false; // 반복 종료
		    }
		});
		
		if(allValid){
			let frm = $('#frm');
			postPath('/memberInsert.do', frm);
		}else{
			alert('중복검사를 모두 해주세요!');
		}
	}
	
	//id입력시 형식체크
	$('#memId').on('keyup', function(){
		//입력한 값을 가져온다
		idValue = $('#memId').val().trim();
		idInputCheck(idValue, $(this));
	});
	
	//id입력시 중복체크
	$('#idck').on('click', function(){
		//입력한 값을 가져온다
		idValue = $('#memId').val().trim();
		idDoubleCheck(idValue, $(this));
	});
	
	//비밀번호 일치 체크
	checkPassword = function(){
		var inputPassword = $('#memPwd').val();
		var inputPasswordCheck = $('#memPwd2').val();
		
		if (inputPassword != inputPasswordCheck) {	//	비밀번호-확인이 같지 않으면
			$('#password-alert-false').css("display", "block");
			$('#password-alert-true').css("display", "none");
			$('#passwordCheck').css("background-color", "lightpink");
			passwordCheck = false;
		} else {										//	비밀번호-확인이 같으면
			$('#password-alert-false').css("display", "none");
			$('#password-alert-true').css("display", "block");
			$('#passwordCheck').css("background-color", "lightgreen");
			passwordCheck = true;
		}
	}
	
	//닉네임 입력시 형식체크
	$('#memAlias').on('keyup', function(){
		//입력한 값을 가져온다
		nickValue = $(this).val().trim();
		nickInputCheck(nickValue, $(this));
	});
	
	//닉네임 입력시 중복체크
	$('#nickck').on('click', function(){
		var idspan2 = $('#idspan2');
		//입력한 값을 가져온다
		nickValue = $('#memAlias').val().trim();
		nickValueDoubleCheck(nickValue, idspan2);
	});
	
	//eamil 입력시 형식체크
	$('#memEmail').on('keyup', function(){
		//입력한 값을 가져온다
		emailValue = $('#memEmail').val().trim();
		
		//규칙 설정
		regid = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
		
		//입력한 값이 규칙에 맞는지 비교 test() - true/ false 리턴
		if(regid.test(emailValue)){
			$(this).css('border', '2px solid blue');
			$('#emailck').prop('disabled', false);
			
		}else{
			$(this).css('border', '2px solid red');
			//사용불가능상태 설정
			$('#emailck').prop('disabled', true);
			/* alert("아이디는 대소문자 숫자만 가능합니다"); */
		}
	});
	
	//emali 중복체크
	$('#emailck').on('click', function(){
		var idspan3 = $('#idspan3');
		//입력한 값을 가져온다
		emailValue = $('#memEmail').val().trim();
		emailValueDoubleCheck(emailValue, idspan3);
	});
	
});//end 달러function
</script>

</head>
<body>
	<div class="box">
		<div id="result1">
			<div class="container mt-3">
				<h2>회원가입</h2>
				<hr>
				<form id="frm" action="/member/join" method="post" id="ff">
					<div class="mb-3 mt-3">
						<label for="id">아이디:</label> <input type="button" class="btn btn-outline-success btn-sm" value="중복검사" id="idck" disabled="disabled">
						<span id="idspan"></span> <input type="text" class="form-control" id="memId" name="memId">
					</div>

					<div>
						<label class="col-sm-2" for="memPwd">비밀번호 :</label><br>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="memPwd" name="memPwd" required placeholder="비밀번호" /><br>
						</div>

						<div>
							<label class="col-sm-2" for="memPwd2">비밀번호 확인 :</label><br>
							<div class="col-sm-3" style="display: flex">
								<input class="form-control" type="password" id="memPwd2" name="memPwd2" required placeholder="비밀번호 확인" oninput="checkPassword()">
							</div>
							<div class="col-sm-3" id="password-alert-false" style="display: none; color: red">비밀번호가 일치하지 않습니다.</div>
							<div class="col-sm-3" id="password-alert-true" style="display: none; color: green">비밀번호가 일치합니다.</div>
						</div>
					</div>

					<div class="mb-3 mt-3">
						<label for="memName">이름:</label> <input type="text" class="form-control" id="memName" name="memName" /><br>
					</div>

					<div class="mb-3 mt-3">
						<label for="nick">닉네임:</label> <input type="button" class="btn btn-outline-success btn-sm" value="중복검사" id="nickck" disabled="disabled">
						<span id="idspan2"></span> <input type="text" class="form-control" id="memAlias" name="memAlias" /><br>
					</div>

					<div class="mb-3 mt-3">
						<label for="memNbti">MBTI: P또는 J를 선택 해주세요.</label><br>
						<input type="radio" name="memMbti" value="P" class="mbit" checked="checked"/>P
						<input type="radio" name="memMbti" value="J" class="mbit" />J
					</div>

					<div class="mb-3 mt-3">
						<label for="memReg">주민번호</label><br>
						<input type="text" id="memReg" name="memReg" maxlength=6 class="form-control2 req" />&nbsp;-&nbsp;
						<input type="text" id="memReg2" name="memReg2" maxlength=7 class="form-control2 req" />
					</div>

					<div class="mb-3 mt-3">
						<label for="memAge">나이</label><br>
						<input type="text" class="form-control age" id="memAge" name="memAge" maxlength="3" /><br>
					</div>

					<div>
						<label for="memGender">성별</label><br>
						<input type="radio" name="memGender" value="여자" class="gender" checked="checked" />여자
						<input type="radio" name="memGender" value="남자" class="gender" />남자
					</div>

					<div class="mb-3 mt-3">
						<label for="memTel">휴대폰</label> 
						<!-- oninput: input 값이 변경될때마다 이벤트가 발생 -->
						<input type="text" class="form-control" id="memTel" name="memTel" 
						 oninput="phoneAutoHyphen(this)" maxlength="13" /><br>
					</div>

					<div class="mb-3 mt-3">
						<label for="memEmail">이메일</label> <input type="button" class="btn btn-outline-success btn-sm" value="중복검사" id="emailck" disabled="disabled">
						<span id="idspan3"></span> <input type="text" class="form-control" id="memEmail" name="memEmail" /><br>
					</div>
					
					<a href="#" onclick="path()" class="btn btn-primary btn-sm">회원가입</a>
					<a href="#" onclick="getPath('/main.do')" class="btn btn-primary btn-sm">메인</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>