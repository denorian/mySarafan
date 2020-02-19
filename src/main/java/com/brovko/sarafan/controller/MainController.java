package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.MessagePageDto;
import com.brovko.sarafan.repo.UserDetailsRepo;
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
	private final UserDetailsRepo userDetailsRepo;
	private final ObjectWriter messagewriter;
	private final ObjectWriter profileWriter;
	
	@Autowired
	public MainController(MessageService messageService, UserDetailsRepo userDetailsRepo, ObjectMapper mapper) {
		this.messageService = messageService;
		this.userDetailsRepo = userDetailsRepo;
		this.messagewriter = mapper
				.setConfig(mapper.getSerializationConfig())
				.writerWithView(View.FullMessage.class);
		this.profileWriter = mapper
				.setConfig(mapper.getSerializationConfig())
				.writerWithView(View.FullProfile.class);
	}
	
	@GetMapping
	public String main(
			Model model,
			@AuthenticationPrincipal User user
	) throws JsonProcessingException {
		HashMap<Object, Object> data = new HashMap<>();
		
		if (user != null) {
			User userFromDB = userDetailsRepo.findById(user.getId()).get();
			String seriallizedProfile = profileWriter.writeValueAsString(userFromDB);
			model.addAttribute("profile", seriallizedProfile);
			
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
			MessagePageDto messagePageDto = messageService.findAll(pageRequest);
			model.addAttribute("messages", messagewriter.writeValueAsString(messagePageDto.getMessages()));
			data.put("currentPage", messagePageDto.getCurrentPage());
			data.put("totalPages", messagePageDto.getTotalPage());
		}else {
			model.addAttribute("messages", "[]");
			model.addAttribute("profile", "null");
		}
		
		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode", "dev".equals(profile));
		return "index";
	}
}
