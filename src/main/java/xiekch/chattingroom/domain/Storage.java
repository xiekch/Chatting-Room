package xiekch.chattingroom.domain;

import java.util.ArrayList;

public class Storage{
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private static Storage storage;

    private Storage(){
    }

    public static Storage getInstance(){
        if(storage==null){
            storage=new Storage();
        }
        return storage;
    }

    public void createRoom(Room room){
        this.rooms.add(room);
    }

    public void createUser(User user){
        this.users.add(user);
    }

    public void deleteRoom(String roomName){
        for(Room r:rooms){
            if(r.getName()==roomName){
                this.rooms.remove(r);
            }
        }
    }

    public void deleteRoom(Room room){
        this.rooms.remove(room);
    }

    public void deleteUser(String userName){
        for(User u:users){
            if(u.getName()==userName){
                this.users.remove(u);
            }
        }
    }

    public void deleteUser(User user){
        this.users.remove(user);
    }

    public boolean isRoom(String roomName){
        for(Room r:rooms){
            if(r.getName()==roomName){
                return true;
            }
        }
        return false;
    }

    public Room getRoom(String roomName){
        for(Room r:rooms){
            if(r.getName()==roomName){
                return r;
            }
        }
        return null;
    }

    public boolean isUser(String userName){
        for(User u:users){
            if(u.getName()==userName){
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName){
        for(User u:users){
            if(u.getName()==userName){
                return u;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public void changeRoom(String roomName,Room newRoom){
        for(Room r:rooms){
            if(r.getName()==roomName){
                r=newRoom;
            }
        }
    }

    public void changeUser(String userName,User newUser){
        for(User u:users){
            if(u.getName()==userName){
                u=newUser;
            }
        }
    }

}