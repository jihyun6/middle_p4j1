package kr.or.ddit.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class GetListner implements Filter  {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        // 요청 메서드가 GET인 경우에만 실행
        if ("GET".equalsIgnoreCase(httpRequest.getMethod())) {
            System.out.println("GET 요청이 수신되었습니다.");

            String uri = "/P4J1_Project/main.do";
            HttpSession session = httpRequest.getSession();
            String requestUri = httpRequest.getRequestURI();

            if(requestUri.contains("js")) {
            	
            }
            // 특정 URI가 요청된 경우
            else if (uri.equals(requestUri)) {
                requestUri = (String) session.getAttribute("requestUri");

                if (requestUri != null) {
                    // 쿠키 전송 필요
                    Cookie cookie = new Cookie("requestUri", requestUri);
                    cookie.setMaxAge(60*3);  // 1초 동안 유효
                    cookie.setPath("/");   // 모든 경로에서 유효
                    httpResponse.addCookie(cookie);
                    System.out.println("쿠키가 전송되었습니다.");
                    System.out.println(requestUri);
                }
            } else {
                // 다른 URI인 경우 세션에 requestUri 저장
            	String queryString =  httpRequest.getQueryString();
            	System.out.println("requestUri : "+requestUri+"?"+queryString );
                session.setAttribute("requestUri", requestUri+"?"+queryString);
            }
        }
        chain.doFilter(httpRequest, httpResponse);
		
	}

}
