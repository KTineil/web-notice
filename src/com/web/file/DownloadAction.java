/*
 * 이제 필요없어진 객체지만 참고용으로 남겨둠.
 * */

package com.web.file;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadAction")
public class DownloadAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = java.net.URLDecoder.decode(req.getParameter("filename"), "UTF-8");
		
		String dir = req.getServletContext().getRealPath("/upload/");
		File file = new File(dir + fileName);
		
		
		/*
		 * 헤더 처리
		 * */
//		String mimeType = getServletContext().getMimeType(file.toString());
//		if (mimeType == null) {
//			resp.setContentType("application/octet-stream");
//		}
		resp.setContentType("application/octet-stream");
		
		String downloadName = null;
		// explore를 고려하여 서 인코딩
		if (req.getHeader("user-agent").indexOf("MSIE") == -1) {
			downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
		} else {
			downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		}
		
		resp.setHeader("Content-Disposition", "attachment:fileName=\"" + downloadName + "\";" );
		
		
		/*
		 * 파일 다운로드 로직
		 * */
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		
		byte b[] = new byte[1024];
		int data = 0;
		
		while ((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			servletOutputStream.write(b, 0, data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
}
