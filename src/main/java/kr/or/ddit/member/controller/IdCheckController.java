package kr.or.ddit.member.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/idcheck.do1")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberServiceImpl memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//userId 체크
		String userId = req.getParameter("id");
		System.out.println("userId : " + userId);
		
		String result = memberService.idCheck(userId);
		
		JSONObject jObj = new JSONObject();
		jObj.put("result", result); // key, value

		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		
		//		///P4J1_Project/idCheck.do?id=jadasff
//		//요청파라미터 : id=jadasff
//		String userId = req.getParameter("id");
//		//userId : jadasff
//		System.out.println("userId : " + userId);
//		
//		MemberServiceImpl service = MemberServiceImpl.getServiceImpl();
//		//service : null
//		System.out.println("service : " + service);
//		
//		//userId : asdf
//		String result = service.idCheck(userId);
//		//result : JAVA
//		System.out.println("result : " + result);
//
//		//forwarding : jsp리턴 / redirect : 새로운 URL 요청
////		req.setAttribute("result", result);
////		req.getRequestDispatcher("/webapp/WEB-INF/view/join/idcheck.jsp").forward(req, resp);
//		
//		//*** 데이터 리턴
//		JSONObject jObj = new JSONObject();
//		//달러('샵idspan').html(data.flag).css('color', 'red');
//		jObj.put("flag", result); // key, value
//
//		resp.setContentType("application/x-json; charset=utf-8");
//		resp.getWriter().print(jObj);
//		resp.setContentType("text/plain");
//		resp.setCharacterEncoding("UTF-8");
//
//        PrintWriter writer = resp.getWriter();
//
//        writer.println(result);
        // 서블릿 실행이 종료되면 클라이언트에 응답 전송 및 스트림 종료
	}
}

