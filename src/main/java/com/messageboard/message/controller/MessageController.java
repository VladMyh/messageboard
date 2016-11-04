package com.messageboard.message.controller;

import com.messageboard.message.Message;
import com.messageboard.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class MessageController {

    private final MessageService service;
    private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(service.getMessages());
    }

    @CrossOrigin
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void addMessage(@RequestBody Message message) {
        service.addMessage(message);
    }

    @CrossOrigin
    @RequestMapping("/sse/updates")
    public SseEmitter subscribeUpdates() {
        SseEmitter sseEmitter = new SseEmitter();
        synchronized (this.sseEmitters) {
            this.sseEmitters.add(sseEmitter);
            Random r = new Random();
            try {
                sseEmitter.send("{responce:'Notification " + r.nextInt() + "'}", MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sseEmitter;
    }
}
