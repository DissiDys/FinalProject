package daoImpl.test;


import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.ConnectionPoolHolder;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoImplTest {
    static DaoFactory daoFactory = DaoFactory.getInstance("dbTest");

    static UserDao userDao;
    static CategoryDao categoryDao;
    static ActivityDao activityDao;

    private static final String RELOAD_DB_SQL_FILE
            = "C:\\Users\\Roman\\IdeaProjects\\EPAM\\FinalProject\\sql\\drop-testDB.sql";

    @BeforeEach
    void dropDB() throws SQLException, FileNotFoundException {
        userDao = daoFactory.createUserDao();
        categoryDao = daoFactory.createCategoryDao();
        activityDao = daoFactory.createActivityDao();

        Connection con = ConnectionPoolHolder.getDataSource("timetracktest").getConnection();
        ScriptRunner scriptRunner = new ScriptRunner(con);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(RELOAD_DB_SQL_FILE));
        scriptRunner.runScript(bufferedReader);
        con.close();
    }

    @Test
    void testCreateUser() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");

        userDao.create(user);
        User u = userDao.findById((int) user.getId()).orElse(User.createUser("", ""));
        assertEquals(u, user);
    }

    @Test
    void testNotUniqueInsertionException() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        User user1 = User.createUser("login", "password");

        userDao.create(user1);
        assertThrows(NotUniqueInsertionException.class, () -> userDao.create(user));
    }

    @Test
    void testSetActivity() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        userDao.create(user);

        Category category = Category.createCategory("test");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("test1", category);
        activityDao.create(activity);

        userDao.setActivityForUser(user, activity);

        assertEquals(activity, userDao.findUsersActivities(user).get(0));
    }

    @Test
    void testSetTimeForActivity() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        userDao.create(user);

        Category category = Category.createCategory("test");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("test1", category);
        activityDao.create(activity);

        userDao.setActivityForUser(user, activity);
        userDao.setActivitySpentTime(user, activity, 5);

        assertEquals(userDao.getActivitySpentTime(user, activity), 5);
    }

    @Test
    void testSetUnconfirmedActivity() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        userDao.create(user);

        Category category = Category.createCategory("test");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("test1", category);
        activityDao.create(activity);

        userDao.setUnconfirmedActivityForUser(user, activity, Operation.ADD);

        assertEquals(userDao.getUnconfirmedActivitiesForUser(user).get(0), activity);
    }

    @Test
    void testGetUnconfirmedActivityOpperation() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        userDao.create(user);

        Category category = Category.createCategory("test");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("test1", category);
        activityDao.create(activity);

        userDao.setUnconfirmedActivityForUser(user, activity, Operation.ADD);

        assertEquals(userDao.getOperationForUserUnconfirmedActivity(user, activity).get(), Operation.ADD);
    }

    @Test
    void testEquals() throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        userDao.create(user);

        assertEquals(user.toString(), "User{login='login', password='password'}");
    }

    @Test
    void testDelete() throws NotUniqueInsertionException {
        User user = User.createUser("1", "2");
        userDao.create(user);
        userDao.delete((int) user.getId());
        assertEquals(0, userDao.findAll().size());
    }


    @AfterEach
    void closeConnection() {
        userDao.close();
        categoryDao.close();
        activityDao.close();
    }
}
