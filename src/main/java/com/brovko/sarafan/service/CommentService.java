package com.brovko.sarafan.service;

import com.brovko.sarafan.domain.Comment;
import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	private final CommentRepo commentRepo;
	
	@Autowired
	public CommentService(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public Comment create(Comment comment, User user){
		comment.setAuthor(user);
		commentRepo.save(comment);
		
		return comment;
	}
}
