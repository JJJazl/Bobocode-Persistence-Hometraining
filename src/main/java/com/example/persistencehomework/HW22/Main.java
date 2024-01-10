package com.example.persistencehomework.HW22;

import com.example.persistencehomework.HW22.dao.Properties;
import com.example.persistencehomework.HW22.entity.User;
import com.example.persistencehomework.HW22.session.Session;
import com.example.persistencehomework.HW22.session.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new SessionFactory(new Properties(
                "jdbc:postgresql://localhost:5432/test_db",
                "postgres",
                "123"
        ));

        // Start a session and update an entity
        Session session = sessionFactory.createSession();

        User user = session.findById(User.class, 1L);
        System.out.println("User before update: " + user);

        user.setFirstName("Renat");
        user.setLastName("Safarov");

        session.close();

        // Start a new session and get updated entity
        Session newSession = sessionFactory.createSession();

        User updatedUser = newSession.findById(User.class, 1L);
        System.out.println("User after update: " + updatedUser);
    }
}