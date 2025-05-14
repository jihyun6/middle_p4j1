<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/0c69fdf2c0.js" crossorigin="anonymous"></script>
<script type="text/javascript">

function travelPlanAdd(contentNo,contentId,conTitle) {
	
	alert("담겼습니다!");
	
	
	$('#contentNo').val(contentNo);
	$('#contentsTypeId').val(contentId);
	$('#conTitle').val(conTitle);
	
// 	$.ajax({
// 		url : "planInsert.do",
// 		type : "POST",
// 		data : {
// 			"contentNo" : contentNo,
// 			"contentId" : contentId,
// 			"conTitle" : conTitle,
// 		},
// 		success : 
// 			function(data){
// 				alert("성공");				
// 			},
// 			error : function(xhr, status, error) {
// 		        alert("에러 발생: " + error);  // 요청 실패 시 에러 메시지 표시
// 		    },
// 	})

}

$(document).ready(function() {
    // 초성 추출 함수
    $('.btn-group .btn[data-filter="ㄱ"]').trigger('click');
    function getChosung(str) {
        const cho = ["ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"];
        let result = "";
        for (let i = 0; i < str.length; i++) {
            const code = str.charCodeAt(i) - 44032;
            if (code > -1 && code < 11172) {
                result += cho[Math.floor(code / 588)];
            }
        }
        return result;
    }

    // 필터 버튼 클릭 이벤트
    $('.btn-group .btn').click(function() {
        const filter = $(this).data('filter');
        const $items = $('.content-item');
        
        // 버튼 활성화 상태 변경
        $('.btn-group .btn').removeClass('active');
        $(this).addClass('active');
        
        $items.each(function() {
        	//div 아이디값을 가져오기 위한 data 변수
            const idx = $(this).data('idx');
        	
            if (filter === 'all') {
            	$('#'+idx).show();
                $items.show();
                return;
            }
        	
            const title = $(this).data('title');
            
            const chosung = getChosung(title);
            
            if (chosung.startsWith(filter)) {
            	$('#'+idx).show();
                $(this).show();
            } else {
                $(this).hide();
                $('#'+idx).hide();
        	};
    	});
	});
});

function loveCheck(contentNo, loveCount) {
    $.ajax({
        url: "loveUpdate.do",
        type: "POST",
        data: {
            "contentNo": contentNo,
            "memNo": ${member.memNo},
            "loveCount": loveCount,
            "boardNo": 0
        },
        success: function(data) {
            const $btn = event.currentTarget;
            const $icon = $btn.querySelector('i');
            const $count = $btn.querySelector('.love-count');
            
            if ($icon.classList.contains('red')) {
                $icon.classList.remove('red');
                $count.textContent = parseInt($count.textContent) - 1;
            } else {
                $icon.classList.add('red');
                $count.textContent = parseInt($count.textContent) + 1;
            }
        },
        error: function(error) {
            console.error('Error:', error);
            alert('오류가 발생했습니다.');
        }
    });
}
</script>
<style>
.portfolio-item {
    position: relative;
    overflow: hidden;
    border-radius: 10px 10px 0 0;
}

.portfolio-item img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    transition: transform 0.3s ease;
}

.portfolio-info {
    position: absolute;
    top: -40px;
    left: 0;
    right: 0;
    height: auto;
    background: rgba(0, 0, 0, 0.7);
    transition: top 0.3s ease;
    padding: 10px;
}

.portfolio-info p {
    color: white;
    margin: 0;
    text-align: center;
    font-size: 0.9rem;
    line-height: 1.2;
}

.card-content {
    background: white;
    padding: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 0 0 10px 10px;
}

.card-title {
    margin: 0;
    font-size: 1rem;
    font-weight: bold;
    color: #333;
    flex: 1;
    text-align: center;
    padding: 0 10px;
}

.portfolio-item:hover img {
    transform: scale(1.05);
}

.portfolio-item:hover .portfolio-info {
    top: 0;
}

.card {
    border: none;
    transition: transform 0.3s, box-shadow 0.3s;
    margin-bottom: 20px;
    background: none;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.btn-light.btn-sm {
    padding: 0.25rem 0.5rem;
    font-size: 0.875rem;
    background: white;
    border: none;
    color: #333;
}

.btn-primary.btn-sm {
    padding: 0.25rem 0.5rem;
    font-size: 0.875rem;
    background: #007bff;
    border: 1px solid #007bff;
    color: white;
}

.btn-primary.btn-sm:hover {
    background: #0056b3;
}

.love-count {
    color: #333;
    margin-left: 4px;
}

.fa-heart {
    color: #dc3545;
}

#contents-container {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
    gap: 20px;
    padding: 20px;
}

#contents-container {
    display: grid;
    grid-template-columns: repeat(4, 1fr); /* 한 줄에 4개씩 표시 */
    gap: 20px;
    padding: 20px;
}

/* 반응형 처리 */
@media (max-width: 1200px) {
    #contents-container {
        grid-template-columns: repeat(3, 1fr); /* 태블릿 크기에서는 3개씩 */
    }
}

@media (max-width: 768px) {
    #contents-container {
        grid-template-columns: repeat(2, 1fr); /* 모바일 크기에서는 2개씩 */
    }
}

