<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.PaymentVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <%

List<PaymentVo>  pvoList = (List<PaymentVo>)request.getAttribute("pvo");

 
Gson  gson = new Gson();

//list자바 객체를 //json형식의 문자열 데이타 만들기  - 직렬화 
String result = gson.toJson(pvoList);

out.print(result);
out.flush();

%>