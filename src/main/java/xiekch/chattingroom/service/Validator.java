package xiekch.chattingroom.service;

import java.util.regex.Pattern;

public class Validator {
    public final static Pattern userName = Pattern.compile("[\\w\\u4e00-\\u9fa5]{5,15}");
    public final static Pattern userPassword = Pattern.compile("\\w{5,15}");

    public final static Pattern roomName = Pattern.compile("[\\w\\u4e00-\\u9fa5]{5,15}");

    public static boolean userNameValid(String name) {
        return userName.matcher(name).matches();
    }

    public static boolean userPasswordValid(String password) {
        return userPassword.matcher(password).matches();
    }

    public static boolean roomNameValid(String name) {
        return roomName.matcher(name).matches();
    }
}
