package com.messenger.simplemessenger.webSocket;


import com.messenger.simplemessenger.chat.port.in.service.MessageUseCase;
import org.openapitools.model.MessageRequest;
import org.openapitools.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class WebsocketController {

    private final MessageUseCase messageUseCase;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebsocketController(MessageUseCase messageUseCase, SimpMessagingTemplate messagingTemplate) {
        this.messageUseCase = messageUseCase;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/websocket/{chatId}")
    @SendTo("/topic/{chatId}/messages")
    public MessageResponse send(@DestinationVariable("chatId") UUID chatId, MessageRequest messageRequest) {
        System.out.println("Es funktioniert");


        // distribute and save the Message
        return messageUseCase.createMessage(messageRequest, chatId);
    }
}