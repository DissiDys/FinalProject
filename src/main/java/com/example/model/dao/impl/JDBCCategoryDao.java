package com.example.model.dao.impl;

import com.example.controller.constants.SQLConstants;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.mapper.CategoryMapper;
import com.example.model.entity.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCategoryDao implements CategoryDao {
    private final Logger logger = LogManager.getLogger(JDBCUserDao.class);
    private Connection connection;

    public JDBCCategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Category category) throws NotUniqueInsertionException {
        boolean res = false;
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.ADD_NEW_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            pstmt.setString(i++, category.getName());
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    category.setId(rs.getLong(1));
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
    public Category findById(int id) {
        for (Category c : findAll()) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(SQLConstants.FIND_ALL_CATEGORIES)
        ) {
            CategoryMapper categoryMapper = new CategoryMapper();

            while (resultSet.next()) {
                categories.add(categoryMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return categories;
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQLConstants.DELETE_CATEGORY_BY_ID)) {
            String name = findById(id).getName();
            pstmt.setString(1, String.valueOf(id));
            pstmt.executeUpdate();
            logger.info("Category with name: " + name + " was deleted");
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() {

    }
}
