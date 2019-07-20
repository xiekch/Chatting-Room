package xiekch.chattingroom.domain;

import java.util.ArrayList;
import java.io.*;

public class Storage {
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private static Storage storage;
    private final static String RoomsFileName = "./data/Rooms.txt";
    private final static String UsersFileName = "./data/Users.txt";
    private final static String MessagesFileName = "./data/Messages.txt";
    private static int dirty = 0;

    private Storage() {
        this.rooms = new ArrayList<Room>();
        this.users = new ArrayList<User>();
        readFromFile();
    }

    private void readFromFile() {
        File inputFile = null;
        BufferedReader reader = null;
        String inline = null;
        String[] text = null;
        try {
            // users
            inputFile = new File(UsersFileName);
            if (!(inputFile.isFile() && inputFile.exists())) {
                inputFile.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(inputFile));
                inline = null;
                while ((inline = reader.readLine()) != null) {
                    text = inline.split(" +");
                    if (text.length != 2)
                        continue;
                    this.users.add(new User(text[0], text[1]));
                }
            }
            reader.close();

            // rooms
            inputFile = new File(RoomsFileName);
            if (!(inputFile.isFile() && inputFile.exists())) {
                inputFile.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(inputFile));
                inline = null;
                while ((inline = reader.readLine()) != null) {
                    text = inline.split(" +");
                    if (text.length < 2)
                        continue;
                    Room room = new Room(text[0]);
                    this.rooms.add(room);
                    for (int i = 1; i < text.length; i++) {
                        User user = this.getUser(text[i]);
                        room.addUser(user);
                    }
                }
            }
            reader.close();

            // messages
            inputFile = new File(MessagesFileName);
            if (!(inputFile.isFile() && inputFile.exists())) {
                inputFile.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(inputFile));
                inline = null;
                while ((inline = reader.readLine()) != null) {
                    text = inline.split(" +");
                    if (text.length != 4)
                        continue;
                    Message message = new Message(text[0], text[1], text[2], Long.valueOf(text[3]));
                    Room room = this.getRoom(text[0]);
                    room.addMessage(message);
                }
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        if (dirty < 10)
            return;
        dirty = 0;
        File outputFile = null;
        BufferedWriter writer = null;
        try {
            // users
            outputFile = new File(UsersFileName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (User user : this.users) {
                writer.write(user.getName());
                writer.write(" ");
                writer.write(user.getPassword());
                writer.newLine();
            }
            writer.close();

            // rooms
            outputFile = new File(RoomsFileName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (Room room : this.rooms) {
                writer.write(room.getName());
                writer.write(" ");
                ArrayList<User> users = room.getUsers();
                for (User user : users) {
                    writer.write(user.getName());
                    writer.write(" ");
                }
                writer.newLine();
            }
            writer.close();

            // messages
            outputFile = new File(MessagesFileName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (Room room : this.rooms) {
                for (Message message : room.getMessage()) {
                    writer.write(message.getRoomName());
                    writer.write(" ");
                    writer.write(message.getUserName());
                    writer.write(" ");
                    writer.write(message.getMessage());
                    writer.write(" ");
                    writer.write(String.valueOf(message.getDate()));
                    writer.newLine();
                }
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void createRoom(Room room, User user) {
        room.addUser(user);
        this.rooms.add(room);
        dirty++;
        this.writeToFile();
    }

    public void createUser(User user) {
        this.users.add(user);
        dirty++;
        this.writeToFile();
    }

    public void deleteRoom(String roomName) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                this.rooms.remove(r);
            }
        }
        dirty++;
        this.writeToFile();
    }

    public void deleteRoom(Room room) {
        this.rooms.remove(room);
        dirty++;
        this.writeToFile();
    }

    public void deleteUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                this.users.remove(u);
            }
        }
        dirty++;
        this.writeToFile();
    }

    public void deleteUser(User user) {
        this.users.remove(user);
        dirty++;
        this.writeToFile();
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
        dirty++;
        this.writeToFile();
    }

    public void changeUser(String userName, User newUser) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                u = newUser;
            }
        }
        dirty++;
        this.writeToFile();
    }

    public void userSpeak(String roomName, Message message) {
        this.getRoom(roomName).addMessage(message);
        dirty++;
        this.writeToFile();
    }

    public void userEnterRoom(Room room, User user) {
        if (!room.isParticipator(user)) {
            room.addUser(user);
            dirty++;
        }
    }

    public void sync() {
        this.writeToFile();
    }

}