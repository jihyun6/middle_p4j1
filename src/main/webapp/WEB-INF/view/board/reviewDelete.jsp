<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 처리</title>
<script>
    let boardNo = '${param.boardNo}'; 
    
    if(confirm('정말 삭제하시겠습니까?')) {
        location.href = "reviewDelete.do?boardNo=" + boardNo;
    } else {
        history.back();
    }
</script>
</head>
<body>
</body>
</html>