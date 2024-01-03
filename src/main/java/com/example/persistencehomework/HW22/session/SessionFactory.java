package com.example.persistencehomework.HW22.session;

import com.example.persistencehomework.HW22.dao.Properties;
import com.example.persistencehomework.HW22.dao.GenericJdbcDAO;

public class SessionFactory {
    private final GenericJdbcDAO jdbcDAO;

    public SessionFactory(Properties properties) {
        this.jdbcDAO = new GenericJdbcDAO(properties);
    }

    public Session createSession() {
        return new Session(jdbcDAO);
    }
}
