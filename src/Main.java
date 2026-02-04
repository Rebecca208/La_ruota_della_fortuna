import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;

void main() throws IOException {
    JSONArray arr = new JSONArray(Files.readString(Path.of("input.json")));
    var ruota = new Ruota();
    var Giocatori = new ListaGiocatori();

//    for(int i = 0; i < 3; i++) {
//        System.out.println("Inserisci il nome del " + i + "° giocatore");
//        Scanner input = new Scanner(System.in);
//        Giocatori.addGiocatore(input.nextLine());
//    }
    for (var i = 0; i < 3; i++) {
        Giocatori.addGiocatore("Rebecca" + i);
    }

    for (int i = 0; i < 5; i++) {
        System.out.println("Manche " + i);
        int nRandom = (int) (Math.random() * (arr.length()));
        var obj = arr.getJSONObject(nRandom);
        var tb = new Tabellone(obj.getString("frase"), obj.getString("argomento"));
        System.out.println(tb.getFraseSegreta());
        System.out.println("Argomento: " + tb.getArg());

        var Giocatore1 = Giocatori.getGiocatore(1);
        var ruotaSpicchio = ruota.gira();
        System.out.println(ruotaSpicchio);
        if (ruotaSpicchio.equals("Passa")){
            //Prossimo Giocatore
        } else if (ruotaSpicchio.equals("Bancarotta")){
            Giocatore1.setManche(0);
            Giocatore1.setSalvadanaio(0);
        } else {
            Giocatore1.setManche(Integer.parseInt(ruotaSpicchio));
        }
    }
}
