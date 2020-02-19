package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {
	@Id
	@JsonView({View.IdName.class})
	private String id;
	@JsonView({View.IdName.class})
	private String name;
	@JsonView({View.IdName.class})
	private String userPic;
	private String email;
	@JsonView({View.FullProfile.class})
	private String gender;
	@JsonView({View.FullProfile.class})
	private String locale;
	
	@JsonView({View.FullProfile.class})
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastVisit;
	
	@ManyToMany
	@JoinTable(
			name = "user_subscriptions",
			joinColumns = @JoinColumn(name = "subscriber_id"),
			inverseJoinColumns = @JoinColumn(name = "channel_id")
	)
	@JsonView({View.FullProfile.class})
	@JsonIdentityReference
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class
	)
	private Set<User> subscriptions = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "user_subscriptions",
			joinColumns = @JoinColumn(name = "channel_id"),
			inverseJoinColumns = @JoinColumn(name = "subscriber_id")
	)
	@JsonView({View.FullProfile.class})
	@JsonIdentityReference
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class
	)
	private Set<User> subscribers = new HashSet<>();
	
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
	
	public Set<User> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(Set<User> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public Set<User> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}
}
