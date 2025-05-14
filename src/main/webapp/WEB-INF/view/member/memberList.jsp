<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- jQuery를 먼저 로드 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 그 다음 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- 삭제 함수 스크립트 -->
    <script>
    function deleteMember(memNo) {
        if(confirm('정말로 이 회원을 삭제하시겠습니까?')) {
            $.ajax({
                url: '${pageContext.request.contextPath}/memberDelete.do',
                type: 'POST',
                data: { memNo: memNo },
                success: function(response) {
                    if(response.status === 'success') {
                        alert('회원이 삭제되었습니다.');
                        location.reload();
                    } else {
                        alert('회원 삭제에 실패했습니다.');
                    }
                },
                error: function(xhr) {
                    if(xhr.status === 401) {
                        alert('로그인이 필요합니다.');
                    } else if(xhr.status === 403) {
                        alert('권한이 없습니다.');
                    } else {
                        alert('오류가 발생했습니다.');
                    }
                }
            });
        }
    }
    
    document.getElementById('searchForm').addEventListener('submit', function(e) {
        // 폼 제출 전 유효성 검사
        const searchField = document.querySelector('select[name="searchField"]').value;
        const searchText = document.querySelector('input[name="searchText"]').value;
        
        // 검색어와 필드 모두 입력된 경우에만 제출
        if (searchField && searchText) {
            return true;
        } else if (isAnyFilterApplied()) {
            return true;
        } else {
            e.preventDefault();
            alert('검색 조건을 입력해주세요.');
        }
    });

    function isAnyFilterApplied() {
        // 마일리지 필터
        const mileageCondition = document.querySelector('select[name="mileageCondition"]').value;
        const mileageValue = document.querySelector('input[name="mileageValue"]').value;
        
        // 가입일자 필터
        const joinDateFrom = document.querySelector('input[name="joinDateFrom"]').value;
        const joinDateTo = document.querySelector('input[name="joinDateTo"]').value;
        
        // 연령대 필터
        const ageGroup = document.querySelector('select[name="ageGroup"]').value;
        
        
        return mileageCondition || mileageValue || 
               joinDateFrom || joinDateTo || 
               ageGroup ;
    }

    function getPath(path) {
        window.location.href = '${pageContext.request.contextPath}' + path;
    }


/*     function deleteMember(memNo) {
        if(confirm('정말로 이 회원을 삭제하시겠습니까?')) {
            $.ajax({
                url: '${pageContext.request.contextPath}/memberDelete.do',
                type: 'POST',
                data: { memNo: memNo },
                success: function(response) {
                    if(response.status === 'success') {
                        alert('회원이 삭제되었습니다.');
                        location.reload(); // 페이지 새로고침
                    } else {
                        alert('회원 삭제에 실패했습니다.');
                    }
                },
                error: function(xhr) {
                    if(xhr.status === 401) {
                        alert('로그인이 필요합니다.');
                    } else if(xhr.status === 403) {
                        alert('권한이 없습니다.');
                    } else {
                        alert('오류가 발생했습니다.');
                    }
                }
            });
        }
    }     */
    
    
    console.log("현재 사용자 권한: ${sessionScope.member.memAuth}");
    
    
    function adminMember(memNo){
        $.ajax({
            url: "${pageContext.request.contextPath}/memberDetail.do",
            type: "get",
            data: {
                "memNo": memNo
            },
            success: function(data) {
                
                $(".container1").html(data);

            },
            error: function(xhr, status, error) {
                alert("에러 발생: " + error);
            }
        });
    }
    
    
    </script>
   
   
</head>
<body>

<div class="container1">
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form method="post" name="searchForm" id="searchForm" action="searchMember.do">
                <div class="card mb-3">
                    <div class="card-header">
                        회원 검색 및 필터링
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <!-- 검색 필드 -->
                            <div class="col-md-4">
                                <select class="form-control mb-2" name="searchField">
                                    <option value="">검색 필드 선택</option>
                                    <option value="memName">회원명</option>
                                    <option value="memAlias">닉네임</option>
                                    <option value="memMbti">MBTI</option>
                                    <option value="memAge">연령대</option>
                                    <option value="memGender">성별</option>
                                    <option value="memEmail">이메일</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control mb-2" 
                                       placeholder="검색어 입력" 
                                       name="searchText" 
                                       maxlength="100">
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary">검색</button>
                            </div>
                        </div>

                        <!-- 필터링 섹션 -->
                        <div class="row mt-3">
                            <div class="col-md-3">
                                <label>마일리지</label>
                                <div class="input-group">
                                    <select class="form-control" name="mileageCondition">
                                        <option value="">선택</option>
                                        <option value="over">이상</option>
                                        <option value="under">이하</option>
                                        <option value="equal">같음</option>
                                    </select>
                                    <input type="number" class="form-control" 
                                           placeholder="마일리지" 
                                           name="mileageValue">
                                </div>
                            </div>
                            
                            <div class="col-md-3">
                                <label>가입일자</label>
                                <input type="date" class="form-control" name="joinDateFrom">
                                <input type="date" class="form-control mt-2" name="joinDateTo">
                            </div>
                            
                            <div class="col-md-3">
                                <label>연령대</label>
                                <select class="form-control" name="ageGroup">
                                    <option value="">전체</option>
                                    <option value="10">10대</option>
                                    <option value="20">20대</option>
                                    <option value="30">30대</option>
                                    <option value="40">40대</option>
                                    <option value="50">50대</option>
                                    <option value="50">60대 이상</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container mt-5">
   <h2 class="mb-4">회원 목록</h2>
   <table class="table table-hover">
       <thead class="table-light">
           <tr>
               <th>회원번호</th>
               <th>아이디</th>
               <th>이름</th>
               <th>닉네임</th>
               <th>MBTI</th>
               <th>나이</th>
               <th>성별</th>
               <th>전화번호</th>
               <th>이메일</th>
               <th>회원 상세</th>
               <!-- 관리자일 경우에만 삭제 열 표시 -->
               <c:if test="${sessionScope.member.memAuth eq 0}">
				    <th>회원 삭제</th>
			   </c:if>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="member" items="${memberList}">
               <tr>
                   <td>${member.memNo}</td>
                   <td>${member.memId}</td>
                   <td>${member.memName}</td>
                   <td>${member.memAlias}</td>
                   <td>${member.memMbti}</td> 
                   <td>${member.memAge}</td>
                   <td>${member.memGender}</td>
                   <td>${member.memTel}</td>
                   <td>${member.memEmail}</td>
                   <td>
					    <button type="button" class="btn btn-primary btn-sm" onclick="adminMember(${member.memNo})">상세보기</button>
					</td>
                   <!-- 관리자일 경우에만 삭제 버튼 표시 -->
                   <c:if test="${sessionScope.member.memAuth eq 0}">
					    <td>
					        <button onclick="deleteMember(${member.memNo})" class="btn btn-danger btn-sm">삭제</button>
					    </td>
					</c:if>
               </tr>
           </c:forEach>
       </tbody>
   </table>
</div>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>