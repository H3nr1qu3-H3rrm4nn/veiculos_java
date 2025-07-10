package prog2_projeto1.controller;

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

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(Veiculo veiculo) {
        try {
            if (veiculoDAO.excluir(veiculo)) {
                logger.info("Veículo excluído no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir veículo no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Veiculo> buscarTodos() {
        try {
            List<Veiculo> veiculos = veiculoDAO.buscarTodosEspecifico();
            if (veiculos != null) {
                logger.info("Veículos encontrados no controller!");
                return veiculos;
            } else {
                logger.info("Erro ao buscar veículos no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Veiculo buscar(int id) {
        try {
            Veiculo veiculo = veiculoDAO.buscar(id);
            if (veiculo != null) {
                logger.info("Veículo encontrado no controller!");
                return veiculo;
            } else {
                logger.info("Erro ao encontrar veículo no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
