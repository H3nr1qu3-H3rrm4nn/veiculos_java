package prog2_projeto1.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prog2_projeto1.DBConnection;
import prog2_projeto1.model.VendaVeiculo;

public class VendaVeiculoDAO extends EntidadeBaseDAO<VendaVeiculo> {

    @Override
    protected String getNomeTabela() {
        return "venda_veiculo";
    }

    @Override
    protected String getQueryInsert() {
        return "INSERT INTO venda_veiculo (data_venda, valor_desconto, valor_final, vendedor_id, cliente_id, veiculo_id, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getQueryUpdate() {
        return "UPDATE venda_veiculo SET data_venda = ?, valor_desconto = ?, valor_final = ?, vendedor_id = ?, cliente_id = ?, veiculo_id = ? WHERE id = ?";
    }

    @Override
    protected void setParametrosInsert(PreparedStatement preparedStatement, VendaVeiculo venda) throws SQLException {
        if (venda.getDataVenda() != null) {
            preparedStatement.setDate(1, Date.valueOf(venda.getDataVenda()));
        } else {
            preparedStatement.setDate(1, null);
        }
        
        preparedStatement.setDouble(2, venda.getValorDesconto());
        preparedStatement.setDouble(3, venda.getValorFinal());
        preparedStatement.setInt(4, venda.getVendedor().getId());
        preparedStatement.setInt(5, venda.getCliente().getId());
        preparedStatement.setInt(6, venda.getVeiculo().getId());
        preparedStatement.setDate(7, new java.sql.Date(venda.getDataCadastro().getTime()));
    }

    @Override
    protected void setParametrosUpdate(PreparedStatement preparedStatement, VendaVeiculo venda) throws SQLException {
        if (venda.getDataVenda() != null) {
            preparedStatement.setDate(1, Date.valueOf(venda.getDataVenda()));
        } else {
            preparedStatement.setDate(1, null);
        }
        
        preparedStatement.setDouble(2, venda.getValorDesconto());
        preparedStatement.setDouble(3, venda.getValorFinal());
        preparedStatement.setInt(4, venda.getVendedor().getId());
        preparedStatement.setInt(5, venda.getCliente().getId());
        preparedStatement.setInt(6, venda.getVeiculo().getId());
        preparedStatement.setInt(7, venda.getId());
    }

    @Override
    protected VendaVeiculo mapearResultSet(ResultSet resultSet) throws SQLException {
        VendaVeiculo venda = new VendaVeiculo();
        venda.setId(resultSet.getInt("id"));
        
        Date dataVenda = resultSet.getDate("data_venda");
        if (dataVenda != null) {
            venda.setDataVenda(dataVenda.toLocalDate());
        }
        
        venda.setValorDesconto(resultSet.getDouble("valor_desconto"));
        venda.setValorFinal(resultSet.getDouble("valor_final"));
        venda.setDataCadastro(resultSet.getDate("data_cadastro"));
        
        // Buscar as entidades relacionadas
        VendedorDAO vendedorDAO = new VendedorDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        
        try {
            venda.setVendedor(vendedorDAO.buscar(resultSet.getInt("vendedor_id")));
            venda.setCliente(clienteDAO.buscar(resultSet.getInt("cliente_id")));
            venda.setVeiculo(veiculoDAO.buscar(resultSet.getInt("veiculo_id")));
        } catch (Exception e) {
            logger.error("Erro ao buscar entidades relacionadas: " + e.getMessage());
        }
        
        return venda;
    }

    @Override
    public List<VendaVeiculo> buscarTodosEspecifico() {
        try {
            logger.info("--- Início do método DAO Buscar Todos ---");

            String consulta = "SELECT * FROM " + getNomeTabela();
            List<VendaVeiculo> lista = new ArrayList<>();

            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Consulta executada: " + preparedStatement);

            while (resultSet.next()) {
                VendaVeiculo venda = mapearResultSet(resultSet);
                lista.add(venda);
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
