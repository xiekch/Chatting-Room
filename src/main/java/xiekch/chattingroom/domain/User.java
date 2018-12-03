package xiekch.chattingroom.domain;

public class User {
    private String name;
    private String password;

    public User(){}

    public User(String name,String password){
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public Message speak(String mess){
        return new Message(this.name,mess);
    }
}
