package com.chitchat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibManager {

    private static SessionFactory factory;      //Heavy

    static {
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(UserInfo.class);
        config.addAnnotatedClass(ChatRoom.class);
        //new SchemaExport(config).create(true, true);   //for creating new table
        factory = config.buildSessionFactory();
    }

    /* public static void addUser(UserInfo s) {         //password cannot be encrypted
     Session session = factory.getCurrentSession();
     session.beginTransaction();
     session.save(s);
     session.getTransaction().commit();
     }
     */
    public static UserInfo getUser(String username) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserInfo where Username=:user");
        query.setParameter("user", username);
        UserInfo s = (UserInfo) query.uniqueResult();
        return s;
    }

    public static void updateUser(UserInfo s) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(s);
        session.getTransaction().commit();
    }

    public static void deleteUser(String userName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserInfo where Username=:user");
        query.setParameter("user", userName);
        UserInfo s = (UserInfo) query.uniqueResult();
        if (s != null) {
            session.delete(s);
        }
        session.getTransaction().commit();
    }

    public static List getAllUsers() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserInfo where 1=1");
        List list = query.list();
        return list;
    }

    public static void addRoom(ChatRoom c) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }

    public static void deleteRoom(String roomName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from ChatRoom where roomName=:room");
        query.setParameter("room", roomName);
        ChatRoom c = (ChatRoom) query.uniqueResult();
        if (c != null) {
            session.delete(c);
        }
        session.getTransaction().commit();
    }

}
