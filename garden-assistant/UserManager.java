import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, UserData> users = new HashMap<>();

    public static boolean validateUser(String userId, String password) {
        UserData user = users.get(userId);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean createUser(String userId, String password) {
        if (users.containsKey(userId)) {
            return false;  // Lietotājvārds jau aizņemts
        }
        UserData newUser = new UserData(userId, password);
        users.put(userId, newUser);
        return true;
    }

    public static UserData getUserData(String userId) {
        return users.get(userId);
    }

    public static void saveUserData(String userId) {
        UserData user = users.get(userId);
        if (user != null) {
            user.displayUserData();  // Parāda lietotāja datus tabulā
        }
    }
}
