package kr.or.ddit.payment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;
@WebServlet("/payment.do")
public class PaymentController extends HttpServlet {
    private static final String ADMIN_KEY = "bafc6147eb3f778db072a8e601558d85";
    private static final String CID = "TC0ONETIME"; // 테스트용 가맹점 코드

    private IPaymentService paymentService = PaymentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/payment/payment.jsp")
               .forward(request, response);
    }
  

    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 배열로 변경
        String[] contentNos = request.getParameterValues("contentNo");
        String[] contentsTypeIds = request.getParameterValues("contentsTypeId");
        
        // 파라미터 null 체크 
        if (contentNos == null || contentsTypeIds == null) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("필수 파라미터(상품 정보)가 누락되었습니다.");
            out.flush();
            return;
        }
        
        String orderName = request.getParameter("orderName");
        String amount = request.getParameter("amount");
        
    
        
        HttpSession session = request.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");
        
        // 세션 체크도 추가
        if (member == null) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("로그인 세션이 만료되었습니다.");
            out.flush();
            return;
        }
        
        String buyerName = member.getMemName();
        
    
        
        // 디버깅을 위한 로그
        System.out.println("주문명: " + orderName);
        System.out.println("금액: " + amount);
        System.out.println("구매자: " + buyerName);

        try {
            // 결제 준비 API 호출 (카카오페이 연동 설정)
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            connection.setDoOutput(true);

            // 요청 파라미터 설정
            String parameters = String.format("cid=%s&partner_order_id=%s&partner_user_id=%s&item_name=%s&quantity=1&total_amount=%s&vat_amount=0&tax_free_amount=0&approval_url=http://localhost:8080%s/paymentComplete.do&cancel_url=http://localhost:8080%s/payment.do&fail_url=http://localhost:8080%s/payment.do",
                CID,
                "ORDER_" + System.currentTimeMillis(),
                buyerName,
                orderName,
                amount,
                request.getContextPath(),
                request.getContextPath(),
                request.getContextPath()
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] request_data = parameters.getBytes("UTF-8");
                os.write(request_data);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("응답 코드: " + responseCode);

            if (responseCode == 200) {
                // 응답 읽기
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                
                while ((line = br.readLine()) != null) {
                    responseBody.append(line);
                }
                br.close();
                
                // 결제 정보 저장
                for (int i = 0; i < contentNos.length; i++) {
                    if (contentNos[i] != null && !contentNos[i].isEmpty() && 
                        contentsTypeIds[i] != null && !contentsTypeIds[i].isEmpty()) {
                        
                        PaymentVo paymentVo = new PaymentVo();
                        paymentVo.setPayMethod("카카오페이");
                        paymentVo.setPayStatus("결제대기");
                        paymentVo.setMemNo(member.getMemNo());
                        paymentVo.setContentNo(contentNos[i]);
                        paymentVo.setContentsTypeId(Integer.parseInt(contentsTypeIds[i]));
                        paymentVo.setPayPrice(Integer.parseInt(amount));
                        
                        paymentService.paymentInsert(paymentVo);
                    }
                }
                
                // JSON 응답 파싱
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(responseBody.toString(), JsonObject.class);
                String redirectUrl = jsonResponse.get("next_redirect_pc_url").getAsString();
                
                // 카카오페이 결제 페이지로 리다이렉트
                response.sendRedirect(redirectUrl);
                
            } else {
                // 에러 응답 읽기
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getErrorStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    responseBody.append(line);
                }
                br.close();
                
                // 에러 내용 출력
                System.out.println("에러 응답: " + responseBody.toString());
                
                // 에러 처리
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("카카오페이 결제 준비 실패");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("결제 처리 중 오류 발생");
            out.flush();
        }
    }
}