<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>couponList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
function coUpdate(){
	const cupName = $('.modal-body input[name="cupName"]').val(); // 쿠폰명
	const cupNo = $('.modal-body input[name="cupNo"]').val();// 쿠폰명
    const cupDiscount = $('#myModal input[name="cupDiscount"]').val(); // 할인율
	$.ajax({
	 url: "couponUpdate.do",
     type: "POST",
     data: {
    	 "cupName":cupName,
		 "cupDiscount":cupDiscount,
    	 "cupNo":cupNo
    		
     },
     success: function () {
	     alert("수정되었습니다");
	     $('#reName').text(cupName);
	     $('#reDis').text(cupDiscount);
	     $('#myModal').modal('hide');
	     $('.modal-backdrop').hide();
	     
     },
     error: function (xhr, status, error) {
         console.error("에러 발생:", error);
         alert("수정에 실패했습니다. 다시 시도해주세요.");
         
     }
	})  
}
</script>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">쿠폰 목록</h2>
    <button type="button" class="btn btn-primary" onclick="getPath('/couponInsert.do')">쿠폰추가</button>
    <table class="table table-hover">
        <thead class="table-light">
            <tr>
                <th>쿠폰번호</th>
                <th>쿠폰명</th>
                <th>할인율</th>
                <th>쿠폰등록일</th>
                <th>쿠폰생성자</th>
                <th>선택한 회원한테 넣어주기 셀렉트박스</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="coupon" items="${couponList}">
                <tr >
                    <td>${coupon.cupNo}</td>
                    <td id="reName">${coupon.cupName}</td>
                    <td id="reDis">${coupon.cupDiscount}%</td>
                    <td>${coupon.cupDate}</td>
                    <td>${member.memName}</td>
                    <td>선택한 회원한테 넣어주기 셀렉트박스</td>
                    <td>
                    	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" >수정</button>
                    	<button type="button" class="btn btn-danger" onclick="getPath('/couponDelete.do?cupNo=${coupon.cupNo}')">삭제</button>
                    </td>
                </tr>
                
                <!-- 모달 창 -->
    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        	
            <div class="modal-content">
                <!-- 모달 헤더 -->
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">쿠폰수정</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <!-- 모달 바디 -->
                <div class="modal-body" >
                    <a>쿠폰번호: <input type="hidden" name="cupNo" value="${coupon.cupNo}">${coupon.cupNo}</a><br>
                    <a>쿠폰명: <input type="text" name="cupName" value="${coupon.cupName}"></a><br>
                    <a>쿠폰할인율: <input type="text" name="cupDiscount" value="${coupon.cupDiscount}">%</a><Br>
                    <a>작성자: ${member.memName}</a>
                </div>
                <!-- 모달 푸터 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="coUpdate()">수정</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
            
        </div>
    </div>
            </c:forEach>
        </tbody>
    </table>
</div>

   
</body>
</html>