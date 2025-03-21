import java.io.*;
import java.util.*;

public class PlantManager {
    private List<Plant> plants = new ArrayList<>();
    private String userId;
    private static final String GREEN = "\u001B[92m";
    private static final String BROWN = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public PlantManager(String userId) {
        this.userId = userId;
    }

    public void addPlant(String name, int interval) {
        Plant plant = new Plant(name, interval);
        plants.add(plant);
        System.out.println(GREEN + "Augs pievienots: " + name + ", laistīt ik pēc " + interval + " dienām." + RESET);
    }

    public void checkWateringReminders() {
        Date now = new Date();
        System.out.println(GREEN + "--- Laistīšanas atgādinājumi ---" + RESET);
        for (Plant plant : plants) {
            if (plant.getNextWateringDate().before(now) || plant.getNextWateringDate().equals(now)) {
                System.out.println(BROWN + "Ir pienācis laiks laistīt: " + plant.getName() + RESET);
                plant.updateWateringDate();
            }
        }
    }

    public void generateCareTip() {
        System.out.println(GREEN + "--- Kopšanas padoms ---" + RESET);
        System.out.println(BROWN + CareTip.getRandomTip() + RESET);
    }

    public void savePlants() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userId + ".txt"))) {
            for (Plant plant : plants) {
                writer.write(plant.getName() + "," + plant.getNextWateringDate().getTime() + "," + plant.getWateringInterval());
                writer.newLine();
            }
            System.out.println(GREEN + "Dati saglabāti!" + RESET);
        } catch (IOException e) {
            System.out.println(BROWN + "Kļūda saglabājot datus." + RESET);
        }
    }

    public void loadPlants() {
        File file = new File(userId + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0];
                        long wateringTime = Long.parseLong(parts[1]);
                        int interval = Integer.parseInt(parts[2]);
                        Plant plant = new Plant(name, interval);
                        plant.setNextWateringDate(new Date(wateringTime));
                        plants.add(plant);
                    }
                }
                System.out.println(GREEN + "Dati ielādēti!" + RESET);
            } catch (IOException e) {
                System.out.println(BROWN + "Kļūda ielādējot datus." + RESET);
            }
        }
    }
}
