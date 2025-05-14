/*
 * public js
 */

var pathList = ["/main.do#"];

window.onload = function(){
	document.querySelector("#backBtn").addEventListener("click", () => {
		let url;
		
		if(pathList.length == 1) {
			url = pathList[pathList.length()-1];
		}else{
			url = pathList[pathList.length - 2];
		}
		
		pathList.pop();
		
		
		if (url == undefined) location.reload(true);
		if (url == "/main.do#") {
			location.reload(true);
		} else {
			getPath(url);
		}
	})	
}
 
/* GET 요청 */
viewPath = function(path, methodType, param) {
	
		//alert("getPath + viewPath" + url);
		//pathList.push(url);
	
		if(param != null){
			path += param;
		}
		
		$.ajax({
			url: "/P4J1_Project"+path,
			type: methodType,
			datatype:"html",
			success:function(data){
				console.log("data : ", data);
				
				$("#starter-section").html(data);
				//location.href=reload();
			}	
		});
}

getPath = function(url){
	
//	alert("getPath" + url);
	pathList.push(url);
	
	$.ajax({
		url: "/P4J1_Project"+url,
		type: "get",
		datatype:"html",
		success:function(data){
			// console.log("data : ", data);
			if(url === '/main.do' && data != null ){
				//로그인 후 처리
				location.href = '/P4J1_Project/main.do';
			}else{
				$("#starter-section").html(data);
			}
		}	
	})
} 

postPath = function(url, frm){
	
	//alert("postPath" + url);
	
	$.ajax({
		url: "/P4J1_Project/" + url,
		type: "post",
		data: frm.serialize(),
		success:function(data){
			if(typeof data.url === 'undefined' || data.url === null){
				alert('id또는 비밀번호가 틀렸습니다.');
				return false;
			}
				
			else{
				getPath(data.url);
			}
			//서버 -> JSON 타입의 String문자{"boardNo":3} -> 클라이언트
			//jObj.put("boardNo", 2);
			//result : {"boardNo":3}
			//viewPath('/boardDetail.do','get', '?boardNo='+result.boardNo);//result.boardNo : 3
		},
		error:function(){
			alert("Error!");
		}
	})
}

/* LOGIN 요청처리 */
//subPath = function( obj ) {
//	console.log(obj.serialize());
//	$.ajax({
//		url: "/P4J1_Project/login.do",
//		type: "post",
//		data:obj.serialize(),
//		success: function(result) {
//			//서버 -> JSON 타입의 String문자{"boardNo":3} -> 클라이언트
//			//jObj.put("boardNo", 2);
//			//result : {"boardNo":3}
//			console.log("result : ", result);
//		
//			if(result.result == 'ok'){
//				viewPath('/boardList.do', 'get', '?codeNo=3');
//			}
//			else{
//				alert('ID 또는 비밀번호가 틀립니다.');
//				viewPath('/login.do', 'get');
//			}
//		},
//		error: function() {
//			alert("Error!");
//		}
//	})
//}

function getCookie(name) {
    var requestUri = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	console.log(requestUri);
	if (requestUri != null) {
		$.ajax({
			url: requestUri[2],
			type: "get",
			datatype: "html",
			success: function(data) {
				console.log("data : ", data);
				$("#starter-section").html(data);
				//location.href=reload();
			}
		})
	}
}
//getCookie("requestUri");
