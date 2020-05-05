package com.epam.borysenko.handler;

import com.epam.borysenko.annotation.Column;
import org.apache.commons.dbutils.ResultSetHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

class DefaultRowResultSetHandler<T> implements ResultSetHandler<T> {

    private final Class<T> classEntity;

    DefaultRowResultSetHandler(Class<T> classEntity) {
        this.classEntity = classEntity;
    }

    public T handle(ResultSet rs) throws SQLException {
        try {
            Constructor constructor = classEntity.getConstructor();
            T entity = (T) constructor.newInstance();
            Field[] fields = classEntity.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                String columnName = field.getName();
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    columnName = columnAnnotation.value();
                }
                Object value = rs.getObject(columnName);
                if (field.getType() == Boolean.class) {
                    value = !value.equals(0);
                }
                field.set(entity, value);
                field.setAccessible(false);
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
