<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- Jquery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.serializejson.min.js"></script>
<script src="${pageContext.request.contextPath}/js/public_script.js"></script>
<script>
// 캐러셀 자동 재생 설정
const carousels = document.querySelectorAll('.carousel');
carousels.forEach(carousel => {
  new bootstrap.Carousel(carousel, {
    interval: 5000,
    ride: 'carousel',
    wrap: true
  });
});
</script>


<style type="text/css">
#backBtn {
	position: fixed;
	right: 100px;
	bottom: 100px;
	

}

  .navmain {
    display: flex;
    flex-direction: column;
    align-items: center;
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
  
.portfolio-item {
    position: relative;
    overflow: hidden;
    border-radius: 8px;
    margin-bottom: 20px;
}

.portfolio-item img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    transition: all 0.3s ease;
}

.portfolio-info {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, 0.7);
    padding: 15px;
    color: white;
    transform: translateY(100%);
    transition: all 0.3s ease;
}

.portfolio-info h4 {
    color: #ffffff;  
    margin-bottom: 5px;  
}

.portfolio-item:hover .portfolio-info {
    transform: translateY(0);
}

.portfolio-item:hover img {
    transform: scale(1.1);
}
  
.service-card {
  background: #fff;
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 5px 25px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.service-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
}

.icon-box {
  width: 65px;
  height: 65px;
  border-radius: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.bg-primary-soft { background: rgba(13, 110, 253, 0.1); }
.bg-success-soft { background: rgba(25, 135, 84, 0.1); }
.bg-warning-soft { background: rgba(255, 193, 7, 0.1); }
.bg-info-soft { background: rgba(13, 202, 240, 0.1); }
.bg-danger-soft { background: rgba(220, 53, 69, 0.1); }
.bg-purple-soft { background: rgba(111, 66, 193, 0.1); }

.service-content {
  padding-top: 10px;
}

.service-content h3 {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 15px;
}

.service-content p {
  color: #6c757d;
  margin-bottom: 20px;
}

.service-link {
  color: #0d6efd;
  text-decoration: none;
  font-weight: 500;
  display: inline-flex;
  align-items: center;
  gap: 5px;
}

.service-link:hover {
  text-decoration: underline;
}  
  
  
.image-container {
  position: relative;
  width: 50%;
  margin: 0 auto;
  cursor: pointer;
}

.plan-image {
  width: 60%;
  height: auto;
  display: block;
  text-align: right;
}

.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-container:hover .overlay {
  opacity: 1;
}

.text {
  color: white;
  font-size: 58px;
  font-weight: bold;
  text-decoration: none;
  text-align: center;
}

.text:hover {
  color: white;
  text-decoration: none;
}


.carousel-controls {
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    transform: translateY(-50%);
    display: flex;
    justify-content: space-between;
    padding: 0 -50px; /* 버튼을 바깥으로 조금 이동 */
    pointer-events: none; /* 컨트롤러 영역이 카드 클릭을 방해하지 않도록 */
}

.carousel-control-prev,
.carousel-control-next {
    position: relative; /* absolute 대신 relative 사용 */
    width: 40px;
    height: 40px;
    background-color: rgba(0,0,0,0.5);
    border-radius: 50%;
    margin: 0;
    pointer-events: auto; /* 버튼 클릭 가능하도록 */
}

/* 카드 스타일 수정 */
.card {
    margin-bottom: 20px;
    transition: transform 0.2s;
    border: none;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.card:hover {
    transform: translateY(-5px);
}

.card-img-top {
    border-radius: 10px 10px 0 0;
}

.card-body {
    padding: 1rem;
}

/* 이미지 컨테이너 스타일 */
.img-container {
    position: relative;
    padding-top: 75%; /* 4:3 비율 유지 */
    overflow: hidden;
}

.img-container img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
}



</style>
</head>

  <header id="header" class="header d-flex align-items-center sticky-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

      <a href="main.do" class="logo d-flex align-items-center">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1 class="sitename"><img src="./image/title.png" ></h1>
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
      <div class="container d-lg-flex justify-content-between align-items-center navmain">
        <h1 class="mb-2 mb-lg-0"></h1>
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
			<li class="dropdown"><a href="#"><span>고객센터</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
						<ul class="test2">
							<li><a href="#"></a></li>
				          	<li><a href="#" onclick="getPath('/noticeList.do')">공지사항</a></li>
				          	<li><a href="#" onclick="getPath('/custormerList.do')">자주하는 질문</a></li>
							</ul>	    
										</li>
										
          	<c:if test="${sessionScope.member.memAuth == '0'}">
          		<li><a href="#" onclick="getPath('/boardList.do?codeNo=3')">리뷰</a></li>
          	</c:if>
          	
          	<c:if test="${ not empty sessionScope.member.memId && sessionScope.member.memAuth != '0' }">
          		<li><a href="#" onclick="getPath('/cartList.do')">장바구니</a></li>
          	</c:if>
          </ol>
        </nav>
      </div>
    </div><!-- End Page Title -->

    <!-- Starter Section Section -->
    <section id="starter-section" class="starter-section section">
	
      <!-- Section Title -->
<!-- Portfolio Section -->
<section id="portfolio" class="portfolio section">
  <div class="container section-title" data-aos="fade-up">
    <h2>인기 여행지</h2>
    <p>한국의 인기 여행지와 맛집을 소개합니다.</p>
  </div>

  <div class="container">
    <div class="row gy-4">
      <c:forEach var="contents" items="${contentsList}" varStatus="status" end="23">
        <div class="col-lg-4 col-md-6 mb-4">
          <div class="portfolio-item">
            <a href="#" onclick="getPath('/contentsDetail.do?contentNo=${contents.contentNo}')">
              <img src="${empty contents.conFirstimage ? '/resources/images/no-image.jpg' : contents.conFirstimage}" 
                   class="img-fluid" alt="${contents.conTitle}">
              <div class="portfolio-info">
                <h4>${contents.conTitle}</h4>
                <p>${contents.conAddr1}</p>
                <div class="portfolio-meta">
                  <i class="bi bi-heart-fill text-danger"></i> ${contents.loveCount}
                </div>
              </div>
            </a>
          </div>
        </div>
        <c:if test="${status.count % 3 == 0}">
          </div><div class="row gy-4">
        </c:if>
      </c:forEach>
    </div>
  </div>
</section>


<!-- Popular Contents by Member Type Section -->
<section id="member-type-contents" class="section">
    <div class="container section-title" data-aos="fade-up">
        <h2>MBTI별 인기 여행지</h2>
        <p>회원님들이 가장 사랑하는 여행지를 소개합니다</p>
    </div>

    <div class="container">
        <!-- J타입 인기 컨텐츠 -->
        <div class="mb-5">
            <h3 class="mb-4">J타입이 선호하는 여행지 TOP 8</h3>
            <div id="jTypeCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <!-- 첫 번째 슬라이드 (0-3) -->
                    <div class="carousel-item active">
                        <div class="row">
                            <c:forEach var="content" items="${jTypeContentsList}" begin="0" end="3" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img src="${empty content.conFirstimage ? '/resources/images/no-image.jpg' : content.conFirstimage}"
                                             class="card-img-top" alt="${content.conTitle}"
                                             style="height: 200px; object-fit: cover;">
                                        <div class="card-body">
                                            <h5 class="card-title text-truncate">${content.conTitle}</h5>
                                            <p class="card-text text-truncate">${content.conAddr1}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <small class="text-danger">
                                                    <i class="bi bi-heart-fill"></i> ${content.loveCount}
                                                </small>
                                                <a href="#" onclick="getPath('/contentsDetail.do?contentNo=${content.contentNo}')" 
                                                   class="btn btn-sm btn-outline-primary">자세히</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- 두 번째 슬라이드 (4-7) -->
                    <div class="carousel-item">
                        <div class="row">
                            <c:forEach var="content" items="${jTypeContentsList}" begin="4" end="7" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img src="${empty content.conFirstimage ? '/resources/images/no-image.jpg' : content.conFirstimage}"
                                             class="card-img-top" alt="${content.conTitle}"
                                             style="height: 200px; object-fit: cover;">
                                        <div class="card-body">
                                            <h5 class="card-title text-truncate">${content.conTitle}</h5>
                                            <p class="card-text text-truncate">${content.conAddr1}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <small class="text-danger">
                                                    <i class="bi bi-heart-fill"></i> ${content.loveCount}
                                                </small>
                                                <a href="#" onclick="getPath('/contentsDetail.do?contentNo=${content.contentNo}')" 
                                                   class="btn btn-sm btn-outline-primary">자세히</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="carousel-controls">
                    <button class="carousel-control-prev" type="button" data-bs-target="#jTypeCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon bg-dark rounded-circle" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#jTypeCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon bg-dark rounded-circle" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>

        <!-- P타입 인기 컨텐츠-->
        <div class="mb-5">
            <h3 class="mb-4">P타입이 선호하는 여행지 TOP 8</h3>
            <div id="pTypeCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <!-- 첫 번째 슬라이드 (0-3) -->
                    <div class="carousel-item active">
                        <div class="row">
                            <c:forEach var="content" items="${pTypeContentsList}" begin="0" end="3" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img src="${empty content.conFirstimage ? '/resources/images/no-image.jpg' : content.conFirstimage}"
                                             class="card-img-top" alt="${content.conTitle}"
                                             style="height: 200px; object-fit: cover;">
                                        <div class="card-body">
                                            <h5 class="card-title text-truncate">${content.conTitle}</h5>
                                            <p class="card-text text-truncate">${content.conAddr1}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <small class="text-danger">
                                                    <i class="bi bi-heart-fill"></i> ${content.loveCount}
                                                </small>
                                                <a href="#" onclick="getPath('/contentsDetail.do?contentNo=${content.contentNo}')" 
                                                   class="btn btn-sm btn-outline-primary">자세히</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- 두 번째 슬라이드 (4-7) -->
                    <div class="carousel-item">
                        <div class="row">
                            <c:forEach var="content" items="${pTypeContentsList}" begin="4" end="7" varStatus="status">
                                <div class="col-lg-3 col-md-6">
                                    <div class="card h-100">
                                        <img src="${empty content.conFirstimage ? '/resources/images/no-image.jpg' : content.conFirstimage}"
                                             class="card-img-top" alt="${content.conTitle}"
                                             style="height: 200px; object-fit: cover;">
                                        <div class="card-body">
                                            <h5 class="card-title text-truncate">${content.conTitle}</h5>
                                            <p class="card-text text-truncate">${content.conAddr1}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <small class="text-danger">
                                                    <i class="bi bi-heart-fill"></i> ${content.loveCount}
                                                </small>
                                                <a href="#" onclick="getPath('/contentsDetail.do?contentNo=${content.contentNo}')" 
                                                   class="btn btn-sm btn-outline-primary">자세히</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="carousel-controls">
                    <button class="carousel-control-prev" type="button" data-bs-target="#pTypeCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon bg-dark rounded-circle" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#pTypeCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon bg-dark rounded-circle" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>



<!-- Services Section -->
<section id="services" class="services section">
  <div class="container section-title" data-aos="fade-up">
    <h2>여행 서비스</h2>
    <p>대전 여행을 더욱 즐겁게 만들어줄 다양한 서비스를 제공합니다.</p>
  </div>

  <div class="container">
    <div class="row gy-4">
      <!-- 관광지 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100">
        <div class="service-card">
          <div class="icon-box bg-primary-soft">
            <i class="bi bi-geo-alt text-primary fs-1"></i>
          </div>
          <div class="service-content">
            <h3>관광지</h3>
            <p>대전의 명소와 볼거리를 소개합니다.</p>
            <a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=12')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 숙박 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="200">
        <div class="service-card">
          <div class="icon-box bg-success-soft">
            <i class="bi bi-house-door text-success fs-1"></i>
          </div>
          <div class="service-content">
            <h3>숙박</h3>
            <p>편안한 숙박시설을 한눈에 확인하세요.</p>
            <a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=32')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 맛집 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="300">
        <div class="service-card">
          <div class="icon-box bg-warning-soft">
            <i class="bi bi-cup-hot text-warning fs-1"></i>
          </div>
          <div class="service-content">
            <h3>맛집</h3>
            <p>대전의 맛있는 식당과 카페를 소개합니다.</p>
            <a href="#" onclick="getPath('/contentsListType.do?contentsTypeId=39')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 여행톡 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="400">
        <div class="service-card">
          <div class="icon-box bg-info-soft">
            <i class="bi bi-chat-dots text-info fs-1"></i>
          </div>
          <div class="service-content">
            <h3>여행톡</h3>
            <p>여행 정보를 공유하고 소통하는 공간입니다.</p>
            <a href="#" onclick="getPath('/boardList.do?codeNo=2')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 공지사항 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="500">
        <div class="service-card">
          <div class="icon-box bg-danger-soft">
            <i class="bi bi-megaphone text-danger fs-1"></i>
          </div>
          <div class="service-content">
            <h3>공지사항</h3>
            <p>중요한 안내사항을 확인하세요.</p>
            <a href="#" onclick="getPath('/boardList.do?codeNo=2')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>

      <!-- 1:1 문의하기 -->
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="600">
        <div class="service-card">
          <div class="icon-box bg-purple-soft">
            <i class="bi bi-headset text-purple fs-1"></i>
          </div>
          <div class="service-content">
            <h3>1:1 문의하기</h3>
            <p>궁금한 점을 문의해주세요.</p>
            <a href="#" onclick="getPath('/boardList.do?codeNo=2')" class="service-link">
              자세히 보기 <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>










<div class="container mt-3">
  	<h1>계획 세우기 click! >> </h1>
  <div class="image-container">
    <img src="./image/p4j1button.png" class="mx-auto d-block plan-image">
    <div class="overlay">
      <a href="#" onclick="getPath('/boardInsert.do?codeNo=1')" class="text">
        여행계획 세우러 GO
      </a>
    </div>
  </div>
</div>


<!--       일정 계획 서비스
      <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="500">
        <div class="service-item item-indigo position-relative">
          <div class="icon">
            <svg width="100" height="100" viewBox="0 0 600 600" xmlns="http://www.w3.org/2000/svg">
    </section>/Starter Section Section -->
    </section>

  </main>

  <footer id="footer" class="footer dark-background">
    <div class="container">
      <h3 class="sitename"><img src="./image/p4j1.png" style="width:100px; height: 40px;"></h3>
      <p>마 계획못짜믄 일로와라</p>
<!--       <div class="social-links d-flex justify-content-center">
        <a href=""><i class="bi bi-twitter-x"></i></a>
        <a href=""><i class="bi bi-facebook"></i></a>
        <a href=""><i class="bi bi-instagram"></i></a>
        <a href=""><i class="bi bi-skype"></i></a>
        <a href=""><i class="bi bi-linkedin"></i></a>
      </div> -->
      <div class="container">
        <div class="copyright">
          <span>Copyright</span> <strong class="px-1 sitename">p4j1</strong> <span>All Rights Reserved</span>
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
<jsp:include page="/WEB-INF/view/template/prescript_.jsp"></jsp:include>
</html>