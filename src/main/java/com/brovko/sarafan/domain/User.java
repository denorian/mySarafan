package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usr")
public class User implements Serializable {
	@Id
	@JsonView({View.IdName.class})
	private String id;
	@JsonView({View.IdName.class})
	private String name;
	@JsonView({View.IdName.class})
	private String userPic;
	private String email;
	private String gender;
	private String locale;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastVisit;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserPic() {
		return userPic;
	}
	
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public LocalDateTime getLastVisit() {
		return lastVisit;
	}
	
	public void setLastVisit(LocalDateTime lastVisit) {
		this.lastVisit = lastVisit;
	}
}
