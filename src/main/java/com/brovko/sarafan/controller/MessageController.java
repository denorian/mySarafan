package com.brovko.sarafan.controller;

import com.brovko.sarafan.domain.Message;
import com.brovko.sarafan.domain.User;
import com.brovko.sarafan.domain.View;
import com.brovko.sarafan.dto.EventType;
import com.brovko.sarafan.dto.MetaDto;
import com.brovko.sarafan.dto.ObjectType;
import com.brovko.sarafan.repo.MessageRepo;
import com.brovko.sarafan.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("message")
public class MessageController {
	private static String URL_PATTERN = "https?:\\/\\/?[\\w\\d\\._\\-%\\/\\?=&#]+";
	private static String IMAGE_PATTERN = "\\.(jpeg|jpg|gif|png)$";
	
	private static Pattern URL_REGEX = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
	private static Pattern IMAGE_REGEX = Pattern.compile(IMAGE_PATTERN, Pattern.CASE_INSENSITIVE);
	
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
	public Message create(@RequestBody Message message ,
						  @AuthenticationPrincipal User user
	) throws IOException {
		message.setCreationDate(LocalDateTime.now());
		fillMeta(message);
		message.setAuthor(user);
		Message updatedMessage = messageRepo.save(message);
		wsSender.accept(EventType.CREATE, updatedMessage);
		return updatedMessage;
	}
	
	@PutMapping("{id}")
	public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) throws IOException {
		fillMeta(message);
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
	
	private void fillMeta(Message message) throws IOException {
		String text = message.getText();
		Matcher matcher = URL_REGEX.matcher(text);
		
		if (matcher.find()) {
			String url = text.substring(matcher.start(), matcher.end());
			
			matcher = IMAGE_REGEX.matcher(url);
			
			message.setLink(url);
			
			if (matcher.find()) {
				message.setLinkCover(url);
			} else if (!url.contains("youtu")) {
				MetaDto meta = getMeta(url);
				
				message.setLinkCover(meta.getCover());
				message.setLinkTitle(meta.getTitle());
				message.setLinkDescription(meta.getDescription());
			}
		}
	}
	
	private MetaDto getMeta(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements title = doc.select("meta[name$=title],meta[property=title]");
		Elements description = doc.select("meta[name$=description],meta[property=description]");
		Elements cover = doc.select("meta[name$=image],meta[property=image]");
		
		return new MetaDto(
				getContent(title.first()),
				getContent(description.first()),
				getContent(cover.first())
		);
	}
	
	private String getContent(Element element) {
		return element == null ? "" : element.attr("content");
	}
}
