package com.example.persistencehomework.HW22.session;

import com.example.persistencehomework.HW22.dao.GenericJdbcDAO;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private final GenericJdbcDAO jdbcDAO;
    private final Map<EntityKey<?>, Object> entitiesCache;

    public Session(GenericJdbcDAO jdbcDAO) {
        this.jdbcDAO = jdbcDAO;
        this.entitiesCache = new HashMap<>();
    }

    public <T> T findById(Class<T> clazz, Object id) {
        EntityKey<T> entityKey = new EntityKey<>(clazz, id);
        Object entity = entitiesCache.computeIfAbsent(entityKey, jdbcDAO::loadFromDB);
        return clazz.cast(entity);
    }

    public void close() {
        entitiesCache.clear();
    }
}
