package kr.or.ddit.login.controller;

import java.io.BufferedReader;

import jakarta.servlet.http.HttpServletRequest;

public class StreamData {

	
	public static String dataChange(HttpServletRequest request) {
		
		  // 문자열을 저장할 StringBuffer 객체를 생성
		StringBuffer strbuf = new StringBuffer();
		// 한 줄씩 읽어올 변수 선언
		String line = null;
		
		try {
			// request 객체에서 데이터를 읽기 위한 BufferedReader 객체 생성
			BufferedReader reader = request.getReader();
			
			 //request에서 데이터를 한줄 씩 읽어 들이는 반복문
			while (true) {
				// 한 줄을 읽어옴
				line = reader.readLine();
				 
				// 더 이상 읽을 줄이 없으면 반복문 종료
				if(line == null) break;
				
				//읽어온 줄을 stringBuffer에 추가
				strbuf.append(line);
			}
		} catch (Exception e) {
			//예외가 발생하면 스텍 트레이스를 출력
			e.printStackTrace();
		}
		
		//stringBuffer에 저장된 데이터를 문자열로 반환
		String reqdata = strbuf.toString();
		 // 콘솔에 읽어온 데이터를 출력 (디버깅 용)
		System.out.println(reqdata);
		
		// 최종적으로 읽어온 데이터를 반환
		return reqdata;
	}
}
