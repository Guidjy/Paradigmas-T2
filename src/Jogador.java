public class Jogador {
    private int id;
    private String nome;
    private String posicao;
    private int idade;
    private int numeroCamisa;
    private int timeId; // chave estrangeira para o time

    public Jogador(String nome, String posicao, int idade, int numeroCamisa, int timeId) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.numeroCamisa = numeroCamisa;
        this.timeId = timeId;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public String getPosicao() { return posicao; }
    public int getIdade() { return idade; }
    public int getNumeroCamisa() { return numeroCamisa; }
    public int getTimeId() { return timeId; }
}
