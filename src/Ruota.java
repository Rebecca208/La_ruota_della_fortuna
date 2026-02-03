import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
