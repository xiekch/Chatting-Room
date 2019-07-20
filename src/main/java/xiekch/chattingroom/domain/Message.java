package xiekch.chattingroom.domain;

public class Message {
    private String roomName;
    private String userName;
    private String message;
    private long date;

    public Message(String roomName, String userName, String message, long date) {
        this.roomName = roomName;
        this.userName = userName;
        this.message = message;
        this.date = date;
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

    public void escape() {
        this.message = this.message.replaceAll("&", "&amp;");
        this.message = this.message.replaceAll("<", "&lt;");
        this.message = this.message.replaceAll(">", "&gt;");
        this.message = this.message.replaceAll(" ", "&nbsp;");
    }

    public String getOriginalMessage() {
        String message = new String(this.message);
        message = message.replaceAll("&amp;", "&");
        message = message.replaceAll("&lt;", "<");
        message = message.replaceAll("&gt;", ">");
        message = message.replaceAll("&nbsp;", " ");
        return message;
    }
}
