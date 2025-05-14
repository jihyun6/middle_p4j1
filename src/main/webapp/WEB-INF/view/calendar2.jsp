<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset='utf-8' />
  <script src="js/jquery-3.7.1.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  
  <!-- 화면 해상도에 따라 글자 크기 대응(모바일 대응) -->
  <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
  <!-- jquery CDN -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
  <!-- fullcalendar CDN -->
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
  
<style>
/* body ì¤íì¼ */
body {
	overflow: hidden;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 10px;
	width : 600px;
	height : 700px;
}
/* body 스타일 */
/* .fc-header-toolbar {
	padding-top: 1em;
	padding-left: 1em;
	padding-right: 1em;
}   
 */

.fc-today-button{
	 display : none;
}
 
</style>


<script type="text/javascript">


$(function(){
	// calendar element 취득
    let calendarEl = $('#calendar')[0];
    // full-calendar 생성하기
    //start = 
    var calendar = new FullCalendar.Calendar(calendarEl, {
      height: '700px', // calendar 높이 설정
      expandRows: true, // 화면에 맞게 높이 재설정
      slotMinTime: '08:00', // Day 캘린더에서 시작 시간
      slotMaxTime: '20:00',
       customButtons:{
    	  mySaveButton:{
        		text:'저장하기',
        		  click : function (){
        			  //start 값, end값 전달하기

        			  
        			 sd = document.getElementById("start");
        			 ed = document.getElementById("end");
        			  
        			 sd.value= $('#date').val();
        			 ed.value= $('#endDate').val(); 
        			 /* window.close(); */
        			}

      	  }
      }, 
   	  // 해더에 표시할 툴바
      headerToolbar: {
        left: 'prev,next',
        center: 'title',
        right: 'dayGridMonth'
      },
      initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
      //initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
      
      editable: true, // 수정 가능?
      selectable: true,// 달력 일자 드래그 설정가능
      nowIndicator: true, // 현재 시간 마크
      dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
      locale: 'ko', // 한국어 설정
      eventAdd: function(obj) { // 이벤트가 추가되면 발생하는 이벤트
        console.log(obj);
      },
      eventChange: function(obj) { // 이벤트가 수정되면 발생하는 이벤트
        console.log(obj);
      },
      eventRemove: function(obj){ // 이벤트가 삭제되면 발생하는 이벤트
        console.log(obj);
      }
	})
    
    
 	// 캘린더 랜더링(달력 출력)
    calendar.render();
    
   
    /* $('td.fc-daygrid-day', calendarEl).on('click', function(){
    	alert($(this).attr("data-date"));
    }) */
      
   var cnt = 0;
    
   $(document).on('click', '#calendar  td.fc-daygrid-day', function(){
	   if(cnt%2==0){
    	$('#date').val($(this).attr("data-date"));
	   }
	   else{
    	$('#endDate').val($(this).attr("data-date"));
	   }
	   cnt++;
    })
    
});


</script>


<body style="padding:30px; width:800px;">

	여행시작일 : <input type="text" id="date">
	여행종료일 : <input type="text" id="endDate">
	
  <!-- 캘린더 위의 해더 스타일(날짜가 있는 부분) -->
  <br><br>
  <div class="container mt-12" style="width:800px;">
    <div id='calendar' style="width:700px;"></div>
  </div>

  
<body>
<html>