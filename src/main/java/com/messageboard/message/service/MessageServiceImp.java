package com.messageboard.message.service;

import com.messageboard.message.Message;
import com.messageboard.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService{

    private final MessageRepository repository;

    @Autowired
    public MessageServiceImp(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Message> getMessages() {
        return repository.getMessages();
    }

    @Override
    public void addMessage(Message msg) {
        repository.addMessage(msg);
    }
}
