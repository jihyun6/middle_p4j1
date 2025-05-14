package kr.or.ddit.filter;

public class LoginFilter {
	
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("loginFilter! 실행");
//	}
//	
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException {
//		
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		HttpSession session = request.getSession();
//		
//		MemberVo member = (MemberVo) session.getAttribute("member");
//		
//		if(member == null) {
//			String cPath=request.getContextPath();
//			//리다일렉트 시킬때 원래 목적지 정보를 url 라는 파라미터 명으로 같이 보낸다.
//			response.sendRedirect(cPath+"/login.jsp");
//		}
//		
//		else {
//			chain.doFilter(req, resp);
//		}
//	}
}
