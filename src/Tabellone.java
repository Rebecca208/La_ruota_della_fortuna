import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tabellone {
    private String frase;
    private String arg;
    private static String FraseDaIndovinare;
    private List<List<Boolean>> FraseSegreta;

    public Tabellone(String frase, String arg) {
        this.frase = frase;
        this.FraseDaIndovinare = frase;
        this.arg = arg;
        List<String> parti = List.of(frase.split(" "));
        FraseSegreta = new ArrayList<>();
        for (int i = 0; i < parti.size(); i++) {
            var lunghezza = parti.get(i).length();
            FraseSegreta.add(new ArrayList<>());
            for (int j = 0; j < lunghezza; j++) {
                FraseSegreta.get(i).add(false);
            }
        }
    }

    public boolean IgnoreCaseEquals(String fraseIndovinata) {
        return this.frase.equalsIgnoreCase(fraseIndovinata);
    }

    public static String getFraseDaIndovinare() {
        return FraseDaIndovinare;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tabellone tabellone = (Tabellone) o;
        return Objects.equals(FraseDaIndovinare, tabellone.FraseDaIndovinare);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(FraseDaIndovinare);
    }

    public String getFraseSegreta() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FraseSegreta.size(); i++){
            for (int j = 0; j < FraseSegreta.get(i).size(); j++) {
                if (FraseSegreta.get(i).get(j)) {
                    sb.append(charAtIndex(j, i));
                } else {
                    sb.append("_");
                }
            }
            if (i < FraseSegreta.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private char charAtIndex(int j, int i) {
        int posizione = 0;
        for (int k = 0; k < i; k++) {
            posizione += FraseSegreta.get(k).size() + 1; // +1 per lo spazio
        }
        posizione += j;
        return frase.charAt(posizione);
    }

    public void callLetter(char lettera){
        int posizioneParola = 0;
        int posizioneNellaParola = 0;

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                posizioneParola++;
                posizioneNellaParola = 0;
            } else {
                if (Character.toUpperCase(frase.charAt(i)) == Character.toUpperCase(lettera)) {
                    FraseSegreta.get(posizioneParola).set(posizioneNellaParola, true);
                }
                switch (lettera){
                    case 'a' -> {
                        callLetter('à');
                    }
                    case 'e' -> {
                        callLetter('é');
                        callLetter('è');
                    }
                    case 'i' -> {
                        callLetter('ì');
                    }
                    case 'o' -> {
                        callLetter('ò');
                        callLetter('ó');
                    }
                    case 'u' -> {
                        callLetter('ù');
                    }
                    default -> {
                    }
                }
                posizioneNellaParola++;
            }
        }
    }

    public int trueLetters(){
        int count = 0;
        for (var lista : FraseSegreta) {
            for (var b : lista) {
                if (b) {
                    count++;
                }
            }
        }
        return count;
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

    public boolean fraseIndovinata(){
        for (int i = 0; i < FraseSegreta.size(); i++){
            for (int j = 0; j < FraseSegreta.get(i).size(); j++){
                if (!FraseSegreta.get(i).get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int contaLettereTrovate(char lettera) {
        int count = 0;
        for (int i = 0; i < FraseSegreta.size(); i++) {
            for (int j = 0; j < FraseSegreta.get(i).size(); j++) {
                if (FraseSegreta.get(i).get(j) && Character.toUpperCase(charAtIndex(j, i)) == Character.toUpperCase(lettera)) {
                    count++;
                }
            }
        }
        return count;
    }
}
