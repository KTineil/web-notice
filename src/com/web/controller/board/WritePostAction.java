package com.web.controller.board;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

// servlet API
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 파일처리를 위한 API
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.Part;

import com.web.controller.board.PostDAO;
import com.web.dtomodel.PostDTO;

import javax.servlet.annotation.MultipartConfig;


//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@MultipartConfig (
//		location="/tmp", // 메모리 사이즈 오버시 사용될 디스크 환경에 따라 사용될 디렉토리가 다르기 때문에 기본값을 줌.
		fileSizeThreshold=1024 * 1024, // 메모리로 보내는 최대 사이즈
		maxFileSize=1024*1024*50, // 파일 하나의 최대 크기
		maxRequestSize=1024*1024*50*5 // 파일 전체의 최대 크기
)

@WebServlet("/writePostAction")
public class WritePostAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
//		/*
//		 * cos.jar을 이용한 file 업로드를 위한 로직
//		 * */
//		String dir = req.getServletContext().getRealPath("/upload/"); // 서버에 자장될 절대 위치 
//		int maxSize = 1024 * 1024 * 100; // 최대 파일 사이즈 100M
//		String encoding = "UTF-8"; // 파일 인코딩
//		MultipartRequest multipartrequest = new MultipartRequest(req, dir, maxSize, encoding, new DefaultFileRenamePolicy()); 
//		// 사용자가 전송한 req를 토대로 dir에다 maxsize 만큼만 utf-8로 엔코딩해서 업로드를 수행
//		
//		String fileName = multipartrequest.getOriginalFileName("file"); // req 내용을 토대로 jsp의 name 중 file을 찾아서 파일의 이름을 받음
//		String fileRealName = multipartrequest.getFilesystemName("file"); // 사용자의 파일시스템에에서의 파일이름을 받음
//		System.out.println(fileName);
//		System.out.println(fileRealName);
		
		
		/*
		 * jsp 자체 file 업로드 로직 
		 * */
		String dir = req.getServletContext().getRealPath("/upload/");
		
		Part filePart = req.getPart("file");
		InputStream fis = filePart.getInputStream();
		String fileName = filePart.getSubmittedFileName();
		String fileRealName = fileName;
		
		
		
		// 파일 이름 중복 가능성을 생각해서 변경 및 변경된 fileRealName 구해오기
		File file = new File(dir + File.separator + fileName);
		if (file.exists()) {
			String[] splited = fileName.split("\\."); // 이름과 확장자 나눔
			String splitedFileName = splited[0]; // 쌩 파일이름
			String extension = splited[1]; // 확장자 
			
			File fileDir = new File(dir);
			File[] files = fileDir.listFiles();
			
			String lastFileName = null; 
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().indexOf(splitedFileName) != -1)
					lastFileName = files[i].getName().split("\\.")[0]; // 확장자 짜르기
			}
			
			if (!lastFileName.equals(splitedFileName))
				fileRealName = splitedFileName + (Character.getNumericValue(lastFileName.charAt(lastFileName.length()-1))+1) + "." + extension;
			else
				fileRealName = splitedFileName + 1 + "." + extension;
		}
		file = null;
		
		
		// 파일 업로드
		file = new File(dir + File.separator + fileRealName);
		FileOutputStream fos = new FileOutputStream(file);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fis.read(buf)) != -1) {
			fos.write(buf, 0, size);
		}

		
		/*
		 * 게시글 내용을 업로드 하기 위한 데이터를 얻어옴
		 * */
		Cookie[] cookies = req.getCookies();
		String uid = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("uid")) {
				uid = cookie.getValue();
			}
		}
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		
		/*
		 * 데이터 엑세스 및 에러 여부에 따른 리디렉션
		 * */
		PostDAO postdao = new PostDAO();
		PostDTO postdto = new PostDTO(-1, uid, title, null, content.replace("\r\n", "<br>"), null, -1, fileName, fileRealName);
		int bid = postdao.write(postdto);
		if (bid == -1) {
			//오류
		}
		else {
			resp.sendRedirect("/post?bid="+bid);
		}
	}
}
