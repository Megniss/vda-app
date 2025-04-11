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

    public void generateCareTip() {
        System.out.println(GREEN + "--- Kopšanas padoms ---" + RESET);
        System.out.println(BROWN + CareTip.getRandomTip() + RESET);
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
            int daysUntilWatering = plant.getDaysUntilWatering();
            if (daysUntilWatering <= 0) {
                System.out.println(BROWN + "Ir pienācis laiks laistīt: " + plant.getName() + RESET);
                plant.updateWateringDate();
            } else {
                System.out.println(BROWN + plant.getName() + " - Augs ir jāaplaista pēc " + daysUntilWatering + " dienām." + RESET);
            }
        }
    }

    public void savePlants() {
        try {
            File dir = new File("userdata");
            if (!dir.exists()) dir.mkdirs(); // <-- Izveido mapi, ja tās nav

            File file = new File("userdata/" + userId + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Plant plant : plants) {
                    writer.write(plant.getName() + "," + plant.getWateringInterval());
                    writer.newLine();
                }
                System.out.println(GREEN + "Dati saglabāti!" + RESET);
            }
        } catch (IOException e) {
            System.out.println(BROWN + "Kļūda saglabājot datus." + RESET);
            e.printStackTrace(); // opcija: pagaidu debug
        }
    }

    public void loadPlants() {
        File file = new File("userdata/" + userId + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int interval = Integer.parseInt(parts[1].replaceAll("\\D+", ""));
                        Plant plant = new Plant(name, interval);
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
