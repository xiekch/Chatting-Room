package xiekch.chattingroom.domain;

import java.util.Date;
import java.text.SimpleDateFormat;;

public class Message {
    private String name;
    private String message;
    private Date date;

    public Message(String name,String message){
        this.name=name;
        this.message=message;
        this.date=new Date();
    }

    public String getName() {
        return this.name;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate(){
        SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sf.format(this.date);
    }
}
