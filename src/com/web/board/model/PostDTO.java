package com.web.board.model;

import java.sql.Timestamp;

public class PostDTO {
	int id;
	String uid;
	String title;
	String writerName;
	String content;
	Timestamp regDate;
	int hit;
	String files; 
	
	public PostDTO(int id, String title, String uid, String writerName, String content, Timestamp regDate, int hit, String files) {
		this.id = id;
		this.title = title;
		this.uid = uid;
		this.writerName = writerName;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.files = files;
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
	public String getFiles() {
		return files;
	}
}
