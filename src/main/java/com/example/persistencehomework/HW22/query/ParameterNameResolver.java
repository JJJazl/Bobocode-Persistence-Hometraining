package com.example.persistencehomework.HW22.query;

import com.example.persistencehomework.HW22.annotation.Column;
import com.example.persistencehomework.HW22.annotation.Table;

import java.lang.reflect.Field;

public class ParameterNameResolver {

    public static String resolveTableName(Class<?> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            return clazz.getSimpleName();
        }
        return tableAnnotation.name().isEmpty()
                ? clazz.getSimpleName()
                : tableAnnotation.name();
    }

    public static String resolveColumnName(Field field) {
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation == null) {
            return field.getName();
        }
        return columnAnnotation.name().isEmpty()
                ? field.getName()
                : columnAnnotation.name();
    }
}
