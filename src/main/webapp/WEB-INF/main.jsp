<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- Jquery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>


<style type="text/css">
#backBtn {
	position: fixed;
	right: 100px;
	bottom: 100px;
	

}
  .test2 li::before {
    content: ""!important;
  }

  .test .dropdown ul {
  	list-style:none;
    margin: 0;
    padding: 10px 0;
    background: var(--nav-dropdown-background-color);
    display: block;
    position: absolute;
    visibility: hidden;
    left: 14px;
    top: 130%;
    opacity: 0;
    transition: 0.3s;
    border-radius: 4px;
    z-index: 99;
    box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.1);
  }

  .test .dropdown ul li {
    min-width: 200px;
  }

  .test .dropdown ul a {
/*     padding: 10px 20px; */
	padding: 10px 0px;
    font-size: 15px;
    text-transform: none;
    color: var(--nav-dropdown-color);
  }

  .test .dropdown ul a i {
    font-size: 12px;
  }

  .test .dropdown ul a:hover,
  .test .dropdown ul .active:hover,
  .test .dropdown ul li:hover>a {
    color: var(--nav-dropdown-hover-color);
  }

  .test .dropdown:hover>ul {
	opacity: 1;
    top: 100%;
    visibility: visible;
  }

  .test .dropdown .dropdown ul {
    top: 0;
    left: -90%;
    visibility: hidden;
  }

  .test .dropdown .dropdown:hover>ul {
    opacity: 1;
    top: 0;
    left: -100%;
    visibility: visible;
  }
</style>
</head>
  <header id="header" class="header d-flex align-items-center sticky-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

      <a href="main.do" class="logo d-flex align-items-center">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1 class="sitename">P4J1</h1>
      </a>

      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="main.do">Home</a></li>
          <jsp:include page="/WEB-INF/view/member/loginview.jsp"></jsp:include>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>
    </div>
  </header>
  
  <main class="main">
    <!-- Page Title -->
    <div class="page-title light-background">
      <div class="container d-lg-flex justify-content-between align-items-center">
        <h1 class="mb-2 mb-lg-0">게시판 제목 연동해줘~~</h1>
        <nav class="breadcrumbs test">
          <ol>
			<li class="dropdown"><a href="#"><span>투어</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
				<ul class="test2">
					<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=12')"></a></li>
					<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=12')">관광지</a></li>
				    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=14')">문화시설</a></li>
				    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=15')">행사</a></li>
				    <li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=28')">레포츠</a></li>    	
				</ul>	    
			</li>
          	<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=32')">숙박</a></li>
          	<li><a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=39')">맛집</a></li>
          	<li><a href="#" onclick="getPath('/boardList.do?codeNo=1')">여행일정계획</a></li>
          	<li><a href="#" onclick="getPath('/boardList.do?codeNo=2')">여행톡</a></li>
          	<c:if test="${sessionScope.member.memAuth == '0'}">
          		<li><a href="#" onclick="getPath('/boardList.do?codeNo=3')">리뷰</a></li>
          	</c:if>
          	
          	<c:if test="${ not empty sessionScope.member.memId && sessionScope.member.memAuth != '0' }">
          		<li><a href="#" onclick="getPath('/cartList.do')">장바구니</a></li>
          	</c:if>
          	
          	<c:if test="${ not empty sessionScope.member.memId && sessionScope.member.memAuth != '0' }">
          		<li><a href="#" onclick="getPath('/noticeList.do')">공지사항</a></li>
          	</c:if>
          </ol>
        </nav>
      </div>
    </div><!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">
	
      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <h2>Starter Section</h2>
        <p>Necessitatibus eius consequatur ex aliquid fuga eum quidem sint consectetur velit</p>
      </div><!-- End Section Title -->

      <div class="container" data-aos="fade-up">
        <p>Use this page as a starter for your own custom pages.</p>
      </div>

    </section><!-- /Starter Section Section -->

  </main>

  <footer id="footer" class="footer dark-background">
    <div class="container">
      <h3 class="sitename">Me &amp; Family</h3>
      <p>Et aut eum quis fuga eos sunt ipsa nihil. Labore corporis magni eligendi fuga maxime saepe commodi placeat.</p>
      <div class="social-links d-flex justify-content-center">
        <a href=""><i class="bi bi-twitter-x"></i></a>
        <a href=""><i class="bi bi-facebook"></i></a>
        <a href=""><i class="bi bi-instagram"></i></a>
        <a href=""><i class="bi bi-skype"></i></a>
        <a href=""><i class="bi bi-linkedin"></i></a>
      </div>
      <div class="container">
        <div class="copyright">
          <span>Copyright</span> <strong class="px-1 sitename">Me &amp; Family</strong> <span>All Rights Reserved</span>
        </div>
        <div class="credits">
          <!-- All the links in the footer should remain intact. -->
          <!-- You can delete the links only if you've purchased the pro version. -->
          <!-- Licensing information: https://bootstrapmade.com/license/ -->
          <!-- Purchase the pro version with working PHP/AJAX contact form: [buy-url] -->
          Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
      </div>
    </div>
  </footer>
  <button id="backBtn">뒤로가기</button>
  
  <!-- Scroll Top -->
  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  
</body>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<jsp:include page="/WEB-INF/view/template/prescript_.jsp"></jsp:include>
</html>