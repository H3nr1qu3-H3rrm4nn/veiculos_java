package prog2_projeto1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Cliente;

public class ClienteDAO extends EntidadeBaseDAO<Cliente> {

    @Override
    protected String getNomeTabela() {
        return "cliente";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO cliente (nome, cpf, rg, endereco, telefone, email, referencia_comercial, data_nascimento, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE cliente SET nome = ?, cpf = ?, rg = ?, endereco = ?, telefone = ?, email = ?, referencia_comercial = ?, data_nascimento = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, Cliente cliente) throws SQLException {
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCpf());
        preparedStatement.setString(3, cliente.getRg());
        preparedStatement.setString(4, cliente.getEndereco());
        preparedStatement.setString(5, cliente.getTelefone());
        preparedStatement.setString(6, cliente.getEmail());
        preparedStatement.setString(7, cliente.getReferencia_comercial());
        
        if (cliente.getData_nascimento() != null) {
            preparedStatement.setDate(8, new java.sql.Date(cliente.getData_nascimento().getTime()));
        } else {
            preparedStatement.setDate(8, null);
        }
        
        preparedStatement.setDate(9, new java.sql.Date(cliente.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, Cliente cliente) throws SQLException {
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCpf());
        preparedStatement.setString(3, cliente.getRg());
        preparedStatement.setString(4, cliente.getEndereco());
        preparedStatement.setString(5, cliente.getTelefone());
        preparedStatement.setString(6, cliente.getEmail());
        preparedStatement.setString(7, cliente.getReferencia_comercial());
        
        if (cliente.getData_nascimento() != null) {
            preparedStatement.setDate(8, new java.sql.Date(cliente.getData_nascimento().getTime()));
        } else {
            preparedStatement.setDate(8, null);
        }
        
        preparedStatement.setInt(9, cliente.getId());
    }

    @Override
    protected Cliente mapearResultSet(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setCpf(resultSet.getString("cpf"));
        cliente.setRg(resultSet.getString("rg"));
        cliente.setEndereco(resultSet.getString("endereco"));
        cliente.setTelefone(resultSet.getString("telefone"));
        cliente.setEmail(resultSet.getString("email"));
        cliente.setReferencia_comercial(resultSet.getString("referencia_comercial"));
        cliente.setData_nascimento(resultSet.getDate("data_nascimento"));
        cliente.setDataCadastro(resultSet.getDate("data_cadastro"));
        return cliente;
    }

    @Override
    public List<Cliente> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<Cliente> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                Cliente cliente = mapearResultSet(resultSet);
                lista.add(cliente);
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
