import java.sql.SQLException;

public class Treinador {
    private int id;
    private String nome;
    private int timeId; // chave estrangeira para o time

    // Construtor
    public Treinador(String nome, int timeId) {
        this.nome = nome;
        this.timeId = timeId;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getTimeId() { return timeId; }
    public void setTimeId(int timeId) { this.timeId = timeId; }

    public void printDados() throws SQLException {
        System.out.println("- Nome: " + this.nome);
        System.out.println("- Time: " + new TimesDAO().buscarPorId(this.timeId).getNome());
    }
}
