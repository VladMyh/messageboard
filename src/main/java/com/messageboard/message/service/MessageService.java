package com.messageboard.message.service;

import com.messageboard.message.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();
    void addMessage(Message msg);
}
