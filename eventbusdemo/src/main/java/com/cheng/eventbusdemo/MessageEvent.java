package com.cheng.eventbusdemo;

public class MessageEvent {
 
    public final String message;
 
    public MessageEvent(String message) {
        this.message = message;
    }
    public String getMsg(){
        return message;
    }
}