<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오류 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .error-container {
            max-width: 600px;
            margin: 100px auto;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-container">
            <div class="card border-danger">
                <div class="card-header bg-danger text-white">
                    <h3>오류 발생</h3>
                </div>
                <div class="card-body">
                    <h4 class="card-title text-danger">요청 중 문제가 발생했습니다.</h4>
                    
                    <%-- 오류 메시지 표시 --%>
                    <c:if test="${not empty errorMsg}">
                        <p class="card-text text-muted">${errorMsg}</p>
                    </c:if>
                    
                    <%-- 시스템 오류인 경우 --%>
                    <c:if test="${empty errorMsg}">
                        <p class="card-text text-muted">
                            시스템 오류가 발생했습니다. 
                            잠시 후 다시 시도해주세요.
                        </p>
                    </c:if>
                    
                    <div class="mt-4">
                        <a href="${pageContext.request.contextPath}/main.do" class="btn btn-primary me-2">
                            메인 페이지로
                        </a>
                        <a href="javascript:history.back()" class="btn btn-secondary">
                            이전 페이지로
                        </a>
                    </div>
                </div>
                
                <%-- 개발 중 디버깅용 상세 오류 정보 --%>
                <% 
                if (exception != null) { 
                    exception.printStackTrace(new java.io.PrintWriter(out));
                } 
                %>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>