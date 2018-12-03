package xiekch.chattingroom.service;

import java.util.regex.Pattern;

public class Validator {
    public final static Pattern userName=Pattern.compile("\\w{5,15}");
    public final static Pattern userPassword=Pattern.compile("\\w{5,15}");

    public final static Pattern roomName=Pattern.compile("\\w{5,15}");
}
