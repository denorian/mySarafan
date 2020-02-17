package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(
		property = "id",
		generator = ObjectIdGenerators.PropertyGenerator.class
)
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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonView({View.FullMessage.class})
	private User author;
	
	@OneToMany(mappedBy = "message", orphanRemoval = true)
	@JsonView({View.FullMessage.class})
	private List<Comment> comments;
	
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
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
