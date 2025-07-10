package prog2_projeto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.DBConnection;
import prog2_projeto1.interfaces.IMetodos;
import prog2_projeto1.model.EntidadeBase;

public abstract class EntidadeBaseDAO<T extends EntidadeBase> implements IMetodos {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para definir o nome da tabela no banco de dados
     */
    protected abstract String getNomeTabela();
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para mapear um ResultSet para o objeto específico
     */
    protected abstract T mapearResultSet(ResultSet resultSet) throws SQLException;
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para definir os parâmetros específicos do INSERT
     */
    protected abstract void setParametrosInsert(PreparedStatement preparedStatement, T entidade) throws SQLException;
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para definir os parâmetros específicos do UPDATE
     */
    protected abstract void setParametrosUpdate(PreparedStatement preparedStatement, T entidade) throws SQLException;
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para retornar a query de INSERT específica
     */
    protected abstract String getQueryInsert();
    
    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para retornar a query de UPDATE específica
     */
    protected abstract String getQueryUpdate();
    
    @Override
    public boolean salvar() {
        logger.info("--- Início do método DAO Salvar Base ---");
        logger.error("Método salvar() sem parâmetros não deve ser usado diretamente");
        return false;
    }
    
    public boolean salvar(T entidade) {
        logger.info("--- Início do método DAO Salvar ---");
        
        if (entidade == null) {
            logger.error("Entidade não pode ser nula");
            return false;
        }
        
        // Validação da data de cadastro
        if (entidade.getDataCadastro() != null && !entidade.valida_data()) {
            logger.error("Data de cadastro inválida");
            return false;
        }
        
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            
            // Se não tem data de cadastro, define a data atual
            if (entidade.getDataCadastro() == null) {
                entidade.setDataCadastro(new Date());
            }
            
            String query = getQueryInsert();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            setParametrosInsert(preparedStatement, entidade);
            
            logger.info("String insert preparada: " + preparedStatement);
            int resultado = preparedStatement.executeUpdate();
            
            if (resultado > 0) {
                logger.info("Inseriu registro: " + resultado);
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
    
    @Override
    public boolean alterar() {
        logger.info("--- Início do método DAO Alterar Base ---");
        logger.error("Método alterar() sem parâmetros não deve ser usado diretamente");
        return false;
    }
    
    public boolean alterar(T entidade) {
        logger.info("--- Início do método DAO Alterar ---");
        
        if (entidade == null) {
            logger.error("Entidade não pode ser nula");
            return false;
        }
        
        // Validação do ID
        if (!entidade.valida_id()) {
            logger.error("ID inválido para alteração");
            return false;
        }
        
        // Validação da data de cadastro
        if (entidade.getDataCadastro() != null && !entidade.valida_data()) {
            logger.error("Data de cadastro inválida");
            return false;
        }
        
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            
            String query = getQueryUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            setParametrosUpdate(preparedStatement, entidade);
            
            logger.info("String update preparada: " + preparedStatement);
            int resultado = preparedStatement.executeUpdate();
            
            if (resultado > 0) {
                logger.info("Retorno maior que zero: " + resultado);
                logger.info("--- Fim do método DAO Alterar ---");
                return true;
            } else {
                logger.info("Retorno menor que zero");
                logger.info("--- Fim do método DAO Alterar ---");
                return false;
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar alterar: " + e.getMessage());
            logger.error("--- Fim do método DAO Alterar ---");
            return false;
        }
    }
    
    @Override
    public boolean excluir() {
        logger.info("--- Início do método DAO Excluir Base ---");
        logger.error("Método excluir() sem parâmetros não deve ser usado diretamente");
        return false;
    }
    
    public boolean excluir(T entidade) {
        logger.info("--- Início do método DAO Excluir ---");
        
        if (entidade == null) {
            logger.error("Entidade não pode ser nula");
            return false;
        }
        
        // Validação do ID
        if (!entidade.valida_id()) {
            logger.error("ID inválido para exclusão");
            return false;
        }
        
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            
            String query = "DELETE FROM " + getNomeTabela() + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidade.getId());
            
            logger.info("String delete preparada: " + preparedStatement);
            int resultado = preparedStatement.executeUpdate();
            
            if (resultado > 0) {
                logger.info("Retorno maior que zero " + resultado);
                logger.info("--- Fim do método DAO Excluir ---");
                return true;
            } else {
                logger.info("Retorno menor que zero " + resultado);
                logger.info("--- Fim do método DAO Excluir ---");
                return false;
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar excluir: " + e.getMessage());
            logger.error("--- Fim do método DAO Excluir ---");
            return false;
        }
    }
    
    @Override
    public Object buscar() {
        logger.info("--- Início do método DAO Buscar Base ---");
        logger.error("Método buscar() sem parâmetros não deve ser usado diretamente");
        return null;
    }
    
    public T buscar(int id) {
        logger.info("--- Início do método DAO Buscar por Id ---");
        
        if (id <= 0) {
            logger.error("ID inválido para busca: " + id);
            return null;
        }
        
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            
            String query = "SELECT * FROM " + getNomeTabela() + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);
            
            if (resultSet.next()) {
                T entidade = mapearResultSet(resultSet);
                logger.info("--- Fim do método DAO Buscar por Id ---");
                return entidade;
            }
            
            logger.info("Nenhum registro encontrado com ID: " + id);
            logger.info("--- Fim do método DAO Buscar por Id ---");
            return null;
            
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao tentar buscar: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Object> buscarTodos() {
        logger.info("--- Início do método DAO Buscar Todos Base ---");
        logger.error("Método buscarTodos() genérico não deve ser usado diretamente");
        return null;
    }
    
    public abstract List<T> buscarTodosEspecifico();
}
