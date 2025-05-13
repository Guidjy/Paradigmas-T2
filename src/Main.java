import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Menu.exibirMenu();

        JogadoresDAO dao = new JogadoresDAO();
        List<Jogador> query = dao.buscarPorNomeDoTime("Inter");
        System.out.println(query.getFirst().getNome());
    }
}
