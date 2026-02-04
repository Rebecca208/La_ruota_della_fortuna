public class Giocatore {
    private int salvadanaio;
    private int manche;
    private String nome;

    public Giocatore(String nome) {
        this.salvadanaio = 0;
        this.nome = nome;
        this.manche = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setSalvadanaio(int salvadanaio) {
        this.salvadanaio = salvadanaio;
    }

    public void setManche(int manche) {
        this.manche = manche;
    }
}
