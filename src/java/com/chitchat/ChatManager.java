package com.chitchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatManager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("chat");
        HashMap<String, ChatRoom> roomList = (HashMap<String,ChatRoom>) getServletContext().getAttribute("roomList");

        if (action.equals("Update")) {
            //Delete Rooms
            String rooms[] = request.getParameterValues("remove");
            if (rooms != null) {
                for (int i = 0; i < rooms.length; i++) {
                    HibManager.deleteRoom(rooms[i]);
                    roomList.remove(rooms[i]);
                }
            }
            //Adding a room.
            String roomname = request.getParameter("roomname");
            String madeby = (String) session.getAttribute("person");
            if (roomname != null && roomname.length() > 0) {
                ChatRoom room1= new ChatRoom(roomname,madeby);
                HibManager.addRoom(room1);
                roomList.put(roomname,room1);
            }
            roomList = DBManager.getAllRooms();
            request.getServletContext().setAttribute("roomList", roomList);
            if (roomList != null) {
                session.setAttribute("hashmap", roomList);
            }
            RequestDispatcher rd = request.getRequestDispatcher("configure.jsp");
            rd.forward(request, response);

        } else if (action.equals("Exit from Chat Room")) {
            String type= session.getAttribute("rolename").toString();
            if(type.equalsIgnoreCase("admin"))
            {
               RequestDispatcher view = request.getRequestDispatcher("configure.jsp");
            view.forward(request, response); 
            }
            else{
            RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
            view.forward(request, response);
            }
        } else if (action.equals("Enter room")) {
            String roomname = request.getParameter("enter");
            if (roomname == null) {
                    request.setAttribute("notselected",true);
                    RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
                    view.forward(request, response);
                    
            } else {
                session.setAttribute("roomname",roomname);
                RequestDispatcher view = request.getRequestDispatcher("chat.jsp");
                view.forward(request, response);
            }
        }else if (action.equals("Send")) {
        String profileName = session.getAttribute("person").toString();
        String roomname = session.getAttribute("roomname").toString();
        String message = request.getParameter("message");
        ChatRoom room = roomList.get(roomname);
        if(message.length()>0){
            ChatRoomEntry entry = new ChatRoomEntry(profileName, message);
            room.joinChatEntry(entry);
        }
            RequestDispatcher view = request.getRequestDispatcher("chat.jsp");
            view.forward(request, response);
        } 

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
