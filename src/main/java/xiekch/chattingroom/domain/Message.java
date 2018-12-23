package xiekch.chattingroom.domain;

public class Message {
    private String roomName;
    private String userName;
    private String message;
    private long date;

    public Message() {
    }

    public Message(String roomName, String userName, String message, long date) {
        this.userName = userName;
        this.message = message;
        this.date = date;
        this.roomName = roomName;
    }

    public String getUserName() {
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
