package com.web.member.module;

import java.util.Date;

public class LoginDTO {
		private int id;
		private String email;
		private String pwd;
		private String name;
		private String gender;
		private Date regDate;
		
		public LoginDTO(int id, String email, String pwd, String name, String gender, Date regDate) {
			this.id = id;
			this.email = email;
			this.pwd = pwd;
			this.name = name;
			this.gender = gender;
			this.regDate = regDate;
		}
		
		public int getId() {
			return id;
		}
		public String getEmail() {
			return email;
		}
		public String getPwd() {
			return pwd;
		}
		public String getName() {
			return name;
		}
		public String getGender() {
			return gender;
		}
		public Date getRegDate() {
			return regDate;
		}
		
}
