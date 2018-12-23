package xiekch.chattingroom.domain;

import java.util.Date;

import org.springframework.core.style.ToStringCreator;

import java.text.SimpleDateFormat;

public class Message {
    private String userName;
    private String message;
    private long date;
    private String roomName;

    public Message() {
    }

    public Message(String userName, String message, String roomName,long data) {
        this.userName = userName;
        this.message = message;
        this.date = data;
        this.roomName = roomName;
    }

    public String getuserName() {
        return this.userName;
    }

    public String getMessage() {
        return this.message;
    }

    public long getDate() {
        return this.date;
    }

    public String getRoomName() {
        return this.roomName;
    }
}
