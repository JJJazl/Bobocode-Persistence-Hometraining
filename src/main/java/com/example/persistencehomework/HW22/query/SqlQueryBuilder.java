package com.example.persistencehomework.HW22.query;

import com.example.persistencehomework.HW22.annotation.Id;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SqlQueryBuilder {
    private static final String SELECT_BY_ID_SQL = "select * from %s where %s = ?";

    public static String buildSelectByIdQuery(Class<?> clazz) {
        Field idField = getIdField(clazz);
        String idColumnName = ParameterNameResolver.resolveColumnName(idField);
        String tableName = ParameterNameResolver.resolveTableName(clazz);
        return String.format(SELECT_BY_ID_SQL, tableName, idColumnName);
    }


    private static Field getIdField(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow();
    }
}
