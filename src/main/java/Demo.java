import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        for (Activity a : JDBCDaoFactory.getInstance().createActivityDao().findAll()) {
            System.out.println(a.getId() + " " + a.getName() + " " + a.getCategory().getName());
        }
    }
}
