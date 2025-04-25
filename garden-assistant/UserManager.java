import java.io.*;

public class UserManager {
    private static final String FILE_PATH = "userdata/users.txt";

    static {
        try {
            File userFile = new File(FILE_PATH);
            userFile.getParentFile().mkdirs();
            if (!userFile.exists()) {
                userFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Neizdevās izveidot lietotāju failu.");
        }
    }

    public static boolean validateUser(String userId, String password) {
        userId = userId.trim();
        password = password.trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2) {
                    String storedUser = parts[0].trim();
                    String storedPass = parts[1].trim();
                    if (storedUser.equals(userId) && storedPass.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Kļūda validējot lietotāju.");
        }
        return false;
    }

    public static boolean createUser(String userId, String password) {
        userId = userId.trim();
        password = password.trim();

        if (userExists(userId)) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(userId + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Neizdevās izveidot kontu.");
            return false;
        }
    }

    public static boolean userExists(String userId) {
        userId = userId.trim();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length >= 1 && parts[0].trim().equals(userId)) {
                    return true;
                }
            }
        } catch (IOException ignored) {}
        return false;
    }

    public static void printUsers() {
        System.out.println("\u001B[92m--- Lietotāju tabula ---\u001B[0m");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2) {
                    System.out.println("Lietotājs: " + parts[0].trim() + " | Parole: " + parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Neizdevās nolasīt lietotāju failu.");
        }
    }
}
