package xiekch.chattingroom.service;

import xiekch.chattingroom.domain.*;

public class ChattingService {
    private Storage storage;

    public ChattingService() {
        this.storage = Storage.getInstance();
    }

    public boolean userSignUp(final User user) {
        if(user.getName().equals("")){
            throw new RuntimeException("User's name must be filled!");
        }
        if(user.getPassword().equals("")){
            throw new RuntimeException("User's password must be filled!");
        }
        if (!Validator.userName.matcher(user.getName()).matches()) {
            throw new RuntimeException("User's name is not valid!");
        }
        if (!Validator.userPassword.matcher(user.getPassword()).matches()) {
            throw new RuntimeException("User's password is not valid!");
        }

        if (this.storage.isUser(user.getName())) {
            throw new RuntimeException("User's name has been used!");
        }

        this.storage.createUser(user);
        return true;
    }

    public boolean roomSignUp(final Room room) {
        if(room.getName().equals("")){
            throw new RuntimeException("Room's name must be filled!");
        }
        if (!Validator.roomName.matcher(room.getName()).matches()) {
            throw new RuntimeException("Room's name is not valid!");
        }
        if (this.storage.isRoom(room.getName())) {
            throw new RuntimeException("Room's name has been used!");
        }

        this.storage.createRoom(room);
        return true;
    }

    public boolean userSignIn(final User user) {
        if (!this.storage.isUser(user)) {
            throw new RuntimeException("User's name or password is not correct!");
        }

        return true;
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
