<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

int  res3 = (Integer)request.getAttribute("result");

if(res3 > 0){
%>
  {
    "flag3"  : "가입성공"
  }

	
<% }else{ %>
	
	{
	  "flag3"  : "가입실패"
	}
	
<%	
}
%>