package com.chitchat;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class DBManager {

    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost/chitchat";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "toor";

    static {
        try {
            Class.forName(DBDRIVER);
            System.out.println("Driver Loaded...");
        } catch (Exception ex) {
            System.out.println("Exception - " + ex);
        }
    }     
    
    public static String login(String Username, String Passwd) {       
        String name=null;
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            PreparedStatement st = con.prepareStatement("Select * from UserInfo where Username=? and Passwd=password(?)");
            st.setString(1, Username);
            st.setString(2, Passwd);
            ResultSet rs = st.executeQuery();
             if(rs.next())
             { 
                 name = rs.getString("name");             
             }           
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return name;
    }

    public static boolean addUser(UserInfo s) {       
        boolean status=false;
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            PreparedStatement st = con.prepareStatement("insert into userinfo values(?,?,password(?),?,?,?,?,?,?,?,?)");
            st.setString(1, s.getName());
            st.setString(2, s.getUsername());
            st.setString(3, s.getPasswd());
            st.setString(3, s.getPasswd());
            st.setString(4, s.getMobileNumber());
            st.setString(5, s.getEmail());
            st.setDate(6,new Date(s.getDateOfBirth().getTime()));
            st.setString(7, s.getCity());
            st.setString(8, s.getGender());
            st.setString(9, s.getNationality());
            st.setString(10, s.getRolename());
            st.setString(11, s.getPhoto());
            st.executeUpdate();
            status = true;
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return status;
    }
    
     public static boolean changepasswd(String Username,String passwdold,String passwdnew) {
        boolean status = false;
        try {

            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            PreparedStatement st = con.prepareStatement("update UserInfo set Passwd=password(?) where (Username=? AND Passwd=password(?)) ");
            st.setString(1, passwdnew);
            st.setString(2, Username);
            st.setString(3, passwdold);
            st.executeUpdate();
            con.close();
            status = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return status;
    }
    public static HashMap<String,ChatRoom> getAllRooms(){
        HashMap<String,ChatRoom> map = new HashMap<String,ChatRoom>();
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            PreparedStatement st = con.prepareStatement("Select * from chatroom");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String roomName = rs.getString(1);
                String madeby = rs.getString(2);  
                ChatRoom room = new ChatRoom(roomName,madeby);
                map.put(roomName,room);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return map;
    }
}
