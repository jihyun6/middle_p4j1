package kr.or.ddit.login.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.login.service.MemberService;
import kr.or.ddit.login.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;
 


@WebServlet("/InsertMember.do")
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InsertMember() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {


		String data = StreamData.dataChange(request);
		
		
		System.out.println("data"+data);
		Gson gson = new Gson();
		MemberVo vo = gson.fromJson(data, MemberVo.class);
		
		MemberService service = MemberServiceImpl.getServiceImpl();
		
		int res = service.Memberjoin(vo);
		System.out.println(res+"res");
		request.setAttribute("result", res);
		
		request.getRequestDispatcher("/WEB-INF/view/join/insert.jsp");
	}
}
