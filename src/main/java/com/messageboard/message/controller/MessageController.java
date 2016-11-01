package com.messageboard.message.controller;

import com.messageboard.message.Message;
import com.messageboard.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(service.getMessages());
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void addMessage(@RequestBody Message message) {
        service.addMessage(message);
    }
}
