package prog2_projeto1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Vendedor;

public class VendedorDAO extends EntidadeBaseDAO<Vendedor> {

    @Override
    protected String getNomeTabela() {
        return "vendedor";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO vendedor (nome, cpf, rg, endereco, telefone, email, salario, comissao, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE vendedor SET nome = ?, cpf = ?, rg = ?, endereco = ?, telefone = ?, email = ?, salario = ?, comissao = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, Vendedor vendedor) throws SQLException {
        preparedStatement.setString(1, vendedor.getNome());
        preparedStatement.setString(2, vendedor.getCpf());
        preparedStatement.setString(3, vendedor.getRg());
        preparedStatement.setString(4, vendedor.getEndereco());
        preparedStatement.setString(5, vendedor.getTelefone());
        preparedStatement.setString(6, vendedor.getEmail());
        preparedStatement.setDouble(7, vendedor.getSalario());
        preparedStatement.setDouble(8, vendedor.getComissao());
        preparedStatement.setDate(9, new java.sql.Date(vendedor.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, Vendedor vendedor) throws SQLException {
        preparedStatement.setString(1, vendedor.getNome());
        preparedStatement.setString(2, vendedor.getCpf());
        preparedStatement.setString(3, vendedor.getRg());
        preparedStatement.setString(4, vendedor.getEndereco());
        preparedStatement.setString(5, vendedor.getTelefone());
        preparedStatement.setString(6, vendedor.getEmail());
        preparedStatement.setDouble(7, vendedor.getSalario());
        preparedStatement.setDouble(8, vendedor.getComissao());
        preparedStatement.setInt(9, vendedor.getId());
    }

    @Override
    protected Vendedor mapearResultSet(ResultSet resultSet) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(resultSet.getInt("id"));
        vendedor.setNome(resultSet.getString("nome"));
        vendedor.setCpf(resultSet.getString("cpf"));
        vendedor.setRg(resultSet.getString("rg"));
        vendedor.setEndereco(resultSet.getString("endereco"));
        vendedor.setTelefone(resultSet.getString("telefone"));
        vendedor.setEmail(resultSet.getString("email"));
        vendedor.setSalario(resultSet.getDouble("salario"));
        vendedor.setComissao(resultSet.getDouble("comissao"));
        vendedor.setDataCadastro(resultSet.getDate("data_cadastro"));
        return vendedor;
    }

    @Override
    public List<Vendedor> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<Vendedor> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                Vendedor vendedor = mapearResultSet(resultSet);
                lista.add(vendedor);
            }

            logger.info("Quantidade de registros pesquisados: " + lista.size());
            logger.info("--- Fim do método DAO Buscar Todos ---");

            return lista;
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao tentar buscar todos: " + e.getMessage());
            return null;
        }
    }
}
