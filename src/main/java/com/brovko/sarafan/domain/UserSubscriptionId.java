package com.brovko.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserSubscriptionId implements Serializable {
	@JsonView(View.Id.class)
	private String channelId;
	@JsonView(View.Id.class)
	private String subscriberId;
	
	public UserSubscriptionId(String channelId, String subscriberId) {
		this.channelId = channelId;
		this.subscriberId = subscriberId;
	}
	
	public UserSubscriptionId() {
	}
}
