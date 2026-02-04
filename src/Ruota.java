import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ruota {
    private List<String> ruota;

    public Ruota() {
        ruota = new ArrayList<>();
        ruota.add("100");
        ruota.add("200");
        ruota.add("100");
        ruota.add("200");
        ruota.add("300");
        ruota.add("1000");
        ruota.add("300");
        ruota.add("5000");
        ruota.add("600");
        ruota.add("Passa");
        ruota.add("600");
        ruota.add("Passa");
        ruota.add("Bancarotta");
        Collections.shuffle(ruota);
    }

    public String gira(){
        int nRandom = (int) (Math.random() * (ruota.size() + 1));
        return ruota.get(nRandom);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ruota ruota1 = (Ruota) o;
        return Objects.equals(ruota, ruota1.ruota);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ruota);
    }
}
