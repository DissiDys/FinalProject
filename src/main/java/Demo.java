import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;

import java.sql.Time;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        User user = User.createUser("Roman", "Roman1");
        Activity activity = Activity.createActivity("1", Category.createCategory("Learning"));

        user.setId(2);
        activity.setId(8);
        JDBCDaoFactory.getInstance().createUserDao().setActivitySpentTime(user, activity, new Time(12341));
        System.out.println(JDBCDaoFactory.getInstance().createUserDao().getActivitySpentTime(user, activity));
    }
}
