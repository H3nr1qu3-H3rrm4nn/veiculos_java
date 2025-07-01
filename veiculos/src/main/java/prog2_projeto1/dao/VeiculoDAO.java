package prog2_projeto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Veiculo;

public class VeiculoDAO {
    Logger logger = Logger.getLogger(VeiculoDAO.class);

    public boolean salvar(Veiculo modelo) {
        logger.info("--- Início do método DAO Salvar ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insertVeiculo = "INSERT INTO veiculo " +
                    "(nome, ano, modelo, cor, placa, unico_dono, categoria_id) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(insertVeiculo);
            preparedStatement1.setString(1, modelo.getNome());
            preparedStatement1.setInt(2, modelo.getAno());
            preparedStatement1.setString(3, modelo.getModelo());
            preparedStatement1.setString(4, modelo.getCor());
            preparedStatement1.setString(5, modelo.getPlaca());
            preparedStatement1.setBoolean(6, modelo.isUnicoDono() ? true : false);
            preparedStatement1.setInt(7, modelo.getCategoria().getId());

            logger.info("String insert Veículo preparada: " + preparedStatement1);
            int resultado = preparedStatement1.executeUpdate();

            if (resultado > 0) {
                logger.info("Inseriu Veículo: " + resultado);
                logger.info("--- Fim do método DAO Salvar ---");
                return true;
            } else {
                logger.info("Retorno menor que zero " + resultado);
                logger.info("--- Fim do método DAO Salvar ---");
                return false;

            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar salvar: " + e.getMessage());
            logger.error("--- Fim do método DAO Salvar ---");
            return false;
        }
    }

    public boolean alterar(Veiculo modelo) throws SQLException {
        logger.info("--- Início do método DAO Alterar ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String updatePessoa = "update veiculo set " +
                    "nome = ?, " +
                    "ano = ?,  " +
                    "modelo = ?,  " +
                    "cor = ?,  " +
                    "placa = ?,  " +
                    "unico_dono = ?, " +
                    "categoria_id = ?  " +
                    "where id = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(updatePessoa);
            preparedStatement1.setString(1, modelo.getNome());
            preparedStatement1.setInt(2, modelo.getAno());
            preparedStatement1.setString(3, modelo.getModelo());
            preparedStatement1.setString(4, modelo.getCor());
            preparedStatement1.setString(5, modelo.getPlaca());
            preparedStatement1.setBoolean(6, modelo.isUnicoDono() ? true : false);
            preparedStatement1.setInt(7, modelo.getCategoria().getId());
            preparedStatement1.setInt(8, modelo.getId());

            logger.info("String update veículo preparada: " + preparedStatement1);
            int resultadoVeiculo = preparedStatement1.executeUpdate();

            if (resultadoVeiculo > 0) {
                logger.info("Retorno maior que zero: " + resultadoVeiculo);
                logger.info("--- Fim do método DAO Alterar ---");
                return true;
            } else {
                logger.info("Retorno menor que zero ");
                logger.info("--- Fim do método DAO Alterar ---");
                return false;

            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar alterar: " + e.getMessage());
            logger.error("--- Fim do método DAO Alterar ---");
            return false;
        }
    }

    public boolean excluir(Veiculo modelo) throws SQLException {
        logger.info("--- Início do método DAO Excluir ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insertPessoa = "delete from veiculo where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertPessoa);
            preparedStatement1.setInt(1, modelo.getId());
            logger.info("String delete veículo preparada: " + preparedStatement1);
            int resultadoVeiculo = preparedStatement1.executeUpdate();

            if (resultadoVeiculo > 0) {
                logger.info("Retorno maior que zero " + resultadoVeiculo);
                logger.info("--- Fim do método DAO Excluir ---");
                return true;
            } else {
                logger.info("Retorno menor que zero " + resultadoVeiculo);
                logger.info("--- Fim do método DAO Excluir ---");
                return false;

            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar excluir: " + e.getMessage());
            logger.error("--- Fim do método DAO Excluir ---");
            return false;
        }
    }

    public boolean buscar(Veiculo modelo) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public List<Veiculo> buscarTodos() throws SQLException {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            Connection connection = DBConnection.getInstance().getConnection();

            String consulta = "select * from veiculo";
            List<Veiculo> lista = new ArrayList<Veiculo>();
            Veiculo veiculo;

            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                veiculo = new Veiculo();
                veiculo.setId(resultSet.getInt("id"));
                veiculo.setNome(resultSet.getString("nome"));
                veiculo.setAno(resultSet.getInt("ano"));
                veiculo.setModelo(resultSet.getString("modelo"));
                veiculo.setCor(resultSet.getString("cor"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setUnicoDono(resultSet.getString("unico_dono") == "Sim" ? true : false);

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                int categoriaId = resultSet.getInt("categoria_id");

                veiculo.setCategoria(categoriaDAO.buscar(categoriaId));
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

    public Veiculo buscar(int id) throws SQLException {
        try {
            logger.info("--- Início do método DAO Buscar por Id ---");

            Connection connection = DBConnection.getInstance().getConnection();

            String consulta = "select * from veiculo where id = ?";
            Veiculo veiculo = new Veiculo();

            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                veiculo = new Veiculo();
                veiculo.setId(resultSet.getInt("id"));
                veiculo.setNome(resultSet.getString("nome"));
                veiculo.setAno(resultSet.getInt("ano"));
                veiculo.setModelo(resultSet.getString("modelo"));
                veiculo.setCor(resultSet.getString("cor"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setUnicoDono(resultSet.getString("unico_dono") == "Sim" ? true : false);
                veiculo.setCategoria(new CategoriaDAO().buscar(resultSet.getInt("categoria_id")));
            }

            logger.info("--- Fim do método DAO Buscar por Id ---");

            return veiculo;
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao tentar buscar: " + e.getMessage());
            return null;
        }
    }
}
