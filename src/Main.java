import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Main {
    public static void main(String[] args) {
        try {
            String inputPath = "input.json";
            if (!Files.exists(Path.of(inputPath))) {
                System.out.println("File 'input.json' non trovato nella cartella del progetto. Creane uno di esempio o copia l'input richiesto.");
                return;
            }

            JSONArray arr = new JSONArray(Files.readString(Path.of(inputPath)));
            var ruota = new Ruota();
            var Giocatori = new ListaGiocatori();
            var MANCHE = 1;

            Giocatori.addGiocatore("Rebecca");
            Giocatori.addGiocatore("Andrea");
            Giocatori.addGiocatore("Mario");



            for (int i = 0; i < MANCHE; i++) {
                //random
                int randomIndex = (int) (Math.random() * arr.length());
                var tabellone = new Tabellone(arr.getJSONObject(randomIndex).getString("frase"), arr.getJSONObject(randomIndex).getString("argomento"));
                while (!tabellone.fraseIndovinata()){
                    System.out.println(Tabellone.getFraseDaIndovinare());
                    System.out.println("Frase segreta: " + tabellone.getFraseSegreta());
                    System.out.println("Argomento: " + arr.getJSONObject(randomIndex).getString("argomento"));
                    for (int j = 0; j < Giocatori.Giocatori.size(); j++) {
                        var giocatore = Giocatori.getGiocatore(j);
                        System.out.println("Tocca a " + giocatore.getNome());
                        Menu.menu();
                        switch (Menu.scelta) {
                            case 1 -> {
                                String risultatoGiro = ruota.gira();
                                System.out.println("Ruota: " + risultatoGiro);
                                if (risultatoGiro.equals("Passa")) {
                                    System.out.println(giocatore.getNome() + " ha passato il turno.");
                                } else if (risultatoGiro.equals("Bancarotta")) {
                                    System.out.println(giocatore.getNome() + " ha perso tutto!");
                                    giocatore.setSalvadanaio(0);
                                } else {
                                    int valore = Integer.parseInt(risultatoGiro);
                                    System.out.println("Chiama una lettera:");
                                    char lettera = ConsoleUtils.readCharFromConsole();
                                    if (ConsoleUtils.isVocale(lettera) && giocatore.getManche() >= 200) {
                                        tabellone.callLetter(lettera);
                                        giocatore.setManche(giocatore.getManche() - 200);
                                        if (tabellone.contaLettereTrovate(lettera) != 0) {
                                            j--;
                                        }
                                    } else if (ConsoleUtils.isVocale(lettera) && giocatore.getManche() < 200){
                                        System.out.println("Non hai abbastanza soldi per chiamare una vocale. Scegli una consonante cretino.");
                                    } else {
                                        tabellone.callLetter(lettera);
                                        System.out.println("Lettere trovate: " + tabellone.contaLettereTrovate(lettera));
                                        giocatore.setManche(giocatore.getManche() + valore * tabellone.contaLettereTrovate(lettera));
                                        if (tabellone.contaLettereTrovate(lettera) != 0) {
                                            j--;
                                        }
                                    }
                                    System.out.println("Frase segreta: " + tabellone.getFraseSegreta());
                                }
                            }
                            case 2 -> {
                                System.out.println("Indovina la frase:");
                                Scanner scanner = new Scanner(System.in);
                                String fraseIndovinata = scanner.nextLine();
                                if (Tabellone.IgnoreCaseEquals(fraseIndovinata)) {
                                    System.out.println(giocatore.getNome() + " ha indovinato la frase!");
                                    giocatore.setSalvadanaio(giocatore.getSalvadanaio() + giocatore.getManche());
                                    tabellone.IgnoreCaseEquals(tabellone.getFraseSegreta());
                                    j = Giocatori.Giocatori.size(); // Esce dal ciclo dei giocatori per passare alla prossima manche
                                } else {
                                    System.out.println("Frase errata!");
                                }
                            }
                            default -> {
                                System.out.println("Scelta non valida. Riprova.");
                                j--;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
    }
}
