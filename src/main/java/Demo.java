import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.User;


public class Demo {
    public static void main(String[] args) throws NotUniqueInsertionException {
        User user = User.createUser("login", "password");
        System.out.println(user);
    }
}
