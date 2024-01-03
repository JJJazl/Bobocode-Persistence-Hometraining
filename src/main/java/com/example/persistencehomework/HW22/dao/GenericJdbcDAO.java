package com.example.persistencehomework.HW22.dao;

import com.example.persistencehomework.HW22.exception.DaoOperationException;
import com.example.persistencehomework.HW22.query.SqlQueryBuilder;
import com.example.persistencehomework.HW22.session.EntityKey;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericJdbcDAO {

    private final DataSource dataSource;

    public GenericJdbcDAO(Properties properties) {
        this.dataSource = initDataSource(properties);
    }

    public <T> T loadFromDB(EntityKey<T> entityKey) {
        try (Connection connection = dataSource.getConnection()) {
            return load(entityKey, connection);
        } catch (SQLException e) {
            throw new DaoOperationException(String.format(
                    "Error loading entity from the DB: %s", entityKey.clazz().getName()),
                    e
            );
        }
    }

    private <T> T load(EntityKey<T> entityKey, Connection connection) throws SQLException {
        PreparedStatement selectByIdStatement = prepareSelectStatement(entityKey, connection);
        ResultSet resultSet = selectByIdStatement.executeQuery();
        if (resultSet.next()) {
            return createEntityFromResultSet(entityKey, resultSet);
        }
        return null;
    }

    private PreparedStatement prepareSelectStatement(EntityKey<?> entityKey, Connection connection) {
        try {
            String selectQuery = SqlQueryBuilder.buildSelectByIdQuery(entityKey.clazz());

            PreparedStatement selectByIdStatement = connection.prepareStatement(selectQuery);
            selectByIdStatement.setObject(1, entityKey.id());
            return selectByIdStatement;
        } catch (SQLException e) {
            throw new DaoOperationException(String.format(
                    "Error preparing select statement for entity: %s", entityKey.clazz().getName()),
                    e
            );
        }
    }

    private <T> T createEntityFromResultSet(EntityKey<T> entityKey, ResultSet resultSet) {
        try {
            T entity = entityKey.clazz().getConstructor().newInstance();
            ResultSetParser.parseForEntity(entity, resultSet);
            return entity;
        } catch (Exception e) {
            throw new DaoOperationException(String.format(
                    "Error creating entity from result set: %s", entityKey.clazz().getName()),
                    e
            );
        }
    }

    private DataSource initDataSource(Properties properties) {
        PGSimpleDataSource simpleDataSource = new PGSimpleDataSource();
        simpleDataSource.setURL(properties.url());
        simpleDataSource.setUser(properties.user());
        simpleDataSource.setPassword(properties.password());

        return simpleDataSource;
    }
}
