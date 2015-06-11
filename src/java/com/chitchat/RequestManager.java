package com.chitchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestManager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String action = request.getParameter("submit");
        HttpSession session = request.getSession();
        boolean status = false;
        boolean login = false;
        boolean logout = false;

        if (action.equals("Login")) {
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            login = true;
            request.setAttribute("login", login);
            String name1 = DBManager.login(username, passwd);
            if(name1!=null) status=true;
            if (status) {
                UserInfo s = HibManager.getUser(username);
                session.setAttribute("person", s.getName());
                session.setAttribute("username", s.getUsername());
                session.setAttribute("rolename", s.getRolename());
                session.setAttribute("s", s);
                HashMap<String,ChatRoom> roomList = null;
                roomList = (HashMap<String,ChatRoom>) request.getServletContext().getAttribute("roomList");
                if (roomList == null) {
                    roomList = DBManager.getAllRooms();
                     request.getServletContext().setAttribute("roomList", roomList);
                     session.setAttribute("hashmap", roomList);
                }else
                    session.setAttribute("hashmap", roomList);
              
                if ((s.getRolename()).equalsIgnoreCase("admin")) {
                    List list = HibManager.getAllUsers();
                    session.setAttribute("list", list);
                    RequestDispatcher view = request.getRequestDispatcher("configure.jsp");
                    view.forward(request, response);
                } else {
                    RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
                    view.forward(request, response);
                }
            } else {
                request.setAttribute("status", status);
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
            }
        } else if (action.equals("SignUp")) {
            RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
            view.forward(request, response);
        } else if (action.equals("logout")) {
            session.invalidate();
            login = false;
            request.setAttribute("login", login);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else if (action.equals("changepasswd")) {
            String username = request.getParameter("username");
            String passwordold = request.getParameter("currentpass");
            String passwordnew = request.getParameter("newpass");
            String passwordconfirm = request.getParameter("confirmpass");
            if (passwordnew.equals(passwordconfirm) && DBManager.login(username, passwordold)!=null) {
                boolean changed = DBManager.changepasswd(username, passwordold, passwordnew);
                session.invalidate();
                login = false;
                request.setAttribute("login", login);
                request.setAttribute("changed", changed);
                RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
                view.forward(request, response);
            } else {
                boolean wrong = true;
                request.setAttribute("wrong", wrong);
                RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
                view.forward(request, response);
            }
        } else if (action.equals("Delete")) {
            String[] selectedItems = request.getParameterValues("check");
            for (String item : selectedItems) {
                HibManager.deleteUser(item);
            }
            List list = HibManager.getAllUsers();
            session.setAttribute("list", list);
            RequestDispatcher view = request.getRequestDispatcher("configure.jsp");
            view.forward(request, response);
        } else if (action.equals("Add")) {
            String Name = request.getParameter("name");
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            String Email = request.getParameter("email");
            String dob = request.getParameter("dob");
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MMMM-yyyy");
            Date dateOfBirth = fmt.parse(dob);
            String Gender = request.getParameter("gender");
            String rolename = request.getParameter("type");
            UserInfo s = new UserInfo(Name, username, passwd, " ", Email, dateOfBirth, " ", Gender, " ", rolename, " ");
            boolean st = DBManager.addUser(s);
            if (st) {
                request.setAttribute("add", st);
                List list = HibManager.getAllUsers();
                session.setAttribute("list", list);
            }
            RequestDispatcher view = request.getRequestDispatcher("configure.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
