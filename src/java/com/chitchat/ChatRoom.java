package com.chitchat;

import java.util.Stack;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChatRoom extends Stack {
    @Id
    private String roomName;
    private String madeby;
    
    public ChatRoom() {
    }

    public ChatRoom(String roomName,String madeby) {
        this.roomName = roomName;
        this.madeby=madeby;
        
    }
    
    public void joinChatEntry(ChatRoomEntry  entry){
	    push(entry);
    }

    
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMadeby() {
        return madeby;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby;
    }
}
