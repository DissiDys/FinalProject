package com.example.model.dao.impl;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.mapper.ActivityMapper;
import com.example.model.dao.mapper.UserMapper;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.controller.constants.SQLConstants;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(User user) throws NotUniqueInsertionException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            pstmt.setString(i++, user.getLogin());
            pstmt.setString(i++, user.getPassword());
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
                res = true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NotUniqueInsertionException();
        }
        return res;
    }

    @Override
    public void setActivityForUser(User user, Activity activity) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.SET_ACTIVITY_FOR_USER)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            try {
                create(user);
            } catch (NotUniqueInsertionException e) {
            }

            try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
                dao.create(activity);
            } catch (NotUniqueInsertionException e) {
            }

            int i = 1;

            pstmt.setLong(i++, user.getId());
            pstmt.setLong(i++, activity.getId());
            pstmt.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Activity> findUsersActivities(User user) {
        List<Activity> activities = new ArrayList<>();

        try (
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(SQLConstants.FIND_USERS_ACTIVITIES + user.getId());
        ) {
            ActivityMapper activityMapper = new ActivityMapper();

            while (resultSet.next()) {
                activities.add(activityMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return activities;
    }

    @Override
    public Time getActivitySpentTime(User user, Activity activity) {
        Time time = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQLConstants.FIND_TIME_SPENT);
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, String.valueOf(activity.getId()));
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                time = resultSet.getTime("time_spent");
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return time;
    }

    @Override
    public void setActivitySpentTime(User user, Activity activity, Time time) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.SET_TIME_SPENT)) {
            pstmt.setString(1, String.valueOf(time));
            pstmt.setString(2, String.valueOf(user.getId()));
            pstmt.setString(3, String.valueOf(activity.getId()));
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void setUnconfirmedActivityForUser(User user, Activity activity) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.SET_UNCONFIRMED_ACTIVITY_FOR_USER)) {
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, String.valueOf(activity.getId()));
            pstmt.executeUpdate();
            logger.info("request to add activity with id " + activity.getId() + " for user with id " + user.getId());
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Activity> getUnconfirmedActivitiesForUser(User user) {
        List<Activity> activities = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(SQLConstants.FIND_UNCONFIRMED_ACTIVITIES_FOR_USER);
            pstmt.setString(1, String.valueOf(user.getId()));
            ResultSet resultSet = pstmt.executeQuery();

            ActivityMapper activityMapper = new ActivityMapper();
            while (resultSet.next()){
                activities.add(activityMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return activities;
    }

    @Override
    public void deleteUnconfirmedActivityForUser(User user, Activity activity) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.DELETE_UNCONFIRMED_ACTIVITY_FOR_USER)) {
            pstmt.setString(1, String.valueOf(user.getId()));
            pstmt.setString(2, String.valueOf(activity.getId()));
            pstmt.executeUpdate();
            logger.info("was deleted unconfirmed activity with id " + activity.getId() + " for user with id " + user.getId());
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }


    @Override
    public User findById(int id) {
        for (User u : findAll()) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(SQLConstants.FIND_ALL_USERS)
        ) {
            UserMapper userMapper = new UserMapper();

            while (resultSet.next()) {
                users.add(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return users;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.DELETE_USER_BY_ID)) {
            String login = findById(id).getLogin();
            pstmt.setString(1, String.valueOf(id));
            pstmt.executeUpdate();
            logger.info("User with login: " + login + " was deleted");
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            logger.error("Connection close error");
        }
    }
}
