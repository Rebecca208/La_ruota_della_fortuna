import java.util.Scanner;

public class Menu {
    int scelta;
    //menu di gioco
    public int menu() {
        System.out.println("Il tuo turno!");
        System.out.println("1. Gira la ruota");
        System.out.println("2. Chiama vocale (costa 200)");
        System.out.println("3. Indovina la frase");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Scegli un'opzione: ");
        scelta = scanner.nextInt();
        return scelta;
    }
}
