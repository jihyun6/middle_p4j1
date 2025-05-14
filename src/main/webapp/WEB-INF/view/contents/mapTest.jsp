<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbbb5ddb701600ead3a6e1ca8df07686"></script>

</head>
<body>

dfsdf <div id="staticMap" style="width:600px;height:350px;" onload="map()">dfdf</div>

<script type="text/javascript">
window.onload=function map(){

    var staticMapContainer = document.getElementById('staticMap');
    var staticMapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 중심 좌표
        level: 3, // 확대 레벨
        marker: {
            position: new kakao.maps.LatLng(33, 126.570667),
            text: '텍스트를 표시할 수 있어요!'
        }
    };

    var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
	
}
</script>

</body>
</html>