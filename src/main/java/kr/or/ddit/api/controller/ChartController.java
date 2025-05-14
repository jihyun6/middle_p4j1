package kr.or.ddit.api.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.api.service.ChartServiceImpl;
import kr.or.ddit.vo.PaymentVo;
@WebServlet("/chart.do")
public class ChartController extends HttpServlet {
    // 서비스 인스턴스 생성
    private ChartServiceImpl chartService = ChartServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // PaymentVo 객체 생성 
        PaymentVo chartParam = new PaymentVo();
        
        // 차트 데이터 조회
        List<PaymentVo> chartData = chartService.chart(chartParam);
        
        //Gson 생성 DB에서 조호된 데이터를 JsonStr로 변환
        Gson gson = new Gson();
        
        //JSON배열 생성
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<chartData.size();i++) {
        	String jsonStr = gson.toJson(chartData.get(i));
        	JSONObject jsonObj = new JSONObject(jsonStr);
        	jsonArray.put(jsonObj);
        }
        
        System.out.println("json:" + jsonArray);
        
        req.setAttribute("chartData", jsonArray);
        req.getRequestDispatcher("/WEB-INF/view/chart/chart.jsp").forward(req, resp);
        
        // 요청 속성에 데이터 설정
//        req.setAttribute("chartData", json);
        
//        System.out.println(chartData);

        // JSP로 포워딩
        
//        if (chartData == null || chartData.isEmpty()) {
//            System.out.println("chartData is empty!");
//        } else {
//            System.out.println(chartData);
//        }
//     
//        List<Integer> list = new ArrayList<Integer>();
//        
//        for (PaymentVo item : chartData) {
//        	list.add(item.getVisitorCount());
//			
//		}
       
    }
}