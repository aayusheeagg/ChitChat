<%@page import="java.util.HashMap"%>
<%@page import="com.chitchat.*"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  String profileName = session.getAttribute("person").toString();
    String roomName = session.getAttribute("roomname").toString();
    HashMap<String, ChatRoom> roomList = (HashMap<String, ChatRoom>) application.getAttribute("roomList");
    ChatRoom room = roomList.get(roomName);
%>
<html>
    <head>
        <title>Chatting - Chit-Chat Application</title>
        <script>
            window.onload = function() {
                var chat = document.getElementById("chattab");
                chat.scrollTop = chat.scrollHeight;
                setInterval("getChat()", 5000);
            }
            function getChat() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        var chat = document.getElementById("chattab");
                        chat.innerHTML = xmlhttp.responseText;
                        chat.scrollTop = chat.scrollHeight;
                    }
                }
                xmlhttp.open("get", "getchat.jsp", true)
                xmlhttp.send(null);
            }
        </script>
    </head>
    <body>
        <div id="page">
            <table width="900" height="550" border="0" align="center" cellpadding="10" bgcolor="#476BC0">
                <tr>
                    <td align="top">
                        <h3>You are : ${person}  (Room : ${roomname} )
                            <a href="RequestManager?submit=logout" style="float: right; text-decoration: none; color: white; margin-right:2%">Logout</a></h3>
                        
                        <div style="padding: 20px; font-family: monospace;overflow: scroll;height: 300px;background-color: #ffff99;color: #0000ff; font-size: 15px;font-weight: bolder" id="chattab">
                            <%
                                for (Iterator iterator = room.iterator(); iterator.hasNext();) {
                                    ChatRoomEntry chatentry = (ChatRoomEntry) iterator.next();
                            %>
                            <p><%= chatentry.getProfileName()%> (<%= chatentry.getTime()%>) : <%= chatentry.getMessage()%></p>
                            <% }%>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="ChatManager" method="post">
                            <table align="center" width=100%>
                                <tr><td><font color='#476BC0'>Your message</font></td></tr>
                                <tr><td><textarea name="message" cols=50 rows=5></textarea></td></tr>
                                <tr><td><input type="submit" name="chat" value="Send"></td></tr>
                            </table>
                        </form>
                        <form action="ChatManager" method="post">
                            <center><input type="submit" name="chat" value="Exit from Chat Room" onclick="return confirm('Are You Sure')"></center>
                        </form>
                        <br>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>