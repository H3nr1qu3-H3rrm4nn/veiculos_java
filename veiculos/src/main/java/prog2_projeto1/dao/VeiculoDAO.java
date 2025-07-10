package prog2_projeto1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Veiculo;

public class VeiculoDAO extends EntidadeBaseDAO<Veiculo> {

    @Override
    protected String getNomeTabela() {
        return "veiculo";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO veiculo (nome, ano, modelo, cor, placa, unico_dono, categoria_id, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE veiculo SET nome = ?, ano = ?, modelo = ?, cor = ?, placa = ?, unico_dono = ?, categoria_id = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, Veiculo veiculo) throws SQLException {
        preparedStatement.setString(1, veiculo.getNome());
        preparedStatement.setInt(2, veiculo.getAno());
        preparedStatement.setString(3, veiculo.getModelo());
        preparedStatement.setString(4, veiculo.getCor());
        preparedStatement.setString(5, veiculo.getPlaca());
        preparedStatement.setBoolean(6, veiculo.isUnicoDono());
        preparedStatement.setInt(7, veiculo.getCategoria().getId());
        preparedStatement.setDate(8, new java.sql.Date(veiculo.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, Veiculo veiculo) throws SQLException {
        preparedStatement.setString(1, veiculo.getNome());
        preparedStatement.setInt(2, veiculo.getAno());
        preparedStatement.setString(3, veiculo.getModelo());
        preparedStatement.setString(4, veiculo.getCor());
        preparedStatement.setString(5, veiculo.getPlaca());
        preparedStatement.setBoolean(6, veiculo.isUnicoDono());
        preparedStatement.setInt(7, veiculo.getCategoria().getId());
        preparedStatement.setInt(8, veiculo.getId());
    }

    @Override
    protected Veiculo mapearResultSet(ResultSet resultSet) throws SQLException {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(resultSet.getInt("id"));
        veiculo.setNome(resultSet.getString("nome"));
        veiculo.setAno(resultSet.getInt("ano"));
        veiculo.setModelo(resultSet.getString("modelo"));
        veiculo.setCor(resultSet.getString("cor"));
        veiculo.setPlaca(resultSet.getString("placa"));
        veiculo.setUnicoDono(resultSet.getBoolean("unico_dono"));
        veiculo.setDataCadastro(resultSet.getDate("data_cadastro"));

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        int categoriaId = resultSet.getInt("categoria_id");
        try {
            veiculo.setCategoria(categoriaDAO.buscar(categoriaId));
        } catch (Exception e) {
            logger.error("Erro ao buscar categoria: " + e.getMessage());
        }
        
        return veiculo;
    }

    @Override
    public List<Veiculo> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<Veiculo> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                Veiculo veiculo = mapearResultSet(resultSet);
                lista.add(veiculo);
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
