import java.util.ArrayList;
import java.util.List;

public class Tabellone {
    private String frase;
    private String arg;
    private List<List<Boolean>> FraseSegreta;

    public Tabellone(String frase, String arg) {
        this.frase = frase;
        this.arg = arg;
        List<String> parti = List.of(frase.split(" "));
        FraseSegreta = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < parti.size(); i++) {
            var lunghezza = parti.get(i).length();
            for (int j = 0; j < lunghezza; j++) {
//                FraseSegreta.add()
            }
        }
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
