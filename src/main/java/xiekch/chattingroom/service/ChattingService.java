package xiekch.chattingroom.service;

import xiekch.chattingroom.domain.*;
import java.util.*;

public class ChattingService {
    private Storage storage;
    private static ChattingService chatting;

    private ChattingService() {
        this.storage = Storage.getInstance();
    }

    public static ChattingService getInstance() {
        if (chatting == null) {
            chatting = new ChattingService();
        }

        return chatting;
    }

    public boolean userSignUp(final User user) {
        if (user.getName().equals("")) {
            throw new RuntimeException("User's name must be filled!");
        }
        if (user.getPassword().equals("")) {
            throw new RuntimeException("User's password must be filled!");
        }
        if (!Validator.userNameValid(user.getName())) {
            throw new RuntimeException("User's name is not valid!");
        }
        if (!Validator.userPasswordValid(user.getPassword())) {
            throw new RuntimeException("User's password is not valid!");
        }

        if (this.storage.isUser(user.getName())) {
            throw new RuntimeException("User's name has been used!");
        }

        this.storage.createUser(user);
        return true;
    }

    public boolean roomSignUp(final Room room, final User user) {
        if (!this.storage.isUser(user)) {
            throw new RuntimeException("User does'nt exit!");
        }
        if (room.getName().equals("")) {
            throw new RuntimeException("Room's name must be filled!");
        }
        if (!Validator.roomNameValid(room.getName())) {
            throw new RuntimeException("Room's name is not valid!");
        }
        if (this.storage.isRoom(room.getName())) {
            throw new RuntimeException("Room's name has been used!");
        }

        this.storage.createRoom(room, user);
        return true;
    }

    public boolean userSignIn(final User user) {
        if (!this.storage.isUser(user)) {
            throw new RuntimeException("User's name or password is not correct!");
        }

        return true;
    }

    public void userEnterRoom(Room room, User user) {
        if (!room.isParticipator(user)) {
            room.addUser(user);
        }
    }

    public void userQuitRoom(Room room,User user){
        if(room.isParticipator(user)){
            room.removeUser(user);
        }
    }

    public boolean userSpeak(final String userName, final String roomName, final String mess) {
        if (this.storage.isRoom(roomName) && this.storage.isUser(userName)) {
            this.storage.getRoom(roomName).addMessage(this.storage.getUser(userName).speak(mess));
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Room> getParticipatedRooms(User user) {
        ArrayList<Room> participatedRooms = new ArrayList<Room>();
        ArrayList<Room> rooms = this.storage.getRooms();
        for (Room r : rooms) {
            if (r.isParticipator(user)) {
                participatedRooms.add(r);
            }
        }
        return participatedRooms;
    }

    public ArrayList<Room> getRestRooms(User user) {
        ArrayList<Room> restRooms = new ArrayList<Room>();
        ArrayList<Room> rooms = this.storage.getRooms();
        for (Room r : rooms) {
            if (!r.isParticipator(user)) {
                restRooms.add(r);
            }
        }

        return restRooms;
    }
}
