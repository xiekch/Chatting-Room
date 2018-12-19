package xiekch.chattingroom.domain;

import java.util.ArrayList;

public class Room {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Message> content;

    public Room(String name){
        this.name=name;
        this.users=new ArrayList<User>();
        this.content=new ArrayList<Message>();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Message> getMessage(){
        return this.content;
    }

    public void addMessage(Message mess){
        this.content.add(mess);
    }    

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    public boolean isParticipator(User user){
        return this.users.contains(user);
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}
