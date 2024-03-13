package aula05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pedido", "root", "")) {
            Cliente cliente = new Cliente(1, "João");
            Produto produto1 = new Produto(1, "Camisa", 29.99);
            Produto produto2 = new Produto(2, "Calça", 49.99);
            Pedido pedido = new Pedido(1, cliente, Arrays.asList(produto1, produto2));
            
            cadastrarCliente(connection, cliente);
            cadastrarProdutos(connection, pedido.getProdutos());
            cadastrarPedido(connection, pedido);
            
            System.out.println("Registros inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarCliente(Connection connection, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (id, nome) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cliente.getId());
            statement.setString(2, cliente.getNome());
            statement.executeUpdate();
        }
    }

    private static void cadastrarProdutos(Connection connection, List<Produto> produtos) throws SQLException {
        String sql = "INSERT INTO produto (id, nome, preco) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Produto produto : produtos) {
                statement.setInt(1, produto.getId());
                statement.setString(2, produto.getNome());
                statement.setDouble(3, produto.getPreco());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    private static void cadastrarPedido(Connection connection, Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (id_cliente) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pedido.getCliente().getId());
            statement.executeUpdate();
        }
    }
}
