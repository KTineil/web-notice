package com.web.board.model;

import java.util.Date;

public class Board {
	int id;
	String title;
	String writerName;
	String content;
	Date regDate;
	int hit;
	
	public Board(int id, String title, String writerName, String content, Date regDate, int hit) {
		this.id = id;
		this.title = title;
		this.writerName = writerName;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
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
	public Date getRegDate() {
		return regDate;
	}
	public int getHit() {
		return hit;
	}
}
