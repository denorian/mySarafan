package com.brovko.sarafan.repo;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.UserSubscription;
import com.brovko.sarafan.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
	List<UserSubscription> findBySubscriber(User user);
}
