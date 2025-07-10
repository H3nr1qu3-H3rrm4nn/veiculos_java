package prog2_projeto1.controller;

import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.dao.VendaVeiculoDAO;
import prog2_projeto1.model.VendaVeiculo;

public class VendaVeiculoController {

    VendaVeiculoDAO vendaVeiculoDAO = new VendaVeiculoDAO();
    Logger logger = Logger.getLogger(VendaVeiculoController.class);

    public boolean salvar(VendaVeiculo vendaVeiculo) {
        try {
            if (vendaVeiculoDAO.salvar(vendaVeiculo)) {
                logger.info("Venda de veículo salva no controller!");
                return true;
            } else {
                logger.info("Erro ao salvar venda de veículo no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean alterar(VendaVeiculo vendaVeiculo) {
        try {
            if (vendaVeiculoDAO.alterar(vendaVeiculo)) {
                logger.info("Venda de veículo alterada no controller!");
                return true;
            } else {
                logger.info("Erro ao alterar venda de veículo no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(VendaVeiculo vendaVeiculo) {
        try {
            if (vendaVeiculoDAO.excluir(vendaVeiculo)) {
                logger.info("Venda de veículo excluída no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir venda de veículo no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<VendaVeiculo> buscarTodos() {
        try {
            List<VendaVeiculo> vendas = vendaVeiculoDAO.buscarTodosEspecifico();
            if (vendas != null) {
                logger.info("Vendas de veículos encontradas no controller!");
                return vendas;
            } else {
                logger.info("Erro ao buscar vendas de veículos no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public VendaVeiculo buscar(int id) {
        try {
            VendaVeiculo vendaVeiculo = vendaVeiculoDAO.buscar(id);
            if (vendaVeiculo != null) {
                logger.info("Venda de veículo encontrada no controller!");
                return vendaVeiculo;
            } else {
                logger.info("Erro ao encontrar venda de veículo no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
