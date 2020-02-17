package com.brovko.sarafan.dto;

import com.brovko.sarafan.domain.Message;
import com.brovko.sarafan.domain.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@JsonView(View.FullMessage.class)
public class MessagePageDto {
	private List<Message> messages;
	private int currentPage;
	private int totalPage;
	
	
	public MessagePageDto(List<Message> messages, int currentPage, int totalPage) {
		this.messages = messages;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
