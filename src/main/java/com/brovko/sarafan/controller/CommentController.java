package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.Comment;
import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.service.CommentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping
	@JsonView(View.FullMessage.class)
	public Comment create(
			@RequestBody Comment comment,
			@AuthenticationPrincipal User user
	) {
		
		
		return commentService.create(comment, user);
	}
}
