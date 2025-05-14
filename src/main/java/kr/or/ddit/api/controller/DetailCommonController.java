package kr.or.ddit.api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.or.ddit.api.service.DetailDommonServiceImpl;
import kr.or.ddit.api.service.IDetailCommonService;

public class DetailCommonController {

	String apikey = "ROahlg%2B4102sG61WNub6c%2F9rfTHbrGgUGKy4CqI8sQQ%2FcAANfxpQF1t7AS%2Fh5ygF2%2FEcNxDbKnJkm4ObQn2gng%3D%3D";
	IDetailCommonService detailCommonService = DetailDommonServiceImpl.getInstance();
	
	public static void main(String[] args) {
		DetailCommonController detailCommon = new DetailCommonController();
		detailCommon.detailCommonList();
	}
	
	public void detailCommonList() {
		List<Map<String, Object>> detailCommonList = detailCommonService.detailCommonList();
		
		for(int i=0;i<detailCommonList.size();i++) {
			Map<String, Object> param = detailCommonList.get(i);
			detailCommonList1(param);
		}
	}
	
	public void detailCommonList1(Map<String, Object> param) {
		
		System.out.println("detailCommonList1 실행");
		
		String contentTypeId = String.valueOf(param.get("CONTENTS_TYPE_ID"));
		String contentId = String.valueOf(param.get("CONTENT_NO"));
		
//		String contentTypeId = "14";
//		String contentId = "130446";
		
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailIntro1"); /* URL */
			
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apikey);
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); // 한페이지결과수
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지번호
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("WIN", "UTF-8")); // OS
																														// 구분
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TestApp", "UTF-8")); // 서비스명(어플명)
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="+ URLEncoder.encode("json", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="+ URLEncoder.encode(contentTypeId, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="+ URLEncoder.encode(contentId , "UTF-8"));
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // session 연결

			conn.setRequestMethod("GET"); // 메소드 방식 설정
			conn.setRequestProperty("Content-type", "application/json"); // 컨텍스트 타입 설정

			BufferedReader rd;

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			
			String jsonStr = sb.toString();

			JSONObject json = new JSONObject(jsonStr);

			JSONObject response = json.getJSONObject("response");
			JSONObject body = response.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray item = items.getJSONArray("item");
			
			System.out.println(json);
			
			for (int i = 0; i < item.length(); i++) {
				JSONObject obj = item.getJSONObject(i);

				Set<String> keySet = obj.keySet();
				Iterator<String> it = keySet.iterator();

				param = new HashMap<String, Object>();
				while (it.hasNext()) {
					String key = it.next();
					param.put(key, obj.get(key));
				}
			}
			
			if(contentTypeId.equals("12")) detailCommonService.detailTourismInsert(param); //관광지 ok
			if(contentTypeId.equals("14")) detailCommonService.detailExhibitionInsert(param); // 전시 ok
			if(contentTypeId.equals("15")) detailCommonService.detailEventInsert(param); //행사공연축제 ok
			if(contentTypeId.equals("28")) detailCommonService.detailLeportsInsert(param); //레포츠 ok
			if(contentTypeId.equals("32")) detailCommonService.detailStayInsert(param); //숙박 ok
			if(contentTypeId.equals("39")) detailCommonService.detailRestaurantInsert(param); //음식점 ok
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
