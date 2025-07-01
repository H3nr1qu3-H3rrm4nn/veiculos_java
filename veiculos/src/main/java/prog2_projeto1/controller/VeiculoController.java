package prog2_projeto1.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.dao.VeiculoDAO;
import prog2_projeto1.model.Veiculo;

public class VeiculoController {

    VeiculoDAO veiculoDAO = new VeiculoDAO();
    Logger logger = Logger.getLogger(VeiculoController.class);

    public boolean salvar(Veiculo veiculo) {
        try {
            if (veiculoDAO.salvar(veiculo)) {
                logger.info("Veículo salvo no controller!");
                return true;
            } else {
                logger.info("Erro ao salvar veículo no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean alterar(Veiculo veiculo) {
        try {
            if (veiculoDAO.alterar(veiculo)) {
                logger.info("Veículo alterado no controller!");
                return true;
            } else {
                logger.info("Erro ao alterar veículo no controller!");
                return false;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(Veiculo veiculo) {
        try {
            if (veiculoDAO.excluir(veiculo)) {
                logger.info("Veículo excluido no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir veículo no controller!");
                return false;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Veiculo> buscarTodos() {
        try {
            List<Veiculo> veiculos = veiculoDAO.buscarTodos();
            if (veiculos != null) {
                logger.info("Veículo salvo no controller!");
                return veiculos;
            } else {
                logger.info("Erro ao salvar veículo no controller!");
                return null;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Veiculo buscar(int id) {
        try {
            if (veiculoDAO.buscar(id) != null) {
                logger.info("Veículo encontrado no controller!");
                return veiculoDAO.buscar(id);
            } else {
                logger.info("Erro ao encontrar veículo no controller!");
                return null;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
