package com.chitchat;
import java.util.*;
import java.text.*;

public class ChatRoomEntry {
    private String profileName;
    private String message;
    private String time;

    public ChatRoomEntry() {
    }

    public ChatRoomEntry(String profileName, String message) {
        this.profileName = profileName;
        this.message = message;
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        time = df.format(new Date());
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }    
}
