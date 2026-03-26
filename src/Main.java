import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
            Giocatori.addGiocatore("Gabriele");
            Giocatori.addGiocatore("Luca");



            for (int i = 0; i < MANCHE; i++) {
                //random
                int randomIndex = (int) (Math.random() * arr.length());
                var tabellone = new Tabellone(arr.getJSONObject(randomIndex).getString("frase"), arr.getJSONObject(randomIndex).getString("argomento"));
                System.out.println("Frase segreta: " + tabellone.getFraseSegreta());
                System.out.println("Argomento: " + arr.getJSONObject(randomIndex).getString("argomento"));

                while (!tabellone.fraseIndovinata()){
                    for (int j = 0; j < Giocatori.Giocatori.size(); j++) {
                        var giocatore = Giocatori.getGiocatore(j);
                        System.out.println("Tocca a " + giocatore.getNome());
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
                            if ((lettera == 'a' || lettera == 'e' || lettera == 'i' || lettera == 'o' || lettera == 'u') && giocatore.getManche() >= 200) {
                                tabellone.callLetter(lettera);
                                giocatore.setManche(giocatore.getManche() - 200);
                                if (tabellone.contaLettereTrovate(lettera) != 0) {
                                    j--;
                                }
                            } else if ((lettera == 'a' || lettera == 'e' || lettera == 'i' || lettera == 'o' || lettera == 'u') && giocatore.getManche() < 200){
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
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura file: " + e.getMessage());
        }
    }
}
