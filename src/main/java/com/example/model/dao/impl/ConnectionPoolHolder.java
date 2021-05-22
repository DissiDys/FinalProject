package com.example.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                BasicDataSource ds = new BasicDataSource();
                ds.setUrl("jdbc:mysql://localhost:3306/regform");
                ds.setUsername("root");
                ds.setPassword("Idalog71_7");
                ds.setMinIdle(5);
                ds.setMaxIdle(10);
                ds.setMaxOpenPreparedStatements(100);
                dataSource = ds;
            }
        }
        return dataSource;
    }
}
