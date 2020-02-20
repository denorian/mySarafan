package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserSubscription implements Serializable {
	@EmbeddedId
	@JsonIgnore
	private UserSubscriptionId id;
	
	@MapsId("channelId")
	@ManyToOne
	@JsonView(View.IdName.class)
	@JsonIdentityReference
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class
	)
	private User channel;
	
	@MapsId("subscriberId")
	@ManyToOne
	@JsonView(View.IdName.class)
	@JsonIdentityReference
	@JsonIdentityInfo(
			property = "id",
			generator = ObjectIdGenerators.PropertyGenerator.class
	)
	private User subscriber;
	
	@JsonView(View.IdName.class)
	private boolean active;
	
	public UserSubscription(User channel, User subscriber) {
		this.channel = channel;
		this.subscriber = subscriber;
		this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
	}
	
	public UserSubscription() {
	}
	
	public UserSubscriptionId getId() {
		return id;
	}
	
	public void setId(UserSubscriptionId id) {
		this.id = id;
	}
	
	public User getChannel() {
		return channel;
	}
	
	public void setChannel(User channel) {
		this.channel = channel;
	}
	
	public User getSubscriber() {
		return subscriber;
	}
	
	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
