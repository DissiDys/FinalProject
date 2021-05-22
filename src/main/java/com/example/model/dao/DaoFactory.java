package com.example.model.dao;

import com.example.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                daoFactory = new JDBCDaoFactory();

            }
        }
        return daoFactory;
    }
}
