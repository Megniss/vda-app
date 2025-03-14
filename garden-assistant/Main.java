import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlantManager manager = new PlantManager();

        while (true) {
            System.out.println("\n1. Pievienot augu");
            System.out.println("2. Pārbaudīt laistīšanas atgādinājumus");
            System.out.println("3. Ģenerēt kopšanas padomu");
            System.out.println("4. Iziet");
            System.out.print("Izvēlies darbību: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
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
                    System.out.println("Programma tiek izslēgta...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Nepareiza izvēle, mēģini vēlreiz.");
            }
        }
    }
}
