<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

//controller에서 저당한 값 꺼내기
String res3 = (String)request.getAttribute("result3");

if(res3 == null){
//korea입력 - db에 없는 경우  - 사용 가능 	

%>

  {
     "flag3"  :  "사용가능"
  }

	
<% }else{
  //a001 입력 - db에서 a001을 찾은 경우  - 사용불가능 
%>

 {
     "flag3"  :  "사용불가능"
  }

  

<%	
}
%>