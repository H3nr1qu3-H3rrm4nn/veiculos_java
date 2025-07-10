package prog2_projeto1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Categoria;

public class CategoriaDAO extends EntidadeBaseDAO<Categoria> {

    @Override
    protected String getNomeTabela() {
        return "categoria";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO categoria (nome, data_cadastro) VALUES (?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE categoria SET nome = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, Categoria categoria) throws SQLException {
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setDate(2, new java.sql.Date(categoria.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, Categoria categoria) throws SQLException {
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setInt(2, categoria.getId());
    }

    @Override
    protected Categoria mapearResultSet(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getInt("id"));
        categoria.setNome(resultSet.getString("nome"));
        categoria.setDataCadastro(resultSet.getDate("data_cadastro"));
        return categoria;
    }

    @Override
    public List<Categoria> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<Categoria> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                Categoria categoria = mapearResultSet(resultSet);
                lista.add(categoria);
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
