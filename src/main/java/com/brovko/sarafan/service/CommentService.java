package com.brovko.sarafan.service;

import com.brovko.sarafan.domain.Comment;
import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.EventType;
import com.brovko.sarafan.dto.ObjectType;
import com.brovko.sarafan.repo.CommentRepo;
import com.brovko.sarafan.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
	private final CommentRepo commentRepo;
	private final BiConsumer<EventType, Comment> wsSender;
	
	@Autowired
	public CommentService(CommentRepo commentRepo, WsSender wsSender) {
		this.commentRepo = commentRepo;
		this.wsSender = wsSender.getSender(ObjectType.COMMENT, View.FullComment.class);
	}
	
	public Comment create(Comment comment, User user) {
		comment.setAuthor(user);
		Comment commentFromDB = commentRepo.save(comment);
		wsSender.accept(EventType.CREATE, commentFromDB);
		
		return commentFromDB;
	}
}