@media (max-width: 576px) {
    #contents-container {
        grid-template-columns: repeat(1, 1fr); /* 작은 모바일에서는 1개씩 */
    }
}
</style>


</head>
<body>

<div class="container mt-3 text-center">
    <!-- 필터 버튼 그룹 -->
    <div class="btn-group mb-3" role="group" aria-label="한글 필터">
        <button type="button" class="btn btn-outline-primary" data-filter="ㄱ">ㄱ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㄴ">ㄴ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㄷ">ㄷ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㄹ">ㄹ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅁ">ㅁ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅂ">ㅂ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅅ">ㅅ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅇ">ㅇ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅈ">ㅈ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅊ">ㅊ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅋ">ㅋ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅌ">ㅌ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅍ">ㅍ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="ㅎ">ㅎ</button>
        <button type="button" class="btn btn-outline-primary" data-filter="all">전체</button>
    </div>

<div class="container mt-5">
    <div id="contents-container">
    <c:forEach var="contents" items="${contentsList}">
        <div id='${contents.contentNo}'>
            <div class="card" style="cursor: pointer;" onclick="getPath('/contentsDetail.do?contentNo=${contents.contentNo}')">
                <div class="portfolio-item">
                    <img src="${empty contents.conFirstimage ? '/resources/images/no-image.jpg' : contents.conFirstimage}" 
                         class="card-img-top content-item" 
                         alt="${contents.conTitle}" 
                         data-idx='${contents.contentNo}' 
                         data-title="${contents.conTitle}">
                    <div class="portfolio-info">
                        <p>${contents.conAddr1}</p>
                    </div>
                </div>
                <div class="card-content">
                    <!-- 좋아요 버튼 -->
                    <c:if test="${member.memNo == null}">
                        <button class="btn btn-light btn-sm" onclick="event.stopPropagation(); alert('로그인후 사용하세요')" >
                            <i class="fa-solid fa-heart"></i>
                            <span class="love-count">${contents.loveCount}</span>
                        </button>
                    </c:if>
                    <c:if test="${member.memNo != null}">
                        <button class="btn btn-light btn-sm love-btn" onclick="event.stopPropagation(); loveCheck(${contents.contentNo}, ${contents.loveCount})">
                            <i class="fa-solid fa-heart"></i>
                            <span class="love-count">${contents.loveCount}</span>
                        </button>
                    </c:if>
                    
                    <!-- 제목 -->
                    <h5 class="card-title">${contents.conTitle}</h5>
                    
                    <!-- 담기 버튼 -->
                    <button type="button" class="btn btn-primary btn-sm" 
                            onclick="event.stopPropagation(); travelPlanAdd('${contents.contentNo}', ${contents.contentsTypeId}, '${contents.conTitle}')">
                        담기
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
</div>
</div>

<script type="text/javascript">

function lochk(contentNo,loveCount){
// const contentNo = $('#loveUpdate input[name="contentNo"]').val(); // 쿠폰명
// const loveCount = $('#loveUpdate input[name="loveCount"]').val(); // 쿠폰명
//	alert("checkkc"+contentNo+"Ddd"+loveCount)
	/* $.ajax({
		 url: "contentsListType.do",
	     type: "POST",
	     data: {
	    	 "contentNo": contentNo,
	    	 "memNo": ${member.memNo},
 	    	 "loveCount":loveCount
	     },
	     success: function (data) {
		     alert("check")   ;
		     if (${lovedByMem}){
		 		$('#loveUpdate i').addClass('red')
		 		alert("이미 좋아요를 눌렀습니다 <br> 취소는 상세보기 페이지에서 하세요")
		 	}else{
		 		$('#loveUpdate i').addClass('black')
		 		loveup(contentNo,loveCount);
		 	}
	     }
	})  */
}

/* function loveup(contentNo,loveCount){
	alert("upupup"+contentNo)
	if( $('#loveUpdate i').hasClass('red')){
 	    	 loveCancel(contentNo,loveCount);
 	}else{
 		$.ajax({
			 url: "loveUpdate.do",
	         type: "POST",
	         data: {
	        	 "contentNo": contentNo,
	        	 "memNo": ${member.memNo},
	        	 "loveCount":loveCount,
	        	 "boardNo": null
	         },
	         success: function (data) {
			     alert("좋아요")   ;
			     $('#loveUpdate i').addClass('red');	
			     $('#loveUpdate i').removeClass('black');	
			  },
			}) 
 	}
}
function loveCancel(contentNo,loveCount){
	 if( $('#loveUpdate i').hasClass('black')){
	    	 loveUpdate(contentNo,loveCount);
	 }else{
			$.ajax({
					 url: "loveCancel.do",
			         type: "POST",
			         data: {
			        	 "contentNo": contentNo,
			        	 "memNo": ${member.memNo},
			        	 "loveCount":loveCount,
			        	 "boardNo": null
			        	 
			         },
			         success: function (data) {
					     alert("좋아요취소")   ;
					     $('#loveUpdate i').addClass('black');	
					     $('#loveUpdate i').removeClass('red');	
						
					},
				})
	 }
}
 */



</script>

</body>
</html>