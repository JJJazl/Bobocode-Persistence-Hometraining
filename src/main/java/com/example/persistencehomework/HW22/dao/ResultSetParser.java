package com.example.persistencehomework.HW22.dao;

import com.example.persistencehomework.HW22.exception.ResultSetParseException;
import com.example.persistencehomework.HW22.query.ParameterNameResolver;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class ResultSetParser {

    public static void parseForEntity(Object entity, ResultSet resultSet) {
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                String columnName = ParameterNameResolver.resolveColumnName(field);

                field.setAccessible(true);
                field.set(entity, resultSet.getObject(columnName));
            }
        } catch (Exception e) {
            throw new ResultSetParseException(String.format(
                    "Error parsing result set for entity of type: %s", entity.getClass().getName()),
                    e
            );
        }
    }
}
