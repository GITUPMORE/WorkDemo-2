package com.example.workdemo101;

public class ChatMessage {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_CHATGPT = "chatgpt";
    String message;
    String sentby;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentby() {
        return sentby;
    }

    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    public ChatMessage(String message, String sentby) {
        this.message = message;
        this.sentby = sentby;
    }
}
