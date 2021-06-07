import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?sslMode=DISABLED&serverTimezone=UTC&user=root");
    }
}
