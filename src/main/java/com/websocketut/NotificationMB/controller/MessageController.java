package com.websocketut.NotificationMB.controller;

import com.websocketut.NotificationMB.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    // send to all
    @MessageMapping("/sendMessage")
    public void sendMessage(Message message) {

        messagingTemplate.convertAndSend(
                "/topic/public",
                message
        );

    }


    // send to specific user
    @MessageMapping("/privateMessage")
    public void privateMessage(Message message) {

        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/private",
                message
        );

    }
}
