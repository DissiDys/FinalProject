package com.example.model.dao.impl;

import com.example.controller.constants.SQLConstants;
import com.example.model.dao.ActivityDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.mapper.ActivityMapper;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCActivityDao implements ActivityDao {
    private final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private Connection connection;

    public JDBCActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Activity activity) throws NotUniqueInsertionException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.ADD_NEW_ACTIVITY, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            pstmt.setString(i++, activity.getName());
            if (activity.getCategory() != null) {
                try {
                    JDBCDaoFactory.getInstance().createCategoryDao().create(activity.getCategory());
                } catch (NotUniqueInsertionException e) {
                    logger.debug("Created activity with already existed category, activity.name: " + activity.getName());
                }
                pstmt.setString(i++, String.valueOf(activity.getCategory().getId()));
            } else {
                pstmt.setString(i++, "4");
            }
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    activity.setId(rs.getLong(1));
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
    public Optional<Activity> findById(int id) {
        for (Activity a : findAll()) {
            if (a.getId() == id) return Optional.of(a);
        }
        return Optional.empty();
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(SQLConstants.FIND_ALL_ACTIVITIES)
        ) {
            ActivityMapper activityMapper = new ActivityMapper();
            while (resultSet.next()) {
                Activity activity = activityMapper.extractFromResultSet(resultSet);
                if (activity != null) {
                    activities.add(activity);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return activities;
    }

    @Override
    public void update(Activity entity) {

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.DELETE_ACTIVITY_BY_ID)) {
            Optional<Activity> optional = findById(id);
            if (optional.isPresent()) {
                String name = findById(id).get().getName();
                pstmt.setString(1, String.valueOf(id));
                pstmt.executeUpdate();
                logger.info("Activity with name: " + name + " was deleted");
            }
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
