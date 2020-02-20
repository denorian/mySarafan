package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
@ToString(of = {"id", "name"})
public class User{
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
	
	@JsonView({View.FullProfile.class})
	@OneToMany(
			mappedBy = "subscriber",
			orphanRemoval = true
	)
	private Set<UserSubscription> subscriptions = new HashSet<>();
	
	@JsonView({View.FullProfile.class})
	@OneToMany(
			mappedBy = "channel",
			orphanRemoval = true,
			cascade = CascadeType.ALL
	)
	private Set<UserSubscription> subscribers = new HashSet<>();
	
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
	
	public Set<UserSubscription> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(Set<UserSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public Set<UserSubscription> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(Set<UserSubscription> subscribers) {
		this.subscribers = subscribers;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
