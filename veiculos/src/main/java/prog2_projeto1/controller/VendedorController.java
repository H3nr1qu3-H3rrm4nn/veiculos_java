package prog2_projeto1.controller;

import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.dao.VendedorDAO;
import prog2_projeto1.model.Vendedor;

public class VendedorController {

    VendedorDAO vendedorDAO = new VendedorDAO();
    Logger logger = Logger.getLogger(VendedorController.class);

    public boolean salvar(Vendedor vendedor) {
        try {
            if (vendedorDAO.salvar(vendedor)) {
                logger.info("Vendedor salvo no controller!");
                return true;
            } else {
                logger.info("Erro ao salvar vendedor no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean alterar(Vendedor vendedor) {
        try {
            if (vendedorDAO.alterar(vendedor)) {
                logger.info("Vendedor alterado no controller!");
                return true;
            } else {
                logger.info("Erro ao alterar vendedor no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(Vendedor vendedor) {
        try {
            if (vendedorDAO.excluir(vendedor)) {
                logger.info("Vendedor excluído no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir vendedor no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Vendedor> buscarTodos() {
        try {
            List<Vendedor> vendedores = vendedorDAO.buscarTodosEspecifico();
            if (vendedores != null) {
                logger.info("Vendedores encontrados no controller!");
                return vendedores;
            } else {
                logger.info("Erro ao buscar vendedores no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Vendedor buscar(int id) {
        try {
            Vendedor vendedor = vendedorDAO.buscar(id);
            if (vendedor != null) {
                logger.info("Vendedor encontrado no controller!");
                return vendedor;
            } else {
                logger.info("Erro ao encontrar vendedor no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
