package com.chitchat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class AddUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        HashMap<String, String> errors = new HashMap<String, String>();
        if (!Pattern.matches("[a-zA-Z ]*", request.getParameter("name"))) {
            errors.put("name", "Name is not valid");
        } else {
            String name = request.getParameter("name");
            request.setAttribute("name", name);
        }
        if (!Pattern.matches("[a-zA-Z0-9_%+-@]*", request.getParameter("username"))) {
            errors.put("Username", "Username is not valid");
        } else {
            String user = request.getParameter("username");
            if (HibManager.getUser(user) != null) {
                errors.put("username", "Username already exists");
                System.out.println("Inside");
            }
            request.setAttribute("username", user);
        }
        if (!Pattern.matches("[0-9]{0,10}", request.getParameter("mobile"))) {
            errors.put("mobile", "Mobile no. must contain 10 digits");
        } else {
            String mobile = request.getParameter("mobile");
            request.setAttribute("mobile", mobile);
        }
        if (!Pattern.matches("[a-z0-9_%+-.]+@[a-zA-Z_]+\\.([a-zA-Z]{2,4})", request.getParameter("email"))) {
            errors.put("email", "Email is not valid");
        } else {
            String email = request.getParameter("email");
            request.setAttribute("email", email);
        }
        if (!Pattern.matches("[a-zA-Z ]*", request.getParameter("city"))) {
            errors.put("city", "City is not valid");
        } else {
            String city = request.getParameter("city");
            request.setAttribute("city", city);
        }
        if (!Pattern.matches("[a-zA-Z ]*", request.getParameter("nation"))) {
            errors.put("nation", "Nationality is not valid");
        } else {
            String nation = request.getParameter("nation");
            request.setAttribute("nation", nation);
        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
            view.forward(request, response);
            System.out.println("Errors Found");
        } else {
            String Name = request.getParameter("name");
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            String MobileNumber = request.getParameter("mobile");
            String Email = request.getParameter("email");
            String dob = request.getParameter("dob");
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MMMM-yyyy");
            Date dateOfBirth = fmt.parse(dob);
            String City = request.getParameter("city");
            String Gender = request.getParameter("gender");
            Part p = request.getPart("photo");
            String FileName = getFileName(p);
            FileName = UUID.randomUUID() + FileName;
            String dirName = getServletContext().getRealPath("/");
            String location = dirName + "uploads/" + FileName;
            String Nationality = request.getParameter("nation");
            String rolename = "user";
            try {

                InputStream is = p.getInputStream();
                FileOutputStream fos = new FileOutputStream(location);
                int x = is.available();
                byte[] arr = new byte[x];
                is.read(arr);
                fos.write(arr);
                is.close();
                fos.close();
            } catch (Exception ex) {
                log(ex.toString());
            }
            UserInfo s = new UserInfo(Name, username, passwd, MobileNumber, Email, dateOfBirth, City, Gender, Nationality, rolename, FileName);
            boolean status = DBManager.addUser(s);
            if (status) {
                request.setAttribute("added", status);
                boolean mail = MailManager.sendMail(Email);
                if (mail) {
                    log("Mail Sent Successfully to : " + Email);
                } else {
                    log("Error in sending mail");
                }
            }
            request.removeAttribute("name");
            request.removeAttribute("username");
            request.removeAttribute("mobile");
            request.removeAttribute("email");
            request.removeAttribute("city");
            request.removeAttribute("nation");
            RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
            view.forward(request, response);
        }
    }

    private String getFileName(Part p) {
        String str = "";
        String value = p.getHeader("content-disposition");
        String[] arr = value.split(";");
        int i = arr[2].indexOf("\"");
        int j = arr[2].lastIndexOf("\"");
        str = arr[2].substring(i + 1, j);
        return str;
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
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
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
