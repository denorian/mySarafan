package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView({View.Id.class})
	private Long id;
	
	@JsonView({View.IdName.class})
	private String text;
	
	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView({View.FullMessage.class})
	private LocalDateTime creationDate;
	
	@JsonView({View.FullMessage.class})
	private String link;
	@JsonView({View.FullMessage.class})
	private String linkTitle;
	@JsonView({View.FullMessage.class})
	private String linkDescription;
	@JsonView({View.FullMessage.class})
	private String linkCover;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLinkTitle() {
		return linkTitle;
	}
	
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}
	
	public String getLinkDescription() {
		return linkDescription;
	}
	
	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}
	
	public String getLinkCover() {
		return linkCover;
	}
	
	public void setLinkCover(String linkCover) {
		this.linkCover = linkCover;
	}
}
