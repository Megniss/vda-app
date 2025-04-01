import java.util.Scanner;

public class Main {
    private static final String GREEN = "\u001B[92m";
    private static final String BROWN = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader();
        System.out.print(GREEN + "Ievadi savu Lietotāja ID (max 10 burti): " + RESET);
        String userId = scanner.nextLine().trim();

        PlantManager manager = new PlantManager(userId);
        manager.loadPlants();

        while (true) {
            printMenu();
            System.out.print("Izvēlies darbību: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            clearConsole();
            printHeader();

            switch (choice) {
                case 1:
                    System.out.print("Ievadi auga nosaukumu: ");
                    String name = scanner.nextLine();
                    System.out.print("Ievadi laistīšanas intervālu (dienās): ");
                    int interval = scanner.nextInt();
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
                default:
                    System.out.println(BROWN + "Nepareiza izvēle, mēģini vēlreiz." + RESET);
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
}
