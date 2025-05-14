/**
 * member javascript function()
 */

//아이디 입력형식 체크
idInputCheck = function( idValue, entity ) {
	
	console.log('idInputCheck');
	
	//규칙 설정
	regid = /^[a-zA-Z][a-zA-Z0-9]{3,7}$/;
		
	//입력한 값이 규칙에 맞는지 비교 test() - true/ false 리턴
	if(regid.test(idValue)){
		entity.css('border', '2px solid blue');
		$('#idck').prop('disabled', false);
		
	}
	
	else{
		entity.css('border', '2px solid red');
		//사용불가능상태 설정
		$('#idck').prop('disabled', true);
		/* alert("아이디는 대소문자 숫자만 가능합니다"); */
	}
}

idDoubleCheck = function( idValue, entity ){
	$.ajax({
		url: "/P4J1_Project/idcheck.do?id=" + idValue,
		type: "get",
		datatype: "json",
		success: function(data) {
			console.log("data : ", data);
			
			if(data.flag == null){
				$('#idspan').html("가능한 아이디입니다.").css('color', 'blue');
			}
			else{
			 	$('#idspan').html("중복된 아이디 입니다.").css('color', 'red');
			}
		}
	});
}

//닉네임 입력형식 체크
nickInputCheck = function( nickValue, entity ) {
	
	console.log('idInputCheck');
	
	//규칙 설정
	regid = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/;
		
	//입력한 값이 규칙에 맞는지 비교 test() - true/ false 리턴
	if(regid.test(nickValue)){
		entity.css('border', '2px solid blue');
		$('#nickck').prop('disabled', false);
	}
	
	else{
		entity.css('border', '2px solid red');
		//사용불가능상태 설정
		$('#nickck').prop('disabled', true);
		/* alert("아이디는 대소문자 숫자만 가능합니다"); */
	}
}

//닉네임 중복체크
nickValueDoubleCheck = function( nickValue, entity ){
	$.ajax({
		url: "/P4J1_Project/nickcheck.do?nick=" + nickValue,
		type: "get",
		datatype: "json",
		success: function(data) {
			console.log("data : ", data);
			
			if(data.result == null){
				entity.html("가능한 닉네임입니다").css('color', 'blue');
			}
			else{
			 	entity.html("중복된 닉네임 입니다. 다시 입력하세요").css('color', 'red');
			}
		}
	});
}

emailValueDoubleCheck = function(emailValue, entity) {
	$.ajax({
		url: "/P4J1_Project/emailcheck.do?email=" + emailValue,
		type: "get",
		datatype: "json",
		success: function(data) {
			console.log("data : ", data);

			if (data.result == null) {
				entity.html("가능한 이메일 입니다").css('color', 'blue');
			}
			else {
				entity.html("중복된 이메일 입니다.").css('color', 'red');
			}
		}
	});
}