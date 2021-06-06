package daoImpl.test;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.ConnectionPoolHolder;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
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

public class ActivityDaoImplTest {
    static DaoFactory daoFactory = DaoFactory.getInstance();


     CategoryDao categoryDao;
     ActivityDao activityDao;

    private static final String RELOAD_DB_SQL_FILE
            = "C:\\Users\\Roman\\IdeaProjects\\EPAM\\FinalProject\\sql\\drop-testDB.sql";

    @BeforeEach
    void dropDB() throws SQLException, FileNotFoundException {
        categoryDao = daoFactory.createCategoryDao("timetracktest");
        activityDao = daoFactory.createActivityDao("timetracktest");

        Connection con = ConnectionPoolHolder.getDataSource("timetracktest").getConnection();
        ScriptRunner scriptRunner = new ScriptRunner(con);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(RELOAD_DB_SQL_FILE));
        scriptRunner.runScript(bufferedReader);
        con.close();
    }

    @Test
    void testCreate() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        categoryDao.create(category);
        Activity activity = Activity.createActivity("1", category);
        activityDao.create(activity);
        assertEquals(activity, activityDao.findById((int) activity.getId()).orElse(Activity.createActivity("", null)));
    }

    @Test
    void testDelete() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        categoryDao.create(category);
        Activity activity = Activity.createActivity("1", category);
        activityDao.create(activity);
        activityDao.delete((int) activity.getId());
        assertEquals(0, activityDao.findAll().size());
    }

    @Test
    void testNotUniqueException() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("1", category);
        activityDao.create(activity);

        Activity activity1 = Activity.createActivity("1", category);
        assertThrows(NotUniqueInsertionException.class, () -> activityDao.create(activity1));
    }

    @Test
    void testFindAll() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        categoryDao.create(category);

        Activity activity = Activity.createActivity("1", category);
        activityDao.create(activity);

        Activity activity1 = Activity.createActivity("2", category);
        activityDao.create(activity1);

        assertEquals(activity1, activityDao.findAll().get(1));
    }

    @AfterEach
    void closeConnection() {
        categoryDao.close();
        activityDao.close();
    }
}
