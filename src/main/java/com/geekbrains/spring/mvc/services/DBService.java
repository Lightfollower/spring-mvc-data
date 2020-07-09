package com.geekbrains.spring.mvc.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBService {
    //    connect
    public void connect() {
        SessionFactory factory = connectDB();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.getTransaction().commit();
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
    public static SessionFactory connectDB() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
