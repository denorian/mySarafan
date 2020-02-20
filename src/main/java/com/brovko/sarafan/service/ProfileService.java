package com.brovko.sarafan.service;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.UserSubscription;
import com.brovko.sarafan.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
	private final UserDetailsRepo userDetailsRepo;
	
	@Autowired
	public ProfileService(UserDetailsRepo userDetailsRepo) {
		this.userDetailsRepo = userDetailsRepo;
	}
	
	public User changeSubscription(User channel, User subscriber) {
		List<UserSubscription> subcriptions = channel.getSubscribers()
				.stream()
				.filter(subscription ->
						subscription.getSubscriber().equals(subscriber)
				)
				.collect(Collectors.toList());
		
		if (subcriptions.isEmpty()) {
			UserSubscription subscription = new UserSubscription(channel, subscriber);
			channel.getSubscribers().add(subscription);
		} else {
			channel.getSubscribers().removeAll(subcriptions);
		}
		
		return userDetailsRepo.save(channel);
	}
}
