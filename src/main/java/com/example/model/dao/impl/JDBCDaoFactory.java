package com.example.model.dao.impl;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource;

    @Override
    public UserDao createUserDao(String dbName) {
        return new JDBCUserDao(getConnection(dbName));
    }

    @Override
    public CategoryDao createCategoryDao(String dbName) {
        return new JDBCCategoryDao(getConnection(dbName));
    }

    @Override
    public ActivityDao createActivityDao(String dbName) {
        return new JDBCActivityDao(getConnection(dbName));
    }

    private Connection getConnection(String dbName) {
        dataSource = ConnectionPoolHolder.getDataSource(dbName);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Couldn`t get connection");
            throw new RuntimeException(e);
        }
    }
}
