import java.util.ArrayList;
import java.util.List;

public class ListaGiocatori {
    List<Giocatore> Giocatori;

    public ListaGiocatori() {
        Giocatori = new ArrayList<>();
    }

    public void addGiocatore(String nome) {
        Giocatori.add(new Giocatore(nome));
    }

    public String getNomi(){
        StringBuilder s = new StringBuilder();
        for(Giocatore g: Giocatori){
            s.append(g.getNome());
            s.append(" ");
        }
        return s.toString();
    }

    public Giocatore getGiocatore(int index) {
        return Giocatori.get(index);
    }
}
