import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;

public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        Activity activity1 = Activity.createActivity("Soccer", Category.createCategory("Sport"));
        Activity activity2 = Activity.createActivity("Cut the grass", null);
        JDBCDaoFactory.getInstance().createActivityDao().create(activity2);
    }
}
