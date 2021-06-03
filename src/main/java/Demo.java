import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.sql.Time;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        System.out.println(125 % 60);
    }
}
