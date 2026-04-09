import java.util.Scanner;

public class Menu {
    static int scelta;
    private static final Scanner scanner = new Scanner(System.in);

    public static int menu() {
        while (true) {
            System.out.println("Il tuo turno!");
            System.out.println("1. Gira la ruota e chiama");
            System.out.println("2. Indovina la frase");
            System.out.print("Scegli un'opzione: ");

            String line = scanner.nextLine();
            try {
                scelta = Integer.parseInt(line.trim());
                if (scelta == 1 || scelta == 2) {
                    return scelta;
                } else {
                    System.out.println("Inserisci 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserire un numero.");
            }
        }
    }
}
