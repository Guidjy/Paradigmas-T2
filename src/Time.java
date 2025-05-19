import java.sql.Date;
import java.util.ArrayList;

public class Time {
    private int id;
    private String nome;
    private String estadio;
    private String cidade;
    private Date dataDeFundacao;
    private int nJogadores;
    private ArrayList<Jogador> jogadores;

    public void addJogador(Jogador jogador) {
        if (jogadores == null) {
            jogadores = new ArrayList<>();
        }
        jogadores.add(jogador);
    }



    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEstadio() { return estadio; }
    public void setEstadio(String estadio) { this.estadio = estadio; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public Date getDataDeFundacao() { return dataDeFundacao; }
    public void setDataDeFundacao(Date dataDeFundacao) { this.dataDeFundacao = dataDeFundacao; }

    public int getnJogadores() { return jogadores.size(); }
    public void setnJogadores(int nJogadores) { this.nJogadores = nJogadores; }

    public Time(String nome, String estadio, String cidade, Date dataDeFundacao) {
        this.setNome(nome);
        this.setEstadio(estadio);
        this.setCidade(cidade);
        this.setDataDeFundacao(dataDeFundacao);
        this.setnJogadores(0);
        this.jogadores = new ArrayList<>();
    }

    public void printDados() {
        System.out.println("- Nome: " + this.nome);
        System.out.println("- Estádio: " + this.estadio);
        System.out.println("- Cidade: " + this.cidade);
        System.out.println("- Data de fundação: " + this.dataDeFundacao);
        System.out.println("- número de jogadores: " + this.nJogadores);
    }
}
