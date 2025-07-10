package prog2_projeto1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.PessoaFisica;

public class PessoaFisicaDAO extends EntidadeBaseDAO<PessoaFisica> {

    @Override
    protected String getNomeTabela() {
        return "pessoa_fisica";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO pessoa_fisica (nome, cpf, rg, endereco, telefone, email, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE pessoa_fisica SET nome = ?, cpf = ?, rg = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, PessoaFisica pessoa) throws SQLException {
        preparedStatement.setString(1, pessoa.getNome());
        preparedStatement.setString(2, pessoa.getCpf());
        preparedStatement.setString(3, pessoa.getRg());
        preparedStatement.setString(4, pessoa.getEndereco());
        preparedStatement.setString(5, pessoa.getTelefone());
        preparedStatement.setString(6, pessoa.getEmail());
        preparedStatement.setDate(7, new java.sql.Date(pessoa.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, PessoaFisica pessoa) throws SQLException {
        preparedStatement.setString(1, pessoa.getNome());
        preparedStatement.setString(2, pessoa.getCpf());
        preparedStatement.setString(3, pessoa.getRg());
        preparedStatement.setString(4, pessoa.getEndereco());
        preparedStatement.setString(5, pessoa.getTelefone());
        preparedStatement.setString(6, pessoa.getEmail());
        preparedStatement.setInt(7, pessoa.getId());
    }

    @Override
    protected PessoaFisica mapearResultSet(ResultSet resultSet) throws SQLException {
        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setId(resultSet.getInt("id"));
        pessoa.setNome(resultSet.getString("nome"));
        pessoa.setCpf(resultSet.getString("cpf"));
        pessoa.setRg(resultSet.getString("rg"));
        pessoa.setEndereco(resultSet.getString("endereco"));
        pessoa.setTelefone(resultSet.getString("telefone"));
        pessoa.setEmail(resultSet.getString("email"));
        pessoa.setDataCadastro(resultSet.getDate("data_cadastro"));
        return pessoa;
    }

    @Override
    public List<PessoaFisica> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<PessoaFisica> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                PessoaFisica pessoa = mapearResultSet(resultSet);
                lista.add(pessoa);
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
