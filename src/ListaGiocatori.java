import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void azzeramentoManche() {
        for (int i = 0; i < Giocatori.size(); i++){
            Giocatori.get(i).setManche(0);
        }
    }

    public int getSize(){
        return Giocatori.size();
    }

    public void inserimentoGiocatori(){
        System.out.println("Max 3 giocatori");
        for (int i = 0; i < 3; i++){
            System.out.println("Inserisci nome giocatore " + i + ": ");
            Scanner sc = new Scanner(System.in);
            String nome = sc.nextLine();
            addGiocatore(nome);
        }
        if (Giocatori.size() >= 3){
            System.out.println("Raggiunto limite giocatori!");
        }
    }
    public Giocatore getGiocatore(int index) {
        return Giocatori.get(index);
    }
}
