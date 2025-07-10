package prog2_projeto1.interfaces;
import java.util.List;

public interface IMetodos{
    public boolean salvar();
    public boolean alterar();
    public boolean excluir();
    public Object buscar();
    public List<Object> buscarTodos();
}

