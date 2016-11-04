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
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class MessageController {

    private final MessageService service;
    private Set<String> users = new HashSet<>();
    private List<String> newUsers = new LinkedList<>();

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

        users.add(message.getAuthor());
        if(!newUsers.contains(message.getAuthor()))
            newUsers.add(message.getAuthor());
    }

    @CrossOrigin
    @RequestMapping("/sse/updates")
    public SseEmitter subscribeUpdates() {
        SseEmitter sseEmitter = new SseEmitter();
        if(newUsers.size() > 0) {
            try {
                sseEmitter.send("User " + newUsers.get(0) + " is online");
                newUsers.remove(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sseEmitter.complete();
        return sseEmitter;
    }
}
