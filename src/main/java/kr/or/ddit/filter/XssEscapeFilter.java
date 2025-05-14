package kr.or.ddit.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class XssEscapeFilter implements Filter {
    private Set<String> badWords = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    	System.out.println("XssEscapeFilter!");
    	
    	badWords.addAll(Arrays.asList(
            "바보", "멍청이", "죽을래"
        ));
        
        String configPath = filterConfig.getServletContext().getRealPath("/WEB-INF/badwords.txt");
        loadAdditionalBadWords(configPath);
    }

    
    private void loadAdditionalBadWords(String configPath) {
        try {
        	// configPath(파일 경로)를 Path 객체로 변환
            Path path = Paths.get(configPath);
         // 파일이 실제로 존재하는지 확인
            if (Files.exists(path)) {
            	// 파일의 모든 줄을 읽어서 리스트로 저장
                // StandardCharsets.UTF_8로 한글 깨짐 방지
                List<String> additionalWords = Files.readAllLines(path, StandardCharsets.UTF_8);
             // 읽어온 비속어 목록을 콘솔에 출력 (디버깅용)
//                System.out.println("Loading additional bad words: " + additionalWords);
             // 읽어온 비속어들을 기존 badWords 셋에 추가
                badWords.addAll(additionalWords);
            } else {
            	// 파일이 없을 경우 콘솔에 메시지 출력
                System.out.println("Bad words file not found at: " + configPath);
            }
        } catch (IOException e) {
        	// 파일 읽기 중 오류 발생시 처리
            System.out.println("Error loading bad words file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
    	System.out.println("XssEscapeFilter doFilter 실행!");
    	
    	XssRequestWrapper wrappedRequest = new XssRequestWrapper((HttpServletRequest) request, badWords);
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {
        badWords.clear();
    }
}