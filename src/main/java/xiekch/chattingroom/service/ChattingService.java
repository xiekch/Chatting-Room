package xiekch.chattingroom.service;

import xiekch.chattingroom.domain.*;

public class ChattingService {
    private Storage storage;

    public ChattingService() {
        this.storage = Storage.getInstance();
    }

    public boolean userSignUp(final String userName, final String password) {
        if (this.storage.isUser(userName)) {
            this.storage.createUser(new User(userName, password));
            return true;
        } else {
            return false;
        }
    }

    public boolean roomSignUp(final String roomName) {
        if (this.storage.isRoom(roomName)) {
            this.storage.createRoom(new Room(roomName));
            return true;
        } else {
            return false;
        }
    }

    public boolean userSignIn(final String userName, final String password) {
        User user = this.storage.getUser(userName);
        if (user != null && user.getName() == userName && user.getPassword() == password) {
            return true;
        } else {
            return false;
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

}
