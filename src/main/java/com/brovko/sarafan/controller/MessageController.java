package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.Message;
import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.MessagePageDto;
import com.brovko.sarafan.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("message")
public class MessageController {
	public static final int MESSAGES_PER_PAGE = 3;
	private final MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping
	@JsonView({View.FullMessage.class})
	public MessagePageDto list(
			@AuthenticationPrincipal User user,
			@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
	) {
		return messageService.findForUser(pageable, user);
	}
	
	@GetMapping("{id}")
	@JsonView({View.FullMessage.class})
	public Message getById(@PathVariable("id") Message message) {
		return message;
	}
	
	@PostMapping
	@JsonView({View.FullMessage.class})
	public Message create(@RequestBody Message message,
						  @AuthenticationPrincipal User user
	) throws IOException {
		return messageService.create(message, user);
	}
	
	@PutMapping("{id}")
	@JsonView({View.FullMessage.class})
	public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) throws IOException {
		return messageService.update(messageFromDb, message);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Message message) {
		messageService.delete(message);
	}
}
