package xiekch.chattingroom.domain;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Message {
    private String userName;
    private String message;
    private Date date;
    private String roomName;

    public Message() {
    }

    public Message(String userName, String message, String roomuserName) {
        this.userName = userName;
        this.message = message;
        this.date = new Date();
        this.roomName = roomuserName;
    }

    public String getuserName() {
        return this.userName;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sf.format(this.date);
    }

    public String getRoomuserName() {
        return this.roomName;
    }
}
