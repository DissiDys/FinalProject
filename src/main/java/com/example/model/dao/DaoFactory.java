package com.example.model.dao;

import com.example.controller.constants.SQLConstants;
import com.example.model.dao.impl.ConnectionPoolHolder;
import com.example.model.dao.impl.JDBCDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao(String dbName);

    public abstract CategoryDao createCategoryDao(String dbName);

    public abstract ActivityDao createActivityDao(String dbName);
}
