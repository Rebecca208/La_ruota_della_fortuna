import org.json.JSONArray;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
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
            var MANCHE = 3;
            int countGiocatore = 0;
            Giocatori.inserimentoGiocatori();

            for (int i = 0; i < MANCHE; i++) {
                boolean tabelloneIndovinato = false;
                Giocatori.azzeramentoManche();
                int randomIndex = (int) (Math.random() * arr.length());
                var tabellone = new Tabellone(arr.getJSONObject(randomIndex).getString("frase"), arr.getJSONObject(randomIndex).getString("argomento"));
                while (!tabelloneIndovinato){
                        if (countGiocatore == Giocatori.getSize()){
                            countGiocatore=0;
                        }
                        var giocatore = Giocatori.getGiocatore(countGiocatore);
                        System.out.println("Tocca a " + giocatore.toString());
                        Menu.menu(tabellone);
                        switch (Menu.scelta) {
                            case 1 -> {
                                String risultatoGiro = ruota.gira();
                                System.out.println("Ruota: " + risultatoGiro);
                                if (risultatoGiro.equals("Passa")) {
                                    System.out.println(giocatore.getNome() + " ha passato il turno.");
                                    countGiocatore++;
                                } else if (risultatoGiro.equals("Bancarotta")) {
                                    System.out.println(giocatore.getNome() + " ha perso tutto!");
                                    giocatore.setSalvadanaio(0);
                                    giocatore.setManche(0);
                                    countGiocatore++;
                                } else {
                                    int valore = Integer.parseInt(risultatoGiro);
                                    System.out.println("Chiama una lettera:");
                                    char lettera = ConsoleUtils.readCharFromConsole();
                                    if (ConsoleUtils.isVocale(lettera) && giocatore.getManche() >= 200) {
                                        tabellone.callLetter(lettera);
                                        giocatore.setManche(giocatore.getManche() - 200);
                                    } else if (ConsoleUtils.isVocale(lettera) && giocatore.getManche() < 200){
                                        System.out.println("Non hai abbastanza soldi per chiamare una vocale. Scegli una consonante cretino.");
                                    } else {
                                        tabellone.callLetter(lettera);
                                        System.out.println("Lettere trovate: " + tabellone.contaLettereTrovate(lettera));
                                        if (tabellone.contaLettereTrovate(lettera) == 0) {
                                            countGiocatore++;
                                        } else {
                                            giocatore.setManche(giocatore.getManche() + valore * tabellone.contaLettereTrovate(lettera));
                                        }

                                    }
                                }
                            }
                            case 2 -> {
                                System.out.println("Indovina la frase:");
                                Scanner scanner = new Scanner(System.in);
                                String fraseIndovinata = scanner.nextLine();
                                if (tabellone.IgnoreCaseEquals(fraseIndovinata)) {
                                    System.out.println(giocatore.getNome() + " ha indovinato la frase!");
                                    tabelloneIndovinato = true;
                                    giocatore.setSalvadanaio(giocatore.getSalvadanaio() + giocatore.getManche());
                                    // Set all letters to true to end the manche
                                    for (int w = 0; w < Tabellone.getFraseDaIndovinare().length(); w++) {
                                        tabellone.callLetter(Tabellone.getFraseDaIndovinare().charAt(w));
                                    }
                                } else {
                                    System.out.println("Frase errata!");
                                    countGiocatore++;
                                }
                            }
                            default -> {
                                System.out.println("Scelta non valida. Riprova.");
                            }
                        }
                        if (tabellone.fraseIndovinata()) {
                            break;
                        }
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
    }
}
