import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String userId;
    private String password;
    private List<Plant> plants;

    public UserData(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.plants = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addPlant(String name, int wateringInterval) {
        plants.add(new Plant(name, wateringInterval));
    }

    public void displayUserData() {
        System.out.println("------------------------------------------------");
        System.out.println("Lietotājvārds: " + userId);
        System.out.println("Parole: " + password);  // Paroles parādīšana tabulā (var šifrēt, ja vajag)
        System.out.println("------------------------------------------------");

        System.out.println("Augi un laistīšanas intervāli:");
        System.out.println(String.format("%-20s %-15s %-20s", "Auga Nosaukums", "Laistīšanas Intervāls", "Nākamais laistīšanas datums"));
        for (Plant plant : plants) {
            System.out.println(String.format("%-20s %-15d %-20s", plant.getName(), plant.getWateringInterval(), plant.getNextWateringDate()));
        }
    }
}
