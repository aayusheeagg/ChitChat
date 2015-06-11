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
public class UpdateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession session = request.getSession();
        HashMap<String, String> errors = new HashMap<String, String>();
        if (!Pattern.matches("[a-zA-Z ]*", request.getParameter("name"))) {
            errors.put("name", "Name is not valid");
        } else {
            String name = request.getParameter("name");
            request.setAttribute("s.name", name);
        }        
        if (!Pattern.matches("[0-9]{0,10}", request.getParameter("mobile"))) {
            errors.put("mobile", "Mobile no. must contain 10 digits");
        } else {
            String mobile = request.getParameter("mobile");
            request.setAttribute("s.mobile", mobile);
        }
        if (!Pattern.matches("[a-z0-9_%+-.]+@[a-zA-Z_]+\\.([a-zA-Z]{2,4})", request.getParameter("email"))) {
            errors.put("email", "Email is not valid");
        } else {
            String email = request.getParameter("email");
            request.setAttribute("s.email", email);
        }
        if (!Pattern.matches("[a-zA-Z ]*", request.getParameter("city"))) {
            errors.put("city", "City is not valid");
        } else {
            String city = request.getParameter("city");
            request.setAttribute("s.city", city);
        }        
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
            view.forward(request, response);
            System.out.println("Errors Found");
        } else {
            String username = session.getAttribute("username").toString();
            String Name = request.getParameter("name");            
            String MobileNumber = request.getParameter("mobile");
            String Email = request.getParameter("email");
            String City = request.getParameter("city");
            Date DateOfBirth = new Date(request.getParameter("dob"));            
            UserInfo s = HibManager.getUser(username);
            s.setCity(City);
            s.setEmail(Email);
            s.setMobileNumber(MobileNumber);
            s.setDateOfBirth(DateOfBirth);
            s.setName(Name);                    
            try {
                Part p=request.getPart("photo");                
            String File = getFileName(p);            
            String FileName = UUID.randomUUID() + File;
            String dirName = getServletContext().getRealPath("/");
            String location = dirName + "uploads/" + FileName;    
                InputStream is = p.getInputStream();
                FileOutputStream fos = new FileOutputStream(location);
                int x = is.available();
                byte[] arr = new byte[x];
                is.read(arr);
                fos.write(arr);
                is.close();
                fos.close();
                if(File.length()>1)                
                    s.setPhoto(FileName);
                
            } catch (Exception ex) {
                log(ex.toString());
            }            
            HibManager.updateUser(s);
            session.setAttribute("s", s);
            boolean updated = true;
            request.setAttribute("updated", updated);
            RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
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
