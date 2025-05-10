import java.sql.*;


public class TimesDAO {

    public void inserir(Time time) throws SQLException {
        // abre uma conexão JDBC com o servidor local
        Connection conexãoJDBC = Conexoes.getConexao();
        // cria uma classe de statement
        PreparedStatement SQLstatement = conexãoJDBC.prepareStatement("INSERT INTO times (nome, estadio, cidade, data_de_fundacao, n_jogadores) VALUES (?, ?, ?, ?, ?)");
        // preenche os valores do statement
        SQLstatement.setString(1, time.getNome());
        SQLstatement.setString(2, time.getEstadio());
        SQLstatement.setString(3, time.getCidade());
        SQLstatement.setDate(4, time.getDataDeFundacao());
        SQLstatement.setInt(5, time.getnJogadores());
        // executa o statement
        SQLstatement.executeUpdate();
    }
}
