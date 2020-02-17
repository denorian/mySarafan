package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.MessagePageDto;
import com.brovko.sarafan.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	private final MessageService messageService;
	private final ObjectWriter writer;
	
	@Autowired
	public MainController(MessageService messageService, ObjectMapper mapper) {
		this.messageService = messageService;
		this.writer = mapper
				.setConfig(mapper.getSerializationConfig())
				.writerWithView(View.FullMessage.class);
	}
	
	@GetMapping
	public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
		HashMap<Object, Object> data = new HashMap<>();
		
		if (user != null) {
			data.put("profile", user);
			
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
			MessagePageDto messagePageDto = messageService.findAll(pageRequest);
			model.addAttribute("messages", writer.writeValueAsString(messagePageDto.getMessages()));
			data.put("currentPage", messagePageDto.getCurrentPage());
			data.put("totalPages", messagePageDto.getTotalPage());
		}
		
		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode", "dev".equals(profile));
		return "index";
	}
}
