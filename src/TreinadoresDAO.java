import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TreinadoresDAO {

    public void inserir(Treinador treinador) throws SQLException {
        Connection conexao = Conexoes.getConexao();

        PreparedStatement statement = conexao.prepareStatement(
                "INSERT INTO treinadores (nome, time) VALUES (?, ?)"
        );

        statement.setString(1, treinador.getNome());
        statement.setInt(2, treinador.getTimeId());

        statement.executeUpdate();
    }
}
