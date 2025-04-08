import java.util.Scanner;

public class Main {
    private static final String GREEN = "\u001B[92m";
    private static final String BROWN = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader();
        String userId = "";

        while (true) {
            System.out.print(GREEN + "Ievadi savu Lietotāja ID (max 10 simboli): " + RESET);
            userId = scanner.nextLine().trim();
            if (!userId.isEmpty() && userId.length() <= 10) break;
            System.out.println(BROWN + "Lietotāja ID nedrīkst būt tukšs un jābūt līdz 10 simboliem!" + RESET);
        }

        PlantManager manager = new PlantManager(userId);
        manager.loadPlants();

        while (true) {
            printMenu();
            int choice = getValidatedInt(scanner, "Izvēlies darbību (1-4): ", 1, 4);

            clearConsole();
            printHeader();

            switch (choice) {
                case 1:
                    String name = getValidatedName(scanner, "Ievadi auga nosaukumu (tikai burti): ");
                    int interval = getValidatedInt(scanner, "Ievadi laistīšanas intervālu (dienās): ", 1, Integer.MAX_VALUE);
                    manager.addPlant(name, interval);
                    break;
                case 2:
                    manager.checkWateringReminders();
                    break;
                case 3:
                    manager.generateCareTip();
                    break;
                case 4:
                    System.out.println("Saglabāju datus un izeju no programmas...");
                    manager.checkWateringReminders();
                    manager.savePlants();
                    scanner.close();
                    System.exit(0);
            }
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printHeader() {
        System.out.println(GREEN +
                "██╗   ██╗ ██████╗    █████╗ \n" +
                "██║   ██║ ██║   ██╗ ██╔══██╗\n" +
                "██║   ██║ ██║   ██║ ███████║\n" +
                "██║   ██║ ██║   ██║ ██╔══██║\n" +
                "╚██████╔╝ ████████║ ██║  ██║\n" +
                " ╚═════╝  ╚══════╝  ╚═╝  ╚═╝" + RESET);
        System.out.println("--------------------------------------------");
        System.out.println(GREEN + "     PALĪDZ DĀRZKOPĪBĀ, VIENMĒR PA ROKAI!" + RESET);
        System.out.println("--------------------------------------------\n");
    }

    private static void printMenu() {
        System.out.println(GREEN + "--------------------------------------------" + RESET);
        System.out.println("1. Pievienot augu");
        System.out.println("2. Pārbaudīt laistīšanas atgādinājumus");
        System.out.println("3. Ģenerēt kopšanas padomu");
        System.out.println("4. Iziet");
        System.out.println(GREEN + "--------------------------------------------" + RESET);
    }

    private static int getValidatedInt(Scanner scanner, String prompt, int min, int max) {
        int number;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine();
                if (number >= min && number <= max) {
                    break;
                } else {
                    System.out.println(BROWN + "Lūdzu, ievadi skaitli no " + min + " līdz " + max + "." + RESET);
                }
            } else {
                System.out.println(BROWN + "Tā nav derīga skaitliska ievade. Mēģini vēlreiz." + RESET);
                scanner.nextLine();
            }
        }
        return number;
    }

    private static String getValidatedName(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.matches("^[a-zA-ZāčēģīķļņōŗšūžĀČĒĢĪĶĻŅŌŖŠŪŽ\\s]+$")) {
                return input;
            } else {
                System.out.println(BROWN + "Auga nosaukumā drīkst būt tikai burti un atstarpes!" + RESET);
            }
        }
    }
}
