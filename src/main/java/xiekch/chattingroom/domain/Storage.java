package xiekch.chattingroom.domain;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private static Storage storage;

    private Storage() {
        this.rooms=new ArrayList<Room>();
        this.users=new ArrayList<User>();
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void createRoom(Room room,User user) {
        room.addUser(user);
        this.rooms.add(room);
    }

    public void createUser(User user) {
        this.users.add(user);
    }

    public void deleteRoom(String roomName) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                this.rooms.remove(r);
            }
        }
    }

    public void deleteRoom(Room room) {
        this.rooms.remove(room);
    }

    public void deleteUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                this.users.remove(u);
            }
        }
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }

    public boolean isRoom(String roomName) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    public Room getRoom(String roomName) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                return r;
            }
        }
        return null;
    }

    public boolean isUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUser(final User user) {
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void changeRoom(String roomName, Room newRoom) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                r = newRoom;
            }
        }
    }

    public void changeUser(String userName, User newUser) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                u = newUser;
            }
        }
    }

}