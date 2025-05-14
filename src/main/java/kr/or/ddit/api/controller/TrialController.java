package kr.or.ddit.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.or.ddit.api.service.ITrialService;
import kr.or.ddit.api.service.TrialServiceImpl;

public class TrialController {
	
	String apikey = "ROahlg%2B4102sG61WNub6c%2F9rfTHbrGgUGKy4CqI8sQQ%2FcAANfxpQF1t7AS%2Fh5ygF2%2FEcNxDbKnJkm4ObQn2gng%3D%3D";
	ITrialService trialService = TrialServiceImpl.getInstance();
	
	public static void main(String[] args) {
		TrialController trial = new TrialController();
		trial.trialList();
	}
	
	public void trialList() {
		//DB에서 trial 데이터 조회
		List<Map<String, Object>> trialList = null;
		trialList = trialService.trialList();
		System.out.println(trialList);
		
		if(trialList.isEmpty()) {
			trialCodeInsert();
		}
	}
	
	public void trialCodeInsert() {
		// StringBuilder: 문자열을 연결할 때 사용하는 String 클래스
		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/areaCode1"); // 지역코드조회

		try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apikey);
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); // 한페이지결과수
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("WIN", "UTF-8")); // OS
																														// 구분
			urlBuilder.append(
					"&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TestApp", "UTF-8")); // 서비스명(어플명)
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "="
					+ URLEncoder.encode("json", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */

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
			System.out.println(jsonStr);

			JSONObject json = new JSONObject(jsonStr);
			JSONObject response = json.getJSONObject("response");
			JSONObject body = response.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray item = items.getJSONArray("item");

			try {
				for (int i = 0; i < item.length(); i++) {
					JSONObject obj = item.getJSONObject(i);

					Set<String> keySet = obj.keySet();
					Iterator<String> it = keySet.iterator();

					Map<String, Object> param = new HashMap<String, Object>();
					while (it.hasNext()) {
						String key = it.next();
						param.put(key, obj.get(key));
					}
					
					trialService.trialInsert(param);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
