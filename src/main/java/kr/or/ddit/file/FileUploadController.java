package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/fileUpload.do")
@MultipartConfig
public class FileUploadController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Upload 실행");
		
		String sender =  req.getParameter("sender");
		System.out.println("sender : "+sender);
		
		String uploadPath = req.getServletContext().getRealPath("/image/"); // 동적 경로 설정
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs(); // 디렉토리 생성
	    }
		for(Part part :  req.getParts()) {
			System.out.println(part.getHeader("content-disposition"));
			
			String fileName = getFileName(part);
			System.out.println(fileName);
			if(fileName != null) {
				part.write("D:/uploadFolder/"+fileName);
			}
		}
	}
	
	private String getFileName(Part part) {
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