package kr.or.ddit.util;

import jakarta.servlet.http.Part;

public class UploadUtil {
	
	//파일 경로 설정
	public static String rootPath = "D:/uploadFolder/";
	//D:/A_TeachingMaterial/04_Middle/workspace/P4J1_Project/src/main/webapp/image/
	public static String getFileName(Part part) {
		
		/*
		 *  Content-Disposition 헤더에 대하여 
		 *   
		 *  파일 업로드에서 사용되는 경우 
		 *  
		 *  Content-Disposition : form-data 
		 *  Content-Disposition : form-data; name="필드명" 
		 *  Content-Disposition : form-data; name="필드명"; filename="파일명" 
		 *  
		 */
		
		for(String content : part.getHeader("Content-Disposition").split(";") ) {
			//  Content-Disposition : form-data; name="필드명"; filename="파일명"
			// 를 ; 으로 분리 
			
			// 시작이 filename
			if(content.trim().startsWith("filename")) {
				// filename="파일명" 
				// = 위치를 찾아서 하나 뒤에 위치를 호출
				return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
			}
		}
		// 파일 이름이 없는 경우
		return null;
	}
}
