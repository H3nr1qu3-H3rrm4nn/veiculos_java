package prog2_projeto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.Categoria;

public class CategoriaDAO {
    Logger logger = Logger.getLogger(CategoriaDAO.class);

    public boolean salvar(Categoria modelo) {
        logger.info("--- Início do método DAO Salvar ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insertCategoria = "INSERT INTO categoria " +
                    "(nome) " +
                    "values (?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(insertCategoria);
            preparedStatement1.setString(1, modelo.getNome());
            logger.info("String insert Categoria preparada: " + preparedStatement1);
            int resultado = preparedStatement1.executeUpdate();

            if (resultado > 0) {
                logger.info("Inseriu Categoria: " + resultado);
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

    public boolean alterar(Categoria modelo) throws SQLException {
        logger.info("--- Início do método DAO Alterar ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String updatePessoa = "update categoria set nome = ? where id = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(updatePessoa);
            preparedStatement1.setString(1, modelo.getNome());
            preparedStatement1.setInt(2, modelo.getId());

            logger.info("String update categoria preparada: " + preparedStatement1);
            int resultadoCategoria = preparedStatement1.executeUpdate();

            if (resultadoCategoria > 0) {
                logger.info("Retorno maior que zero: " + resultadoCategoria);
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

    public boolean excluir(Categoria modelo) throws SQLException {
        logger.info("--- Início do método DAO Excluir ---");

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insertPessoa = "delete from categoria where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertPessoa);
            preparedStatement1.setInt(1, modelo.getId());
            logger.info("String delete categoria preparada: " + preparedStatement1);
            int resultadoCategoria = preparedStatement1.executeUpdate();

            if (resultadoCategoria > 0) {
                logger.info("Retorno maior que zero " + resultadoCategoria);
                logger.info("--- Fim do método DAO Excluir ---");
                return true;
            } else {
                logger.info("Retorno menor que zero " + resultadoCategoria);
                logger.info("--- Fim do método DAO Excluir ---");
                return false;

            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar excluir: " + e.getMessage());
            logger.error("--- Fim do método DAO Excluir ---");
            return false;
        }
    }

    public boolean buscar(Categoria modelo) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public List<Categoria> buscarTodos() throws SQLException {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            Connection connection = DBConnection.getInstance().getConnection();

            String consulta = "select * from categoria";
            List<Categoria> lista = new ArrayList<Categoria>();
            Categoria categoria;

            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));

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

    public Categoria buscar(int id) throws SQLException {
        try {
            logger.info("--- Início do método DAO Buscar por Id ---");

            Connection connection = DBConnection.getInstance().getConnection();

            String consulta = "select * from categoria where id = ?";
            Categoria categoria = new Categoria();

            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
            }

            logger.info("--- Fim do método DAO Buscar por Id ---");

            return categoria;
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao tentar buscar: " + e.getMessage());
            return null;
        }
    }
}
