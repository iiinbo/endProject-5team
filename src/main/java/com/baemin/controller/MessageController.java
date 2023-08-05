package com.baemin.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	
	
	@MessageMapping("/order-complete-message/{storeId}")
	@SendTo("/topic/order-complete/{storeId}")
	public String message(@DestinationVariable long storeId, String message) {
		return message;
	}
}