import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.service.activityService.ActivitiesListService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        UserDao dao = JDBCDaoFactory.getInstance().createUserDao();
        User user = User.createUser("Roman", "Roman1");
        user.setId(2);

        for (Activity a:dao.findUsersActivities(user)) {
            System.out.println(a.getName());
        }
    }
}
