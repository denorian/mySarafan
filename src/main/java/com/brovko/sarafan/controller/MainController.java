package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.repo.MessageRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	private final MessageRepo messageRepo;
	private final ObjectWriter writer;
	
	@Autowired
	public MainController(MessageRepo messageRepo, ObjectMapper mapper) {
		this.messageRepo = messageRepo;
		this.writer = mapper
				.setConfig(mapper.getSerializationConfig())
				.writerWithView(View.FullMessage.class);
	}
	
	@GetMapping
	public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
		HashMap<Object, Object> data = new HashMap<>();
		
		if(user != null){
			data.put("profile", user);
			model.addAttribute("messages", writer.writeValueAsString(messageRepo.findAll()));
		}
		
		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode", "dev".equals(profile));
		return "index";
	}
}
