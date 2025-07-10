package prog2_projeto1.controller;

import java.util.List;

import org.apache.log4j.Logger;

import prog2_projeto1.dao.CategoriaDAO;
import prog2_projeto1.model.Categoria;

public class CategoriaController {

    CategoriaDAO categoriaDAO = new CategoriaDAO();
    Logger logger = Logger.getLogger(CategoriaController.class);

    public boolean salvar(Categoria categoria) {
        try {
            if (categoriaDAO.salvar(categoria)) {
                logger.info("Categoria salva no controller!");
                return true;
            } else {
                logger.info("Erro ao salvar categoria no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean alterar(Categoria categoria) {
        try {
            if (categoriaDAO.alterar(categoria)) {
                logger.info("Categoria alterada no controller!");
                return true;
            } else {
                logger.info("Erro ao alterar categoria no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean excluir(Categoria categoria) {
        try {
            if (categoriaDAO.excluir(categoria)) {
                logger.info("Categoria excluída no controller!");
                return true;
            } else {
                logger.info("Erro ao excluir categoria no controller!");
                return false;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Categoria> buscarTodos() {
        try {
            List<Categoria> categorias = categoriaDAO.buscarTodosEspecifico();
            if (categorias != null) {
                logger.info("Categorias encontradas no controller!");
                return categorias;
            } else {
                logger.info("Erro ao buscar categorias no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Categoria buscar(int id) {
        try {
            Categoria categoria = categoriaDAO.buscar(id);
            if (categoria != null) {
                logger.info("Categoria encontrada no controller!");
                return categoria;
            } else {
                logger.info("Erro ao encontrar categoria no controller!");
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
