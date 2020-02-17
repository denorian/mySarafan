package com.brovko.sarafan.repo;

import com.brovko.sarafan.domain.Message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {
	@EntityGraph(attributePaths = { "comments" })
	Page<Message> findAll(Pageable pageable);
}
