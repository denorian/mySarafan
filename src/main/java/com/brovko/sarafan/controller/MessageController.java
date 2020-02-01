package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.Message;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
	private final MessageRepo messageRepo;
	
	@Autowired
	public MessageController(MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}
	
	@GetMapping
	public List<Message> list() {
		return messageRepo.findAll();
	}
	
	@GetMapping("{id}")
	@JsonView({View.FullMessage.class})
	public Message getById(@PathVariable("id") Message message) {
		return message;
	}
	
	@PostMapping
	public Message create(@RequestBody Message message) {
		message.setCreationDate(LocalDateTime.now());
		return messageRepo.save(message);
	}
	
	@PutMapping("{id}")
	public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
		BeanUtils.copyProperties(message,messageFromDB, "id");
		return messageRepo.save(messageFromDB);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Message message) {
		messageRepo.delete(message);
	}
}
