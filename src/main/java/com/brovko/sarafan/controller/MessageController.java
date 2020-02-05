package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.Message;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.EventType;
import com.brovko.sarafan.dto.ObjectType;
import com.brovko.sarafan.repo.MessageRepo;
import com.brovko.sarafan.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {
	private final MessageRepo messageRepo;
	private final BiConsumer<EventType, Message> wsSender;
	
	@Autowired
	public MessageController(MessageRepo messageRepo, WsSender wsSender) {
		this.messageRepo = messageRepo;
		this.wsSender = wsSender.getSender(ObjectType.MESSAGE, View.IdName.class);
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
		Message updatedMessage = messageRepo.save(message);
		wsSender.accept(EventType.CREATE, updatedMessage);
		return updatedMessage;
	}
	
	@PutMapping("{id}")
	public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
		BeanUtils.copyProperties(message, messageFromDb, "id");
		Message updatedMessage = messageRepo.save(messageFromDb);
		wsSender.accept(EventType.UPDATE, updatedMessage);
		
		return updatedMessage;
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Message message) {
		wsSender.accept(EventType.REMOVE, message);
		messageRepo.delete(message);
	}
	/*
	@MessageMapping("/changeMessage")
	@SendTo("/topic/activity")
	public Message change(Message message){
		return messageRepo.save(message);
	}*/
}
