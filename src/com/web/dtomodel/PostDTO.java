package com.web.dtomodel;

import java.sql.Timestamp;

public class PostDTO {
	int id;
	String uid;
	String title;
	String writerName;
	String content;
	Timestamp regDate;
	int hit;
	String fileName;
	String fileRealName;
	int CommentCnt;
	
	public int getCommentCnt() {
		return CommentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		CommentCnt = commentCnt;
	}

	public PostDTO(int id, String uid, String title, String writerName, String content, Timestamp regDate, int hit,
			String fileName, String fileRealName) {
		this.id = id;
		this.uid = uid;
		this.title = title;
		this.writerName = writerName;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
	}
	
	public String getUid() {
		return uid;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getWriterName() {
		return writerName;
	}
	public String getContent() {
		return content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public int getHit() {
		return hit;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
}
