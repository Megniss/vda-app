import java.util.*;

public class PlantManager {
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(String name, int interval) {
        Plant plant = new Plant(name, interval);
        plants.add(plant);
        System.out.println("Augs pievienots: " + name + ", laistīt ik pēc " + interval + " dienām.");
    }

    public void checkWateringReminders() {
        Date now = new Date();
        System.out.println("--- Laistīšanas atgādinājumi ---");
        for (Plant plant : plants) {
            if (plant.getNextWateringDate().before(now) || plant.getNextWateringDate().equals(now)) {
                System.out.println("Ir pienācis laiks laistīt: " + plant.getName());
                plant.updateWateringDate();
            }
        }
    }

    public void generateCareTip() {
        System.out.println("--- Kopšanas padoms ---");
        System.out.println(CareTip.getRandomTip());
    }
}
