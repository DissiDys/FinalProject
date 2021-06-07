package daoImpl.test;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.ConnectionPoolHolder;
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

public class CategoryDaoImplTest {
    static DaoFactory daoFactory = DaoFactory.getInstance("dbTest");

    static CategoryDao categoryDao;

    private static final String RELOAD_DB_SQL_FILE
            = "C:\\Users\\Roman\\IdeaProjects\\EPAM\\FinalProject\\sql\\drop-testDB.sql";

    @BeforeEach
    void dropDB() throws SQLException, FileNotFoundException {
        categoryDao = daoFactory.createCategoryDao();

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
        assertEquals(category, categoryDao.findById((int) category.getId()).orElse(Category.createCategory("")));
    }

    @Test
    void testDelete() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        categoryDao.create(category);
        categoryDao.delete((int) category.getId());
        assertEquals(0, categoryDao.findAll().size());
    }

    @Test
    void testNotUniqueException() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        Category category1 = Category.createCategory("1");
        categoryDao.create(category);

        assertThrows(NotUniqueInsertionException.class, () -> categoryDao.create(category1));
    }

    @Test
    void testFindAll() throws NotUniqueInsertionException {
        Category category = Category.createCategory("1");
        Category category1 = Category.createCategory("2");
        categoryDao.create(category);
        categoryDao.create(category1);

        assertEquals(category1, categoryDao.findAll().get(1));
    }

    @AfterEach
    void closeConnection() {
        categoryDao.close();
    }
}
