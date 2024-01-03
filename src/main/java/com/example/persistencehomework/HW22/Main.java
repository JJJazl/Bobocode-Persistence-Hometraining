package com.example.persistencehomework.HW22;

import com.example.persistencehomework.HW22.dao.Properties;
import com.example.persistencehomework.HW22.entity.Note;
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
        Session session = sessionFactory.createSession();

        // Load users
        var user = session.findById(User.class, 1L);
        var theSameUser = session.findById(User.class, 1L);

        System.out.println(user);
        System.out.println(theSameUser);
        System.out.println("Loaded from the cache: " + (user == theSameUser));

        // Load notes
        var note = session.findById(Note.class, 2L);
        System.out.println(note);

        session.close();
    }
}