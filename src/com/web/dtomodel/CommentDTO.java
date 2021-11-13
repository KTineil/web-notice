package com.web.dtomodel;

import java.sql.Timestamp;

public class CommentDTO {
	int board_id;
	String content;
	String email;
	String name;
	Timestamp date;
	
	public CommentDTO(int board_id, String content, String email, String name, Timestamp date) {
		this.board_id = board_id;
		this.content = content;
		this.email = email;
		this.name = name;
		this.date = date;
	}

	public int getBoard_id() {
		return board_id;
	}

	public String getContent() {
		return content;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	public Timestamp getDate() {
		return date;
	}
}