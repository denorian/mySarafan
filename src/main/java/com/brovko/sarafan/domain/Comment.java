package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Comment {
	@Id
	@GeneratedValue
	@JsonView({View.IdName.class})
	private Long id;
	
	@JsonView({View.IdName.class})
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	@JsonView({View.FullComment.class})
	private Message message;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	@JsonView({View.IdName.class})
	private User author;
	
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
	
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return id.equals(comment.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
