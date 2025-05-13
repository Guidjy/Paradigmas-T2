
public class Partida {
    private int id;
    private int timeCasaId;
    private int timeForaId;
    private int golsCasa;
    private int golsFora;
    private int numero_rodada;

    // Construtor
    public Partida(int timeCasaId, int timeForaId, int golsCasa, int golsFora, int numero_rodada) {
        this.timeCasaId = timeCasaId;
        this.timeForaId = timeForaId;
        this.golsCasa = golsCasa;
        this.golsFora = golsFora;
        this.numero_rodada = numero_rodada;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTimeCasaId() { return timeCasaId; }
    public void setTimeCasaId(int timeCasaId) { this.timeCasaId = timeCasaId; }

    public int getTimeForaId() { return timeForaId; }
    public void setTimeForaId(int timeForaId) { this.timeForaId = timeForaId; }

    public int getGolsCasa() { return golsCasa; }
    public void setGolsCasa(int golsCasa) { this.golsCasa = golsCasa; }

    public int getGolsFora() { return golsFora; }
    public void setGolsFora(int golsFora) { this.golsFora = golsFora; }

    public int getNumero_rodada() { return numero_rodada; }
    public void setNumero_rodada(int numero_rodada) { this.numero_rodada = numero_rodada; }
}
