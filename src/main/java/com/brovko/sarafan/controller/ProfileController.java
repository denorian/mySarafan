package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {
	private final ProfileService profileService;
	
	@Autowired
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@GetMapping("{id}")
	@JsonView(View.FullProfile.class)
	public User get(@PathVariable("id") User user) {
		return user;
	}
	
	@PostMapping("change-subscription/{channelId}")
	@JsonView(View.FullProfile.class)
	public User changeSubscriprion(
			@AuthenticationPrincipal User subscriber,
			@PathVariable("channelId") User channel
	) {
		if (subscriber.equals(channel)) {
			return channel;
		} else {
			return profileService.changeSubscription(subscriber, channel);
		}
	}
}
