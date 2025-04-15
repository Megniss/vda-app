import java.io.*;
import java.text.SimpleDateFormat;
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
        saveReminder(plant);
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
                saveReminder(plant);
            } else {
                System.out.println(BROWN + "Augs: " + plant.getName() + " - jālaista pēc " + daysUntilWatering + " dienām." + RESET);
            }
        }
    }

    public void savePlants() {
        try {
            File dir = new File("userdata");
            if (!dir.exists()) dir.mkdirs();

            File plantFile = new File("userdata/plants_" + userId + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(plantFile))) {
                for (Plant plant : plants) {
                    writer.write(plant.getName() + "," + plant.getWateringInterval() + "," + plant.getNextWateringDate().getTime());
                    writer.newLine();
                }
                System.out.println(GREEN + "Augu dati saglabāti!" + RESET);
            }
        } catch (IOException e) {
            System.out.println(BROWN + "Kļūda saglabājot augu datus." + RESET);
        }
    }

    private void saveReminder(Plant plant) {
        try {
            File file = new File("userdata/reminders_" + userId + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                writer.write(plant.getName() + ": " + sdf.format(plant.getNextWateringDate()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(BROWN + "Neizdevās saglabāt atgādinājumu." + RESET);
        }
    }

    public void loadPlants() {
        File file = new File("userdata/plants_" + userId + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0];
                        int interval = Integer.parseInt(parts[1]);
                        long dateMillis = Long.parseLong(parts[2]);
                        Date nextWatering = new Date(dateMillis);
                        Plant plant = new Plant(name, interval);
                        plant.setNextWateringDate(nextWatering);
                        plants.add(plant);
                    }
                }
                System.out.println(GREEN + "Augu dati ielādēti!" + RESET);
            } catch (IOException e) {
                System.out.println(BROWN + "Kļūda ielādējot augu datus." + RESET);
            }
        }
    }

    public void viewDataTables(Scanner scanner) {
        while (true) {
            System.out.println(GREEN + "\n--- Datu tabulu apskate ---" + RESET);
            System.out.println("1. Augi");
            System.out.println("2. Lietotāji");
            System.out.println("3. Laistīšanas atgādinājumi");
            System.out.println("4. Atpakaļ");
            System.out.print("Izvēlies tabulu: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    printPlantsTable();
                    break;
                case "2":
                    printFile("userdata/users.txt", "Lietotāju tabula", "Lietotājs, Parole");
                    break;
                case "3":
                    printFile("userdata/reminders_" + userId + ".txt", "Laistīšanas atgādinājumi", "Augs: Datums");
                    break;
                case "4":
                    return;
                default:
                    System.out.println(BROWN + "Nederīga izvēle. Mēģini vēlreiz." + RESET);
            }
        }
    }

    private void printPlantsTable() {
        System.out.println(GREEN + "--- Augu tabula ---" + RESET);
        if (plants.isEmpty()) {
            System.out.println(BROWN + "Nav neviena auga." + RESET);
        } else {
            System.out.printf("%-20s %-20s %-25s\n", "Auga nosaukums", "Intervāls (dienas)", "Jālaista pēc (dienām)");
            System.out.println("-----------------------------------------------------------------------");
            for (Plant plant : plants) {
                System.out.printf("%-20s %-20d %-25d\n",
                        plant.getName(),
                        plant.getWateringInterval(),
                        plant.getDaysUntilWatering());
            }
        }
    }

    private void printFile(String filePath, String title, String header) {
        System.out.println(GREEN + "--- " + title + " ---" + RESET);
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(BROWN + "Fails '" + filePath + "' neeksistē." + RESET);
            return;
        }

        System.out.println(BROWN + header + RESET);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println(BROWN + line + RESET);
                empty = false;
            }
            if (empty) {
                System.out.println(BROWN + "Tabula ir tukša." + RESET);
            }
        } catch (IOException e) {
            System.out.println(BROWN + "Kļūda lasot failu." + RESET);
        }
    }
}
