package com.messageboard.message;

import java.io.Serializable;

public class Message implements Serializable{
    private String author;
    private String message;

    public Message() {}

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
            "author='" + author + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
