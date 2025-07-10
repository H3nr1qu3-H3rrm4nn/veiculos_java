package prog2_projeto1.controller;

import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.dao.PessoaFisicaDAO;
import prog2_projeto1.model.PessoaFisica;

public class PessoaFisicaController {

    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    Logger logger = Logger.getLogger(PessoaFisicaController.class);

    public boolean salvar(PessoaFisica pessoa) {
        try {
            if (pessoaFisicaDAO.salvar(pessoa)) {
                logger.info("Pessoa física salva no controller!");
                return true;
            } else {
                logger.info("Erro ao salvar pessoa física no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean alterar(PessoaFisica pessoa) {
        try {
            if (pessoaFisicaDAO.alterar(pessoa)) {
                logger.info("Pessoa física alterada no controller!");
                return true;
            } else {
                logger.info("Erro ao alterar pessoa física no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(PessoaFisica pessoa) {
        try {
            if (pessoaFisicaDAO.excluir(pessoa)) {
                logger.info("Pessoa física excluída no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir pessoa física no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<PessoaFisica> buscarTodos() {
        try {
            List<PessoaFisica> pessoas = pessoaFisicaDAO.buscarTodosEspecifico();
            if (pessoas != null) {
                logger.info("Pessoas físicas encontradas no controller!");
                return pessoas;
            } else {
                logger.info("Erro ao buscar pessoas físicas no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public PessoaFisica buscar(int id) {
        try {
            PessoaFisica pessoa = pessoaFisicaDAO.buscar(id);
            if (pessoa != null) {
                logger.info("Pessoa física encontrada no controller!");
                return pessoa;
            } else {
                logger.info("Erro ao encontrar pessoa física no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
