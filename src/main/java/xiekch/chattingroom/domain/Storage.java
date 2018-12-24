package xiekch.chattingroom.domain;

import java.util.ArrayList;
import java.io.*;

public class Storage {
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private static Storage storage;
    private final static String RoomsFileName = "Rooms.txt";
    private final static String UsersFileName = "Users.txt";
    private final static String MessagesFileName = "Messages.txt";
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
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null)
                    reader.close();
                reader = null;
            }

            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null)
                    reader.close();
                reader = null;
            }

            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null)
                    reader.close();
                reader = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        if (dirty < 10)
            return;
        dirty = 0;
        File outputFile = null;
        Writer writer = null;
        try {
            try {
                outputFile = new File(UsersFileName);
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }
                writer = new FileWriter(outputFile);
                for (User user : this.users) {
                    writer.write(user.getName());
                    writer.write(" ");
                    writer.write(user.getPassword());
                    writer.write("\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.close();
                writer = null;
            }

            try {
                outputFile = new File(RoomsFileName);
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }
                writer = new FileWriter(outputFile);
                for (Room room : this.rooms) {
                    writer.write(room.getName());
                    writer.write(" ");
                    ArrayList<User> users = room.getUsers();
                    for (User user : users) {
                        writer.write(user.getName());
                        writer.write(" ");
                    }
                    writer.write("\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.close();
                writer = null;
            }

            try {
                outputFile = new File(MessagesFileName);
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }
                writer = new FileWriter(outputFile);
                for (Room room : this.rooms) {
                    for (Message message : room.getMessage()) {
                        writer.write(message.getRoomName());
                        writer.write(" ");
                        writer.write(message.getUserName());
                        writer.write(" ");
                        writer.write(message.getMessage());
                        writer.write(" ");
                        writer.write(String.valueOf(message.getDate()));
                        writer.write("\r\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.close();
                writer = null;
            }
        } catch (IOException e) {
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
        System.out.println(dirty);
        this.writeToFile();
    }

    public void createUser(User user) {
        this.users.add(user);
        dirty++;
        System.out.println(dirty);
        this.writeToFile();
    }

    public void deleteRoom(String roomName) {
        for (Room r : rooms) {
            if (r.getName().equals(roomName)) {
                this.rooms.remove(r);
            }
        }
        dirty++;
        System.out.println(dirty);
        this.writeToFile();
    }

    public void deleteRoom(Room room) {
        this.rooms.remove(room);
        dirty++;
        System.out.println(dirty);
        this.writeToFile();
    }

    public void deleteUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                this.users.remove(u);
            }
        }
        dirty++;
        System.out.println(dirty);
        this.writeToFile();
    }

    public void deleteUser(User user) {
        this.users.remove(user);
        dirty++;
        System.out.println(dirty);
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
        System.out.println(dirty);
        this.writeToFile();
    }

    public void changeUser(String userName, User newUser) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                u = newUser;
            }
        }
        dirty++;
        System.out.println(dirty);
        this.writeToFile();
    }

    public void userSpeak(String roomName, Message message) {
        this.getRoom(roomName).addMessage(message);
        dirty++;
        this.writeToFile();
    }

    public void sync() {
        this.writeToFile();
    }

}