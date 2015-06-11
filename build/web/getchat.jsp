<%@page import="com.chitchat.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.text.*"%>

    <%  String profileName = session.getAttribute("person").toString();
        String roomName = session.getAttribute("roomname").toString();
        HashMap<String, ChatRoom> roomList = (HashMap<String, ChatRoom>) application.getAttribute("roomList");
        ChatRoom room = roomList.get(roomName);
        for (Iterator iterator = room.iterator(); iterator.hasNext();) {
            ChatRoomEntry chatentry = (ChatRoomEntry) iterator.next();
    %>
    <p><%= chatentry.getProfileName()%> (<%= chatentry.getTime()%>) : <%= chatentry.getMessage()%></p>
    <%
        }
    %>


