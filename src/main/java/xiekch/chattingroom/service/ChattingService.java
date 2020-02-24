package xiekch.chattingroom.service;

import xiekch.chattingroom.domain.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChattingService {
    @Autowired
    private Storage storage;

    public ChattingService() {
        System.out.println("chatting service constructor");
    }

    public boolean userSignUp(final User user) {
        if (user == null) {
            throw new RuntimeException("User must not be null!");
        }

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

        if (storage.isUser(user.getName())) {
            throw new RuntimeException("User's name has been used!");
        }

        storage.createUser(user);
        return true;
    }

    public boolean roomSignUp(final Room room, final User user) {
        if (!this.isUser(user)) {
            throw new RuntimeException("User does'nt exit!");
        }
        if (room.getName().equals("")) {
            throw new RuntimeException("Room's name must be filled!");
        }
        if (!Validator.roomNameValid(room.getName())) {
            throw new RuntimeException("Room's name is not valid!");
        }
        if (storage.isRoom(room.getName())) {
            throw new RuntimeException("Room's name has been used!");
        }

        storage.createRoom(room, user);
        return true;
    }

    public boolean userSignIn(final User user) {
        if (!this.isUser(user)) {
            throw new RuntimeException("User's password is not correct or user's name has been used!");
        }

        return true;
    }

    public void userEnterRoom(Room room, User user) {
        if (!this.isUser(user)) {
            throw new RuntimeException("User does'nt exit!");
        }
        if (!this.isRoom(room.getName())) {
            throw new RuntimeException("Room does'nt exit!");
        }
        storage.userEnterRoom(room, user);

    }

    public void userQuitRoom(Room room, User user) {
        if (room.isParticipator(user)) {
            room.removeUser(user);
            if (room.isEmpty()) {
                storage.deleteRoom(room);
            }
        }
    }

    public boolean isUser(User user) {
        if (user == null || user.getName() == null || user.getPassword() == null)
            return false;
        return storage.isUser(user);
    }

    public boolean isRoom(String roomName) {
        if (roomName == null)
            return false;
        return storage.isRoom(roomName);
    }

    public boolean isRoomUser(Room room, User user) {
        if (this.isRoom(room.getName()) && this.isUser(user) && room.isParticipator(user)) {
            return true;
        }
        return false;
    }

    public boolean isRoomUser(String roomName, User user) {
        if (this.isRoom(roomName) && this.isUser(user) && storage.getRoom(roomName).isParticipator(user)) {
            return true;
        }
        return false;
    }

    public Message userSpeak(final String userName, final String roomName, final String mess, final long date) {
        if (!this.isRoom(roomName)) {
            throw new RuntimeException("Room does'nt exit!");
        }
        if (!storage.isUser(userName)) {
            throw new RuntimeException("User does'nt exit!");
        }
        User user = storage.getUser(userName);
        if (!storage.getRoom(roomName).isParticipator(user)) {
            throw new RuntimeException("User is not in the room!");
        }

        Message message = user.speak(roomName, mess, date);
        storage.userSpeak(roomName, message);
        return message;
    }

    public Room getRoom(String roomName) {
        if (!this.isRoom(roomName)) {
            throw new RuntimeException("Room does'nt exit!");
        }

        return storage.getRoom(roomName);
    }

    public ArrayList<Room> getParticipatedRooms(User user) {
        if (!this.isUser(user)) {
            throw new RuntimeException("User does'nt exit!");
        }

        ArrayList<Room> participatedRooms = new ArrayList<Room>();
        ArrayList<Room> rooms = storage.getRooms();
        for (Room r : rooms) {
            if (r.isParticipator(user)) {
                participatedRooms.add(r);
            }
        }
        return participatedRooms;
    }

    public ArrayList<Room> getRestRooms(User user) {
        ArrayList<Room> restRooms = new ArrayList<Room>();
        ArrayList<Room> rooms = storage.getRooms();
        for (Room r : rooms) {
            if (!r.isParticipator(user)) {
                restRooms.add(r);
            }
        }

        return restRooms;
    }
}
