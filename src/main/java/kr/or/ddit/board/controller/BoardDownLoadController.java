package kr.or.ddit.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.util.UploadUtil;
import kr.or.ddit.vo.AttachFileVo;

@WebServlet("/download")
public class BoardDownLoadController extends HttpServlet{
	
	IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileNoStr = req.getParameter("fileNo");
		int fileNo = Integer.parseInt(fileNoStr);
		
		AttachFileVo fileVo = boardService.fileDetail(fileNo);
		
		resp.setContentType("application/octet-stream"); //stream으로 내보내겠다.
		resp.setHeader("Content-Disposition", "attachment;filename=\"" + 
						URLEncoder.encode(fileVo.getFbOrgName(), "UTF-8")
						.replaceAll("\\+", "%20")+"\""); //%20: 공백
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UploadUtil.rootPath+fileVo.getFbSaveName()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data = 0;
		while( (data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
	}
}
