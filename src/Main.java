import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;

void main() throws IOException {
    JSONArray arr = new JSONArray(Files.readString(Path.of("input.json")));
    int nRandom = (int) (Math.random() * (arr.length() + 1));
    var ruota = new Ruota();

    var obj = arr.getJSONObject(nRandom);
    var tabellone = new Tabellone(obj.getString("frase"), obj.getString("argomento"));

    ruota.gira();
}
