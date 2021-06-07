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
    protected String dbResourceName = "db";

    public static DaoFactory getInstance(String dbResourceName) {

        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                    daoFactory.dbResourceName = dbResourceName;
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao();

    public abstract CategoryDao createCategoryDao();

    public abstract ActivityDao createActivityDao();
}
