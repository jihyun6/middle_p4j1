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

import kr.or.ddit.api.service.ContentsServiceImpl;
import kr.or.ddit.api.service.IContentsService;

public class ContentsController {
	
	String apikey = "ROahlg%2B4102sG61WNub6c%2F9rfTHbrGgUGKy4CqI8sQQ%2FcAANfxpQF1t7AS%2Fh5ygF2%2FEcNxDbKnJkm4ObQn2gng%3D%3D";
	IContentsService contentService = ContentsServiceImpl.getInstance();
	
	//Test2
	public static void main(String[] args) {
		ContentsController contents = new ContentsController();
		contents.contentList();
	}
	
	private void contentList() {
		List<Map<String, Object>> contentsList = contentService.contentsList();
		String contentsTypeId = "";
		
		for(int i=0;i<contentsList.size();i++) {
			Map<String, Object> param = contentsList.get(i);
			contentsTypeId = String.valueOf(param.get("CONTENTS_TYPE_ID"));
			
			System.out.println(contentsTypeId);
			
			contentDetailInsert(contentsTypeId);
		}
	}
	
	private void contentDetailInsert(String contentsTypeId) {
		// StringBuilder: 문자열을 연결할 때 사용하는 String 클래스
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1"); /* URL */

		try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apikey);
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); // 한페이지결과수
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("350", "UTF-8")); // 페이지번호
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("WIN", "UTF-8")); // OS
																														// 구분
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TestApp", "UTF-8")); // 서비스명(어플명)
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contentsTypeId, "UTF-8"));

			// urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); //목록구분
			urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("O", "UTF-8")); //정렬구분 A:제목순 C:수정일순 D 생성일순 (이미지 있는 거부터 )O: 제목순, Q:수정일순, R: 생성일순
//			urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //지역코드
//			urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //시군구코드
//			urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode("A01", "UTF-8")); // 대분류
//			urlBuilder.append("&" + URLEncoder.encode("cat2", "UTF-8") + "=" + URLEncoder.encode("A0102", "UTF-8")); // 중분류
//			urlBuilder.append("&" + URLEncoder.encode("cat3","UTF-8") + "=" + URLEncoder.encode("A01010100", "UTF-8")); //소뷴류
			
//			System.out.println(urlBuilder.toString());

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
					contentList(param);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void contentList(Map<String, Object> param) {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailCommon1"); /* URL */
		
		String contenttypeid = (String)param.get("contenttypeid");
		String contentid = (String)param.get("contentid");
		
		try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apikey);
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 한페이지결과수
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지번호
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("WIN", "UTF-8")); // OS
																														// 구분
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TestApp", "UTF-8")); // 서비스명(어플명)
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contenttypeid, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(contentid, "UTF-8")); 

			urlBuilder.append("&" + URLEncoder.encode("defaultYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); //정렬구분 A:제목순 C:수정일순 D 생성일순 (이미지 있는 거부터 )O: 제목순, Q:수정일순, R: 생성일순
			urlBuilder.append("&" + URLEncoder.encode("firstImageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("areacodeYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("overviewYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); 
			
			// urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" +
			// URLEncoder.encode("Y", "UTF-8")); //목록구분
//			urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //지역코드
//			urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); //시군구코드
//			urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode("A01", "UTF-8")); // 대분류
//			urlBuilder.append("&" + URLEncoder.encode("cat2", "UTF-8") + "=" + URLEncoder.encode("A0102", "UTF-8")); // 중분류
//			urlBuilder.append("&" + URLEncoder.encode("cat3","UTF-8") + "=" + URLEncoder.encode("A01010100", "UTF-8")); //소뷴류
			
//			System.out.println(urlBuilder.toString());

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

//			System.out.println(json);
			
			JSONObject response = json.getJSONObject("response");
			JSONObject body = response.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray item = items.getJSONArray("item");
			
			try {
				for (int i = 0; i < item.length(); i++) {
					JSONObject obj = item.getJSONObject(i);

					Set<String> keySet = obj.keySet();
					Iterator<String> it = keySet.iterator();

					param = new HashMap<String, Object>();
					while (it.hasNext()) {
						String key = it.next();
						param.put(key, obj.get(key));
					}
					
					System.out.println(param);
					param.put("contenttypeid",contenttypeid);
					contentService.contentsDefaultInput(param);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
